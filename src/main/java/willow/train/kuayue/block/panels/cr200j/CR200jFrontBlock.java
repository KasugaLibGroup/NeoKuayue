package willow.train.kuayue.block.panels.cr200j;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import willow.train.kuayue.block.panels.block_entity.CR200jFrontBlockEntity;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.initial.panel.CR200JPanel;

import java.util.List;

public class CR200jFrontBlock extends HorizontalKineticBlock implements IBE<CR200jFrontBlockEntity> {
    public CR200jFrontBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING).getAxis();
    }

    @Override
    public Class<CR200jFrontBlockEntity> getBlockEntityClass() {
        return CR200jFrontBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends CR200jFrontBlockEntity> getBlockEntityType() {
        return (BlockEntityType<? extends CR200jFrontBlockEntity>)
                CR200JPanel.CR200J_FRONT_BLOCK.getBlockEntityReg().getType();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.getBlockEntity(pPos) instanceof CR200jFrontBlockEntity entity)
            entity.onNormalUse(pLevel, pPlayer, pHand, pPos, pState, new CompoundTag());
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
