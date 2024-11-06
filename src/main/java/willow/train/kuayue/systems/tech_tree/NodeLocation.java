package willow.train.kuayue.systems.tech_tree;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.systems.tech_tree.json.TechTreeNodeData;

public class NodeLocation {
    private final String namespace, group, name;

    public NodeLocation(String currentNamespace, String currenGroup, String path) {
        String cache = path;
        if (path.contains(":")) {
            String[] strs = cache.split(":");
            this.namespace = strs[0];
            cache = strs[1];
        } else {
            this.namespace = currentNamespace;
        }
        if (cache.contains(".")) {
            String[] strs = cache.split("\\.");
            this.group = strs[0];
            this.name = strs[1];
        } else {
            this.group = currenGroup;
            this.name = cache;
        }
    }

    public NodeLocation(TechTreeNodeData node) {
        this.namespace = node.group.tree.namespace;
        this.group = node.group.identifier;
        this.name = node.getIdentifier();
    }

    public NodeLocation(String fullPath) {
        String[] namespaceAndPath = fullPath.split(":");
        this.namespace = namespaceAndPath[0];
        String[] groupAndName = namespaceAndPath[1].split("\\.");
        this.group = groupAndName[0];
        this.name = groupAndName[1];
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public ResourceLocation getGroupLocation() {
        return new ResourceLocation(this.namespace, this.group);
    }

    public String toString() {
        return this.namespace + ":" + this.group + "." + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof NodeLocation location)) return false;
        return this.namespace.equals(location.namespace) &&
                this.group.equals(location.group) &&
                this.name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
