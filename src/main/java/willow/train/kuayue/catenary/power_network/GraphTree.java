package willow.train.kuayue.catenary.power_network;

import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphTree {
    private final GraphTreeNode head;
    private final List<GraphTreeNode> nodes;

    public GraphTree(GraphTreeNode head) {
        this.head = head;
        nodes = new LinkedList<>();
    }

    public GraphTree(PowerNode node) {
        this.head = new GraphTreeNode(node);
        this.nodes = new LinkedList<>();
    }

    public GraphTreeNode getHead() {
        return head;
    }

    public List<GraphTreeNode> getNodes() {
        return nodes;
    }

    public void genTreeFromHead() {
        this.nodes.clear();
        ArrayList<PowerNode> powerNodes = new ArrayList<>(64);
        ArrayList<Integer> route = new ArrayList<>(20);
        GraphTreeNode cache = head;
        this.nodes.add(head);
        GraphTreeNode gtn;
        do {
            powerNodes.clear();
            powerNodes.addAll(cache.element.getConnections());
            for (PowerNode node : powerNodes) {
                gtn = new GraphTreeNode(node);
                if (nodes.contains(gtn)) continue;
                gtn.prev = cache;
                this.nodes.add(gtn);
                cache.next.add(gtn);
            }
            if (cache.next.isEmpty()) {
                int j = route.get(route.size() - 1);
                while (j + 1 >= cache.prev.next.size()) {
                    route.remove(route.size() - 1);
                    if (route.isEmpty()) return;
                    cache = cache.prev;
                    j = route.get(route.size() - 1);
                }
                cache = cache.prev.next.get(j + 1);
                route.set(route.size() - 1, j + 1);
            } else {
                route.add(0);
                cache = cache.next.get(0);
            }
        } while (!route.isEmpty());
    }

    // public ArrayList<GraphTreeNode> serialize() {
        // if (nodes.isEmpty()) genTreeFromHead();

    // }

    public static void test() {
        PowerNode node1 = new PowerNode(new BlockPos(0, 0, 0), AllPowerNodeTypes.JOINT),
                node2 = new PowerNode(new BlockPos(1, 0, 0), AllPowerNodeTypes.JOINT),
                node3 = new PowerNode(new BlockPos(2, 0, 0), AllPowerNodeTypes.JOINT),
                node4 = new PowerNode(new BlockPos(3, 0, 0), AllPowerNodeTypes.JOINT),
                node5 = new PowerNode(new BlockPos(4, 0, 0), AllPowerNodeTypes.JOINT);
        node1.connect(node3);
        node1.connect(node2);
        node3.connect(node4);
        node4.connect(node5);
        node3.connect(node5);
        GraphTree tree = new GraphTree(node5);
        tree.genTreeFromHead();
        System.out.println("size = " + tree.nodes.size());
    }

    public static void main(String[] args) {
        test();
    }
}
