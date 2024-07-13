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

public class MKZRenderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel
            MKZ_FRAME = new PartialModel(asBlockModelResource("bogey/mkz/mkz_frame")),
            MKZ_WHEEL = new PartialModel(asBlockModelResource("bogey/mkz/mkz_wheel"));

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, MKZ_FRAME);
        this.createModelInstance(materialManager, MKZ_WHEEL, 2);
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCarriageBogeys.mkz.getSize();
    }

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {

        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(MKZ_FRAME, ms, inInstancedContraption);
        frame.translate(0, 0.320, 0).render(ms, light, vb);

        BogeyModelData[] wheels =
                getTransform(MKZ_WHEEL, ms, inInstancedContraption, 2);

        for (int side : Iterate.positiveAndNegative) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[(side + 1) / 2];
            wheel.translate(0, 0.695, ((double) side) * 0.733d).rotateX(wheelAngle);
            wheel.render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
    }
}
