package willow.train.kuayue.systems.device.driver.combustion;

import com.jozufozu.flywheel.core.virtual.VirtualRenderWorld;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector4f;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.foundation.render.BlockEntityRenderHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import kasuga.lib.core.menu.locator.ContraptionBlockMenuLocator;
import kasuga.lib.core.menu.locator.GuiMenuHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.systems.device.AllDevicesMenus;
import willow.train.kuayue.systems.device.IEntityTrackingMovementBehavior;

import javax.annotation.Nullable;
import java.util.HashMap;

public class InternalCombustionDriveControllerMovementBehavior implements MovementBehaviour, IEntityTrackingMovementBehavior {
    protected HashMap<MovementContext, GuiMenuHolder> MENUS = new HashMap<>();


    @Override
    public void tick(MovementContext context) {
        if(!MENUS.containsKey(context)){
            ContraptionBlockMenuLocator locator = ContraptionBlockMenuLocator.fromLocator(context);
            locator.withLevel(context.world);
            GuiMenuHolder holder = new GuiMenuHolder.Builder()
                    .with(AllDevicesMenus.LKJ2000, (menu)->{
                        if(context.contraption.entity instanceof CarriageContraptionEntity carriageContraption){
                            menu.provideTrainData(new TrainDataHandler(Create.RAILWAYS.trains.get(carriageContraption.trainId)));
                        }
                    })
                    .with(AllDevicesMenus.CIR)
                    .locatedAt(locator)
                    .build();
            holder.enable(context.world);
            MENUS.put(context, holder);
        }
        if(MENUS.containsKey(context) && !context.world.isClientSide()) {
            ((ContraptionBlockMenuLocator) MENUS.get(context).getLocator()).update(context);
        }
    }


    @Override
    public void onEntityLeave(MovementContext behaviour) {
        if(!MENUS.containsKey(behaviour))
            return;
        GuiMenuHolder holder = MENUS.remove(behaviour);
        holder.disable();
    }

    @Override
    public boolean mustTickWhileDisabled() {
        return true;
    }

    private static BlockPos getLightPos(@Nullable Matrix4f lightTransform, BlockPos contraptionPos) {
        // Copied from Create mod
        if (lightTransform != null) {
            Vector4f lightVec = new Vector4f((float)contraptionPos.getX() + 0.5F, (float)contraptionPos.getY() + 0.5F, (float)contraptionPos.getZ() + 0.5F, 1.0F);
            lightVec.transform(lightTransform);
            return new BlockPos((double)lightVec.x(), (double)lightVec.y(), (double)lightVec.z());
        } else {
            return contraptionPos;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld, ContraptionMatrices matrices, MultiBufferSource buffer) {
        if(!MENUS.containsKey(context))
            return;
        GuiMenuHolder holder = MENUS.get(context);
        PoseStack ms = matrices.getModelViewProjection();
        ms.pushPose();
        ms.translate(context.localPos.getX(), context.localPos.getY(), context.localPos.getZ());
        InternalCombustionDriveControllerBlockEntityRenderer.renderCommon(
                context.state,
                holder,
                matrices.getModelViewProjection(),
                buffer,
                BlockEntityRenderHelper.getCombinedLight(context.world, getLightPos(matrices.getLight(), context.localPos), renderWorld, context.localPos),
                OverlayTexture.NO_OVERLAY,
                AnimationTickHolder.getPartialTicks()
        );
        ms.popPose();
    }
}
