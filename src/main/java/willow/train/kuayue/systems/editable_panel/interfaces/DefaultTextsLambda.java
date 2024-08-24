package willow.train.kuayue.systems.editable_panel.interfaces;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface DefaultTextsLambda {

    void defaultTextComponent (BlockEntity blockEntity, BlockState blockState, CompoundTag nbt);
}
