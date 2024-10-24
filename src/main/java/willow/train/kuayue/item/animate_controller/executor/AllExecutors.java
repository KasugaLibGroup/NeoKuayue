package willow.train.kuayue.item.animate_controller.executor;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;

public class AllExecutors {
    public static final HashMap<ResourceLocation, AnimateExecutorType> EXECUTORS = new HashMap<>();

    public static AnimateExecutorType register(ResourceLocation location, String displayName,
                                           boolean defaultCondition, AnimateExecutorType.ExecutorFunction executor) {
        AnimateExecutorType executor1 = new AnimateExecutorType(location, displayName, defaultCondition, executor);
        EXECUTORS.put(location, executor1);
        return executor1;
    }

    public static @Nullable AnimateExecutorType getExecutorType(ResourceLocation location) {
        return EXECUTORS.getOrDefault(location, null);
    }
}
