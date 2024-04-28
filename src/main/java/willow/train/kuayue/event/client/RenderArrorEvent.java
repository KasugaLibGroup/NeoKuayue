package willow.train.kuayue.event.client;

import kasuga.lib.core.client.render.texture.SimpleTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.item.PanelBlockItem;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderArrorEvent {

    @SubscribeEvent
    public static void renderBlockBounds(RenderGuiEvent event) {
        Player player = Minecraft.getInstance().player;
        ClientLevel level = Minecraft.getInstance().level;
        BlockHitResult result = (BlockHitResult) Minecraft.getInstance().hitResult;
        if (result == null) return;
        if (player == null) return;
        if (player.isShiftKeyDown()) return;
        if (level == null) return;
        ItemStack item = player.getMainHandItem();
        if (!(item.getItem() instanceof PanelBlockItem panelItem)) return;
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!state.is(Objects.requireNonNull(AllTags.SIDE_PLACEMENT.tag()))) return;
        if (!state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) return;
        if (!(result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING) ||
                result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite())) return;
        PanelBlockItem.Face face = panelItem.getArrorDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
        int width = event.getWindow().getGuiScaledWidth();
        int height = event.getWindow().getGuiScaledHeight();
        SimpleTexture texture = switch (face) {
            case UP -> ClientInit.arrow_up;
            case LEFT -> ClientInit.arrow_left;
            case RIGHT -> ClientInit.arrow_right;
            case DOWN -> ClientInit.arrow_down;
        };
        texture.renderCentered(width / 2, height / 2, 24, 24);
    }
}
