package willow.train.kuayue.systems.tech_tree.server;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.json.HideContext;
import willow.train.kuayue.systems.tech_tree.json.TechTreeData;
import willow.train.kuayue.systems.tech_tree.json.TechTreeNodeData;

import java.util.ArrayList;

public class TechTreeNode {

    public final TechTreeNodeData data;

    public final TechTreeGroup group;
    private final ArrayList<TechTreeNode> prev;
    private final ArrayList<TechTreeNode> next;
    private final ArrayList<TechTreeGroup> nextGroups;
    public TechTreeNode(TechTreeGroup group, TechTreeNodeData data) {
        this.data = data;
        this.group = group;
        this.prev = new ArrayList<>();
        this.next = new ArrayList<>();
        this.nextGroups = new ArrayList<>();
    }

    public void compileConnections() {
        for (NodeLocation next : data.getNextNodes()) {
            TechTreeNode node = TechTreeManager.MANAGER.getNode(next);
            if (node == null) continue;
            this.next.add(node);
            node.prev.add(this);
        }
        for (ResourceLocation next : data.getNextGroups()) {
            TechTreeGroup g = TechTreeManager.MANAGER.getGroup(next);
            if (g == null) continue;
            this.nextGroups.add(g);
        }
    }

    public NodeLocation getLocation() {
        return data.getLocation();
    }

    public boolean isHide() {
        return data.isHide();
    }

    public HideContext getHideContext() {
        return data.getHide();
    }

    public void addPrev(TechTreeNode node) {
        prev.add(node);
    }

    public ArrayList<TechTreeNode> getPrev() {
        return prev;
    }

    public void addNext(TechTreeNode node) {
        next.add(node);
    }

    public ArrayList<TechTreeNode> getNext() {
        return next;
    }

    public void addNextGroup(TechTreeGroup group) {
        nextGroups.add(group);
    }

    public ArrayList<TechTreeGroup> getNextGroups() {
        return nextGroups;
    }

    public boolean is(NodeLocation location) {
        return location.equals(data.getLocation());
    }

    public boolean is(String location) {
        return new NodeLocation(group.getNamespace(), group.getIdentifier(), location).equals(data.getLocation());
    }
}
