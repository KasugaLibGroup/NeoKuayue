package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import java.util.HashMap;
import java.util.HashSet;

@Getter
public class ClientTechTree {

    private final String namespace;

    private final HashMap<String, ClientTechTreeGroup> groups;

    private final HashMap<NodeLocation, ClientTechTreeNode> nodes;

    private final int groupSize, nodeSize;

    public ClientTechTree(FriendlyByteBuf buf) {
        groups = new HashMap<>();
        namespace = buf.readUtf();

        this.groupSize = buf.readInt();
        this.nodeSize = buf.readInt();
        for (int i = 0; i < groupSize; i++) {
            groups.put(buf.readUtf(), null);
        }

        nodes = new HashMap<>();
    }

    public void update() {
        HashSet<ClientTechTreeNode> needToAdd = new HashSet<>();
        nodes.forEach((loc, node) -> {
            if (node.getNextNode().size() == node.getNext().size()) return;
            node.getNext().forEach(n -> {
                for (ClientTechTreeNode node1 : node.getNextNode()) {
                    if (node1.location.equals(n)) return;
                }
                ClientTechTreeNode neoNext = ClientTechTreeManager.getInstance().getNode(n);
                if (neoNext == null) return;
                needToAdd.add(neoNext);
            });
            node.getNextNode().addAll(needToAdd);
            needToAdd.clear();
        });
    }
}
