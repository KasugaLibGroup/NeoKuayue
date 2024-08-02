package willow.train.kuayue.block.structure.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PlatformWallBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final VoxelShape PLATFORM_WALL_NORTH_AABB = Block.box(0.0D, 0.0D, 3.0D, 16.0D, 20.0D, 5.0D);
    public static final VoxelShape PLATFORM_WALL_SOUTH_AABB = Block.box(0.0D, 0.0D, 11.0D, 16.0D, 20.0D, 13.0D);
    public static final VoxelShape PLATFORM_WALL_EAST_AABB = Block.box(11.0D, 0.0D, 0.0D, 13.0D, 20.0D, 16.0D);
    public static final VoxelShape PLATFORM_WALL_WEST_AABB = Block.box(3.0D, 0.0D, 0.0D, 5.0D, 20.0D, 16.0D);

    public PlatformWallBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return getPlatformWallShape(pState.getValue(FACING).getOpposite());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return getShape(pState, blockGetter, pos, context);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected VoxelShape getPlatformWallShape(Direction direction) {
        return switch (direction) {
            case EAST -> PLATFORM_WALL_EAST_AABB;
            case SOUTH -> PLATFORM_WALL_SOUTH_AABB;
            case WEST -> PLATFORM_WALL_WEST_AABB;
            case NORTH -> PLATFORM_WALL_NORTH_AABB;
            default -> Shapes.block();
        };
    }
}
