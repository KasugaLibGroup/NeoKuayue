package willow.train.kuayue.utils.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import willow.train.kuayue.Kuayue;

public class ComponentTranslationTool {

    public static final Component HELP_WITH_LSHIFT_COMPONENT = Component.translatable("tooltip.kuayue.abc.binding.explain.un_1").withStyle(ChatFormatting.GRAY)
            .append(Component.literal("「LShift」").withStyle(ChatFormatting.GOLD))
            .append(Component.translatable("tooltip.kuayue.abc.binding.explain.un_2").withStyle(ChatFormatting.GRAY));

    // 向某玩家展示
    public static void showMsg(Player pPlayer, String s, boolean b) {
        pPlayer.displayClientMessage(Component.translatable("msg." + Kuayue.MODID + "." + s), b);
    }

    public static void showSuccess(Player pPlayer, String s, boolean b) {
        pPlayer.displayClientMessage(Component.literal("✔ ").withStyle(ChatFormatting.GREEN).copy()
                .append(Component.translatable("msg." + Kuayue.MODID + "." + s).withStyle(ChatFormatting.GREEN)), b);
    }

    public static void showWarning(Player pPlayer, String s, boolean b) {
        pPlayer.displayClientMessage(Component.literal("● ").withStyle(ChatFormatting.YELLOW).copy()
                .append(Component.translatable("msg." + Kuayue.MODID + "." + s).withStyle(ChatFormatting.YELLOW)), b);
    }

    public static void showError(Player pPlayer, String s, boolean b) {
        pPlayer.displayClientMessage(Component.literal("❌ ").withStyle(ChatFormatting.RED).copy()
                .append(Component.translatable("msg." + Kuayue.MODID + "." + s).withStyle(ChatFormatting.RED)), b);
    }

    public static void showSuccess(Player pPlayer, boolean b, Component... components) {
        Component cx = Component.literal("✔ ").withStyle(ChatFormatting.GREEN);
        for(Component c : components) {
            cx = cx.copy().append(c).withStyle(ChatFormatting.GREEN);
        }
        pPlayer.displayClientMessage(cx, b);
    }

    public static void showWarning(Player pPlayer, boolean b, Component... components) {
        Component cx = Component.literal("● ").withStyle(ChatFormatting.YELLOW);
        for(Component c : components) {
            cx = cx.copy().append(c).withStyle(ChatFormatting.YELLOW);
        }
        pPlayer.displayClientMessage(cx, b);
    }

    public static void showError(Player pPlayer, boolean b, Component... components) {
        Component cx = Component.literal("❌ ").withStyle(ChatFormatting.RED);
        for(Component c : components) {
            cx = cx.copy().append(c).withStyle(ChatFormatting.RED);
        }
        pPlayer.displayClientMessage(cx, b);
    }

    public static Component translatable(String s, ChatFormatting formatting) {
        return Component.translatable("msg." + Kuayue.MODID + "." + s).withStyle(formatting);
    }

    public static Component translatable(String s) {
        return Component.translatable("msg." + Kuayue.MODID + "." + s);
    }

    public static Component literal(String s) {
        return Component.literal(s);
    }
}
