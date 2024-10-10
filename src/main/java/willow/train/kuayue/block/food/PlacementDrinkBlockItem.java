package willow.train.kuayue.block.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.food.effect.EffectUtil;

import java.util.List;

public class PlacementDrinkBlockItem extends PlacementFoodBlockItem {
    public PlacementDrinkBlockItem(PlacementFoodBlock pBlock, Properties pProperties, boolean hasEffect, boolean hasTooltip) {
        super(pBlock, pProperties, hasEffect, hasTooltip);
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
