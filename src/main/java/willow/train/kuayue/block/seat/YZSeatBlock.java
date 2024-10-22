package willow.train.kuayue.block.seat;

import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import com.simibubi.create.content.contraptions.actors.seat.SeatEntity;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.utils.DirectionUtil;

import java.util.List;

public class YZSeatBlock extends SeatBlock implements IWrenchable, IBE<SeatBlockEntity> {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape BASE_AABB = Block.box(0, 4, 0, 16, 8, 16);
    // protected static final VoxelShape SN_AABB = Block.box(0, 8, 6, 16, 23, 10);
    // protected static final VoxelShape WE_AABB = Block.box(6, 8, 0, 10, 23, 16);
    public final int seatSize; public final OffsetFunction offsetFunction;

    public YZSeatBlock(Properties properties, DyeColor color, int seatSize, OffsetFunction offsetFunction) {
        super(properties, color);
        this.seatSize = seatSize;
        this.offsetFunction = offsetFunction;
    }

    public YZSeatBlock(@NotNull Properties settings, int seatSize, OffsetFunction offsetFunction) {
        this(settings.noOcclusion(), DyeColor.BLUE, seatSize, offsetFunction);
        registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(
            BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case EAST, WEST -> weShape();
            default -> snShape();
        };
    }

    @Override
    protected void createBlockStateDefinition(
            @NotNull StateDefinition.Builder<Block, BlockState> stateManager) {
        super.createBlockStateDefinition(stateManager.add(FACING));
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, false);
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    public VoxelShape snShape() {
        // return Shapes.join(BASE_AABB, SN_AABB, BooleanOp.OR);
        return BASE_AABB;
    }

    public VoxelShape weShape() {
        // return Shapes.join(BASE_AABB, WE_AABB, BooleanOp.OR);
        return BASE_AABB;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(
            BlockState state,
            Level world,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult p_225533_6_) {

        if (player.isShiftKeyDown()) return InteractionResult.PASS;

        if (world.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof SeatBlockEntity)) return InteractionResult.PASS;
        SeatBlockEntity seat = (SeatBlockEntity) blockEntity;

        int index = seat.getAnEmptySeat();
        if (index == -1) return InteractionResult.PASS;
        List<SeatEntity> seatEntities =
                world.getEntitiesOfClass(SeatEntity.class,
                        AABB.ofSize(DirectionUtil.centerOf(pos), 1, 1, 1)
                );

        if (seatEntities.isEmpty()) {
            seat.clearAllSeat();
        }

        SeatEntity originalEntity = null;
        for (SeatEntity seatEntity : seatEntities) {
            if (seatEntity.getPassengers().contains(player)) {
                originalEntity = seatEntity;
                break;
            }
        }

        if (originalEntity == null) {
            resolveSitDown(world, state, pos, player, index);
            seat.fillPassenger(index, player.getUUID());
            return InteractionResult.SUCCESS;
        }
        Vec3 offset = originalEntity.getPosition(0).subtract(DirectionUtil.toVec3(pos).add(.5, 0, .5));
        int orgIndex = getIndex(state, offset);
        if (orgIndex == -1) return InteractionResult.PASS;
        index = seat.getNextEmptySeat(orgIndex);
        resolveSitDown(world, state, pos, player, index);
        seat.fillPassenger(index, player.getUUID());
        seat.removePassenger(orgIndex);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void fallOn(Level p_152426_, BlockState p_152427_, BlockPos p_152428_, Entity p_152429_, float p_152430_) {
        super.fallOn(p_152426_, p_152427_, p_152428_, p_152429_, p_152430_);
    }

    public void resolveSitDown(
            Level level, BlockState state, BlockPos pos, Entity entity, int index) {
        Direction direction = state.getValue(FACING);
        sitDown(level, pos, entity, getOffset(state, index));
    }

    public Vec3 getOffset(BlockState state, int index) {
        return offsetFunction.getOffset(state, index);
    }

    public int getIndex(BlockState state, Vec3 position) {
        for(int i = 0; i < getSeatSize(); i++) {
            if (getOffset(state, i).equals(position))
                return i;
        }
        return -1;
    }

    public void sitDown(
            Level world, BlockPos pos, Entity entity, Vec3 offset) {
        if (world.isClientSide) return;
        SeatEntity seat = new SeatEntity(world, pos);
        seat.setPos(
                (float) pos.getX() + offset.x() + .5,
                (float) pos.getY() + offset.y(),
                (float) pos.getZ() + offset.z() + .5);
        world.addFreshEntity(seat);
        entity.startRiding(seat, true);
        if (entity instanceof TamableAnimal ta) ta.setInSittingPose(true);
    }

    public int getSeatSize() {
        return seatSize;
    }

    @Override
    public Class<SeatBlockEntity> getBlockEntityClass() {
        return SeatBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SeatBlockEntity> getBlockEntityType() {
        return AllBlocks.SEAT_BLOCK_ENTITY.getType();
    }

    public interface OffsetFunction {
        Vec3 getOffset(BlockState state, int index);
    }
}
