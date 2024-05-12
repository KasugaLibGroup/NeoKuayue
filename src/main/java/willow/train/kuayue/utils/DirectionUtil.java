package willow.train.kuayue.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DirectionUtil {

    public static BlockPos left(BlockPos pos, Direction direction, int distance) {
        return switch (direction) {
            case EAST -> new BlockPos(pos.getX(), pos.getY(), pos.getZ() + distance);
            case NORTH -> new BlockPos(pos.getX() + distance, pos.getY(), pos.getZ());
            case WEST -> new BlockPos(pos.getX(), pos.getY(), pos.getZ() - distance);
            case SOUTH -> new BlockPos(pos.getX() - distance, pos.getY(), pos.getZ());
            default -> pos;
        };
    }

    public static BlockPos right(BlockPos pos, Direction direction, int distance) {
        return switch (direction) {
            case EAST -> new BlockPos(pos.getX(), pos.getY(), pos.getZ() - distance);
            case NORTH -> new BlockPos(pos.getX() - distance, pos.getY(), pos.getZ());
            case WEST -> new BlockPos(pos.getX(), pos.getY(), pos.getZ() + distance);
            case SOUTH -> new BlockPos(pos.getX() + distance, pos.getY(), pos.getZ());
            default -> pos;
        };
    }

    public static VoxelShape leftMoveShape(VoxelShape shape, Direction direction, int distance) {
        return switch (direction) {
            case EAST -> shape.move(0, 0, distance);
            case NORTH -> shape.move(distance, 0, 0);
            case WEST -> shape.move(0, 0, - distance);
            case SOUTH -> shape.move(- distance, 0, 0);
            default -> shape;
        };
    }

    public static VoxelShape rightMoveShape(VoxelShape shape, Direction direction, int distance) {
        return switch (direction) {
            case EAST -> shape.move(0, 0, - distance);
            case NORTH -> shape.move(- distance, 0, 0);
            case WEST -> shape.move(0, 0, distance);
            case SOUTH -> shape.move(distance, 0, 0);
            default -> shape;
        };
    }

    public static VoxelShape sideMoveShape(VoxelShape shape, Direction direction, int distance, boolean left) {
        if (left) {
            return leftMoveShape(shape, direction, distance);
        }
        return rightMoveShape(shape, direction, distance);
    }

    public static BlockPos rotate(BlockPos pos, Direction from, Direction to) {
        return pos.rotate(getRotation(from, to));
    }

    public static Rotation getRotation(Direction from, Direction to) {
        float deg = (to.toYRot() - from.toYRot()) % 360;
        if (deg < 0) deg += 360;

        return switch ((int) deg) {
            case 90 -> Rotation.CLOCKWISE_90;
            case 180 -> Rotation.CLOCKWISE_180;
            case 270 -> Rotation.COUNTERCLOCKWISE_90;
            default -> Rotation.NONE;
        };
    }

    public static Vec3 centerOf(BlockPos pos) {
        return new Vec3(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);
    }

    public static Vec3 toVec3(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }
}
