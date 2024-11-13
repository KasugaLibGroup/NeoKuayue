package willow.train.kuayue.systems.tech_tree.server;

import kasuga.lib.core.network.Packet;
import kasuga.lib.core.network.S2CPacket;
import lombok.Getter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.s2c.*;
import willow.train.kuayue.systems.tech_tree.NetworkState;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.function.Consumer;

public class ServerNetworkCache implements Runnable {
    private UUID batch;

    @Getter
    private final Player player;
    private final Queue<TechTree> waitingForSend;
    private final Queue<S2CPacket> packets;
    private boolean waiting;
    private int delay, times;
    private TransmitStage transmitStage;
    private static final int handshakeDelay = 10, timeout = 30;
    private boolean threadStarted;
    private final Thread myThread;
    public ServerNetworkCache(Player player) {
        packets = new LinkedList<>();
        waitingForSend = new LinkedList<>();
        transmitStage = TransmitStage.STANDING_BY;
        this.player = player;
        myThread = new Thread(this);
        threadStarted = false;
    }

    public void enqueueTree(TechTree tree) {
        this.waitingForSend.add(tree);
        if (!threadStarted) {
            myThread.start();
            threadStarted = true;
            System.out.println("thread started");
        }
    }

    private void startBatch(UUID batchId) {
        this.batch = batchId;
        transmitStage = TransmitStage.HANDSHAKE;
        delay = 0;
        times = 0;
    }

    private void compileTree(TechTree tree) {
        if (batch == null) return;
        packets.offer(new TechTreePacket(batch, tree));
        tree.getGroups().forEach((grpName, grp) -> {
            packets.offer(new TechTreeGroupPacket(batch, grp));
        });
        tree.getNodes().forEach((loc, node) -> {
            packets.offer(new TechTreeNodePacket(batch, node));
        });
    }

    public void collectClientNetworkState(NetworkState state) {
        if (state == NetworkState.BUSY) {
            waiting = true;
            delay = 0;
            times = 0;
        } else {
            transmitStage = TransmitStage.TRANSMITTING;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (transmitStage == TransmitStage.STANDING_BY) {
                if (waitingForSend.isEmpty()) {
                    threadStarted = false;
                    return;
                }
                startBatch(UUID.randomUUID());
                compileTree(waitingForSend.poll());
                continue;
            }
            if (transmitStage == TransmitStage.HANDSHAKE) {
                waiting = true;
                if (!onWait((obj) -> {
                    sendHandShakePacket();
                })) forceStop();
                continue;
            }
            if (transmitStage == TransmitStage.TRANSMITTING) {
                delay = 0;
                times = 0;
                while (!packets.isEmpty()) {
                    S2CPacket payload = packets.poll();
                    AllPackets.TECH_TREE_CHANNEL.sendToClient(payload, (ServerPlayer) player);
                }
                transmitStage = TransmitStage.EOF;
            }
            if (transmitStage == TransmitStage.EOF) {
                waiting = true;
                if (!onWait(obj -> {
                    sendEOFPacket();
                })) forceStop();
            }
        }
    }

    public boolean onWait(Consumer<Object> consumer) {
        if (waiting && times >= timeout) {
            clear();
            return false;
        } else if (waiting && delay < handshakeDelay) {
            delay();
            delay++;
        } else if (waiting) {
            consumer.accept(null);
            delay();
            delay = 1;
            times++;
        } else {
            consumer.accept(null);
        }
        return true;
    }

    private void clear() {
        packets.clear();
        transmitStage = TransmitStage.STANDING_BY;
        batch = null;
        waiting = false;
        times = 0;
        delay = 0;
    }

    public void nextTree() {
        clear();
    }

    public void forceStop() {
        clear();
        waitingForSend.clear();
        transmitStage = TransmitStage.STANDING_BY;
    }

    private void delay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}
    }

    private void sendHandShakePacket() {
        AllPackets.TECH_TREE_CHANNEL.sendToClient(new TechTreeHandShakeS2CPacket(batch), (ServerPlayer) player);
    }

    private void sendEOFPacket() {
        if (batch == null) return;
        AllPackets.TECH_TREE_CHANNEL.sendToClient(new TechTreeEOFS2CPacket(batch), (ServerPlayer) player);
    }

    public enum TransmitStage {
        STANDING_BY,
        HANDSHAKE,
        TRANSMITTING,
        EOF;
    }
}
