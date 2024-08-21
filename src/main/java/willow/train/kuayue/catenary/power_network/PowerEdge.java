package willow.train.kuayue.catenary.power_network;

import kasuga.lib.core.base.NbtSerializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.catenary.constants.AllCatenaryLineTypes;
import willow.train.kuayue.catenary.constants.Utils;
import willow.train.kuayue.catenary.types.CatenaryLineType;

public class PowerEdge implements IPower, NbtSerializable {
    private final CatenaryLineType type;
    private PowerNode left, right;

    public PowerEdge(CatenaryLineType type, PowerNode left, PowerNode right) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    public PowerEdge(ResourceLocation type, PowerNode left, PowerNode right) {
        this.left = left;
        this.right = right;
        this.type = AllCatenaryLineTypes.getType(type);
    }

    public boolean hasNode(PowerNode node) {
        return left.equals(node) || right.equals(node);
    }

    public PowerNode getNextSide(PowerNode mySelf) {
        if (left == mySelf) return right;
        return left;
    }

    public PowerNode getLeft() {
        return left;
    }

    public PowerNode getRight() {
        return right;
    }

    public CatenaryLineType getType() {
        return type;
    }

    public void setLeft(PowerNode left) {
        this.left = left;
    }

    public void setRight(PowerNode right) {
        this.right = right;
    }

    @Override
    public float getMaxVoltage() {
        return type.getMaxVoltage();
    }

    @Override
    public float getMaxCurrent() {
        return type.getMaxCurrent();
    }

    @Override
    public float getMaxPower() {
        return type.getMaxPower();
    }

    @Override
    public boolean isOverloaded(float voltage, float current) {
        return voltage > getMaxVoltage() || current > getMaxCurrent();
    }

    @Override
    public void write(CompoundTag compoundTag) {
        Utils.writeResourceLocation(type.getName(), compoundTag, "type");
    }

    @Override
    public void read(CompoundTag compoundTag) {}

    public static PowerEdge readFromNbt(CompoundTag nbt) {
        return new PowerEdge(Utils.readResourceLocation(nbt, "type"), null, null);
    }
}
