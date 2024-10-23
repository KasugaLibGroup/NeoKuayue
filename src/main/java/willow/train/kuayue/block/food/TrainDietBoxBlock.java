package willow.train.kuayue.block.food;

import kasuga.lib.core.base.item_helper.ExternalRemainderBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.mixins.mixin.AccessorExternalRemainderBlockItem;

import java.util.function.Supplier;

public class TrainDietBoxBlock extends PlacementFoodBlock {

    public static final BooleanProperty EMPTY = BooleanProperty.create("empty");

    public TrainDietBoxBlock(Properties pProperties, FoodType foodType) {
        super(pProperties, foodType);
        this.registerDefaultState(this.getStateDefinition().any().setValue(EMPTY, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(EMPTY);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(EMPTY, false);
    }

    @Override
    protected InteractionResult eat(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        // 如果玩家不可以进食，则直接PASS。
        if (!pPlayer.canEat(true)) {
            return InteractionResult.PASS;
        }
        // 获取食物方块对应的物品栈
        ItemStack stack = pState.getBlock().asItem().getDefaultInstance();
        // 获取食用后剩余物品栈
        ItemStack craftingRemainingItem;
        if (stack.getItem() instanceof ExternalRemainderBlockItem erbi) {
            Supplier<Item> itemSup = ((AccessorExternalRemainderBlockItem) erbi).getCraftingRemainder();
            if (itemSup != null && itemSup.get() != null)
                craftingRemainingItem = itemSup.get().getDefaultInstance();
            else
                craftingRemainingItem = ItemStack.EMPTY;
        } else {
            craftingRemainingItem = ItemStack.EMPTY;
        }


        // 当盒饭没有食用
        if (!pState.getValue(EMPTY)) {
            // 执行玩家吃下物品的方法
            pPlayer.eat(pLevel, stack);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            // 更新Property
            BlockState changeBlockState = pState.setValue(EMPTY, true);
            pLevel.setBlock(pPos, changeBlockState, 11);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos,
                    GameEvent.Context.of(pPlayer, pState));
            return InteractionResult.SUCCESS;
        } else {
            // 当盒饭食用完毕
            pLevel.playSound(null, pPos,
                    SoundEvents.WOOL_BREAK,
                    SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.removeBlock(pPos, false);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);

            // 食用后返还物品
            finishEating(craftingRemainingItem, pPlayer);
            return InteractionResult.SUCCESS;
        }
    }

}
