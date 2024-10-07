package willow.train.kuayue.block.food.instant_noodles;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.food.PlacementDrinkBlockItem;
import willow.train.kuayue.block.food.PlacementFoodBlock;

import java.util.List;

public class SoakedInstantNoodlesBlockItem extends PlacementDrinkBlockItem {
    public SoakedInstantNoodlesBlockItem(PlacementFoodBlock pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("item.kuayue.tooltip.soaked_instant_noodles.tip1")
                .withStyle(ChatFormatting.BLUE));
        pTooltip.add(Component.translatable("item.kuayue.tooltip.soaked_instant_noodles.tip2")
                .withStyle(ChatFormatting.RED));
    }
}
