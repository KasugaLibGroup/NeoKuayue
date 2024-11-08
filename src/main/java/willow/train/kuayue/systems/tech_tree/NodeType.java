package willow.train.kuayue.systems.tech_tree;

import net.minecraft.network.FriendlyByteBuf;
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

    public void writeToByteBuf(FriendlyByteBuf buf) {
        buf.writeUtf(this.getSerializedName());
    }

    public static NodeType readFromByteBuf(FriendlyByteBuf buf) {
        return getType(buf.readUtf());
    }
}
