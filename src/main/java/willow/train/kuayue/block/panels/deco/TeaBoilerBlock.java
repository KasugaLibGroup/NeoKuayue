package willow.train.kuayue.block.panels.deco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.TrainPanelBlock;

public class TeaBoilerBlock extends TrainPanelBlock {

    public static final VoxelShape BOILER_NORTH_AABB = Block.box(5, 0, 8, 16, 20, 16);
    public static final VoxelShape BOILER_SOUTH_AABB = Block.box(0, 0, 0, 11, 20, 8);
    public static final VoxelShape BOILER_WEST_AABB = Block.box(8, 0, 0, 16, 20, 11);
    public static final VoxelShape BOILER_EAST_AABB = Block.box(0, 0, 5, 8, 20, 16);

    public TeaBoilerBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos) {
        super(pProperties, beginPos, endPos);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> BOILER_NORTH_AABB;
            case SOUTH -> BOILER_SOUTH_AABB;
            case WEST -> BOILER_WEST_AABB;
            case EAST -> BOILER_EAST_AABB;
            default -> BOILER_NORTH_AABB;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
