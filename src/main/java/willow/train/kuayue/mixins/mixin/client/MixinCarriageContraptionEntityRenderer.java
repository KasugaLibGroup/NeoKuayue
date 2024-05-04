package willow.train.kuayue.mixins.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import willow.train.kuayue.block.bogey.loco.renderer.QJMainRenderer;

@Mixin(value = CarriageContraptionEntityRenderer.class, remap = false)
public class MixinCarriageContraptionEntityRenderer {
    @Inject(method = "translateBogey", at = @At("TAIL"), remap = false)
    private static void sendValueToBogeyRenderer(
            PoseStack ms, CarriageBogey bogey, int bogeySpacing,
            float viewYRot, float viewXRot, float partialTicks,
            CallbackInfo ci) {
        QJMainRenderer.carriage = bogey.carriage;
    }
}
