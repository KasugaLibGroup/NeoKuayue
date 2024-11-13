package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.client.ClientNetworkCache;
import willow.train.kuayue.systems.tech_tree.server.TechTreeNode;

import java.util.UUID;

public class TechTreeNodePacket extends S2CPacket {

    private TechTreeNode node;
    private final UUID batch;

    public TechTreeNodePacket(UUID batch, TechTreeNode node) {
        this.node = node;
        this.batch = batch;
    }

    public TechTreeNodePacket(FriendlyByteBuf buf) {
        super(buf);
        batch = buf.readUUID();
        ClientNetworkCache.INSTANCE.addNode(buf);
    }

    @Override
    public void handle(Minecraft minecraft) {}

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(batch);
        node.toNetwork(friendlyByteBuf);
    }
}
