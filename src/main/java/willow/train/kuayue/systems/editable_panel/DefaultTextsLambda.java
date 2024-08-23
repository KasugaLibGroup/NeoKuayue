package willow.train.kuayue.systems.editable_panel;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface DefaultTextsLambda {

    CompoundTag defaultTextComponent (BlockEntity blockEntity, BlockState blockState, CompoundTag nbt);
}
