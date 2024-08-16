package willow.train.kuayue.block.panels.block_entity;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class CustomRenderedDoorRenderer implements BlockEntityRenderer<CustomRenderedDoorEntity> {

    public CustomRenderedDoorRenderer(BlockEntityRendererProvider.Context pContext) {}

    @Override
    public void render(
            CustomRenderedDoorEntity entity,
            float partial,
            PoseStack pose,
            MultiBufferSource buffer,
            int light,
            int overlay) {
        BlockState state = entity.getBlockState();

        Couple<PartialModel> models = entity.getModels(state);

        boolean open = entity.isOpen();
        boolean slide = entity.isSlideDoor();
        boolean leftSide = entity.isLeftSide(state);

        SuperByteBuffer lower = CachedBufferer.partial(models.get(true), state);
        SuperByteBuffer upper = CachedBufferer.partial(models.get(false), state);
        VertexConsumer consumer = buffer.getBuffer(RenderType.tripwire());

        pose.pushPose();
        float f = -state.getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite().toYRot();
        pose.translate(.5, 0, .5);
        pose.mulPose(Vector3f.YP.rotationDegrees(f));
        pose.translate(leftSide ? .5 : -.5, 0, -.5);
        Vec3 offset = entity.offset;
        if (!offset.equals(Vec3.ZERO))
            pose.translate((leftSide ? offset.x() : - offset.x()), offset.y(), offset.z());
        upper.translate(0, 1, 0);

        if(open && entity.animation_controller < 1) {
            entity.animation_controller += slide ? 0.025f : 0.05f;
        } else if (!open && entity.animation_controller > 0) {
            entity.animation_controller -= slide ? 0.025f : 0.05f;
        } else if(open && entity.animation_controller > 1){
            entity.animation_controller = 1;
        } else if(!open && entity.animation_controller < 0){
            entity.animation_controller = 0;
        }

        float counter = entity.animation_controller;

        if(slide) {
            float level1 = .4f;
            if(counter > level1) {
            double d = (.2 + ((counter - level1)/(1 - level1)) * .7) * (leftSide ? 1 : -1);
                lower.translate(d, 0, -.2);
                upper.translate(d, 0, -.2);
            } else {
                lower.translate(counter * (leftSide ? .2 : -.2) ,0, (counter/level1) * - .2);
                upper.translate(counter * (leftSide ? .2 : -.2) ,0, (counter/level1) * - .2);
            }
        } else {
            lower.rotateY(counter * (leftSide ? 90 : -90));
            upper.rotateY(counter * (leftSide ? 90 : -90));
        }
        lower.renderInto(pose, consumer);
        upper.renderInto(pose, consumer);
        pose.popPose();
    }
}
