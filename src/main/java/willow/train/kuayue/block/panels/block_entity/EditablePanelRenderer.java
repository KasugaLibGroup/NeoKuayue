package willow.train.kuayue.block.panels.block_entity;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.FormattedCharSequence;
import willow.train.kuayue.systems.editable_panel.EditableTypeConstants;
import willow.train.kuayue.systems.editable_panel.SignType;

public class EditablePanelRenderer implements BlockEntityRenderer<EditablePanelEntity> {

    private final Font font;

    public EditablePanelRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.getFont();
    }

    @Override
    public void render(EditablePanelEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(pBlockEntity.getEditType())) {
                signType.getLambdaSupplier().get().render(pBlockEntity, pPartialTick, pPoseStack, pBufferSource, pPackedLight ,pPackedOverlay);
                return;
            }
        }
    }

    private void renderText(
            FormattedCharSequence[] aformattedcharsequence,
            int index,
            int pX,
            int pY,
            EditablePanelEntity pBlockEntity,
            PoseStack pPoseStack,
            MultiBufferSource pBufferSource,
            int pPackedLight) {

        FormattedCharSequence formattedcharsequence = aformattedcharsequence[index];
        this.font.drawInBatch(
                formattedcharsequence,
                pX,
                pY,
                getDarkColor(pBlockEntity) * 2,
                false,
                pPoseStack.last().pose(),
                pBufferSource,
                false,
                0,
                pPackedLight);
    }

    private static int getDarkColor(EditablePanelEntity pBlockEntity) {
        int i = pBlockEntity.getSignColor();
        double d0 = 0.4D;
        int j = (int) ((double) NativeImage.getR(i) * 0.4D);
        int k = (int) ((double) NativeImage.getG(i) * 0.4D);
        int l = (int) ((double) NativeImage.getB(i) * 0.4D);
        return NativeImage.combine(0, l, k, j);
    }
}
