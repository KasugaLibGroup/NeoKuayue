package willow.train.kuayue.item;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.utils.DirectionUtil;

public class SlabBlockItem extends BlockItem {
    public SlabBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext pContext, BlockState pState) {
        SkirtBlockItem.PlacePosContext context = getPlacePos(pContext);
        Direction direction = pState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        if (context.success() > 0)
            direction = getParentDirection(pContext);
        return pContext.getLevel().setBlock(context.pos(),
                pState.setValue(BlockStateProperties.HORIZONTAL_FACING, direction),
                11);
    }

    public SkirtBlockItem.PlacePosContext getPlacePos(BlockPlaceContext context) {
        if (context.getPlayer() == null || context.getPlayer().isShiftKeyDown())
            return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
        if (!(getBlock() instanceof TrainSlabBlock))
            return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
        Direction direction = context.getClickedFace();
        BlockPos parentPos = context.getClickedPos().relative(direction.getOpposite());
        boolean upOrDown = direction == Direction.UP || direction == Direction.DOWN;
        Level level = context.getLevel();
        BlockState parentState = level.getBlockState(parentPos);
        if (!(parentState.getBlock() instanceof TrainSlabBlock))
            return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
        if (upOrDown) {
            Vec3 clicked = context.getClickLocation();
            BlockPos pos1 = parentPos.relative(Direction.WEST),
                    pos2 = parentPos.relative(Direction.EAST),
                    pos3 = parentPos.relative(Direction.NORTH),
                    pos4 = parentPos.relative(Direction.SOUTH);
            Vec3 loc1 = DirectionUtil.centerOf(pos1),
                    loc2 = DirectionUtil.centerOf(pos2),
                    loc3 = DirectionUtil.centerOf(pos3),
                    loc4 = DirectionUtil.centerOf(pos4);
            double d1 = clicked.distanceToSqr(loc1),
                    d2 = clicked.distanceToSqr(loc2),
                    d3 = clicked.distanceToSqr(loc3),
                    d4 = clicked.distanceToSqr(loc4);
            double min = Math.min(d1, d2);
            min = Math.min(min, d3);
            min = Math.min(min, d4);
            if (min == d1) {
                if (!level.getBlockState(pos1).getMaterial().isReplaceable())
                    return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
                return new SkirtBlockItem.PlacePosContext(pos1, 1);
            } else if (min == d2) {
                if (!level.getBlockState(pos2).getMaterial().isReplaceable())
                    return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
                return new SkirtBlockItem.PlacePosContext(pos2, 1);
            } else if (min == d3) {
                if (!level.getBlockState(pos3).getMaterial().isReplaceable())
                    return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
                return new SkirtBlockItem.PlacePosContext(pos3, 1);
            }
            if (!level.getBlockState(pos4).getMaterial().isReplaceable())
                return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
            return new SkirtBlockItem.PlacePosContext(pos4, 1);
        }
        BlockPos left = DirectionUtil.left(parentPos, direction, 1),
            right = DirectionUtil.right(parentPos, direction, 1);
        Vec3 clicked = context.getClickLocation(),
                lLoc = DirectionUtil.centerOf(left),
                rLoc = DirectionUtil.centerOf(right);
        boolean leftHinge = clicked.distanceToSqr(lLoc) <= clicked.distanceToSqr(rLoc);
        if (leftHinge && level.getBlockState(left).getMaterial().isReplaceable()) {
            return new SkirtBlockItem.PlacePosContext(left, 2);
        } else if (!leftHinge && level.getBlockState(right).getMaterial().isReplaceable()) {
            return new SkirtBlockItem.PlacePosContext(right, 2);
        }
        return new SkirtBlockItem.PlacePosContext(context.getClickedPos(), 0);
    }

    public Direction getParentDirection(BlockPlaceContext context) {
        Level level = context.getLevel();
        Direction direction = context.getClickedFace();
        BlockPos parentPos = context.getClickedPos().relative(direction.getOpposite());
        BlockState parentState = level.getBlockState(parentPos);
        if (!parentState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            return Direction.EAST;
        }
        return parentState.getValue(BlockStateProperties.HORIZONTAL_FACING);
    }

    public PanelBlockItem.Face getArrowDirection(BlockHitResult result) {
        Direction direction = result.getDirection();
        BlockPos hitPos = result.getBlockPos();
        if (Minecraft.getInstance().player == null)
            return null;
        Direction hoz = Direction.from2DDataValue((int) Minecraft.getInstance().player.getViewXRot(0));
        Vec3 clicked = result.getLocation();
        boolean upOrDown = direction == Direction.UP || direction == Direction.DOWN;
        if (upOrDown) {
            BlockPos leftPos = DirectionUtil.left(hitPos, hoz, 1),
                    rightPos = DirectionUtil.right(hitPos, hoz, 1),
                    upPos = hitPos.relative(hoz),
                    downPos = hitPos.relative(hoz.getOpposite());
            Vec3 lLoc = DirectionUtil.centerOf(leftPos),
                    rLoc = DirectionUtil.centerOf(rightPos),
                    uLoc = DirectionUtil.centerOf(upPos),
                    dLoc = DirectionUtil.centerOf(downPos);
            double ld = clicked.distanceToSqr(lLoc),
                    rd = clicked.distanceToSqr(rLoc),
                    ud = clicked.distanceToSqr(uLoc),
                    dd = clicked.distanceToSqr(dLoc);
            double min = Math.min(ld, rd);
            min = Math.min(min, ud);
            min = Math.min(min, dd);
            if (min == ld) {
                return PanelBlockItem.Face.LEFT;
            }
            if (min == rd) {
                return PanelBlockItem.Face.RIGHT;
            }
            if (min == ud) {
                return PanelBlockItem.Face.UP;
            }
            return PanelBlockItem.Face.DOWN;
        }
        BlockPos leftPos = DirectionUtil.left(hitPos, direction, 1);
        BlockPos rightPos = DirectionUtil.right(hitPos, direction, 1);
        Vec3 lLoc = DirectionUtil.centerOf(leftPos), rLoc = DirectionUtil.centerOf(rightPos);
        if (clicked.distanceToSqr(lLoc) <= clicked.distanceToSqr(rLoc)) {
            return PanelBlockItem.Face.LEFT;
        }
        return PanelBlockItem.Face.RIGHT;
    }
}
