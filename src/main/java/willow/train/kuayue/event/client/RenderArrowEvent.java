package willow.train.kuayue.event.client;

import kasuga.lib.core.client.render.texture.SimpleTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.block.panels.SkirtBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.item.PanelBlockItem;
import willow.train.kuayue.item.SkirtBlockItem;
import willow.train.kuayue.item.SlabBlockItem;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderArrowEvent {

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
        if ((item.getItem() instanceof PanelBlockItem panelItem)) {
            renderPanelArrow(event, level, result, panelItem);
            return;
        }
        if ((item.getItem()) instanceof SkirtBlockItem skirtItem) {
            renderSkirtArrow(event, level, result, skirtItem);
            return;
        }
        if ((item.getItem()) instanceof SlabBlockItem slabItem) {
            renderSlabArrow(event, level, result, slabItem);
            return;
        }
    }

    public static void renderPanelArrow(RenderGuiEvent event, Level level, BlockHitResult result, PanelBlockItem panelItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!state.is(Objects.requireNonNull(AllTags.SIDE_PLACEMENT.tag()))) return;
        if (!state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) return;
        if (!(result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING) ||
                result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite())) return;
        PanelBlockItem.Face face = panelItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
        showArrow(event, face);
    }

    public static void renderSkirtArrow(RenderGuiEvent event, Level level, BlockHitResult result, SkirtBlockItem skirtItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!(state.getBlock() instanceof SkirtBlock)) return;
        PanelBlockItem.Face face = skirtItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
        showArrow(event, face);
    }

    public static void renderSlabArrow(RenderGuiEvent event, Level level, BlockHitResult result, SlabBlockItem slabItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!(state.getBlock() instanceof TrainSlabBlock)) return;
        PanelBlockItem.Face face = slabItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
        showArrow(event, face);
    }

    public static void showArrow(RenderGuiEvent event, PanelBlockItem.Face face) {
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
