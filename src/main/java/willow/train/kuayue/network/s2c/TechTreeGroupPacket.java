package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.client.ClientNetworkCache;
import willow.train.kuayue.systems.tech_tree.server.TechTreeGroup;

import java.util.UUID;

public class TechTreeGroupPacket extends S2CPacket {

    private TechTreeGroup group;
    private final UUID batch;
    public TechTreeGroupPacket(UUID batch, TechTreeGroup group) {
        this.group = group;
        this.batch = batch;
    }

    public TechTreeGroupPacket(FriendlyByteBuf buf) {
        this.batch = buf.readUUID();
        ClientNetworkCache.INSTANCE.addGroup(buf);
    }

    @Override
    public void handle(Minecraft minecraft) {

    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(batch);
        group.toNetwork(friendlyByteBuf);
    }
}
