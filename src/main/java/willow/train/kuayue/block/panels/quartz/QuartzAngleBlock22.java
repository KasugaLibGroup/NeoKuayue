package willow.train.kuayue.block.panels.quartz;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

public class QuartzAngleBlock22 extends TrainPanelBlock {

    private static VoxelShape shape;
    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape NORTH_AABB =
            Shapes.or(
                    Block.box(0.05, 0, 0, 15.95, 2, 6.75),
                    Block.box(0.05, 2, 0, 15.95, 4, 6),
                    Block.box(0.05, 4, 0, 15.95, 5, 5.5),
                    Block.box(0.05, 5, 0, 15.95, 7, 4.75),
                    Block.box(0.05, 7, 0, 15.95, 9, 3.75),
                    Block.box(0.05, 9, 0, 15.95, 11, 3),
                    Block.box(0.05, 11, 0, 15.95, 13, 2.25),
                    Block.box(0.05, 13, 0, 15.95, 15, 1.25),
                    Block.box(0.05, 15, 0, 15.95, 16, 1));
    protected static final VoxelShape WEST_AABB =
            Shapes.or(
                    Block.box(0, 0, 0.05, 6.75, 2, 15.95),
                    Block.box(0, 2, 0.05, 6, 4, 15.95),
                    Block.box(0, 4, 0.05, 5.5, 5, 15.95),
                    Block.box(0, 5, 0.05, 4.75, 7, 15.95),
                    Block.box(0, 7, 0.05, 3.75, 9, 15.95),
                    Block.box(0, 9, 0.05, 3, 11, 15.95),
                    Block.box(0, 11, 0.05, 2.25, 13, 15.95),
                    Block.box(0, 13, 0.05, 1.25, 15, 15.95),
                    Block.box(0, 15, 0.05, 1, 16, 15.95));
    protected static final VoxelShape EAST_AABB =
            Shapes.or(
                    Block.box(9.25, 0, 0.05000000000000071, 16, 2, 15.95),
                    Block.box(10, 2, 0.05, 16, 4, 15.95),
                    Block.box(10.5, 4, 0.05, 16, 5, 15.95),
                    Block.box(11.25, 5, 0.05, 16, 7, 15.95),
                    Block.box(12.25, 7, 0.05, 16, 9, 15.95),
                    Block.box(13, 9, 0.050, 16, 11, 15.95),
                    Block.box(13.75, 11, 0.050, 16, 13, 15.95),
                    Block.box(14.75, 13, 0.050, 16, 15, 15.95),
                    Block.box(15, 15, 0.050, 16, 16, 15.95));
    protected static final VoxelShape SOUTH_AABB =
            Shapes.or(
                    Block.box(0.05, 0, 9.25, 15.95, 2, 16),
                    Block.box(0.05, 2, 10, 15.95, 4, 16),
                    Block.box(0.05, 4, 10.5, 15.95, 5, 16),
                    Block.box(0.05, 5, 11.25, 15.95, 7, 16),
                    Block.box(0.05, 7, 12.25, 15.95, 9, 16),
                    Block.box(0.05, 9, 13, 15.95, 11, 16),
                    Block.box(0.05, 11, 13.75, 15.95, 13, 16),
                    Block.box(0.05, 13, 14.75, 15.95, 15, 16),
                    Block.box(0.05, 15, 15, 15.95, 16, 16));

    public QuartzAngleBlock22(Properties properties) {
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
