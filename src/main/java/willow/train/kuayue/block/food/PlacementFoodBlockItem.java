package willow.train.kuayue.block.food;

import kasuga.lib.core.base.item_helper.ExternalRemainderBlockItem;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

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
}
