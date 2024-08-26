package willow.train.kuayue.event.client;

import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.model.SimpleModel;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.royawesome.jlibnoise.module.combiner.Min;
import willow.train.kuayue.block.panels.SkirtBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.item.PanelBlockItem;
import willow.train.kuayue.item.SkirtBlockItem;
import willow.train.kuayue.item.SlabBlockItem;

import java.io.IOException;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderArrowEvent {

    public static final LazyRecomputable<ImageMask>
            arrow_left, arrow_up, arrow_right, arrow_down;

    static {
            arrow_left = LazyRecomputable.of(
                    () -> {
                        try {
                            return ClientInit.arrow.getImage().get().getMask().rectangleUV(0, 0, .5f, .5f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            arrow_right = LazyRecomputable.of(
                    () -> {
                        try {
                            return ClientInit.arrow.getImage().get().getMask().rectangleUV(.5f, 0, 1, .5f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            arrow_up = LazyRecomputable.of(
                    () -> {
                        try {
                            return ClientInit.arrow.getImage().get().getMask().rectangleUV(.5f, .5f, 1, 1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            arrow_down = LazyRecomputable.of(
                    () -> {
                        try {
                            return ClientInit.arrow.getImage().get().getMask().rectangleUV(0, .5f, .5f, 1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
    }

    @SubscribeEvent
    public static void renderBlockBounds(RenderGuiEvent event) {
        Player player = Minecraft.getInstance().player;
        ClientLevel level = Minecraft.getInstance().level;
        HitResult hitResult = Minecraft.getInstance().hitResult;
        if (!(hitResult instanceof BlockHitResult result)) return;
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
        ImageMask texture = switch (face) {
            case UP -> arrow_up.get();
            case LEFT -> arrow_left.get();
            case RIGHT -> arrow_right.get();
            case DOWN -> arrow_down.get();
        };
        texture.rectangle(new Vector3f((float) width / 2f - 12f, (float) height / 2f - 12f, 0),
                ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 24, 24);
        texture.renderToGui();
    }


}
