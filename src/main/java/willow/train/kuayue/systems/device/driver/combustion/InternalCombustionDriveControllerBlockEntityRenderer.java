package willow.train.kuayue.systems.device.driver.combustion;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import kasuga.lib.core.client.frontend.rendering.RenderContext;
import kasuga.lib.core.client.model.BedrockModelLoader;
import kasuga.lib.core.client.model.anim_model.AnimModel;
import kasuga.lib.core.menu.locator.GuiMenuHolder;
import kasuga.lib.core.menu.targets.Target;
import kasuga.lib.core.menu.targets.WorldRendererTarget;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.initial.AllElements;

public class InternalCombustionDriveControllerBlockEntityRenderer extends SmartBlockEntityRenderer<InternalCombustionDriveControllerBlockEntity> {
    protected static AnimModel bedrockModel = BedrockModelLoader.getModel(
            AllElements.testRegistry.asResource("block/drive/drive_internal_combustion"),
            RenderType.cutoutMipped()
    );

    static {
        bedrockModel.init();
    }

    public InternalCombustionDriveControllerBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(
            InternalCombustionDriveControllerBlockEntity blockEntity,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int overlay
    ) {
        renderCommon(blockEntity.getBlockState(), blockEntity.getMenuHolder(), poseStack, bufferSource, packedLight, overlay, partialTicks);
    }

    public static void renderCommon(
            BlockState blockState,
            GuiMenuHolder holder,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int overlay,
            float partialTicks
    ){
        poseStack.pushPose();
        Direction facing = blockState.getValue(InternalCombustionDriveControllerBlock.FACING);
        int offset = blockState.getValue(InternalCombustionDriveControllerBlock.OFFSET);
        poseStack.translate(0.5,0,0.5);
        poseStack.mulPose(Quaternion.fromXYZ(0, facing.toYRot() * 3.141f / 180, 0));
        poseStack.translate(-0.5, 0, -0.5-(offset*(1/16F)));
        bedrockModel.render(poseStack, bufferSource, packedLight, overlay);

        poseStack.pushPose();
        poseStack.translate(1.125,1.1,0.925);
        poseStack.mulPose(Quaternion.fromXYZ((float) (22.5 * (Math.PI) / 180),(float) (Math.PI),0));
        poseStack.scale(0.0025f * 0.35f, 0.0025f * 0.35f, 0.0025f);

        RenderContext worldContext = new RenderContext(RenderContext.RenderContextType.WORLD);
        worldContext.setBufferSource(bufferSource);
        worldContext.setPoseStack(poseStack);
        worldContext.pushLight(packedLight);
        worldContext.pushLight(LightTexture.FULL_BRIGHT);
        worldContext.setSource(WorldRendererTarget.class);

        (holder.getMenu(0)).ifPresent(menu -> {
            WorldRendererTarget binding = menu.getBinding().apply(Target.WORLD_RENDERER);
            if(binding != null) {
                binding.render(worldContext);
            }
        });

        poseStack.popPose();
        poseStack.pushPose();

        poseStack.translate(0.725,1.1,0.925);
        poseStack.mulPose(Quaternion.fromXYZ((float) (22.5 * (Math.PI) / 180),(float) (Math.PI),0));
        poseStack.scale(0.0025f * 0.35f, 0.0025f * 0.35f, 0.0025f);

        (holder.getMenu(1)).ifPresent(menu -> {
            WorldRendererTarget binding = menu.getBinding().apply(Target.WORLD_RENDERER);
            if(binding != null) {
                binding.render(worldContext);
            }
        });

        poseStack.popPose();
        poseStack.popPose();
    }
}
