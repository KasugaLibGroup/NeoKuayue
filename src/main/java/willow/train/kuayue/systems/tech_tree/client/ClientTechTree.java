package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;

@Getter
public class ClientTechTree {

    private final String namespace;

    private final HashMap<String, ClientTechTreeGroup> groups;

    public ClientTechTree(FriendlyByteBuf buf) {
        groups = new HashMap<>();
        namespace = buf.readUtf();

        int groupSize = buf.readInt();
        for (int i = 0; i < groupSize; i++) {
            ClientTechTreeGroup group = new ClientTechTreeGroup(buf);
            groups.put(group.getId().getPath(), group);
        }
    }
}
