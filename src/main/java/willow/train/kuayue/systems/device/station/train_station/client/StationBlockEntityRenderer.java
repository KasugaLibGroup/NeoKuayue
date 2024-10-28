package willow.train.kuayue.systems.device.station.train_station.client;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.model.BedrockModelLoader;
import kasuga.lib.core.client.model.anim_model.AnimModel;
import kasuga.lib.core.client.model.model_json.UnbakedBedrockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.station.train_station.StationBlockEntity;

public class StationBlockEntityRenderer implements BlockEntityRenderer<StationBlockEntity> {
    static {
        BedrockModelLoader.fromFile(AllElements.testRegistry.asResource("block/signal/track/station/station"));
    };
    protected static AnimModel bedrockModel = BedrockModelLoader.getModel(
            AllElements.testRegistry.asResource("block/signal/track/station/station"),
            RenderType.solid()
    );

    public StationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(StationBlockEntity stationBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        bedrockModel.render(poseStack, multiBufferSource, i, i1);
    }
}
