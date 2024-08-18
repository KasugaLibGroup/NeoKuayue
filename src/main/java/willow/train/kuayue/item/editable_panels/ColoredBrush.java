package willow.train.kuayue.item.editable_panels;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.initial.item.EditablePanelItem;

import java.util.List;

public class ColoredBrush extends Item {

    public ColoredBrush(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @Nullable Level level,
            List<Component> tooltips,
            TooltipFlag tooltipFlag) {
        tooltips.add(
                Component.translatable("item.kuayue.colored_brush.tooltip_1")
                        .withStyle(ChatFormatting.RED));
        tooltips.add(
                Component.translatable("item.kuayue.colored_brush.tooltip_2")
                        .withStyle(ChatFormatting.GOLD));
        tooltips.add(
                Component.translatable("item.kuayue.colored_brush.tooltip_3")
                        .withStyle(ChatFormatting.GREEN));
        tooltips.add(
                Component.translatable("item.kuayue.colored_brush.tooltip_4")
                        .withStyle(ChatFormatting.BLUE));
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTag().putInt("durability", 256);
        return stack;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level pLevel, Player pPlayer) {
        stack.getOrCreateTag().putInt("durability", 256);
        super.onCraftedBy(stack, pLevel, pPlayer);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        if(!pStack.is(EditablePanelItem.COLORED_BRUSH.getItem())) return false;
        return pStack.getOrCreateTag().getInt("durability") < 256;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        if(!pStack.is(EditablePanelItem.COLORED_BRUSH.getItem())) return 0;
        return Math.round(((float) pStack.getOrCreateTag().getInt("durability")) / 256 * 13f);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        float percentage = ((float) pStack.getOrCreateTag().getInt("durability")) / 256;
        float f = Math.max(0.0F, percentage);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(group)) {
            stacks.add(getDefaultInstance());
        }
    }
}
