package willow.train.kuayue.block.panels.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import willow.train.kuayue.block.panels.base.EditableTypeConstants;
import willow.train.kuayue.initial.registration.SignType;

public class EditablePanelRenderer implements BlockEntityRenderer<EditablePanelEntity> {

    @Override
    public void render(EditablePanelEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(pBlockEntity.editType)) {
                signType.getLambdaSupplier().get().render(pBlockEntity, pPartialTick, pPoseStack, pBufferSource, pPackedLight ,pPackedOverlay);
            }
        }
    }
}
