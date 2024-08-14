package willow.train.kuayue.catenary.power_network;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PowerNode {
    private final BlockPos pos;

    public final PowerNodeType type;
    private final List<PowerNode> connection;

    public PowerNode(BlockPos pos, ResourceLocation type) {
        this.pos = pos;
        this.type = AllPowerNodeTypes.get(type);
        connection = new ArrayList<>();
    }

    public PowerNode(BlockPos pos, PowerNodeType type) {
        this.pos = pos;
        this.type = type;
        connection = new ArrayList<>();
    }

    public BlockPos getPos() {
        return pos;
    }

    public PowerNodeType getType() {
        return type;
    }

    public PowerPackage consume(PowerPackage input) {
        return type.applyConsumer(input);
    }

    public PowerPackage apply(PowerPackage input) {
        return type.applySupplier(input);
    }

    public boolean connectedTo(PowerNode node) {
        return connection.contains(node);
    }

    public void connect(PowerNode node) {
        connection.add(node);
        node.connection.add(this);
    }

    public boolean disConnect(PowerNode node) {
        if (!connection.contains(node)) return false;
        node.connection.remove(this);
        this.connection.remove(node);
        return true;
    }

    public List<PowerNode> getConnections() {
        return connection;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PowerNode node)) return false;
        return this.pos.equals(node.pos);
    }
}
