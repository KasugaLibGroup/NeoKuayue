package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.TechTreeHandShakeC2SPacket;
import willow.train.kuayue.systems.tech_tree.NetworkState;
import willow.train.kuayue.systems.tech_tree.client.ClientNetworkCache;

import java.util.UUID;

public class TechTreeHandShakeS2CPacket extends S2CPacket {

    private final UUID batchId;

    public TechTreeHandShakeS2CPacket(UUID batch) {
        batchId = batch;
    }

    public TechTreeHandShakeS2CPacket(FriendlyByteBuf buf) {
        super(buf);
        this.batchId = buf.readUUID();
    }
    @Override
    public void handle(Minecraft minecraft) {
        NetworkState state = ClientNetworkCache.INSTANCE.queryState(batchId);
        if (state == NetworkState.STANDING_BY) {
            ClientNetworkCache.INSTANCE.startBatch(batchId);
        }
        AllPackets.TECH_TREE_CHANNEL.sendToServer(new TechTreeHandShakeC2SPacket(state));
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(batchId);
    }
}
