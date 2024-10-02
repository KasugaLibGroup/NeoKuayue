package willow.train.kuayue.block.food;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

import static willow.train.kuayue.initial.AllFoods.TRAIN_DIET_EFFECT;

public class PlacementFoodBlock extends Block {

    public final FoodType FOOD_TYPE;

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public PlacementFoodBlock(Properties pProperties, FoodType foodType) {
        super(pProperties);
        this.FOOD_TYPE = foodType;
        this.registerDefaultState(
                this.getStateDefinition().any().setValue(FACING, Direction.EAST)
        );
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.HALF_HEIGHT_TOP_AABB;
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

        // 客户端侧
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

    protected static InteractionResult eat(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        // 如果玩家不可以进食，则直接PASS。
        if (!pPlayer.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            ItemStack stack = pState.getBlock().asItem().getDefaultInstance();
            pPlayer.eat(pLevel, stack);
            pLevel.gameEvent(pPlayer, GameEvent.EAT, pPos);
            pLevel.playSound(null, pPos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.0F);
            pLevel.removeBlock(pPos, false);
            pLevel.gameEvent(pPlayer, GameEvent.BLOCK_DESTROY, pPos);
            return InteractionResult.SUCCESS;
        }
    }

    public enum FoodType {
        BOX, CUP;
    }
}
