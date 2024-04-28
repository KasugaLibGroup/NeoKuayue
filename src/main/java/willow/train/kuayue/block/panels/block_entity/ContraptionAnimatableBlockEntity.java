package willow.train.kuayue.block.panels.block_entity;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ContraptionAnimatableBlockEntity {
    void onContraptionUse(Level level, Player player, InteractionHand hand, BlockPos localPos, AbstractContraptionEntity contraptionEntity, BlockState state, CompoundTag externalData);
    void onNormalUse(Level level, Player player, InteractionHand hand, BlockPos worldPos, BlockState state, CompoundTag externalData);
}
