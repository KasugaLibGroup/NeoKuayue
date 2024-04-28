package willow.train.kuayue.block.panels.base;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class TrainPanelProperties {

    public static final EnumProperty<DoorType> DOOR_TYPE = EnumProperty.create("door_type", DoorType.class);

    public enum DoorType implements StringRepresentable {
        FOLD, SLIDE, ROTATE;

        @Override
        public String getSerializedName() {
            return switch (this) {
                case FOLD -> "fold";
                case SLIDE -> "slide";
                case ROTATE -> "rotate";
            };
        }

        public DoorType fromString(String input) {
            return switch (input) {
                case "fold" -> FOLD;
                case "slide" -> SLIDE;
                default -> ROTATE;
            };
        }
    }
}
