package willow.train.kuayue.block.seat;

import com.simibubi.create.content.contraptions.actors.seat.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class M1SeatBlock extends YZSeatBlock {

    public static final BooleanProperty SEAT_OFFSET = BooleanProperty.create("seat_offset");
    public M1SeatBlock(@NotNull Properties settings, int seatSize, OffsetFunction offsetFunction) {
        super(settings, DyeColor.GREEN, seatSize, offsetFunction, false);
        registerDefaultState(
                this.stateDefinition.any().setValue(SEAT_OFFSET, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!pState.getValue(SEAT_OFFSET)) {
            return Block.box(0, 0, 0, 16, 10, 16).move(0, -0.5, 0);
            // return Shapes.block();
        }

        return switch (pState.getValue(FACING)) {
            case EAST -> Block.box(0, 0, 8, 16, 10, 24).move(0, -0.5, 0);
            case WEST -> Block.box(0, 0, -8, 16, 10, 8).move(0, -0.5, 0);
            case NORTH -> Block.box(8, 0, 0, 24, 10, 16).move(0, -0.5, 0);
            case SOUTH -> Block.box(-8, 0, 0, 8, 10, 16).move(0, -0.5, 0);
            default -> Block.box(0, 0, 8, 16, 10, 24).move(0, -0.5, 0);
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        // return Block.box(0, 0, 0, 0, 0, 0);
        return Block.box(0, -8, 0, 16, -6, 16);
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

    public void sitDown(
            Level world, BlockPos pos, Entity entity, Vec3 offset) {
        if (world.isClientSide) return;
        M1SeatEntity seat = new M1SeatEntity(world, pos);
        seat.setPos(
                (float) pos.getX() + offset.x() + .5,
                (float) pos.getY() + offset.y(),
                (float) pos.getZ() + offset.z() + .5);
        world.addFreshEntity(seat);
        entity.startRiding(seat, true);
        if (entity instanceof TamableAnimal ta) ta.setInSittingPose(true);
    }
}
