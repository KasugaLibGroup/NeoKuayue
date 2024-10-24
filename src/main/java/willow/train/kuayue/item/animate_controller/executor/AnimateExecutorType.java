package willow.train.kuayue.item.animate_controller.executor;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import kasuga.lib.core.base.NbtSerializable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.item.animate_controller.sensor.AnimateSensorType;

import javax.annotation.Nullable;
import java.util.logging.Level;

public class AnimateExecutorType {

    public final ResourceLocation identifier;
    public final String displayName;
    public final boolean defaultCondition;
    private final ExecutorFunction executor;

    public AnimateExecutorType(ResourceLocation identifier, String displayName,
                               boolean defaultCondition, ExecutorFunction executor) {
        this.identifier = identifier;
        this.displayName = displayName;
        this.defaultCondition = defaultCondition;
        this.executor = executor;
    }

    public void execute(AnimateExecutor executor, Level level, AnimateSensorType.PlayerData playerData,
                        MovementContext movementContext) {
        this.executor.execute(executor, level, playerData, movementContext, movementContext != null);
    }

    @FunctionalInterface
    public interface ExecutorFunction {
        void execute(AnimateExecutor executor, Level level, @Nullable AnimateSensorType.PlayerData playerData,
                     @Nullable MovementContext movementContext, boolean onTrain);
    }
}
