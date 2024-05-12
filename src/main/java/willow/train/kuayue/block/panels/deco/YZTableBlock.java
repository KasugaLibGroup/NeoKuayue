package willow.train.kuayue.block.panels.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;

public class YZTableBlock extends TrainPanelBlock {

    VoxelShape N_AABB = Block.box(3, 14, 0, 13, 15, 8);
    VoxelShape S_AABB = Block.box(3, 14, 8, 13, 15, 16);
    VoxelShape E_AABB = Block.box(8, 14, 3, 16, 15, 13);
    VoxelShape W_AABB = Block.box(0, 14, 3, 8, 15, 13);

    public YZTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case SOUTH -> S_AABB;
            case EAST -> E_AABB;
            case WEST -> W_AABB;
            default -> N_AABB;
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
