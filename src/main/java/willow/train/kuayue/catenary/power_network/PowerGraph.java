package willow.train.kuayue.catenary.power_network;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;

public class PowerGraph {

    public final ResourceLocation name;
    public final HashMap<BlockPos, PowerNode> nodes;
    private PowerNode head;

    public PowerGraph(ResourceLocation name, PowerNode firstNode) {
        this.name = name;
        this.nodes = new HashMap<>();
        this.head = firstNode;
        nodes.put(firstNode.getPos(), firstNode);
    }

    public PowerGraph(ResourceLocation name) {
        this.name = name;
        this.nodes = new HashMap<>();
        this.head = null;
    }

    public PowerNode getNode(BlockPos pos) {
        return nodes.getOrDefault(pos, null);
    }

    public boolean containsNode(BlockPos pos) {
        return nodes.containsKey(pos);
    }

    public boolean addNode(BlockPos prevPos, PowerNode node) {
        if (!nodes.containsKey(prevPos)) {
            if (this.head == null) {
                this.head = node;
                this.nodes.put(node.getPos(), node);
                return true;
            }
            return false;
        }
        PowerNode prev = getNode(prevPos);
        node.connect(prev);
        nodes.put(node.getPos(), node);
        return true;
    }

    /*
    public boolean setNodeAt(PowerNode node) {
        BlockPos pos = node.getPos();
        if (!nodes.containsKey(pos)) {

        }
    }

     */

    public boolean removeNode(BlockPos pos) {
        if (!nodes.containsKey(pos)) return false;
        PowerNode node = getNode(pos);
        for (PowerNode n : node.getConnections()) node.disConnect(n);
        this.nodes.remove(pos);
        return true;
    }
}
