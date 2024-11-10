package willow.train.kuayue.network.s2c;

import kasuga.lib.core.network.S2CPacket;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTree;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTreeManager;
import willow.train.kuayue.systems.tech_tree.server.TechTreeManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DistributeTechTreePacket extends S2CPacket {

    @Getter
    private HashSet<UUID> payloadID;
    public DistributeTechTreePacket() {}

    public DistributeTechTreePacket(FriendlyByteBuf buf) {
        super(buf);
        payloadID = new HashSet<>();
        int payloadSize = buf.readInt();
        for (int i = 0; i < payloadSize; i++) {
            payloadID.add(buf.readUUID());
        }
    }

    @Override
    public void handle(Minecraft minecraft) {
        ClientTechTreeManager.CACHE.setMeta(this);
        ClientTechTreeManager.CACHE.attemptToHandle();
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.clear();
        friendlyByteBuf.writeInt(TechTreeManager.MANAGER.trees().size());
        TechTreeManager.MANAGER.trees().forEach((location, tree) -> {
            tree.toNetwork(friendlyByteBuf);
        });

        int counter = 0;
        Set<UUID> packIds = new HashSet<>();
        while(friendlyByteBuf.readerIndex() < friendlyByteBuf.writerIndex() - 1) {
            if (friendlyByteBuf.writerIndex() - friendlyByteBuf.readerIndex() >= 256) {
                FriendlyByteBuf buf = new FriendlyByteBuf(friendlyByteBuf.readBytes(256));
                DistributeTechTreePayloadPacket payload = new DistributeTechTreePayloadPacket(UUID.randomUUID(), counter, buf);
                TechTreeManager.addPayload(payload);
                packIds.add(payload.getUuid());
            } else {
                FriendlyByteBuf buf = new FriendlyByteBuf(
                        friendlyByteBuf.readBytes(friendlyByteBuf.writerIndex() - friendlyByteBuf.readerIndex() - 1)
                );
                DistributeTechTreePayloadPacket payload = new DistributeTechTreePayloadPacket(UUID.randomUUID(), counter, buf);
                TechTreeManager.addPayload(payload);
                packIds.add(payload.getUuid());
            }
            counter++;
        }

        friendlyByteBuf.clear();
        friendlyByteBuf.writeBoolean(true);
        friendlyByteBuf.writeInt(packIds.size());
        packIds.forEach(friendlyByteBuf::writeUUID);
    }
}
