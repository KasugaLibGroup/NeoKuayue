package willow.train.kuayue.systems.editable_panel.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;

@FunctionalInterface
public interface SignRenderLambda {

    void render(EditablePanelEntity blockEntity, float pPartialTick, ClientObject pose,
                ClientObject pBufferSource, int pPackedLight, int pPackedOverlay);

    static record ClientObject(Object obj) {

        @OnlyIn(Dist.CLIENT)
        public PoseStack getPoseStack() {
            return (PoseStack) obj;
        }

        @OnlyIn(Dist.CLIENT)
        public MultiBufferSource getBuffer() {
            return (MultiBufferSource) obj;
        }
    }
}
