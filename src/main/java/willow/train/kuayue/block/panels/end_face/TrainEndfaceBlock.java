package willow.train.kuayue.block.panels.end_face;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.block.panels.base.CompanyTrainPanel;
import willow.train.kuayue.block.panels.base.EndFaceShapes;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.utils.DirectionUtil;

public class TrainEndfaceBlock extends TrainPanelBlock {
    public final TrainPanelProperties.DoorType DOOR_TYPE;

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public TrainEndfaceBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos, TrainPanelProperties.DoorType doorType) {
        super(pProperties, beginPos, endPos);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST).setValue(OPEN, false));
        this.DOOR_TYPE = doorType;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(OPEN));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return EndFaceShapes.getEndFaceShape(pState.getValue(FACING).getOpposite(), DOOR_TYPE, pState.getValue(OPEN)).move(0, 1, 0);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(OPEN, false);
    }

    @Override
    public void generateCompanyBlock(Level level, BlockState state, BlockPos pos, boolean isMoving) {
        Direction direction = state.getValue(FACING);
        BlockPos firstPos = pos;
        boolean open = state.hasProperty(BlockStateProperties.OPEN) ?
                state.getValue(BlockStateProperties.OPEN) : false;
        boolean leftHinge = state.hasProperty(BlockStateProperties.DOOR_HINGE) ?
                state.getValue(BlockStateProperties.DOOR_HINGE) != DoorHingeSide.LEFT : false;
        firstPos = DirectionUtil.left(firstPos, direction, (int) (leftHinge ? - beginPos.x : beginPos.x));
        firstPos = firstPos.offset(0, beginPos.y, 0);
        int length = (int) (endPos.x - beginPos.x),
                height = (int) (endPos.y - beginPos.y);

        BlockPos centerBlock = new BlockPos((int) - beginPos.x, (int) beginPos.y, 0);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                if (i == centerBlock.getX() && j == centerBlock.getY()) {
                    firstPos = firstPos.above().above();
                    j++;
                    continue;
                }
                BlockState state1 = generateCompanyState(direction, leftHinge ? DoorHingeSide.LEFT : DoorHingeSide.RIGHT, open);
                level.setBlock(firstPos, state1, 10);
                CompanyTrainPanel.setParentBlock(firstPos, level, state1, pos);
                firstPos = firstPos.above();
            }
            firstPos = firstPos.offset(0, - height, 0);
            firstPos = DirectionUtil.right(firstPos, direction, leftHinge ? 1 : -1);
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        return TrainSmallWindowBlock.windowUse(pState, this.material, pLevel, pPos, pPlayer, pHand, pHit);
    }
}
