package willow.train.kuayue.item.animate_controller.sensor;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import kasuga.lib.core.base.NbtSerializable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.logging.Level;

public class AnimateSensorType {

    public final ResourceLocation identifier;
    public final String displayName;
    public final float defaultCondition;
    private final SensorFunction sensor;

    public AnimateSensorType(ResourceLocation name, String displayName, float defaultCondition, SensorFunction sensor) {
        this.identifier = name;
        this.defaultCondition = defaultCondition;
        this.sensor = sensor;
        this.displayName = displayName;
    }

    public Component getDisplayComponent() {
        return Component.literal(this.displayName);
    }

    public float tick(AnimateSensor sensor, Level level, BlockState blockState, BlockPos blockPos,
                     @Nullable PlayerData playerData, @Nullable MovementContext movementContext,
                     boolean onTrain) {
        return this.sensor.tick(sensor, level, blockState, blockPos, playerData, movementContext, onTrain);
    }

    @FunctionalInterface
    public interface SensorFunction {
        float tick(AnimateSensor sensor, Level level, BlockState blockState, BlockPos blockPos,
                     @Nullable PlayerData playerData, @Nullable MovementContext movementContext,
                     boolean onTrain);
    }

    public record PlayerData(Player player, InteractionHand hand) {}
}
