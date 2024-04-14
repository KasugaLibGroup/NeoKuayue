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
import willow.train.kuayue.initial.AllCarriageBogeys;
import willow.train.kuayue.initial.AllElements;

public class SW160Renderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static PartialModel SW160_FRAME = new PartialModel(asBlockModelResource("bogey/sw160/sw160_frame"));
    public static PartialModel SW160_WHEEL = new PartialModel(asBlockModelResource("bogey/sw160/sw160_wheel"));

    @Override
    public void initialiseContraptionModelData(
            MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, SW160_FRAME);
        this.createModelInstance(materialManager, SW160_WHEEL, 2);
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCarriageBogeys.sw160.getSize();
    }

    @Override
    public void render(
            CompoundTag bogeyData,
            float wheelAngle,
            PoseStack ms,
            int light,
            VertexConsumer vb,
            boolean inContraption) {

        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(SW160_FRAME, ms, inInstancedContraption);
        BogeyModelData[] wheels = getTransform(SW160_WHEEL, ms, inInstancedContraption, 2);

        frame.translate(0, 0.264, 0).render(ms, light, vb);

        for (int side : Iterate.positiveAndNegative) {
            if (!inInstancedContraption) ms.pushPose();
            BogeyModelData wheel = wheels[(side + 1) / 2];
            wheel.translate(0, 0.805, ((double) side) * 1.28d).rotateX(wheelAngle);
            wheel.render(ms, light, vb);
            if (!inInstancedContraption) ms.popPose();
        }
    }
}
