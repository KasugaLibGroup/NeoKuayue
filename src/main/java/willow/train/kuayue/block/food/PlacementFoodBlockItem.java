package willow.train.kuayue.block.food;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;

public class PlacementFoodBlockItem extends BlockItem {

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
}
