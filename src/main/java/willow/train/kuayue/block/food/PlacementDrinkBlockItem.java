package willow.train.kuayue.block.food;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class PlacementDrinkBlockItem extends PlacementFoodBlockItem {
    public PlacementDrinkBlockItem(PlacementFoodBlock pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
