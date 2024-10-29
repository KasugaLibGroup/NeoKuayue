package willow.train.kuayue.block.seat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class M1SeatBlock extends YZSeatBlock {

    public static final BooleanProperty SEAT_OFFSET = BooleanProperty.create("seat_offset");
    public M1SeatBlock(@NotNull Properties settings, int seatSize, OffsetFunction offsetFunction) {
        super(settings, seatSize, offsetFunction);
        registerDefaultState(
                this.stateDefinition.any().setValue(SEAT_OFFSET, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!pState.getValue(SEAT_OFFSET))
            return Block.box(0, 0, 0, 16, 4, 16).move(0, -0.5, 0);

        return switch (pState.getValue(FACING)) {
            case EAST -> Block.box(0, 0, 8, 16, 4, 24).move(0, -0.5, 0);
            case WEST -> Block.box(0, 0, -8, 16, 4, 8).move(0, -0.5, 0);
            case NORTH -> Block.box(8, 0, 0, 24, 4, 16).move(0, -0.5, 0);
            case SOUTH -> Block.box(-8, 0, 0, 8, 4, 16).move(0, -0.5, 0);
            default -> Block.box(0, 0, 8, 16, 4, 24).move(0, -0.5, 0);
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(0, 0, 0, 0, 0, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> stateManager) {
        stateManager.add(SEAT_OFFSET);
        super.createBlockStateDefinition(stateManager);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {

        return super.getStateForPlacement(ctx).setValue(SEAT_OFFSET, false);
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if(!level.isClientSide()) {
            level.setBlock(pos,state.cycle(SEAT_OFFSET),3);
        }
        return InteractionResult.SUCCESS;
    }
}
