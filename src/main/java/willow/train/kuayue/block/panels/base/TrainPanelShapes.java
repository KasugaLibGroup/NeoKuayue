package willow.train.kuayue.block.panels.base;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.utils.DirectionUtil;

public class TrainPanelShapes {

    protected static final VoxelShape SOUTH_AABB = Block.box(0, 0, 15, 16, 16, 17);
    protected static final VoxelShape EAST_AABB = Block.box(15, 0, 0, 17, 16, 16);
    protected static final VoxelShape NORTH_AABB = Block.box(0, 0, -1, 16, 16, 1);
    protected static final VoxelShape WEST_AABB = Block.box(-1, 0, 0, 1, 16, 16);

    protected static final VoxelShape DOOR_SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
    protected static final VoxelShape DOOR_NORTH_AABB = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape DOOR_WEST_AABB = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape DOOR_EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
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

    public static VoxelShape getSlidingDoorShape(Direction direction, DoorHingeSide side, boolean open) {
        if (!open) return getCloseDoorShape(direction);
        return switch (direction) {
            case EAST -> DirectionUtil.sideMoveShape(DOOR_EAST_AABB, direction, 1, side == DoorHingeSide.LEFT);
            case NORTH -> DirectionUtil.sideMoveShape(DOOR_NORTH_AABB, direction, 1, side == DoorHingeSide.LEFT);
            case SOUTH -> DirectionUtil.sideMoveShape(DOOR_SOUTH_AABB, direction, 1, side == DoorHingeSide.LEFT);
            case WEST -> DirectionUtil.sideMoveShape(DOOR_WEST_AABB, direction, 1, side == DoorHingeSide.LEFT);
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
}
