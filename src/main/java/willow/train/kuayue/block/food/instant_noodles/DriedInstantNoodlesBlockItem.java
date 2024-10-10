package willow.train.kuayue.block.food.instant_noodles;

import com.simibubi.create.foundation.particle.AirParticleData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.block.food.PlacementFoodBlockItem;
import willow.train.kuayue.block.panels.deco.TeaBoilerBlock;
import willow.train.kuayue.initial.food.AllFoods;

import java.util.List;

public class DriedInstantNoodlesBlockItem extends PlacementFoodBlockItem {
    public DriedInstantNoodlesBlockItem(PlacementFoodBlock pBlock, Properties pProperties, boolean hasEffect, boolean hasTooltip) {
        super(pBlock, pProperties, hasEffect, hasTooltip);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        // 获取右键点击的方块位置
        BlockPos pos = pContext.getClickedPos();
        // 获取世界对象
        Level level = pContext.getLevel();
        // 获取到右键点击到的方块对象
        BlockState clickedBlock = level.getBlockState(pos);

        if (clickedBlock.getBlock() instanceof TeaBoilerBlock) {
            // 若为茶炉方块，则替换物品栈。
            Player player = pContext.getPlayer();
            InteractionHand hand = pContext.getHand();
            assert player != null;
            ItemStack itemstack = player.getItemInHand(hand);
            level.playSound(player, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_DRINK, SoundSource.NEUTRAL, 1.0F, 1.0F);
            // 替换玩家手中物品栈为已冲泡的方便面
            player.setItemInHand(hand, ItemUtils.createFilledResult(itemstack, player,
                    new ItemStack(AllFoods.SOAKED_INSTANT_NOODLES.item.getItem()), false));
            // 加点特效
            spawnParticles(level, pos);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useOn(pContext);
    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;

        double d1 = 0.1 * randomsource.nextFloat();
        double d2 = 0.1 * randomsource.nextFloat();
        double d3 = 0.1 * randomsource.nextFloat();
        pLevel.addParticle(new AirParticleData(),
                pPos.getX() + 0.4 + d1, pPos.getY() + 0.7 + d2, pPos.getZ() + 0.4 + d3,
                0.0D, 0.1D, 0.0D);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("item.kuayue.tooltip.dried_instant_noodles.tip1")
                .withStyle(ChatFormatting.BLUE));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
