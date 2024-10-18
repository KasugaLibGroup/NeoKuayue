package willow.train.kuayue.block.bogey.loco.renderer;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.foundation.utility.NBTHelper;
import kasuga.lib.core.create.BogeyDataConstants;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.create.AllLocoBogeys;

public class SS3Renderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel
            SS3_FRAME = new PartialModel(asBlockModelResource("bogey/ss3/ss3_frame")),
            SS3_WHEEL = new PartialModel(asBlockModelResource("bogey/ss3/ss3_wheel"));

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {
        boolean forwards = BogeyDataConstants.isForwards(bogeyData, inContraption);

        Direction direction =
                bogeyData.contains(BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY)
                        ? NBTHelper.readEnum(
                        bogeyData,
                        BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY,
                        Direction.class)
                        : Direction.NORTH;

        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(SS3_FRAME, ms, inInstancedContraption);
        BogeyModelData[] wheels = getTransform(SS3_WHEEL, ms, inInstancedContraption, 3);

        if (!inContraption) {
            // 正向转向架未组装架体
            frame.rotateY(180).translate(0, 0.012, 0).render(ms, light, vb);

            // 正向转向架未组装轮对
            if (!inInstancedContraption) ms.pushPose();
            wheels[0].translate(0, 0.905, -0.01).rotateY(180).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[1].translate(0, 0.905, 1.96).rotateY(180).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[2].translate(0, 0.905, -2.11).rotateY(180).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            return;
        }

        if (direction == Direction.NORTH || direction == Direction.WEST) {
            // 正向转向架北西方向架体
            frame.rotateY(180).translate(0, 0.012, 0).render(ms, light, vb);

            // 正向转向架北西方向轮对
            if (!inInstancedContraption) ms.pushPose();
            wheels[0].translate(0, 0.905, -0.01).rotateY(180).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[1].translate(0, 0.905, 1.96).rotateY(180).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[2].translate(0, 0.905, -2.11).rotateY(180).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            return;
        }

        // 正向转向架南东方向架体
        frame.translate(0, 0.012, 0).render(ms, light, vb);

        // 正向转向架南东方向轮对
        if (!inInstancedContraption) ms.pushPose();
        wheels[0].translate(0, 0.905, 0.01).rotateX(wheelAngle).render(ms, light, vb);
        if (!inInstancedContraption) ms.popPose();

        if (!inInstancedContraption) ms.pushPose();
        wheels[1].translate(0, 0.905, 2.11).rotateX(wheelAngle).render(ms, light, vb);
        if (!inInstancedContraption) ms.popPose();

        if (!inInstancedContraption) ms.pushPose();
        wheels[2].translate(0, 0.905, -1.96).rotateX(wheelAngle).render(ms, light, vb);
        if (!inInstancedContraption) ms.popPose();
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllLocoBogeys.ss3.getSize();
    }

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, SS3_FRAME);
        this.createModelInstance(materialManager, SS3_WHEEL, 3);
    }

    public static class Backward extends BogeyRenderer {

        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {
            boolean forwards = BogeyDataConstants.isForwards(bogeyData, inContraption);

            Direction direction =
                    bogeyData.contains(BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY)
                            ? NBTHelper.readEnum(
                            bogeyData,
                            BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY,
                            Direction.class)
                            : Direction.NORTH;

            wheelAngle = -wheelAngle;
            boolean inInstancedContraption = vb == null;

            BogeyModelData frame = getTransform(SS3_FRAME, ms, inInstancedContraption);
            BogeyModelData[] wheels = getTransform(SS3_WHEEL, ms, inInstancedContraption, 3);

            // 反向转向架未组装
            if (!inContraption) {
                // 未组装架体
                frame.translate(0, 0.012, 0).render(ms, light, vb);

                // 未组装轮对
                if (!inInstancedContraption) ms.pushPose();
                wheels[0].translate(0, 0.905, 0.01).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();

                if (!inInstancedContraption) ms.pushPose();
                wheels[1].translate(0, 0.905, -1.96).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();

                if (!inInstancedContraption) ms.pushPose();
                wheels[2].translate(0, 0.905, 2.115).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();
                return;
            }

            // 反向转向架北西方向已组装
            if (direction == Direction.NORTH || direction == Direction.WEST) {
                // 北西方向已组装架体
                frame.translate(0, 0.012, 0).render(ms, light, vb);

                // 北西方向已组装轮对
                if (!inInstancedContraption) ms.pushPose();
                wheels[0].translate(0, 0.905, 0.01).rotateX(-wheelAngle).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();

                if (!inInstancedContraption) ms.pushPose();
                wheels[1].translate(0, 0.905, -1.96).rotateX(-wheelAngle).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();

                if (!inInstancedContraption) ms.pushPose();
                wheels[2].translate(0, 0.905, 2.115).rotateX(-wheelAngle).render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();

                return;
            }

            // 反向转向架南东方向已组装
            // 南东方向已组装架体
            frame.rotateY(180).translate(0, 0.012, 0).render(ms, light, vb);

            // 南东方向已组装轮对
            if (!inInstancedContraption) ms.pushPose();
            wheels[0].translate(0, 0.905, -0.01).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[1].translate(0, 0.905, -2.115).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();

            if (!inInstancedContraption) ms.pushPose();
            wheels[2].translate(0, 0.905, 1.955).rotateX(-wheelAngle).render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }

        @Override
        public BogeySizes.BogeySize getSize() {
            return AllLocoBogeys.ss3Backward.getSize();
        }

        @Override
        public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
            this.createModelInstance(materialManager, SS3_FRAME);
            this.createModelInstance(materialManager, SS3_WHEEL, 3);
        }
    }
}
