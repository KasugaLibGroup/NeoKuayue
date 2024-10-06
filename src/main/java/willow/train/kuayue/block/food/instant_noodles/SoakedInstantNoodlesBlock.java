package willow.train.kuayue.block.food.instant_noodles;

import com.simibubi.create.foundation.particle.AirParticleData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.food.PlacementFoodBlock;

import static willow.train.kuayue.block.food.instant_noodles.InstantNoodlesProperties.NoodlesState.FINISH_EATING;
import static willow.train.kuayue.block.food.instant_noodles.InstantNoodlesProperties.NoodlesState.OPENING;

public class SoakedInstantNoodlesBlock extends PlacementFoodBlock {

    public static final EnumProperty<InstantNoodlesProperties.NoodlesState> NOODLES_STATE =
            InstantNoodlesProperties.NOODLES_STATE;

    public SoakedInstantNoodlesBlock(Properties pProperties, FoodType foodType) {
        super(pProperties, foodType);
        this.registerDefaultState(this.getStateDefinition().any().setValue(
                NOODLES_STATE, InstantNoodlesProperties.NoodlesState.WITH_FORK));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(NOODLES_STATE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(NOODLES_STATE, InstantNoodlesProperties.NoodlesState.WITH_FORK);
    }

    @Override
    protected InteractionResult eat(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        // 如果玩家不可以进食，则直接PASS。
        if (!pPlayer.canEat(true)) {
            return InteractionResult.PASS;
        }
        // 获取食物方块对应的物品栈
        ItemStack stack = pState.getBlock().asItem().getDefaultInstance();

        if (pState.getValue(NOODLES_STATE) == InstantNoodlesProperties.NoodlesState.WITH_FORK) {
            // 冲泡后带有叉子的状态
            // 更新Property
            pLevel.playSound(null, pPos,
                    SoundEvents.WOOL_BREAK,
                    SoundSource.BLOCKS, 1.0F, 1.0F);
            BlockState changeBlockState = pState.setValue(NOODLES_STATE, OPENING);
            pLevel.setBlock(pPos, changeBlockState, 11);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos,
                    GameEvent.Context.of(pPlayer, pState));
            return InteractionResult.SUCCESS;
        } else if (pState.getValue(NOODLES_STATE) == OPENING) {
            // 打开盖子的状态
            // 执行玩家吃下物品的方法
            pPlayer.eat(pLevel, stack);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            // 更新Property
            BlockState changeBlockState = pState.setValue(NOODLES_STATE, FINISH_EATING);
            pLevel.setBlock(pPos, changeBlockState, 11);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos,
                    GameEvent.Context.of(pPlayer, pState));
            return InteractionResult.SUCCESS;
        } else if (pState.getValue(NOODLES_STATE) == FINISH_EATING) {
            // 吃完的状态
            pLevel.playSound(null, pPos,
                    SoundEvents.WOOL_BREAK,
                    SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.removeBlock(pPos, false);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(NOODLES_STATE) == OPENING) {
            RandomSource randomsource = pLevel.random;

            double d1 = 0.1 * randomsource.nextFloat();
            double d2 = 0.1 * randomsource.nextFloat();
            double d3 = 0.1 * randomsource.nextFloat();
            pLevel.addParticle(new AirParticleData(),
                    pPos.getX() + 0.4 + d1, pPos.getY() + 0.75 + d2, pPos.getZ() + 0.4 + d3,
                    0.0D, 0.1D, 0.0D);
        }
    }
}
