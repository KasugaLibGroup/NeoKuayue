package willow.train.kuayue.block.panels.block_entity;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedEndfaceEntity;

public class CustomRenderedEndFaceRenderer implements BlockEntityRenderer<CustomRenderedEndfaceEntity> {
    public static float STEP = 0.025f;

    public CustomRenderedEndFaceRenderer(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(
            CustomRenderedEndfaceEntity pBlockEntity,
            float pPartialTick,
            PoseStack pose,
            MultiBufferSource pBufferSource,
            int pPackedLight,
            int pPackedOverlay) {
        boolean is_rotate = pBlockEntity.isRotateDoor();
        BlockState blockState = pBlockEntity.getBlockState();
        boolean isOpened = pBlockEntity.isOpen();
        Couple<PartialModel> doorModels = pBlockEntity.getModels();
        if(doorModels == null) return;
        PartialModel left = doorModels.get(true);
        PartialModel right = doorModels.get(false);
        PartialModel frame = pBlockEntity.getFrameModel();
        float f = -blockState.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite().toYRot() - 90;
        SuperByteBuffer leftBuffer = left == null ? null : CachedBufferer.partial(left, blockState);
        SuperByteBuffer rightBuffer = right == null ? null : CachedBufferer.partial(right, blockState);
        pose.pushPose();
        pose.translate(0.5f, -1f, 0.5f);
        pose.mulPose(Vector3f.YP.rotationDegrees(f + 90f));
        if (frame != null) {
            pose.translate(-.5, 2, -.5);
            SuperByteBuffer frameBuffer = CachedBufferer.partial(frame, blockState);
            frameBuffer.renderInto(pose, pBufferSource.getBuffer(RenderType.cutout()));
            pose.translate(.5, -2, .5);
        }
        pose.translate(0, 1, 0);
        pose.mulPose(Vector3f.YN.rotationDegrees(-90f));
        if (leftBuffer != null)
            leftBuffer.translate(-.6f, 0, .375f);
        if (rightBuffer != null)
            rightBuffer.translate(-.6f, 0, -.375f);
        float level1 = .4f;
        if(isOpened && pBlockEntity.counter < 1) {
            pBlockEntity.counter += STEP;
        } else if (!isOpened && pBlockEntity.counter > 0) {
            pBlockEntity.counter -= STEP;
        }
        if(!is_rotate) {
            double distance_1 = .2;
            if (pBlockEntity.counter < level1) {
                double ctk = (pBlockEntity.counter)/level1;
                if (leftBuffer != null)
                    leftBuffer.translate(ctk * distance_1, 0, 0);
                if (rightBuffer != null)
                    rightBuffer.translate(ctk * distance_1, 0, 0);
            } else {
                double ctk = (pBlockEntity.counter - level1) / (1 - level1);
                double level2 = .4;
                if (leftBuffer != null)
                    leftBuffer.translate(distance_1, 0, ctk * level2);
                if (rightBuffer != null)
                    rightBuffer.translate(distance_1, 0, -ctk * level2);
            }
        } else {
            if (leftBuffer != null)
                leftBuffer.rotateY(pBlockEntity.counter * 90);
            if (rightBuffer != null)
                rightBuffer.rotateY(- pBlockEntity.counter * 90);
        }
        if (leftBuffer != null)
            leftBuffer.renderInto(pose, pBufferSource.getBuffer(RenderType.cutout()));
        if (rightBuffer != null)
            rightBuffer.renderInto(pose, pBufferSource.getBuffer(RenderType.cutout()));
        pose.popPose();
    }
}
