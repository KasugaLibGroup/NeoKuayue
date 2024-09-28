package willow.train.kuayue.block.panels.base;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.utils.DirectionUtil;

public class TrainPanelShapes {

    public static final VoxelShape SOUTH_AABB = Block.box(0, 0, 15, 16, 16, 17);
    public static final VoxelShape EAST_AABB = Block.box(15, 0, 0, 17, 16, 16);
    public static final VoxelShape NORTH_AABB = Block.box(0, 0, -1, 16, 16, 1);
    public static final VoxelShape WEST_AABB = Block.box(-1, 0, 0, 1, 16, 16);

    public static final VoxelShape DOOR_SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    public static final VoxelShape DOOR_NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape DOOR_WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape DOOR_EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);

    public static final VoxelShape DOUBLE_DOOR_CLOSE_NORTH_AABB = Block.box(-16.0D, 0.0D, 0.0D, 32.0D, 20.0D, 5.0D);
    public static final VoxelShape DOUBLE_DOOR_CLOSE_SOUTH_AABB = Block.box(-16.0D, 0.0D, 11.0D, 32.0D, 20.0D, 16.0D);
    public static final VoxelShape DOUBLE_DOOR_CLOSE_EAST_AABB = Block.box(11.0D, 0.0D, -16.0D, 16.0D, 20.0D, 32.0D);
    public static final VoxelShape DOUBLE_DOOR_CLOSE_WEST_AABB = Block.box(0.0D, 0.0D, -16.0D, 5.0D, 20.0D, 32.0D);

    public static final VoxelShape DOUBLE_DOOR_OPEN_NORTH_AABB = Shapes.or(
            Block.box(-24.0D, 0.0D, 0.0D, -8.0D, 20.0D, 5.0D),
            Block.box(24.0D, 0.0D, 0.0D, 40.0D, 20.0D, 5.0D));
    public static final VoxelShape DOUBLE_DOOR_OPEN_SOUTH_AABB = Shapes.or(
            Block.box(-24.0D, 0.0D, 11.0D, -8.0D, 20.0D, 16.0D),
            Block.box(24.0D, 0.0D, 11.0D, 40.0D, 20.0D, 16.0D));
    public static final VoxelShape DOUBLE_DOOR_OPEN_EAST_AABB = Shapes.or(
            Block.box(11.0D, 0.0D, -24.0D, 16.0D, 20.0D, -8.0D),
            Block.box(11.0D, 0.0D, 24.0D, 16.0D, 20.0D, 40.0D));
    public static final VoxelShape DOUBLE_DOOR_OPEN_WEST_AABB = Shapes.or(
            Block.box(0.0D, 0.0D, -24.0D, 5.0D, 20.0D, -8.0D),
            Block.box(0.0D, 0.0D, 24.0D, 5.0D, 20.0D, 40.0D));

    public static final VoxelShape FLOOR = Block.box(0, 8, 0, 16, 16, 16);
    public static final VoxelShape CARPORT_CENTER = Block.box(0, 0, 0, 16, 8, 16);
    public static final VoxelShape EXTEND_CARPORT_CENTER_EAST = Block.box(0, 0, 0, 24, 8, 16);
    public static final VoxelShape EXTEND_CARPORT_CENTER_WEST = Block.box(-8, 0, 0, 16, 8, 16);
    public static final VoxelShape EXTEND_CARPORT_CENTER_SOUTH = Block.box(0, 0, 0, 16, 8, 24);
    public static final VoxelShape EXTEND_CARPORT_CENTER_NORTH = Block.box(0, 0, -8, 16, 8, 16);
    public static final VoxelShape LADDER_SOUTH_AABB =
            Shapes.or(
                    Block.box(0.5, 0, 0.5, 15, 1, 9),
                    Block.box(0.5, 6, 5.5, 15, 7, 12.5),
                    Block.box(0.5, 12, 10.5, 15, 13, 15.5),
                    Block.box(15.25, 13.75, 2.25, 16, 16, 16),
                    Block.box(15.25, 9.5, 12, 16, 11.25, 14.25),
                    Block.box(15.25, 4.25, 2.25, 16, 13.75, 12),
                    Block.box(15.25, 0, 1, 16, 2.25, 10.25),
                    Block.box(15.25, 2, 1.75, 16, 4.25, 11),
                    Block.box(15.25, 7.25, 12, 16, 9.5, 13.25),
                    Block.box(15.25, 11.25, 12, 16, 13.75, 15),
                    Block.box(13.5, 10, 1, 16, 16, 2.5),
                    Block.box(0, 10, 1, 2.5, 16, 2.5),
                    Block.box(0, 13.75, 2.25, 0.75, 16, 16),
                    Block.box(0, 4.25, 2.25, 0.75, 13.75, 12),
                    Block.box(0, 11.25, 12, 0.75, 13.75, 15),
                    Block.box(0, 2, 1.75, 0.75, 4.25, 11),
                    Block.box(0, 0, 1, 0.75, 2.25, 10.25),
                    Block.box(0, 7.25, 12, 0.75, 9.5, 13.25),
                    Block.box(0, 9.5, 12, 0.75, 11.25, 14.25));
    public static final VoxelShape LADDER_WEST_AABB =
            Shapes.or(
                    Block.box(7, 0, 0.5, 15.5, 1, 15),
                    Block.box(3.5, 6, 0.5, 10.5, 7, 15),
                    Block.box(0.5, 12, 0.5, 5.5, 13, 15),
                    Block.box(0, 13.75, 15.25, 13.75, 16, 16),
                    Block.box(1.75, 9.5, 15.25, 4, 11.25, 16),
                    Block.box(4, 4.25, 15.25, 13.75, 13.75, 16),
                    Block.box(5.75, 0, 15.25, 15, 2.25, 16),
                    Block.box(5, 2, 15.25, 14.25, 4.25, 16),
                    Block.box(2.75, 7.25, 15.25, 4, 9.5, 16),
                    Block.box(1, 11.25, 15.25, 4, 13.75, 16),
                    Block.box(13.5, 10, 13.5, 15, 16, 16),
                    Block.box(13.5, 10, 0, 15, 16, 2.5),
                    Block.box(0, 13.75, 0, 13.75, 16, 0.75),
                    Block.box(4, 4.25, 0, 13.75, 13.75, 0.75),
                    Block.box(1, 11.25, 0, 4, 13.75, 0.75),
                    Block.box(5, 2, 0, 14.25, 4.25, 0.75),
                    Block.box(5.75, 0, 0, 15, 2.25, 0.75),
                    Block.box(2.75, 7.25, 0, 4, 9.5, 0.75),
                    Block.box(1.75, 9.5, 0, 4, 11.25, 0.75));
    public static final VoxelShape LADDER_NORTH_AABB =
            Shapes.or(
                    Block.box(1, 0, 7, 15.5, 1, 15.5),
                    Block.box(1, 6, 3.5, 15.5, 7, 10.5),
                    Block.box(1, 12, 0.5, 15.5, 13, 5.5),
                    Block.box(0, 13.75, 0, 0.75, 16, 13.75),
                    Block.box(0, 9.5, 1.75, 0.75, 11.25, 4),
                    Block.box(0, 4.25, 4, 0.75, 13.75, 13.75),
                    Block.box(0, 0, 5.75, 0.75, 2.25, 15),
                    Block.box(0, 2, 5, 0.75, 4.25, 14.25),
                    Block.box(0, 7.25, 2.75, 0.75, 9.5, 4),
                    Block.box(0, 11.25, 1, 0.75, 13.75, 4),
                    Block.box(0, 10, 13.5, 2.5, 16, 15),
                    Block.box(13.5, 10, 13.5, 16, 16, 15),
                    Block.box(15.25, 13.75, 0, 16, 16, 13.75),
                    Block.box(15.25, 4.25, 4, 16, 13.75, 13.75),
                    Block.box(15.25, 11.25, 1, 16, 13.75, 4),
                    Block.box(15.25, 2, 5, 16, 4.25, 14.25),
                    Block.box(15.25, 0, 5.75, 16, 2.25, 15),
                    Block.box(15.25, 7.25, 2.75, 16, 9.5, 4),
                    Block.box(15.25, 9.5, 1.75, 16, 11.25, 4));
    public static final VoxelShape LADDER_EAST_AABB =
            Shapes.or(
                    Block.box(0.5, 0, 1, 9, 1, 15.5),
                    Block.box(5.5, 6, 1, 12.5, 7, 15.5),
                    Block.box(10.5, 12, 1, 15.5, 13, 15.5),
                    Block.box(2.25, 13.75, 0, 16, 16, 0.75),
                    Block.box(12, 9.5, 0, 14.25, 11.25, 0.75),
                    Block.box(2.25, 4.25, 0, 12, 13.75, 0.75),
                    Block.box(1, 0, 0, 10.25, 2.25, 0.75),
                    Block.box(1.75, 2, 0, 11, 4.25, 0.75),
                    Block.box(12, 7.25, 0, 13.25, 9.5, 0.75),
                    Block.box(12, 11.25, 0, 15, 13.75, 0.75),
                    Block.box(1, 10, 0, 2.5, 16, 2.5),
                    Block.box(1, 10, 13.5, 2.5, 16, 16),
                    Block.box(2.25, 13.75, 15.25, 16, 16, 16),
                    Block.box(2.25, 4.25, 15.25, 12, 13.75, 16),
                    Block.box(12, 11.25, 15.25, 15, 13.75, 16),
                    Block.box(1.75, 2, 15.25, 11, 4.25, 16),
                    Block.box(1, 0, 15.25, 10.25, 2.25, 16),
                    Block.box(12, 7.25, 15.25, 13.25, 9.5, 16),
                    Block.box(12, 9.5, 15.25, 14.25, 11.25, 16));

    public static final VoxelShape METER_LADDER_SOUTH_AABB =
            Shapes.or(
                    Block.box(1, 0, 15, 15, 8, 16),
                    Block.box(0, 0, 0, 1, 8, 16),
                    Block.box(15, 0, 0, 16, 8, 16),
                    Block.box(0, 0, 1, 15, 1, 15));

    public static final VoxelShape METER_LADDER_WEST_AABB =
            Shapes.or(
                    Block.box(0, 0, 1, 1, 8, 15),
                    Block.box(0, 0, 0, 16, 8, 1),
                    Block.box(0, 0, 15, 16, 8, 16),
                    Block.box(0, 0, 1, 15, 1, 15));

    public static final VoxelShape METER_LADDER_NORTH_AABB =
            Shapes.or(
                    Block.box(1, 0, 0, 15, 8, 1),
                    Block.box(0, 0, 0, 1, 8, 16),
                    Block.box(15, 0, 0, 16, 8, 16),
                    Block.box(1, 0, 0, 15, 1, 15));

    public static final VoxelShape METER_LADDER_EAST_AABB =
            Shapes.or(
                    Block.box(15, 0, 1, 16, 8, 15),
                    Block.box(0, 0, 0, 16, 8, 1),
                    Block.box(0, 0, 15, 16, 8, 16),
                    Block.box(1, 0, 0, 15, 1, 15));

    public static VoxelShape HALF_HEIGHT_TOP_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public static VoxelShape getShape(Direction direction) {
        return switch (direction) {
            case EAST -> EAST_AABB;
            case NORTH -> NORTH_AABB;
            case WEST -> WEST_AABB;
            case SOUTH -> SOUTH_AABB;
            default -> Shapes.block();
        };
    }

    public static VoxelShape getDoorShape(Direction direction, DoorHingeSide side, boolean open) {
        if (!open) {
            return getCloseDoorShape(direction);
        }
        return switch (direction) {
            case EAST -> side != DoorHingeSide.LEFT ? DOOR_NORTH_AABB : DOOR_SOUTH_AABB;
            case SOUTH -> side != DoorHingeSide.LEFT ? DOOR_EAST_AABB : DOOR_WEST_AABB;
            case WEST -> side != DoorHingeSide.LEFT ? DOOR_SOUTH_AABB : DOOR_NORTH_AABB;
            case NORTH -> side != DoorHingeSide.LEFT ? DOOR_WEST_AABB : DOOR_EAST_AABB;
            default -> Shapes.block();
        };
    }

    public static VoxelShape getDoubleDoorShape(Direction direction, boolean open) {
        if (!open) {
            return getDoubleDoorCloseShape(direction);
        }
        return switch (direction) {
            case EAST -> DOUBLE_DOOR_OPEN_EAST_AABB;
            case SOUTH -> DOUBLE_DOOR_OPEN_SOUTH_AABB;
            case WEST -> DOUBLE_DOOR_OPEN_WEST_AABB;
            case NORTH -> DOUBLE_DOOR_OPEN_NORTH_AABB;
            default -> Shapes.block();
        };
    }

    public static VoxelShape getDoubleDoorCloseShape(Direction direction) {
        return switch (direction) {
            case EAST -> DOUBLE_DOOR_CLOSE_EAST_AABB;
            case SOUTH -> DOUBLE_DOOR_CLOSE_SOUTH_AABB;
            case WEST -> DOUBLE_DOOR_CLOSE_WEST_AABB;
            case NORTH -> DOUBLE_DOOR_CLOSE_NORTH_AABB;
            default -> Shapes.block();
        };
    }

    public static VoxelShape getSlidingDoorShape(Direction direction, DoorHingeSide side, boolean open) {
        if (!open) return getCloseDoorShape(direction);
        return switch (direction) {
            case EAST -> DirectionUtil.sideMoveShape(DOOR_EAST_AABB, direction, 1, side == DoorHingeSide.RIGHT);
            case NORTH -> DirectionUtil.sideMoveShape(DOOR_NORTH_AABB, direction, 1, side == DoorHingeSide.RIGHT);
            case SOUTH -> DirectionUtil.sideMoveShape(DOOR_SOUTH_AABB, direction, 1, side == DoorHingeSide.RIGHT);
            case WEST -> DirectionUtil.sideMoveShape(DOOR_WEST_AABB, direction, 1, side == DoorHingeSide.RIGHT);
            default -> getCloseDoorShape(direction);
        };
    }

    public static VoxelShape getCloseDoorShape(Direction direction) {
        return switch (direction) {
            case EAST -> DOOR_EAST_AABB;
            case NORTH -> DOOR_NORTH_AABB;
            case SOUTH -> DOOR_SOUTH_AABB;
            case WEST -> DOOR_WEST_AABB;
            default -> Shapes.block();
        };
    }

    public static VoxelShape getLadderShape(BlockState state) {
        boolean open = state.getValue(BlockStateProperties.OPEN);
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (!open) return Shapes.block();
        return switch (direction) {
            case EAST -> LADDER_EAST_AABB;
            case WEST -> LADDER_WEST_AABB;
            case SOUTH -> LADDER_SOUTH_AABB;
            case NORTH -> LADDER_NORTH_AABB;
            default -> LADDER_EAST_AABB;
        };
    }

    public static VoxelShape getMeterLadderShape(BlockState state) {
        boolean open = state.getValue(BlockStateProperties.OPEN);
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (!open)
            return HALF_HEIGHT_TOP_AABB;
        return switch (direction) {
            case EAST -> METER_LADDER_EAST_AABB;
            case WEST -> METER_LADDER_WEST_AABB;
            case SOUTH -> METER_LADDER_SOUTH_AABB;
            case NORTH -> METER_LADDER_NORTH_AABB;
            default -> METER_LADDER_EAST_AABB;
        };
    }
}
