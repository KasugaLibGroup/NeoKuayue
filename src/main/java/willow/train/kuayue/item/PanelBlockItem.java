package willow.train.kuayue.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.utils.DirectionUtil;

import java.util.Objects;

public class PanelBlockItem extends BlockItem {
    public PanelBlockItem(TrainPanelBlock pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
        TrainPanelBlock panel = (TrainPanelBlock) getBlock();
        BlockPos pos = getPlacePos(context, panel.beginPos, panel.endPos, .5f);
        Direction direction = context.getHorizontalDirection();
        return checkForSpacing(context.getLevel(), pos, direction.getOpposite(), !panel.hasHinge() ||
                        leftOrNot(context.getClickedPos(), context.getHorizontalDirection(), context.getClickLocation())
                );
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext pContext, BlockState pState) {
        TrainPanelBlock panel = (TrainPanelBlock) getBlock();
        return pContext.getLevel().setBlock(getPlacePos(pContext, panel.beginPos, panel.endPos, .5f), pState, 11);
    }

    public BlockPos getPlacePos(BlockPlaceContext context, Vec2 first, Vec2 last, float radius) {
        // 若玩家对象为空或玩家按下shift键直接则直接返回点击位置
        if (context.getPlayer() == null || context.getPlayer().isShiftKeyDown()) {
            return context.getClickedPos();
        }
        // 获取要放置的方块位置
        BlockPos pos = context.getClickedPos();
        // 获取世界对象
        Level level = context.getLevel();
        // 获取点击到的是方块的哪一面（东、西、南、北、上、下表面...）
        Direction direction = context.getClickedFace();
        // 获取右键点击到的方块位置
        BlockPos parentPos = pos.relative(direction.getOpposite());
        // 获取到右键点击到的方块对象
        BlockState clickedBlock = level.getBlockState(parentPos);
        // 若点击到的方块没有FACING属性，则直接返回放置位置。
        if (!clickedBlock.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) return pos;
        // 若点击到的方块不带有SIDE_PLACEMENT标签，则直接返回放置位置。
        if (!clickedBlock.is(Objects.requireNonNull(AllTags.SIDE_PLACEMENT.tag()))) return pos;
        // 获取右键点击到的方块的朝向
        Direction clickedDirection = clickedBlock.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (direction == Direction.UP) return pos.relative(Direction.UP, (int) - first.y);
        if (direction == Direction.DOWN) return pos.relative(Direction.DOWN, (int) (last.y - 1));
        if (!(clickedDirection == direction || clickedDirection == direction.getOpposite())) return pos;
        Vec3 clickLocation = context.getClickLocation();
        Face leftHinge = isLeftHinge(pos, context.getHorizontalDirection(), clickLocation);
        TrainPanelBlock panel = (TrainPanelBlock) this.getBlock();
        if (leftHinge == Face.UP) {
            return parentPos.relative(Direction.UP, (int) - first.y + 1);
        } else if (leftHinge == Face.DOWN) {
            return parentPos.relative(Direction.DOWN, (int) (last.y));
        }
        if (panel.beginPos.equals(Vec2.ZERO) && panel.endPos.equals(Vec2.ONE)) {
            return DirectionUtil.left(parentPos, direction.getOpposite(), leftHinge == Face.LEFT ? 1 : -1);
        }
        parentPos = DirectionUtil.left(parentPos, direction.getOpposite(), leftHinge == Face.LEFT ? 1 : -1);
        parentPos = DirectionUtil.left(parentPos, direction.getOpposite(),
                (int) (leftHinge == Face.LEFT ? ((panel.beginPos.x + panel.endPos.x)) : - (panel.beginPos.x + panel.endPos.x)));
        return parentPos;
    }

