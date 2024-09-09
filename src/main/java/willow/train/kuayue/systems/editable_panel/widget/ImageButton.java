package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.utils.client.RenderablePicture;

@OnlyIn(Dist.CLIENT)
public class ImageButton extends Button {
    private final LazyRecomputable<ImageMask> mask;

    public ImageButton(LazyRecomputable<ImageMask> mask, int x, int y, int width, int height, OnPress press) {
        super(0, 0, width, height, Component.empty(), press);
        this.mask = mask;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void setImageMask(SimpleColor color) {
        this.mask.get().setColor(color);
    }

    public void controlImage(ImageAction action) {
        action.act(this.mask.get(), this);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;
        this.mask.get().renderToGui();
    }

    public interface ImageAction {
        void act(ImageMask mask, ImageButton button);
    }
}
