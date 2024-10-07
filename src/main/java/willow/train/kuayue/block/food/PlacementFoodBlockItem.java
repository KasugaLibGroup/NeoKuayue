package willow.train.kuayue.block.food;

import kasuga.lib.core.base.item_helper.ExternalRemainderBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlacementFoodBlockItem extends ExternalRemainderBlockItem {

    public PlacementFoodBlockItem(PlacementFoodBlock pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext pContext, BlockState pState) {
        Direction clickedFace = pContext.getClickedFace();
        if (clickedFace != Direction.UP)
            return false;
        return super.canPlace(pContext, pState);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        // 获取返还物品栈
        ItemStack craftingRemainingItem = pStack.getCraftingRemainingItem();
        super.finishUsingItem(pStack, pLevel, pLivingEntity);
        if (pStack.isEmpty())
            return craftingRemainingItem;
        if (pLivingEntity instanceof Player player) {
            ItemStack itemStack = new ItemStack(craftingRemainingItem.getItem());
            if (!(player.getInventory().add(itemStack))) {
                player.drop(itemStack, false);
            }
        }
        return pStack;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("item.kuayue.tooltip."+ this)
                .withStyle(ChatFormatting.BLUE));
    }
}
