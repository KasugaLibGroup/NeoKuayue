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
        FOLD, SLIDE, SLIDE_2, SLIDE_3, ROTATE, ROTATE_SINGLE_SIDED, NO_DOOR;

        public boolean isSliding() {
            return (this == SLIDE || this == SLIDE_2 || this == SLIDE_3);
        }

        public boolean isRotating() {
            return this == ROTATE || this == ROTATE_SINGLE_SIDED;
        }

        @Override
        public @NotNull String getSerializedName() {
            return switch (this) {
                case FOLD -> "fold";
                case SLIDE_2 -> "slide_2";
                case SLIDE_3 -> "slide_3";
                case SLIDE -> "slide";
                case NO_DOOR -> "no_door";
                case ROTATE -> "rotate";
                case ROTATE_SINGLE_SIDED -> "rotate_single_sided";
            };
        }

        public static DoorType fromString(String input) {
            return switch (input) {
                case "fold" -> FOLD;
                case "slide" -> SLIDE;
                case "slide_2" -> SLIDE_2;
                case "slide_3" -> SLIDE_3;
                case "no_door" -> NO_DOOR;
                case "rotate_single_sided" -> ROTATE_SINGLE_SIDED;
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

    public enum EditType implements StringRepresentable{
        NONE, LAQUERED, TYPE, SPEED, NUM;

        @Override
        public @NotNull String getSerializedName() {
            return switch (this) {
                case NONE -> "none";
                case LAQUERED -> "laquered";
                case TYPE -> "type";
                case SPEED -> "speed";
                case NUM -> "num";
            };
        }

        public static EditType fromString(String input) {
            return switch (input) {
                case "none" -> NONE;
                case "laquered" -> LAQUERED;
                case "type" -> TYPE;
                case "speed" -> SPEED;
                case "num" -> NUM;
                default -> NONE;
            };
        }
    }

    public enum PanelType implements StringRepresentable {
        P25B,
        P25G,
        P25ZA,
        P25ZB,
        P25ZC,
        P25TA,
        P25TB,
        P25KA,
        P25KB, // 标识线
        P25KC,
        M25B,
        M25G,
        M25Z,
        M25T,
        M25KA,
        M25KB; // 标识线

        public String toString() {
            return this.getSerializedName();
        }

        @Override
        public @NotNull String getSerializedName() {
            switch (this) {
                case P25B -> {
                    return "p25b";
                }
                case P25G -> {
                    return "p25g";
                }
                case P25ZA -> {
                    return "p25za";
                }
                case P25ZB -> {
                    return "p25zb";
                }
                case P25ZC -> {
                    return "p25zc";
                }
                case P25TA -> {
                    return "p25ta";
                }
                case P25TB -> {
                    return "p25tb";
                }
                case P25KA -> {
                    return "p25ka";
                }
                case P25KB -> {
                    return "p25kb";
                }
                case P25KC -> {
                    return "p25kc";
                }
                case M25B -> {
                    return "m25b";
                }
                case M25G -> {
                    return "m25g";
                }
                case M25Z -> {
                    return "m25z";
                }
                case M25T -> {
                    return "m25t";
                }
                case M25KA -> {
                    return "m25ka";
                } // 细线
                case M25KB -> {
                    return "m25kb";
                } // 粗线
                default -> {
                    return "p25b";
                }
            }
        }

        public static PanelType encode(String str) {
            switch (str) {
                case "p25b" -> {
                    return P25B;
                }
                case "p25g" -> {
                    return P25G;
                }
                case "p25za" -> {
                    return P25ZA;
                }
                case "p25zb" -> {
                    return P25ZB;
                }
                case "p25zc" -> {
                    return P25ZC;
                }
                case "p25ta" -> {
                    return P25TA;
                }
                case "p25tb" -> {
                    return P25TB;
                }
                case "p25ka" -> {
                    return P25KA;
                }
                case "p25kb" -> {
                    return P25KB;
                }
                case "p25kc" -> {
                    return P25KC;
                }
                case "m25b" -> {
                    return M25B;
                }
                case "m25g" -> {
                    return M25G;
                }
                case "m25z" -> {
                    return M25Z;
                }
                case "m25t" -> {
                    return M25T;
                }
                case "m25ka" -> {
                    return M25KA;
                }
                case "m25kb" -> {
                    return M25KB;
                }
                default -> {
                    return P25B;
                }
            }
        }
    }
}
