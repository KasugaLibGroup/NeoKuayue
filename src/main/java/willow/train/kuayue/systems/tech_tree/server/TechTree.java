package willow.train.kuayue.systems.tech_tree.server;

import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.packs.resources.ResourceManager;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.json.TechTreeData;

import java.util.HashMap;
import java.util.HashSet;

@Getter
public class TechTree {

    public final TechTreeData data;

    private final HashMap<String, TechTreeGroup> groups;

    private final HashMap<NodeLocation, TechTreeNode> nodes;

    public TechTree(TechTreeData data) {
        this.data = data;
        this.groups = new HashMap<>();
        this.nodes = new HashMap<>();
        collectGroups();
    }

    protected void collectGroups() {
        data.getGroups().forEach((name, dat) -> {
            groups.put(name, new TechTreeGroup(this, dat));
        });
    }

    protected void compileConnections() {
        nodes.forEach((loc, node) -> {
            node.compileConnections();
        });
        groups.forEach((str, grp) -> {
            nodes.values().forEach(n -> {
                if (!n.getNextGroups().contains(grp)) return;
                grp.addPrev(n);
            });
        });
    }

    protected void grepNbt(ResourceManager manager) {
        groups.forEach((loc, group) -> {
            group.data.loadAllNbt(manager);
        });
    }

    protected void grepNodes(HashMap<NodeLocation, TechTreeNode> grepNodes) {
        nodes.putAll(grepNodes);
    }

    public String getNamespace() {
        return data.namespace;
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeUtf(getNamespace());

        buf.writeInt(groups.size());
        buf.writeInt(nodes.size());
        groups.forEach((loc, grp) -> buf.writeUtf(loc));
        // groups.forEach((location, grp) -> grp.toNetwork(buf));
    }
}
