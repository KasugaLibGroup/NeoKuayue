package willow.train.kuayue.block.panels.base;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class EndFaceShapes {

    protected static final VoxelShape KT_NORTH_AABB =
            Shapes.or(
                    Block.box(-16, -16, -1, 32, 21, 1),
                    Block.box(-1, 17.75, -7.975, 17, 19, -1),
                    Block.box(15.2, -14, -7.975, 17.2, 18, -1),
                    Block.box(0, -16, -7.7, 2, -11, -1.25),
                    Block.box(-1.2, -14, -7.975, 0.8, 18, -1),
                    Block.box(-1, -16, -7.75, 17, -14, -6.15),
                    Block.box(-1, -16, -6.375, 17, -14, 1),
                    Block.box(1, 18, -1, 15, 21, 1),
                    Block.box(14, -16, -7.7, 16, -11, -1.25));
    protected static final VoxelShape KT_SOUTH_AABB =
            Shapes.or(
                    Block.box(-16, -16, 15, 32, 21, 17),
                    Block.box(-1, 17.75, 17, 17, 19, 23.975),
                    Block.box(-1.2, -14, 17, 0.8, 18, 23.975),
                    Block.box(14, -16, 17.25, 16, -11, 23.7),
                    Block.box(15.2, -14, 17, 17.2, 18, 23.975),
                    Block.box(-1, -16, 22.15, 17, -14, 23.75),
                    Block.box(-1, -16, 15, 17, -14, 22.375),
                    Block.box(1, 18, 15, 15, 21, 17),
                    Block.box(0, -16, 17.25, 2, -11, 23.7));
    protected static final VoxelShape KT_EAST_AABB =
            Shapes.or(
                    Block.box(15, -16, -16, 17, 21, 32),
                    Block.box(17, 17.75, -1, 23.975, 19, 17),
                    Block.box(17, -14, 15.2, 23.975, 18, 17.2),
                    Block.box(17.25, -16, 0, 23.7, -11, 2),
                    Block.box(17, -14, -1.2, 23.975, 18, 0.8),
                    Block.box(22.15, -16, -1, 23.75, -14, 17),
                    Block.box(15, -16, -1, 22.375, -14, 17),
                    Block.box(15, 18, 1, 17, 21, 15),
                    Block.box(17.25, -16, 14, 23.7, -11, 16));
    protected static final VoxelShape KT_WEST_AABB =
            Shapes.or(
                    Block.box(-1, -16, -16, 1, 21, 32),
                    Block.box(-7.975, 17.75, -1, -1, 19, 17),
                    Block.box(-7.975, -14, -1.2, -1, 18, 0.8),
                    Block.box(-7.699999999999999, -16, 14, -1.25, -11, 16),
                    Block.box(-7.975, -14, 15.2, -1, 18, 17.2),
                    Block.box(-7.75, -16, -1, -6.15, -14, 17),
                    Block.box(-6.375, -16, -1, 1, -14, 17),
                    Block.box(-1, 18, 1, 1, 21, 15),
                    Block.box(-7.7, -16, 0, -1.25, -11, 2));

    protected static final VoxelShape KT_NORTH_AABB_OPEN =
            Shapes.or(
                    Block.box(15, -16, -1, 32, 21, 1),
                    Block.box(-16, -16, -1, 1, 21, 1),
                    Block.box(1, 18, -1, 15, 21, 1),
                    Block.box(-1, 17.75, -7.9750, 17, 19, -1),
                    Block.box(15.2, -14, -7.9750, 17.2, 18, -1),
                    Block.box(0, -16, -7.7, 2, -11, -1.25),
                    Block.box(-1.2, -14, -7.975, 0.8, 18, -1),
                    Block.box(-1, -16, -7.75, 17, -14, -6.15),
                    Block.box(-1, -16, -6.375, 17, -14, 1),
                    Block.box(14, -16, -7.7, 16, -11, -1.25),
                    Block.box(-6, -14, 1, 1, 1, 2),
                    Block.box(-4.75, 15, 1, -0.25, 18, 2),
                    Block.box(-0.25, 1, 1, 1, 18, 2),
                    Block.box(-6, 1, 1, -4.75, 18, 2),
                    Block.box(15, -14, 1, 22, 1, 2),
                    Block.box(16.25, 15, 1, 20.75, 18, 2),
                    Block.box(15, 1, 1, 16.25, 18, 2),
                    Block.box(20.75, 1, 1, 22, 18, 2));
    protected static final VoxelShape KT_WEST_AABB_OPEN =
            Shapes.or(
                    Block.box(-1, -16, -16, 1, 21, 1),
                    Block.box(-1, -16, 15, 1, 21, 32),
                    Block.box(-1, 18, 1, 1, 21, 15),
                    Block.box(-7.975, 17.75, -1, -1, 19, 17),
                    Block.box(-7.975, -14, -1.2, -1, 18, 0.80),
                    Block.box(-7.7, -16, 14, -1.25, -11, 16),
                    Block.box(-7.975, -14, 15.2, -1, 18, 17.2),
                    Block.box(-7.75, -16, -1, -6.15, -14, 17),
                    Block.box(-6.375, -16, -1, 1, -14, 17),
                    Block.box(-7.7, -16, 0, -1.25, -11, 2),
                    Block.box(1, -14, 15, 2, 1, 22),
                    Block.box(1, 15, 16.25, 2, 18, 20.75),
                    Block.box(1, 1, 15, 2, 18, 16.25),
                    Block.box(1, 1, 20.75, 2, 18, 22),
                    Block.box(1, -14, -6, 2, 1, 1),
                    Block.box(1, 15, -4.75, 2, 18, -0.25),
                    Block.box(1, 1, -0.25, 2, 18, 1),
                    Block.box(1, 1, -6, 2, 18, -4.75));
    protected static final VoxelShape KT_SOUTH_AABB_OPEN =
            Shapes.or(
                    Block.box(-16, -16, 15, 1, 21, 17),
                    Block.box(15, -16, 15, 32, 21, 17),
                    Block.box(1, 18, 15, 15, 21, 17),
                    Block.box(-1, 17.75, 17, 17, 19, 23.975),
                    Block.box(-1.2, -14, 17, 0.8, 18, 23.975),
                    Block.box(14, -16, 17.25, 16, -11, 23.7),
                    Block.box(15.2, -14, 17, 17.2, 18, 23.975),
                    Block.box(-1, -16, 22.15, 17, -14, 23.75),
                    Block.box(-1, -16, 15, 17, -14, 22.375),
                    Block.box(0, -16, 17.25, 2, -11, 23.7),
                    Block.box(15, -14, 14, 22, 1, 15),
                    Block.box(16.25, 15, 14, 20.75, 18, 15),
                    Block.box(15, 1, 14, 16.25, 18, 15),
                    Block.box(20.75, 1, 14, 22, 18, 15),
                    Block.box(-6, -14, 14, 1, 1, 15),
                    Block.box(-4.75, 15, 14, -0.25, 18, 15),
                    Block.box(-0.25, 1, 14, 1, 18, 15),
                    Block.box(-6, 1, 14, -4.75, 18, 15));
    protected static final VoxelShape KT_EAST_AABB_OPEN =
            Shapes.or(
                    Block.box(15, -16, 15, 17, 21, 32),
                    Block.box(15, -16, -16, 17, 21, 1),
                    Block.box(15, 18, 1, 17, 21, 15),
                    Block.box(17, 17.75, -1, 23.975, 19, 17),
                    Block.box(17, -14, 15.2, 23.975, 18, 17.2),
                    Block.box(17.25, -16, 0, 23.7, -11, 2),
                    Block.box(17, -14, -1.2, 23.975, 18, 0.8),
                    Block.box(22.15, -16, -1, 23.75, -14, 17),
                    Block.box(15, -16, -1, 22.375, -14, 17),
                    Block.box(17.25, -16, 14, 23.7, -11, 16),
                    Block.box(14, -14, -6, 15, 1, 1),
                    Block.box(14, 15, -4.75, 15, 18, -0.25),
                    Block.box(14, 1, -0.25, 15, 18, 1),
                    Block.box(14, 1, -6, 15, 18, -4.75),
                    Block.box(14, -14, 15, 15, 1, 22),
                    Block.box(14, 15, 16.25, 15, 18, 20.75),
                    Block.box(14, 1, 15, 15, 18, 16.25),
                    Block.box(14, 1, 20.75, 15, 18, 22));

    protected static final VoxelShape M1_INSIDE_NORTH_AABB_OPEN =
            Shapes.or(
                    Block.box(16, -16, -1, 31, 21, 1),
                    Block.box(-15, -16, -1, 0, 21, 1),
                    Block.box(0, 18, -1, 16, 21, 1));

    protected static final VoxelShape M1_INSIDE_WEST_AABB_OPEN =
            Shapes.or(
                    Block.box(-1, -16, -15, 1, 21, 0),
                    Block.box(-1, -16, 16, 1, 21, 31),
                    Block.box(-1, 18, 0, 1, 21, 16));

    protected static final VoxelShape M1_INSIDE_SOUTH_AABB_OPEN =
            Shapes.or(
                    Block.box(-15, -16, 15, 0, 21, 17),
                    Block.box(16, -16, 15, 31, 21, 17),
                    Block.box(0, 18, 15, 16, 21, 17));

    protected static final VoxelShape M1_INSIDE_EAST_AABB_OPEN =
            Shapes.or(
                    Block.box(15, -16, 16, 17, 21, 31),
                    Block.box(15, -16, -15, 17, 21, 0),
                    Block.box(15, 18, 0, 17, 21, 16));

    protected static final VoxelShape M1_INSIDE_NORTH_AABB =
            Block.box(-15, -16, -1, 31, 21, 1);

    protected static final VoxelShape M1_INSIDE_WEST_AABB =
            Block.box(-1, -16, -15, 1, 21, 31);

    protected static final VoxelShape M1_INSIDE_SOUTH_AABB =
            Block.box(-15, -16, 15, 31, 21, 17);

    protected static final VoxelShape M1_INSIDE_EAST_AABB =
            Block.box(15, -16, -15, 17, 21, 31);

    protected static final VoxelShape BG_NORTH_AABB =
            Shapes.or(
                    Block.box(-16, -16, -1, 32, 21, 1),
                    Block.box(-3, 18, -7.9, 19, 21, -5.15),
                    Block.box(-1, 17.75, -6.15, 17, 19, -1),
                    Block.box(15.5, -14, -7.9, 18.5, 18, -5.25),
                    Block.box(-0.2, -14, -6.15, 0.8, 18, -1),
                    Block.box(-2.5, -14, -7.9, 0.5, 18, -5.25),
                    Block.box(15.2, -14, -6.15, 16.2, 18, -1),
                    Block.box(-1, -16, -7.75, 17, -14, -6.15),
                    Block.box(-1, -16, -6.15, 17, -14, 1),
                    Block.box(1, 18, -1, 15, 21, 1));

    protected static final VoxelShape BG_SOUTH_AABB =
            Shapes.or(
                    Block.box(-16, -16, 15, 32, 21, 17),
                    Block.box(-3, 18, 21.15, 19, 21, 23.9),
                    Block.box(-1, 17.75, 17, 17, 19, 22.15),
                    Block.box(-2.5, -14, 21.25, 0.5, 18, 23.9),
                    Block.box(15.2, -14, 17, 16.2, 18, 22.15),
                    Block.box(15.5, -14, 21.25, 18.5, 18, 23.9),
                    Block.box(-0.2, -14, 17, 0.8, 18, 22.15),
                    Block.box(-1, -16, 22.15, 17, -14, 23.75),
                    Block.box(-1, -16, 15, 17, -14, 22.15),
                    Block.box(1, 18, 15, 15, 21, 17));
    protected static final VoxelShape BG_EAST_AABB =
            Shapes.or(
                    Block.box(15, -16, -16, 17, 21, 32),
                    Block.box(21.15, 18, -3, 23.9, 21, 19),
                    Block.box(17, 17.75, -1, 22.15, 19, 17),
                    Block.box(21.25, -14, 15.5, 23.9, 18, 18.5),
                    Block.box(17, -14, -0.2, 22.15, 18, 0.8),
                    Block.box(21.25, -14, -2.5, 23.9, 18, 0.5),
                    Block.box(17, -14, 15.2, 22.15, 18, 16.2),
                    Block.box(22.15, -16, -1, 23.75, -14, 17),
                    Block.box(15, -16, -1, 22.15, -14, 17),
                    Block.box(15, 18, 1, 17, 21, 15));

    protected static final VoxelShape BG_WEST_AABB =
            Shapes.or(
                    Block.box(-1, -16, -16, 1, 21, 32),
                    Block.box(-7.9, 18, -3, -5.15, 21, 19),
                    Block.box(-6.15, 17.75, -1, -1, 19, 17),
                    Block.box(-7.9, -14, -2.5, -5.25, 18, 0.5),
                    Block.box(-6.15, -14, 15.2, -1, 18, 16.2),
                    Block.box(-7.9, -14, 15.5, -5.25, 18, 18.5),
                    Block.box(-6.15, -14, -0.2, -1, 18, 0.80),
                    Block.box(-7.75, -16, -1, -6.15, -14, 17),
                    Block.box(-6.15, -16, -1, 1, -14, 17),
                    Block.box(-1, 18, 1, 1, 21, 15));

    protected static final VoxelShape BG_NORTH_AABB_OPEN =
            Shapes.or(
                    Block.box(0, -14, 0, 2, 18, 14),
                    Block.box(-1, -16, -7.75, 17, -14, -6.15),
                    Block.box(15.5, -14, -7.9, 18.5, 18, -5.25),
                    Block.box(-2.5, -14, -7.9, 0.5, 18, -5.25),
                    Block.box(15.2, -14, -6.15, 16.2, 18, -1),
                    Block.box(-0.2, -14, -6.15, 0.8, 18, -1),
                    Block.box(-1, -16, -6.15, 17, -14, 1),
                    Block.box(-3, 18, -7.9, 19, 21, -5.15),
                    Block.box(-1, 17.75, -6.15, 17, 19, -1),
                    Block.box(-16, -16, -1, 1, 21, 1),
                    Block.box(1, 18, -1, 15, 21, 1),
                    Block.box(15, -16, -1, 32, 21, 1));
    protected static final VoxelShape BG_WEST_AABB_OPEN =
            Shapes.or(
                    Block.box(0, -14, 14, 14, 18, 16),
                    Block.box(-7.75, -16, -1, -6.15, -14, 17),
                    Block.box(-7.9, -14, -2.5, -5.25, 18, 0.5),
                    Block.box(-7.9, -14, 15.5, -5.25, 18, 18.5),
                    Block.box(-6.15, -14, -0.2, -1, 18, 0.8),
                    Block.box(-6.15, -14, 15.2, -1, 18, 16.2),
                    Block.box(-6.15, -16, -1, 1, -14, 17),
                    Block.box(-7.9, 18, -3, -5.15, 21, 19),
                    Block.box(-6.15, 17.75, -1, -1, 19, 17),
                    Block.box(-1, -16, 15, 1, 21, 32),
                    Block.box(-1, 18, 1, 1, 21, 15),
                    Block.box(-1, -16, -16, 1, 21, 1));
    protected static final VoxelShape BG_SOUTH_AABB_OPEN =
            Shapes.or(
                    Block.box(14, -14, 2, 16, 18, 16),
                    Block.box(-1, -16, 22.15, 17, -14, 23.75),
                    Block.box(15.5, -14, 21.25, 18.5, 18, 23.9),
                    Block.box(-2.5, -14, 21.25, 0.5, 18, 23.9),
                    Block.box(15.2, -14, 17, 16.2, 18, 22.15),
                    Block.box(-0.2, -14, 17, 0.8, 18, 22.15),
                    Block.box(-1, -16, 15, 17, -14, 22.15),
                    Block.box(-3, 18, 21.15, 19, 21, 23.9),
                    Block.box(-1, 17.75, 17, 17, 19, 22.15),
                    Block.box(-16, -16, 15, 1, 21, 17),
                    Block.box(1, 18, 15, 15, 21, 17),
                    Block.box(15, -16, 15, 32, 21, 17));
    protected static final VoxelShape BG_EAST_AABB_OPEN =
            Shapes.or(
                    Block.box(2, -14, 0, 16, 18, 2),
                    Block.box(22.15, -16, -1, 23.75, -14, 17),
                    Block.box(21.25, -14, -2.5, 23.9, 18, 0.5),
                    Block.box(21.25, -14, 15.5, 23.9, 18, 18.5),
                    Block.box(17, -14, -0.12, 22.15, 18, 0.8),
                    Block.box(17, -14, 15.2, 22.15, 18, 16.2),
                    Block.box(15, -16, -1, 22.15, -14, 17),
                    Block.box(21.15, 18, -3, 23.9, 21, 19),
                    Block.box(17, 17.75, -1, 22.15, 19, 17),
                    Block.box(15, -16, 15, 17, 21, 32),
                    Block.box(15, 18, 1, 17, 21, 15),
                    Block.box(15, -16, -16, 17, 21, 1));

    protected static final VoxelShape FREIGHT_C70_NORTH_AABB =
            Block.box(-16, 0, -1, 32, 32, 3);

    protected static final VoxelShape FREIGHT_C70_WEST_AABB =
            Block.box(-1, 0, -16, 3, 32, 32);

    protected static final VoxelShape FREIGHT_C70_SOUTH_AABB =
            Block.box(-16, 0, 15, 32, 32, 19);

    protected static final VoxelShape FREIGHT_C70_EAST_AABB =
            Block.box(15, 0, -16, 19, 32, 32);

    protected static final VoxelShape FREIGHT_NX70_NORTH_AABB =
            Block.box(-16, 0, -1, 32, 20, 3);

    protected static final VoxelShape FREIGHT_NX70_WEST_AABB =
            Block.box(-1, 0, -16, 3, 20, 32);

    protected static final VoxelShape FREIGHT_NX70_SOUTH_AABB =
            Block.box(-16, 0, 15, 32, 20, 19);

    protected static final VoxelShape FREIGHT_NX70_EAST_AABB =
            Block.box(15, 0, -16, 19, 20, 32);

    public static VoxelShape moveByDirection(VoxelShape shape, Direction direction, double x, double y, double z) {
        Vec3 vec3 = new Vec3(x, y, z);
        vec3 = vec3.yRot(-direction.toYRot());
        return shape.move(vec3.x(), vec3.y(), vec3.z());
    }

    public static VoxelShape getEndFaceShape(Direction direction, TrainPanelProperties.DoorType type, boolean open) {
        if (type == TrainPanelProperties.DoorType.NO_DOOR) {
            open = true;
            type = TrainPanelProperties.DoorType.SLIDE;
        }
        if (type.isSliding()) {
            if (open) {
                return switch (direction) {
                    case NORTH -> KT_NORTH_AABB_OPEN;
                    case WEST -> KT_WEST_AABB_OPEN;
                    case SOUTH -> KT_SOUTH_AABB_OPEN;
                    case EAST -> KT_EAST_AABB_OPEN;
                    default -> KT_EAST_AABB_OPEN;
                };
            }
            return switch (direction) {
                case NORTH -> BG_NORTH_AABB;
                case WEST -> BG_WEST_AABB;
                case EAST -> BG_EAST_AABB;
                case SOUTH -> BG_SOUTH_AABB;
                default -> BG_EAST_AABB;
            };
        } else {
            if (open) {
                return switch (direction) {
                    case NORTH -> BG_NORTH_AABB_OPEN;
                    case EAST -> BG_EAST_AABB_OPEN;
                    case SOUTH -> BG_SOUTH_AABB_OPEN;
                    case WEST -> BG_WEST_AABB_OPEN;
                    default -> BG_EAST_AABB_OPEN;
                };
            }
            return switch (direction) {
                case NORTH -> KT_NORTH_AABB;
                case SOUTH -> KT_SOUTH_AABB;
                case WEST -> KT_WEST_AABB;
                case EAST -> KT_EAST_AABB;
                default -> KT_EAST_AABB;
            };
        }
    }

    public static VoxelShape getInsideEndFaceShape(Direction direction, boolean open) {
        // 关
        if (!open) {
            return getInsideEndFaceCloseShape(direction);
        }
        // 开
        return switch (direction) {
            case NORTH -> M1_INSIDE_NORTH_AABB_OPEN;
            case WEST -> M1_INSIDE_WEST_AABB_OPEN;
            case SOUTH -> M1_INSIDE_SOUTH_AABB_OPEN;
            case EAST -> M1_INSIDE_EAST_AABB_OPEN;
            default -> M1_INSIDE_NORTH_AABB_OPEN;
        };
    }

    public static VoxelShape getInsideEndFaceCloseShape(Direction direction) {
        // 关
        return switch (direction) {
            case NORTH -> M1_INSIDE_NORTH_AABB.move(0, 0, 0.125);
            case WEST -> M1_INSIDE_WEST_AABB.move(0.125, 0, 0);
            case EAST -> M1_INSIDE_EAST_AABB.move(-0.125, 0, 0);
            case SOUTH -> M1_INSIDE_SOUTH_AABB.move(0, 0, -0.125);
            default -> M1_INSIDE_NORTH_AABB;
        };
    }

    public static VoxelShape getC70EndFaceShape(Direction direction) {
        return switch (direction) {
            case NORTH -> FREIGHT_C70_NORTH_AABB.move(0, -0.5, 0);
            case WEST -> FREIGHT_C70_WEST_AABB.move(0, -0.5, 0);
            case EAST -> FREIGHT_C70_EAST_AABB.move(0, -0.5, 0);
            case SOUTH -> FREIGHT_C70_SOUTH_AABB.move(0, -0.5, 0);
            default -> FREIGHT_C70_NORTH_AABB;
        };
    }

    public static VoxelShape getNX70EndFaceShape(Direction direction) {
        return switch (direction) {
            case NORTH -> FREIGHT_NX70_NORTH_AABB.move(0, -0.5, 0);
            case WEST -> FREIGHT_NX70_WEST_AABB.move(0, -0.5, 0);
            case EAST -> FREIGHT_NX70_EAST_AABB.move(0, -0.5, 0);
            case SOUTH -> FREIGHT_NX70_SOUTH_AABB.move(0, -0.5, 0);
            default -> FREIGHT_NX70_NORTH_AABB;
        };
    }
}
