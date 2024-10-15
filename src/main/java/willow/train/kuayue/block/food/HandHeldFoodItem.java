package willow.train.kuayue.block.food;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.food.effect.EffectUtil;

import java.util.List;

public class HandHeldFoodItem extends Item {

    public HandHeldFoodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        EffectUtil.addFoodEffectToTooltip(pStack, pTooltipComponents);
    }
}
