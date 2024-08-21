package willow.train.kuayue.catenary.constants;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.catenary.types.CatenaryLineType;

import java.util.HashMap;

public class AllCatenaryLineTypes {

    private static final HashMap<ResourceLocation, CatenaryLineType> MAP = new HashMap<>();

    public static CatenaryLineType register(ResourceLocation name, float maxVoltage, float maxCurrent, float maxLength) {
        CatenaryLineType type = new CatenaryLineType(name, maxVoltage, maxCurrent, maxLength);
        MAP.put(name, type);
        return type;
    }

    public static boolean contains(ResourceLocation location) {
        return MAP.containsKey(location);
    }

    public static CatenaryLineType getType(ResourceLocation location) {
        return MAP.getOrDefault(location, null);
    }
}
