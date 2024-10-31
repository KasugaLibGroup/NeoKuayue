package willow.train.kuayue.block.seat;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SeatEntityRenderer extends EntityRenderer<M1SeatEntity> {
    public SeatEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(M1SeatEntity pEntity) {
        return TextureManager.INTENTIONAL_MISSING_TEXTURE;
    }
}
