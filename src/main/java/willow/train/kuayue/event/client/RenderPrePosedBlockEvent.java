package willow.train.kuayue.event.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import kasuga.lib.KasugaLib;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.model.SimpleModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import willow.train.kuayue.block.panels.SkirtBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.item.PanelBlockItem;
import willow.train.kuayue.item.SkirtBlockItem;
import willow.train.kuayue.item.SlabBlockItem;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RenderPrePosedBlockEvent {

    public static final ModelManager models = Minecraft.getInstance().getModelManager();
    @SubscribeEvent
    public static void renderBlock(RenderLevelStageEvent event) {
        if (!event.getStage().equals(RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS)) return;
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
            renderPanelBlock(event, level, result, panelItem);
            return;
        }
        if ((item.getItem()) instanceof SkirtBlockItem skirtItem) {
            renderSkirtBlock(event, level, result, skirtItem);
            return;
        }
        if ((item.getItem()) instanceof SlabBlockItem slabItem) {
            renderSlabBlock(event, level, result, slabItem);
            return;
        }
    }

    public static void renderPanelBlock(RenderLevelStageEvent event, Level level, BlockHitResult result, PanelBlockItem panelItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!state.is(Objects.requireNonNull(AllTags.SIDE_PLACEMENT.tag()))) return;
        if (!state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) return;
        if (!(result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING) ||
                result.getDirection() == state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite())) return;
        PanelBlockItem.Face face = panelItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
        showPreRenderedBlock(event, result.getBlockPos(), face, panelItem);
    }

    public static void renderSkirtBlock(RenderLevelStageEvent event, Level level, BlockHitResult result, SkirtBlockItem skirtItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!(state.getBlock() instanceof SkirtBlock)) return;
        PanelBlockItem.Face face = skirtItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
    }

    public static void renderSlabBlock(RenderLevelStageEvent event, Level level, BlockHitResult result, SlabBlockItem slabItem) {
        BlockState state = level.getBlockState(result.getBlockPos());
        if (!(state.getBlock() instanceof TrainSlabBlock)) return;
        PanelBlockItem.Face face = slabItem.getArrowDirection(result);
        if (!face.canShowArrow(level, result.getBlockPos(), result.getDirection())) return;
    }

    public static void showPreRenderedBlock(RenderLevelStageEvent event, BlockPos pos, PanelBlockItem.Face face, PanelBlockItem item) {
        TrainPanelBlock block = (TrainPanelBlock) item.getBlock();
        ResourceLocation resourceLocation = ForgeRegistries.BLOCKS.getKey(block);
        if (resourceLocation == null) return;
        SimpleModel model = ClientInit.testModel.getModel();
        model.renderType(RenderType::solid);
        model.render(event.getPoseStack(),
                Minecraft.getInstance().renderBuffers().bufferSource(), pos.getX(), pos.getY(), pos.getZ(), LightTexture.FULL_BLOCK, 0);
    }
}
