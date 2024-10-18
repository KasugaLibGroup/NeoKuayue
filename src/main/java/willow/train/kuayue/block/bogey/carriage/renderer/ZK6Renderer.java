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

public class ZK6Renderer extends BogeyRenderer {

    private static ResourceLocation asBlockModelResource(String path) {
        return AllElements.testRegistry.asResource("block/" + path);
    }

    public static final PartialModel
            ZK6_FRAME = new PartialModel(asBlockModelResource("bogey/zk6/zk6_frame")),
            ZK6_WHEEL = new PartialModel(asBlockModelResource("bogey/zk6/zk6_wheel"));

    @Override
    public void render(CompoundTag bogeyData, float wheelAngle, PoseStack ms, int light, VertexConsumer vb, boolean inContraption) {
        boolean inInstancedContraption = vb == null;

        BogeyModelData frame = getTransform(ZK6_FRAME, ms, inInstancedContraption);
        frame.translate(0, 0.380, 0).render(ms, light, vb);

        BogeyModelData[] wheels =
                getTransform(ZK6_WHEEL, ms, inInstancedContraption, 2);

        if (!inInstancedContraption) ms.pushPose();
        wheels[0].translate(0, 0.745, 0.918d).rotateX(wheelAngle * 1.09).render(ms, light, vb);
        if (!inInstancedContraption) ms.popPose();

        if (!inInstancedContraption) ms.pushPose();
        wheels[1].translate(0, 0.745, -0.918d).rotateX(wheelAngle * 1.09).render(ms, light, vb);
        if (!inInstancedContraption) ms.popPose();
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCarriageBogeys.zk6.getSize();
    }

    @Override
    public void initialiseContraptionModelData(MaterialManager materialManager, CarriageBogey carriageBogey) {
        this.createModelInstance(materialManager, ZK6_FRAME);
        this.createModelInstance(materialManager, ZK6_WHEEL, 2);
    }
}
