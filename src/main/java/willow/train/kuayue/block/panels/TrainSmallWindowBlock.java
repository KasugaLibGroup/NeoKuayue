package willow.train.kuayue.block.panels;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.item.PanelBlockItem;

import static willow.train.kuayue.block.panels.TrainDoorBlock.*;

public class TrainSmallWindowBlock extends TrainPanelBlock {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public TrainSmallWindowBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos) {
        super(pProperties, beginPos, endPos);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST).setValue(OPEN, false));
    }

    public TrainSmallWindowBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST).setValue(OPEN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(OPEN));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(OPEN, false);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        return windowUse(pState, this.material, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public static InteractionResult windowUse(BlockState state, Material material, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).getItem() instanceof PanelBlockItem) return InteractionResult.PASS;
        BlockUseFunction function = (l, parentPos, parentState, myPos, myState, player1, hand1, hit1) -> {
            if(!(myState.getBlock() instanceof TrainPanelBlock)) return InteractionResult.FAIL;
            Material mat = myState.getMaterial();
            myState = myState.cycle(OPEN);
            level.setBlock(myPos, myState, 10);
            level.levelEvent(player1, myState.getValue(OPEN) ? getOpenSound(mat) : getCloseSound(mat), myPos, 0);
            level.gameEvent(player1, isOpen(myState) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return InteractionResult.sidedSuccess(l.isClientSide);
        };
        ((TrainPanelBlock)state.getBlock()).walkAllValidPos(level, pos, state, player, hand, hit, function);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
