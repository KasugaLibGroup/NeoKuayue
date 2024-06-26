package willow.train.kuayue.block.panels.base;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class TrainPanelProperties {

    public static final EnumProperty<SkirtType> SKIRT_TYPE = EnumProperty.create("skirt", SkirtType.class);
    public static final IntegerProperty TYPE = IntegerProperty.create("type", 0, 1);
    public static final IntegerProperty TYPE_2 = IntegerProperty.create("type", 0, 2);
    public static final EnumProperty<FloorHeight> FLOOR_HEIGHT = EnumProperty.create("floor", FloorHeight.class);
    public static final EnumProperty<ExternalHinge> EXTERNAL_HINGE = EnumProperty.create("hinge", ExternalHinge.class);

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

    public enum FloorHeight implements StringRepresentable {
        BOTTOM, DOUBLE, TOP;

        @Override
        public @NotNull String getSerializedName() {
            return switch (this) {
                case TOP -> "top";
                case BOTTOM -> "bottom";
                case DOUBLE -> "double";
            };
        }

        public static FloorHeight fromString(String input) {
            return switch (input) {
                case "double" -> DOUBLE;
                case "bottom" -> BOTTOM;
                default -> TOP;
            };
        }
    }

    public enum ExternalHinge implements StringRepresentable {
        SINGLE, LEFT, RIGHT, DOUBLE;

        @Override
        public String getSerializedName() {
            return switch (this) {
                case DOUBLE -> "double";
                case RIGHT -> "right";
                case LEFT -> "left";
                case SINGLE -> "single";
            };
        }

        public static ExternalHinge fromString(String input) {
            return switch (input) {
                case "single" -> SINGLE;
                case "right" -> RIGHT;
                case "left" -> LEFT;
                default -> DOUBLE;
            };
        }
    }
}
