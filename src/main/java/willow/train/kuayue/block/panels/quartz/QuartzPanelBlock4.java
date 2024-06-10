package willow.train.kuayue.block.panels.quartz;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

public class QuartzPanelBlock4 extends TrainPanelBlock {

    protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 12, 16, 16, 16);
    protected static final VoxelShape WEST_AABB = Block.box(0, 0.0D, 0.0D, 4, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4);
    protected static final VoxelShape EAST_AABB = Block.box(12, 0.0D, 0D, 16.0D, 16.0D, 16.0D);

    public QuartzPanelBlock4(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(
            BlockState state,
            BlockGetter blockGetter,
            BlockPos blockPos,
            CollisionContext collisionContext) {
        switch ((Direction) state.getValue(FACING)) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
            default:
                return EAST_AABB;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.getShape(pState.getValue(FACING));
    }
}
