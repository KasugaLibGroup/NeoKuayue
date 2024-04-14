package willow.train.kuayue.block.bogey.carriage.renderer;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.foundation.utility.Iterate;
import kasuga.lib.example_env.AllExampleElements;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.initial.AllElements;

public class CW2BogeyRenderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel CW2_FRAME = new PartialModel(asBlockModelResource("bogey/cw2/bogey_cw2_temple"));
    public static final PartialModel CW2_WHEEL = new PartialModel(asBlockModelResource("bogey/cw2/cw2_wheel"));

    @Override
    public void initialiseContraptionModelData(
            MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, CW2_FRAME);
        this.createModelInstance(materialManager, CW2_WHEEL, 2);
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return BogeySizes.SMALL;
    }

    /**
     * @param bogeyData Custom data stored on the bogey able to be used for rendering
     * @param wheelAngle The angle of the wheel
     * @param ms The posestack to render to
     * @param light (Optional) Light used for in-world rendering
     * @param vb (Optional) Vertex Consumer used for in-world rendering
     * @param inContraption
     */
    @Override
    public void render(
            CompoundTag bogeyData,
            float wheelAngle,
            PoseStack ms,
            int light,
            VertexConsumer vb,
            boolean inContraption) {

        boolean inInstancedContraption = vb == null;
        BogeyModelData frame = getTransform(CW2_FRAME, ms, inInstancedContraption);
        frame.render(ms, light, vb);

        BogeyModelData[] wheels =
                getTransform(CW2_WHEEL, ms, inInstancedContraption, 2);

        for (int side : Iterate.positiveAndNegative) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[(side + 1) / 2];
            wheel.translate(0, 0.805, ((double) side) * 1.2d).rotateX(wheelAngle);
            wheel.render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
    }
}
