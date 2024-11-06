package willow.train.kuayue.systems.tech_tree;

import net.minecraft.util.StringRepresentable;

public enum NodeType implements StringRepresentable {
    PLAIN, EPIC;

    @Override
    public String getSerializedName() {
        return switch (this) {
            case PLAIN -> "plain";
            case EPIC -> "epic";
        };
    }

    public static NodeType getType(String str) {
        return switch (str) {
            case "epic" -> EPIC;
            default -> PLAIN;
        };
    }
}
