package willow.train.kuayue.systems.device.driver.seat;

import com.jozufozu.flywheel.core.virtual.VirtualRenderWorld;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector4f;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.foundation.render.BlockEntityRenderHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class DoubleDriverSeatBlockMovementBehaviour implements MovementBehaviour {
    private static BlockPos getLightPos(@Nullable Matrix4f lightTransform, BlockPos contraptionPos) {
        if (lightTransform != null) {
            Vector4f lightVec = new Vector4f(
                (float)contraptionPos.getX() + 0.5F, 
                (float)contraptionPos.getY() + 0.5F, 
                (float)contraptionPos.getZ() + 0.5F, 
                1.0F
            );
            lightVec.transform(lightTransform);
            return new BlockPos((double)lightVec.x(), (double)lightVec.y(), (double)lightVec.z());
        } else {
            return contraptionPos;
        }
    }

    @Override
    public void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld, 
            ContraptionMatrices matrices, MultiBufferSource buffer) {
        PoseStack ms = matrices.getModelViewProjection();
        ms.pushPose();
        
        // 设置渲染位置
        ms.translate(context.localPos.getX(), context.localPos.getY(), context.localPos.getZ());
        
        // 渲染模型
        DoubleDriverSeatBlockRenderer.renderCommon(
            context.state,
            matrices.getModelViewProjection(),
            buffer,
            BlockEntityRenderHelper.getCombinedLight(
                context.world, 
                getLightPos(matrices.getLight(), context.localPos), 
                renderWorld, 
                context.localPos
            ),
            OverlayTexture.NO_OVERLAY
        );
        
        ms.popPose();
    }
}
