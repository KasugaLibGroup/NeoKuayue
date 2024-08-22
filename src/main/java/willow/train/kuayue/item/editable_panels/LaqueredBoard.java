package willow.train.kuayue.item.editable_panels;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LaqueredBoard extends Item {

    public LaqueredBoard(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @Nullable Level level,
            List<Component> tooltips,
            TooltipFlag tooltipFlag) {
        tooltips.add(Component.translatable("item.kuayue.laquered_board_tooltip"));
        super.appendHoverText(itemStack, level, tooltips, tooltipFlag);
    }
}
