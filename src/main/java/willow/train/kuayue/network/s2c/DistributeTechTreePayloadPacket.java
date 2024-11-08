package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTreeManager;

import java.util.UUID;

public class DistributeTechTreePayloadPacket extends S2CPacket {

    @Getter
    private final UUID uuid;

    @Getter
    private final int index;

    @Getter
    private final FriendlyByteBuf buf;

    public DistributeTechTreePayloadPacket(UUID id, int index, FriendlyByteBuf buf) {
        this.uuid = id;
        this.buf = buf;
        this.index = index;
    }
    public DistributeTechTreePayloadPacket(FriendlyByteBuf buf) {
        this.uuid = buf.readUUID();
        this.index = buf.readInt();
        this.buf = new FriendlyByteBuf(buf.readBytes(buf.writerIndex() - buf.readerIndex() - 1));
    }
    @Override
    public void handle(Minecraft minecraft) {
        ClientTechTreeManager.CACHE.addPayload(this);
        ClientTechTreeManager.CACHE.attemptToHandle();
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUUID(uuid);
        friendlyByteBuf.writeInt(index);
        friendlyByteBuf.writeBytes(buf);
    }
}
