package willow.train.kuayue.block.bogey.carriage.renderer;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.create.AllCarriageBogeys;

public class TKZ1Renderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel
            TKZ1_FRAME = new PartialModel(asBlockModelResource("bogey/tkz1/tkz1_frame")),
            TKZ1_WHEEL = new PartialModel(asBlockModelResource("bogey/tkz2/tkz2_wheel"));

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {

        light *= 1.1;
        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(TKZ1_FRAME, ms, inInstancedContraption);
        frame.translate(0, 0.14, 0).render(ms, light, vb);

        BogeyModelData[] wheels =
                getTransform(TKZ1_WHEEL, ms, inInstancedContraption, 2);

        for (int side : Iterate.positiveAndNegative) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[(side + 1) / 2];
            wheel.translate(0, 0.695, ((double) side) * 0.97d).rotateX(wheelAngle * 1.22);
            wheel.render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCarriageBogeys.tkz1.getSize();
    }

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, TKZ1_FRAME);
        this.createModelInstance(materialManager, TKZ1_WHEEL, 2);
    }
}
