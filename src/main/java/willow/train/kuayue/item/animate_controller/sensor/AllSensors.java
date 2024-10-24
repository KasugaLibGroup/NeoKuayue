package willow.train.kuayue.item.animate_controller.sensor;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;

public class AllSensors {

    public static final HashMap<ResourceLocation, AnimateSensorType> SENSORS = new HashMap<>();

    public static AnimateSensorType register(ResourceLocation location, String displayName, float defaultCondition,
                                             AnimateSensorType.SensorFunction sensorFunction) {
        AnimateSensorType sensorType = new AnimateSensorType(location, displayName, defaultCondition, sensorFunction);
        SENSORS.put(location, sensorType);
        return sensorType;
    }

    public static @Nullable AnimateSensorType getSensorType(ResourceLocation location) {
        return SENSORS.getOrDefault(location, null);
    }
}
