package willow.train.kuayue.block.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.initial.AllFoods;
import willow.train.kuayue.initial.AllItems;

public class PlacementFoodBlock extends Block {

    protected final FoodType FOOD_TYPE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static VoxelShape BOX_SHAPE = Block.box(2, 0, 2, 14, 2, 14);
    public static VoxelShape BOWL_SHAPE = Block.box(4, 0, 4, 12, 4, 12);
    public static VoxelShape BOTTLE_SHAPE = Block.box(6, 0, 6, 10, 6, 10);

    public PlacementFoodBlock(Properties pProperties, FoodType foodType) {
        super(pProperties);
        this.FOOD_TYPE = foodType;
        this.registerDefaultState(
                this.getStateDefinition().any().setValue(FACING, Direction.EAST)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getFoodShape(pState);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        // 获取手中物品栈
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        // 成功吃掉食物
        if (eat(pLevel, pPos, pState, pPlayer).consumesAction()) {
            return InteractionResult.SUCCESS;
        }
        // 没有成功吃掉食物，且手中物品栈为空。
        if (itemstack.isEmpty()) {
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }

    protected InteractionResult eat(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        // 如果玩家不可以进食，则直接PASS。
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            // 获取食物方块对应的物品栈
            ItemStack stack = pState.getBlock().asItem().getDefaultInstance();
            // 获取食物的枚举类型
            FoodType foodType = ((PlacementFoodBlock) pState.getBlock()).FOOD_TYPE;
            // 获取食用后剩余物品栈
            ItemStack craftingRemainingItem =
                    new ItemStack(stack.getItem().getCraftingRemainingItem(stack).getItem());
            // 执行玩家吃下物品的方法
            pPlayer.eat(pLevel, stack);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            pLevel.playSound(null, pPos,
                    foodType == FoodType.BOX ? SoundEvents.GENERIC_EAT : SoundEvents.GENERIC_DRINK,
                    SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.removeBlock(pPos, false);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            // 食用后返还物品
            finishEating(craftingRemainingItem, pPlayer);
            return InteractionResult.SUCCESS;
        }
    }

    protected void finishEating(ItemStack craftingRemainingItem, Player pPlayer) {

        ItemStack itemStack = new ItemStack(craftingRemainingItem.getItem());
        if (!(pPlayer.getInventory().add(itemStack))) {
            pPlayer.drop(itemStack, false);
        }
    }

    public FoodType getFoodType() {
        return FOOD_TYPE;
    }

    public VoxelShape getFoodShape(BlockState pState) {
        return switch (((PlacementFoodBlock) pState.getBlock()).FOOD_TYPE) {
            case BOX -> BOX_SHAPE;
            case BOWL -> BOWL_SHAPE;
            case BOTTLE -> BOTTLE_SHAPE;
            default -> BOX_SHAPE;
        };
    }

    public enum FoodType {
        BOX, BOWL, BOTTLE;
    }
}
