package willow.train.kuayue.block.bogey.carriage.renderer;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.NBTHelper;
import kasuga.lib.core.create.BogeyDataConstants;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.create.AllCarriageBogeys;

public class TKZ2Renderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel
            TKZ2_FRAME = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_frame")),
            TKZ2_WHEEL = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_wheel")),
            TKZ2_MOTOR_FRAME = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_motor_frame")),
            TKZ2_MOTOR_WHEEL = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_motor_wheel")),
            TKZ2_MOTOR_PONY_WHEEL = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_motor_pony_wheel"));

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {

        light *= 1.1;
        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(TKZ2_FRAME, ms, inInstancedContraption);
        frame.translate(0, 0.195, 0).render(ms, light, vb);

        BogeyModelData[] wheels =
                getTransform(TKZ2_WHEEL, ms, inInstancedContraption, 2);

        for (int side : Iterate.positiveAndNegative) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[(side + 1) / 2];
            wheel.translate(0, 0.695, ((double) side) * 0.97d).rotateX(wheelAngle);
            wheel.render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCarriageBogeys.tkz2.getSize();
    }

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, TKZ2_FRAME);
        this.createModelInstance(materialManager, TKZ2_WHEEL, 2);
    }

    public static class Motor extends BogeyRenderer {

        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {

            light *= 1.1;
            Direction direction =
                    bogeyData.contains(BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY)
                            ? NBTHelper.readEnum(
                            bogeyData,
                            BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY,
                            Direction.class)
                            : Direction.NORTH;

            boolean inInstancedContraption = vb == null;

            BogeyModelData frame = getTransform(TKZ2_MOTOR_FRAME, ms, inInstancedContraption);
            BogeyModelData wheel = getTransform(TKZ2_WHEEL, ms, inInstancedContraption);
            BogeyModelData motorWheel = getTransform(TKZ2_MOTOR_WHEEL, ms, inInstancedContraption);
            BogeyModelData motorPonyWheel = getTransform(TKZ2_MOTOR_PONY_WHEEL, ms, inInstancedContraption);

            if (!inContraption) {
                frame.translate(0, 0.125, 0).render(ms, light, vb);
                wheel.translate(0, 0.695, 0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorWheel.translate(0, 0.695, -0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorPonyWheel.translate(0, 0.721, -1.94).rotateX(wheelAngle * 3.256).render(ms, light, vb);
                return;
            }

            if (direction == Direction.NORTH || direction == Direction.WEST) {
                frame.translate(0, 0.125, 0).render(ms, light, vb);
                wheel.translate(0, 0.695, 0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorWheel.translate(0, 0.695, -0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorPonyWheel.translate(0, 0.721, -1.94).rotateX(wheelAngle * 3.256).render(ms, light, vb);
                return;
            }

            frame.rotateY(180).translate(0, 0.125, 0).render(ms, light, vb);
            wheel.rotateY(180).translate(0, 0.695, 0.97).rotateX(-wheelAngle * 1.2).render(ms, light, vb);
            motorWheel.rotateY(180).translate(0, 0.695, -0.97).rotateX(-wheelAngle * 1.2).render(ms, light, vb);
            motorPonyWheel.rotateY(180).translate(0, 0.721, -1.94).rotateX(-wheelAngle * 3.256).render(ms, light, vb);
        }

        @Override
        public BogeySizes.BogeySize getSize() {
            return AllCarriageBogeys.tkz2Motor.getSize();
        }

        @Override
        public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
            this.createModelInstance(
                    materialManager, TKZ2_MOTOR_FRAME, TKZ2_WHEEL, TKZ2_MOTOR_WHEEL, TKZ2_MOTOR_PONY_WHEEL);
        }
    }

    public static class MotorBackward extends BogeyRenderer {

        @Override
        public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {
            light *= 1.1;
            Direction direction =
                    bogeyData.contains(BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY)
                            ? NBTHelper.readEnum(
                            bogeyData,
                            BogeyDataConstants.BOGEY_ASSEMBLY_DIRECTION_KEY,
                            Direction.class)
                            : Direction.NORTH;

            boolean inInstancedContraption = vb == null;

            BogeyModelData frame = getTransform(TKZ2_MOTOR_FRAME, ms, inInstancedContraption);
            BogeyModelData wheel = getTransform(TKZ2_WHEEL, ms, inInstancedContraption);
            BogeyModelData motorWheel = getTransform(TKZ2_MOTOR_WHEEL, ms, inInstancedContraption);
            BogeyModelData motorPonyWheel = getTransform(TKZ2_MOTOR_PONY_WHEEL, ms, inInstancedContraption);


            if (!inContraption) {
                frame.rotateY(180).translate(0, 0.125, 0).render(ms, light, vb);
                wheel.rotateY(180).translate(0, 0.695, 0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorWheel.rotateY(180).translate(0, 0.695, -0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
                motorPonyWheel.rotateY(180).translate(0, 0.721, -1.94).rotateX(wheelAngle * 3.256).render(ms, light, vb);
                return;
            }

            if (direction == Direction.NORTH || direction == Direction.WEST) {
                frame.rotateY(180).translate(0, 0.125, 0).render(ms, light, vb);
                wheel.rotateY(180).translate(0, 0.695, 0.97).rotateX(-wheelAngle * 1.2).render(ms, light, vb);
                motorWheel.rotateY(180).translate(0, 0.695, -0.97).rotateX(-wheelAngle * 1.2).render(ms, light, vb);
                motorPonyWheel.rotateY(180).translate(0, 0.721, -1.94).rotateX(-wheelAngle * 3.256).render(ms, light, vb);
                return;
            }

            frame.translate(0, 0.125, 0).render(ms, light, vb);
            wheel.translate(0, 0.695, 0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
            motorWheel.translate(0, 0.695, -0.97).rotateX(wheelAngle * 1.2).render(ms, light, vb);
            motorPonyWheel.translate(0, 0.721, -1.94).rotateX(wheelAngle * 3.256).render(ms, light, vb);
        }

        @Override
        public BogeySizes.BogeySize getSize() {
            return null;
        }

        @Override
        public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
            this.createModelInstance(
                    materialManager, TKZ2_MOTOR_FRAME, TKZ2_WHEEL, TKZ2_MOTOR_WHEEL, TKZ2_MOTOR_PONY_WHEEL);
        }
    }
}
