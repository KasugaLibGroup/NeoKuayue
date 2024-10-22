package willow.train.kuayue.block.seat;

import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class RZSeatBlock extends SeatBlock implements IWrenchable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final VoxelShape northShape;
    private final VoxelShape southShape;
    private final VoxelShape westShape;
    private final VoxelShape eastShape;

    public RZSeatBlock(@NotNull Properties settings) {
        super(settings.noOcclusion(), DyeColor.BLUE);
        // Preventing lag
        this.northShape = makeNorthShape();
        this.southShape = makeSouthShape();
        this.westShape = makeWestShape();
        this.eastShape = makeEastShape();
        registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(
            @NotNull StateDefinition.Builder<Block, BlockState> stateManager) {
        super.createBlockStateDefinition(stateManager.add(FACING));
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, false);
    }

    @Override
    public @NotNull VoxelShape getShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext ctx) {
        return switch (state.getValue(FACING)) {
            default -> Shapes.block();
            case NORTH -> northShape;
            case SOUTH -> southShape;
            case WEST -> westShape;
            case EAST -> eastShape;
        };
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(
            @NotNull BlockState state,
            @NotNull BlockGetter world,
            @NotNull BlockPos pos,
            @NotNull CollisionContext ctx) {
        return getShape(state, world, pos, ctx);
    }

    // Generated using Blockbench
    private static VoxelShape makeNorthShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.0625, 0.375, 0.875, 0.25, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.1875, 0.875, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.0625, 0.375, 0.1875, 0.25, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.125, 0, 0.1875, 0.1875, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.4375, 0.8125, 0.0625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.25, 0, 0.875, 0.5, 0.75));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.375, 0.578125, 0.875, 1.375, 0.828125));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.1875, 0.125, 0.6875, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.03125, 0.125, 0.4375, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0, 0.6875, 0.0625, 0.125, 0.8125, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.6875, 0.0625, 1, 0.8125, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.25, 0.1875, 1, 0.6875, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.25, 0.03125, 1, 0.4375, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.15625, 0.6875, 0.8125, 0.84375, 1.125, 0.84375));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0.5625, 0.80625, 0.15625, 1.078125, 0.8375));
        shape = Shapes.or(shape, Shapes.box(0.84375, 0.5625, 0.80625, 0.90625, 1.078125, 0.8375));
        return shape;
    }

    // Generated using Blockbench
    private static VoxelShape makeSouthShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.125, 0.0625, 0.375, 0.1875, 0.25, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.125, 0, 0.1875, 0.1875, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.0625, 0.375, 0.875, 0.25, 0.625));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0, 0.1875, 0.875, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.4375, 0.8125, 0.0625, 0.5625));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.25, 0.25, 0.875, 0.5, 1));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.375, 0.171875, 0.875, 1.375, 0.421875));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.25, 0.1875, 1, 0.6875, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.25, 0.8125, 1, 0.4375, 0.96875));
        shape = Shapes.or(shape, Shapes.box(0.875, 0.6875, 0.1875, 1, 0.8125, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0, 0.6875, 0.1875, 0.125, 0.8125, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.1875, 0.125, 0.6875, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.8125, 0.125, 0.4375, 0.96875));
        shape = Shapes.or(shape, Shapes.box(0.15625, 0.6875, 0.15625, 0.84375, 1.125, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.84375, 0.5625, 0.1625, 0.90625, 1.078125, 0.19375));
        shape = Shapes.or(shape, Shapes.box(0.09375, 0.5625, 0.1625, 0.15625, 1.078125, 0.19375));
        return shape;
    }

    // Generate using Blockbench
    private static VoxelShape makeEastShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.375, 0.0625, 0.8125, 0.625, 0.25, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.8125, 0.8125, 0.0625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.375, 0.0625, 0.125, 0.625, 0.25, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.125, 0.8125, 0.0625, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.1875, 0.5625, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.25, 0.25, 0.125, 1, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.171875, 0.375, 0.125, 0.421875, 1.375, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.25, 0, 0.8125, 0.6875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.25, 0, 0.96875, 0.4375, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.6875, 0, 0.9375, 0.8125, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.6875, 0.875, 0.9375, 0.8125, 1));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.25, 0.875, 0.8125, 0.6875, 1));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.25, 0.875, 0.96875, 0.4375, 1));
        shape = Shapes.or(shape, Shapes.box(0.15625, 0.6875, 0.15625, 0.1875, 1.125, 0.84375));
        shape = Shapes.or(shape, Shapes.box(0.1625, 0.5625, 0.09375, 0.19375, 1.078125, 0.15625));
        shape = Shapes.or(shape, Shapes.box(0.1625, 0.5625, 0.84375, 0.19375, 1.078125, 0.90625));
        return shape;
    }

    // Generate using Blockbench
    private static VoxelShape makeWestShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.375, 0.0625, 0.125, 0.625, 0.25, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.125, 0.8125, 0.0625, 0.1875));
        shape = Shapes.or(shape, Shapes.box(0.375, 0.0625, 0.8125, 0.625, 0.25, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0, 0.8125, 0.8125, 0.0625, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.4375, 0, 0.1875, 0.5625, 0.0625, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.125, 0.75, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.578125, 0.375, 0.125, 0.828125, 1.375, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.25, 0.875, 0.8125, 0.6875, 1));
        shape = Shapes.or(shape, Shapes.box(0.03125, 0.25, 0.875, 0.1875, 0.4375, 1));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.6875, 0.875, 0.8125, 0.8125, 1));
        shape = Shapes.or(shape, Shapes.box(0.0625, 0.6875, 0, 0.8125, 0.8125, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.1875, 0.25, 0, 0.8125, 0.6875, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.03125, 0.25, 0, 0.1875, 0.4375, 0.125));
        shape = Shapes.or(shape, Shapes.box(0.8125, 0.6875, 0.15625, 0.84375, 1.125, 0.84375));
        shape = Shapes.or(shape, Shapes.box(0.80625, 0.5625, 0.84375, 0.8375, 1.078125, 0.90625));
        shape = Shapes.or(shape, Shapes.box(0.80625, 0.5625, 0.09375, 0.8375, 1.078125, 0.15625));
        return shape;
    }
}
