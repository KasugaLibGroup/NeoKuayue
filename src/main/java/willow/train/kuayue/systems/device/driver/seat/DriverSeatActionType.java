package willow.train.kuayue.systems.device.driver.seat;

import net.minecraft.util.StringRepresentable;

public enum DriverSeatActionType implements StringRepresentable {
    SIT_DOWN("sit_down"),
    STAND("stand"),
    WATCHING_DOOR("watching_door");


    private final String name;

    DriverSeatActionType(String name) {
        this.name = name;
    }

    public static DriverSeatActionType fromName(String s) {
        switch (s) {
            case "sit_down":
                return SIT_DOWN;
            case "stand":
                return STAND;
            case "watching_door":
                return WATCHING_DOOR;
            default:
                throw new IllegalArgumentException("Unknown action type: " + s);
        }
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
