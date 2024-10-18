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
            frame.rotateY(180).translate(0, 0.225, 0.17).render(ms, light, vb);

            for (int side = -1; side < 2; side++) {
                if (!inInstancedContraption) ms.pushPose();
                BogeyModelData wheel = wheels[side + 1];
                wheel.translate(0, 0.88, ((double) side) * 1.805d)
                        .rotateX(wheelAngle)
                        .render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();
            }
            return;
        }

        if (direction == Direction.NORTH || direction == Direction.WEST) {
            frame.rotateY(180).translate(0, 0.225, 0.17).render(ms, light, vb);

            for (int side = -1; side < 2; side++) {
                if (!inInstancedContraption) ms.pushPose();
                BogeyModelData wheel = wheels[side + 1];
                wheel.translate(0, 0.88, ((double) side) * 1.805d)
                        .rotateX(wheelAngle)
                        .render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();
            }
            return;
        }

        frame.translate(0, 0.225, 0.17).render(ms, light, vb);

        for (int side = -1; side < 2; side++) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[side + 1];
            wheel.translate(0, 0.88, ((double) side) * 1.805d)
                    .rotateX(wheelAngle)
                    .render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
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

            if (!inContraption) {
                frame.translate(0, 0.225, 0.17).render(ms, light, vb);
                for (int side = -1; side < 2; side++) {
                    if (!inInstancedContraption) ms.pushPose();
                    BogeyModelData wheel = wheels[side + 1];
                    wheel.translate(0, 0.88, ((double) side) * 1.805d)
                            .rotateX(wheelAngle)
                            .render(ms, light, vb);
                    if (!inInstancedContraption) ms.popPose();
                }
                return;
            }

            if (direction == Direction.NORTH || direction == Direction.WEST) {
                frame.translate(0, 0.225, 0.17).render(ms, light, vb);

                for (int side = -1; side < 2; side++) {
                    if (!inInstancedContraption) ms.pushPose();
                    BogeyModelData wheel = wheels[side + 1];
                    wheel.translate(0, 0.88, ((double) side) * 1.805d)
                            .rotateX(-wheelAngle)
                            .render(ms, light, vb);
                    if (!inInstancedContraption) ms.popPose();
                }
                return;
            }

            frame.rotateY(180).translate(0, 0.225, 0.17).render(ms, light, vb);

            for (int side = -1; side < 2; side++) {
                if (!inInstancedContraption) ms.pushPose();
                BogeyModelData wheel = wheels[side + 1];
                wheel.translate(0, 0.88, ((double) side) * 1.805d)
                        .rotateX(-wheelAngle)
                        .render(ms, light, vb);
                if (!inInstancedContraption) ms.popPose();
            }
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
