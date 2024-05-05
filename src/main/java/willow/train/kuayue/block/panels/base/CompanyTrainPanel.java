package willow.train.kuayue.block.panels.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.utils.DirectionUtil;

public class CompanyTrainPanel extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    public CompanyTrainPanel(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(FACING, Direction.EAST)
                        .setValue(HINGE, DoorHingeSide.LEFT)
                        .setValue(OPEN, false)
        );
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.getShape(pState.getValue(FACING).getOpposite());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.getShape(pState.getValue(FACING).getOpposite());
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        BlockPos pos = getParentPos(pLevel, pPos);
        if (pos == null) {
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
            return;
        }
        BlockState state = pLevel.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof TrainPanelBlock trainPanelBlock)
            trainPanelBlock.onRemove(state, pLevel, pos, pNewState, pIsMoving);
        else
            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, HINGE, OPEN);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(HINGE, DoorHingeSide.LEFT)
                .setValue(OPEN, false);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (pIsMoving) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof CompanyTrainBlockEntity entity) {
                entity.updateDirection(pState.getValue(FACING));
            }
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        BlockPos pos = getParentPos(pLevel, pPos);
        if (pos == null) return InteractionResult.PASS;
        BlockState state = pLevel.getBlockState(pos);
        if (state.getBlock() instanceof CompanyTrainPanel) return InteractionResult.PASS;
        return state.getBlock().use(state, pLevel, pos, pPlayer, pHand, pHit);
    }

    public static BlockPos getParentPos(BlockGetter level, BlockPos myPos) {
        BlockEntity entity = level.getBlockEntity(myPos);
        if (!(entity instanceof CompanyTrainBlockEntity companyTrainBlockEntity)) return null;
        return companyTrainBlockEntity.getParentPos().offset(myPos);
    }

    public static void setParentBlock(BlockPos myPos, BlockGetter level, BlockState state, BlockPos pos) {
        BlockEntity entity = level.getBlockEntity(myPos);
        if (!(entity instanceof CompanyTrainBlockEntity companyTrainBlockEntity)) return;
        companyTrainBlockEntity.setParentPos(pos.subtract(myPos), state.getValue(FACING));
    }

    public static BlockState getParentBlock(BlockGetter level, BlockPos myPos) {
        BlockPos pos = getParentPos(level, myPos);
        if (pos == null) return Blocks.AIR.defaultBlockState();
        return level.getBlockState(pos);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CompanyTrainBlockEntity(pPos, pState);
    }
}
