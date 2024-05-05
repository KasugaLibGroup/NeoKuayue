package willow.train.kuayue.block.panels.base;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class TrainPanelProperties {

    public static final EnumProperty<SkirtType> SKIRT_TYPE = EnumProperty.create("skirt", SkirtType.class);
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 1);
    public static final IntegerProperty TYPE_2 = IntegerProperty.create("type", 0, 2);

    public enum DoorType implements StringRepresentable {
        FOLD, SLIDE, ROTATE;

        @Override
        public @NotNull String getSerializedName() {
            return switch (this) {
                case FOLD -> "fold";
                case SLIDE -> "slide";
                case ROTATE -> "rotate";
            };
        }

        public static DoorType fromString(String input) {
            return switch (input) {
                case "fold" -> FOLD;
                case "slide" -> SLIDE;
                default -> ROTATE;
            };
        }
    }

    public enum SkirtType implements StringRepresentable {

        LEFT, RIGHT, MIDDLE;

        @Override
        public @NotNull String getSerializedName() {
            return switch (this) {
                case LEFT -> "left";
                case RIGHT -> "right";
                case MIDDLE -> "middle";
            };
        }

        public SkirtType fromString(String input) {
            return switch (input) {
                case "left" -> LEFT;
                case "right" -> RIGHT;
                case "middle" -> MIDDLE;
                default -> LEFT;
            };
        }
    }
}
