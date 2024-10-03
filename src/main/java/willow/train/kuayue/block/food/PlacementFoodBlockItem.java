package willow.train.kuayue.block.food;

import kasuga.lib.core.base.item_helper.ExternalRemainderBlockItem;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        ItemStack itemStack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        PlacementFoodBlock.finishEating(pStack, (Player) pLivingEntity);
        return itemStack;
    }
}
