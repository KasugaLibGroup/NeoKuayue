package willow.train.kuayue.systems.tech_tree.server;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.json.TechTreeGroupData;
import willow.train.kuayue.systems.tech_tree.json.TechTreeNodeData;

import java.util.HashMap;
import java.util.Map;

public class TechTreeGroup {

    public final TechTreeGroupData data;

    public final TechTree tree;
    private final HashMap<NodeLocation, TechTreeNode> nodes;
    private final TechTreeNode root;

    public TechTreeGroup(TechTree tree, TechTreeGroupData data) {
        this.data = data;
        this.tree = tree;
        nodes = new HashMap<>();
        collectNodes();
        tree.grepNodes(nodes);
        root = nodes.getOrDefault(data.getRootLocation(), null);
    }

    protected void collectNodes() {
        data.getNodes().forEach((loc, dat) -> {
            nodes.put(loc, new TechTreeNode(this, dat));
        });
    }

    public TechTreeNode getRoot() {
        return root;
    }

    public HashMap<NodeLocation, TechTreeNode> getNodes() {
        return nodes;
    }

    public String getIdentifier() {
        return data.identifier;
    }

    public String getNamespace() {
        return tree.getNamespace();
    }

    public boolean is(ResourceLocation location) {
        return data.getLocation().equals(location);
    }

    public boolean is(String str) {
        return data.getLocation().toString().equals(str);
    }
}
