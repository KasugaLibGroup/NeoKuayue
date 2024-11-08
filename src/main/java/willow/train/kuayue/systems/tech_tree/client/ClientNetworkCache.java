package willow.train.kuayue.systems.tech_tree.client;

import lombok.Setter;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.network.s2c.DistributeTechTreePacket;
import willow.train.kuayue.network.s2c.DistributeTechTreePayloadPacket;

import java.util.*;

public class ClientNetworkCache {
    private final HashMap<UUID, DistributeTechTreePayloadPacket> payloads;

    @Setter
    private DistributeTechTreePacket meta = null;

    public ClientNetworkCache() {
        payloads = new HashMap<>();
    }

    public void addPayload(DistributeTechTreePayloadPacket payload) {
        payloads.put(payload.getUuid(), payload);
    }

    public void attemptToHandle() {
        // verify
        if (meta == null) return;
        HashSet<UUID> payloadId = meta.getPayloadID();
        if (payloadId.size() > payloads.size()) return;
        Collection<UUID> verify = new HashSet<>(payloadId);
        verify.removeAll(payloads.keySet());
        if (!verify.isEmpty()) return;

        // sort
        DistributeTechTreePayloadPacket[] payloadArray = new DistributeTechTreePayloadPacket[payloadId.size()];
        for (Map.Entry<UUID, DistributeTechTreePayloadPacket> entry : payloads.entrySet()) {
            DistributeTechTreePayloadPacket pl = entry.getValue();
            payloadArray[pl.getIndex()] = pl;
        }
        if (payloadArray.length < 1) return;

        // combine
        FriendlyByteBuf buf = payloadArray[0].getBuf();
        for (int i = 1; i < payloadArray.length; i++) {
            buf.writeBytes(payloadArray[i].getBuf());
        }

        // compile
        HashMap<String, ClientTechTree> trees = new HashMap<>();
        int count = buf.readInt();
        for(int i = 0; i < count; i++) {
            ClientTechTree tree = new ClientTechTree(buf);
            trees.put(tree.getNamespace(), tree);
        }
        ClientTechTreeManager.MANAGER.getTREES().putAll(trees);

        // clean
        payloads.clear();
        meta = null;
    }
}
