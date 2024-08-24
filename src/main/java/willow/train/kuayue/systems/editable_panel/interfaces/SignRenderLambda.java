package willow.train.kuayue.systems.editable_panel.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;

@FunctionalInterface
public interface SignRenderLambda {

    void render(EditablePanelEntity blockEntity, float pPartialTick, PoseStack pPoseStack,
                MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay);
}
