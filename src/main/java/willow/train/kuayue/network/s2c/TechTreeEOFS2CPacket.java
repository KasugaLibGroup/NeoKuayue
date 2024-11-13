package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.TechTreeEOFC2SPacket;
import willow.train.kuayue.systems.tech_tree.client.ClientNetworkCache;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTree;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTreeManager;

import java.util.UUID;

public class TechTreeEOFS2CPacket extends S2CPacket {

    private final UUID batch;
    public TechTreeEOFS2CPacket(UUID batch) {
        this.batch = batch;
    }

    public TechTreeEOFS2CPacket(FriendlyByteBuf buf) {
        this.batch = buf.readUUID();
    }

    @Override
    public void handle(Minecraft minecraft) {
        if (ClientNetworkCache.INSTANCE.verify()) {
            ClientTechTree tree = ClientNetworkCache.INSTANCE.construct();
            ClientTechTreeManager.MANAGER.trees().put(tree.getNamespace(), tree);
            tree.update();
            ClientNetworkCache.INSTANCE.reset();
            AllPackets.TECH_TREE_CHANNEL.sendToServer(new TechTreeEOFC2SPacket());
        }
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(batch);
    }
}
