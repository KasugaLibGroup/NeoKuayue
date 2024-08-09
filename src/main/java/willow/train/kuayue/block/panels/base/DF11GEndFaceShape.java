package willow.train.kuayue.block.panels.base;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DF11GEndFaceShape {
    protected static final VoxelShape NORTH_AABB =
            Shapes.or(
                    Block.box(-1, -16, 15, 17, -15, 24),
                    Block.box(14, -16, 15, 32, 21, 24),
                    Block.box(-16, -16, 15, 2, 21, 24),
                    Block.box(-1, 20, 15, 17, 26, 24));
    protected static final VoxelShape SOUTH_AABB =
            Shapes.or(
                    Block.box(-1, -16, -8, 17, -15, 1),
                    Block.box(-16, -16, -8, 2, 21, 1),
                    Block.box(14, -16, -8, 32, 21, 1),
                    Block.box(-1, 20, -8, 17, 26, 1));
    protected static final VoxelShape EAST_AABB =
            Shapes.or(
                    Block.box(-8, -16, -1, 1, -15, 17),
                    Block.box(-8, -16, 14, 1, 21, 32),
                    Block.box(-8, -16, -16, 1, 21, 2),
                    Block.box(-8, 20, -1, 1, 26, 17));
    protected static final VoxelShape WEST_AABB =
            Shapes.or(
                    Block.box(15, -16, -1, 24, -15, 17),
                    Block.box(15, -16, -16, 24, 21, 2),
                    Block.box(15, -16, 14, 24, 21, 32),
                    Block.box(15, 20, -1, 24, 26, 17));

    protected static final VoxelShape NORTH_AABBo = Block.box(0, 0, 15, 16, 16, 17);
    protected static final VoxelShape WEST_AABBo = Block.box(15, 0, 0, 17, 16, 16);
    protected static final VoxelShape SOUTH_AABBo = Block.box(0, 0, -1, 16, 16, 1);
    protected static final VoxelShape EAST_AABBo = Block.box(-1, 0, 0, 1, 16, 16);


    public static VoxelShape getCollisionShape(
            Direction direction) {
        return switch (direction) {
            case SOUTH -> SOUTH_AABB;
            case NORTH -> NORTH_AABB;
            case WEST -> WEST_AABB;
            default -> EAST_AABB;
        };
    }

    public static VoxelShape getShape(
            Direction direction) {
        return switch (direction) {
            case SOUTH -> SOUTH_AABBo;
            case NORTH -> NORTH_AABBo;
            case WEST -> WEST_AABBo;
            default -> EAST_AABBo;
        };
    }
}