    public Face isLeftHinge(BlockPos pos, Direction direction, Vec3 clickLocation) {
        BlockPos leftPos = DirectionUtil.left(pos, direction, 1);
        BlockPos rightPos = DirectionUtil.right(pos, direction, 1);
        Vec3 leftLoc = new Vec3(leftPos.getX(), leftPos.getY(), leftPos.getZ()).add(.5, .5, .5);
        Vec3 rightLoc = new Vec3(rightPos.getX(), rightPos.getY(), rightPos.getZ()).add(.5, .5, .5);
        Vec3 upLoc = new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(.5, 1.5, .5);
        Vec3 downLoc = new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(.5, -.5, .5);
        double leftD = clickLocation.distanceToSqr(leftLoc), rightD = clickLocation.distanceToSqr(rightLoc),
                upD = clickLocation.distanceToSqr(upLoc), downD = clickLocation.distanceToSqr(downLoc);
        if (leftD <= rightD && leftD <= upD && leftD <= downD) return Face.LEFT;
        if (rightD <= leftD && rightD <= upD && rightD <= downD) return Face.RIGHT;
        if (upD <= leftD && upD <= rightD && upD <= downD) return Face.UP;
        return Face.DOWN;
    }

    public boolean leftOrNot(BlockPos pos, Direction direction, Vec3 clickLocation) {
        BlockPos leftPos = DirectionUtil.left(pos, direction, 1);
        BlockPos rightPos = DirectionUtil.right(pos, direction, 1);
        Vec3 leftLoc = new Vec3(leftPos.getX(), leftPos.getY(), leftPos.getZ()).add(.5, .5, .5);
        Vec3 rightLoc = new Vec3(rightPos.getX(), rightPos.getY(), rightPos.getZ()).add(.5, .5, .5);
        return clickLocation.distanceToSqr(leftLoc) >= clickLocation.distanceToSqr(rightLoc);
    }

    public boolean checkForSpacing(Level level, BlockPos placePos, Direction direction, boolean leftHinge) {
        TrainPanelBlock panel = (TrainPanelBlock) getBlock();
        Vec2 start = panel.beginPos, last = panel.endPos;
        BlockPos scanner = placePos;
        scanner = DirectionUtil.left(scanner, direction, (int)(leftHinge ? - start.x : start.x));
        scanner = scanner.above((int) start.y);
        int width = (int) last.x - (int) start.x, height = (int) last.y - (int) start.y;
        if (width <= 0 || height <= 0) return false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!level.getBlockState(scanner).getMaterial().isReplaceable()) return false;
                scanner = scanner.above();
            }
            scanner = scanner.below(height);
            scanner = DirectionUtil.right(scanner, direction, leftHinge ? 1 : -1);
        }
        return true;
    }

    public boolean shouldSidedPlace(boolean positiveDirection, BlockPos pos, Vec3 clickPos, float radius) {
        Vec3 p = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        p = p.add(.5f, .5f, .5f);
        double direction = Math.pow(clickPos.x() - p.x(), 2) + Math.pow(clickPos.z() - p.z(), 2);
        double boundaryXz = (positiveDirection ? 2.25f : 0.25f) + radius * radius / 4;
        if (direction > boundaryXz) return true;
        double py = Math.abs(p.y() - clickPos.y());
        return py >= radius / 2;
    }

    public Face getArrowDirection(BlockHitResult hit) {
        return isLeftHinge(hit.getBlockPos(), hit.getDirection(), hit.getLocation());
    }

    public enum Face {
        UP, DOWN, LEFT, RIGHT;

        public boolean canShowArrow(Level level, BlockPos pos, Direction direction) {
            if (this == UP) {
                return level.getBlockState(pos.above()).getMaterial().isReplaceable();
            } else if (this == DOWN) {
                return level.getBlockState(pos.below()).getMaterial().isReplaceable();
            } else if (this == LEFT) {
                return level.getBlockState(DirectionUtil.left(pos, direction, 1)).getMaterial().isReplaceable();
            }
            return level.getBlockState(DirectionUtil.right(pos, direction, 1)).getMaterial().isReplaceable();
        }
    }
}
