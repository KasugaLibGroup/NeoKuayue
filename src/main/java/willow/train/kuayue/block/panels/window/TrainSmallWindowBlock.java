package willow.train.kuayue.block.panels.window;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.door.TrainDoorBlock;
import willow.train.kuayue.item.PanelBlockItem;

import static willow.train.kuayue.block.panels.door.TrainDoorBlock.*;

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

    public TrainSmallWindowBlock(Properties properties, int height) {
        super(properties, new Vec2(0, 0), new Vec2(1, height));
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
        return windowUse(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    public static InteractionResult windowUse(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.getItemInHand(hand).getItem() instanceof PanelBlockItem) return InteractionResult.PASS;
        BlockUseFunction function = (l, parentPos, parentState, myPos, myState, player1, hand1, hit1) -> {
            if(!(myState.getBlock() instanceof TrainPanelBlock)) return InteractionResult.FAIL;
            myState = myState.cycle(OPEN);
            level.setBlock(myPos, myState, 10);
            playSound(player, level, pos, myState.getValue(OPEN), BlockSetType.OAK);
            level.gameEvent(player1, isOpen(myState) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return InteractionResult.sidedSuccess(l.isClientSide);
        };
        ((TrainPanelBlock)state.getBlock()).walkAllValidPos(level, pos, state, player, hand, hit, function);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public static void playSound(@javax.annotation.Nullable Entity pSource, Level pLevel, BlockPos pPos, boolean pIsOpening, BlockSetType type) {
        pLevel.playSound(pSource, pPos, pIsOpening ? type.trapdoorOpen() : type.trapdoorClose(), SoundSource.BLOCKS, 1.0F, pLevel.getRandom().nextFloat() * 0.1F + 0.9F);
    }
}
