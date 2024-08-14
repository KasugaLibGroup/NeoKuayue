package willow.train.kuayue.catenary.power_network;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.initial.AllElements;

import java.util.HashMap;
import java.util.function.Supplier;

public class AllPowerNodeTypes {
    private static final HashMap<ResourceLocation, PowerNodeType> MAP = new HashMap<>();
    public static final PowerNodeType JOINT = jumpPoint(new ResourceLocation("kuayue", "joint"), 256, 256);

    public static void register(PowerNodeType type) {
        MAP.put(type.getLocation(), type);
    }

    public static PowerNodeType jumpPoint(ResourceLocation location, float maxV, float maxC) {
        if (MAP.containsKey(location)) return MAP.get(location);
        PowerNodeType type = new PowerNodeType(location, maxV, maxC);
        MAP.put(location, type);
        return type;
    }

    public static boolean contains(PowerNodeType type) {
        return MAP.containsValue(type);
    }

    public static boolean contains(ResourceLocation location) {
        return MAP.containsKey(location);
    }

    public static PowerNodeType get(ResourceLocation location) {
        return MAP.getOrDefault(location, null);
    }
}
