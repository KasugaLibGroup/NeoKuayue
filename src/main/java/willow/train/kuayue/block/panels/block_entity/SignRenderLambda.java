package willow.train.kuayue.block.panels.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;

@FunctionalInterface
public interface SignRenderLambda {

    void render(EditablePanelEntity blockEntity, float pPartialTick, PoseStack pPoseStack,
                MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay);
}
