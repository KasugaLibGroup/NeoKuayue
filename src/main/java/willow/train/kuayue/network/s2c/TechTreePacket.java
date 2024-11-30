package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.client.ClientNetworkCache;
import willow.train.kuayue.systems.tech_tree.server.TechTree;

import java.util.UUID;

public class TechTreePacket extends S2CPacket {

    private TechTree tree;
    private final UUID batch;
    public TechTreePacket(UUID batch, TechTree tree) {
        this.tree = tree;
        this.batch = batch;
    }

    public TechTreePacket(FriendlyByteBuf buf) {
        super(buf);
        this.batch = buf.readUUID();
        ClientNetworkCache.INSTANCE.setTree(buf);
    }

    @Override
    public void handle(Minecraft minecraft) {

    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(batch);
        tree.toNetwork(friendlyByteBuf);
    }
}
