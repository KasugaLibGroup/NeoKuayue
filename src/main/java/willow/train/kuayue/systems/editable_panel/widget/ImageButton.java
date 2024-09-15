package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.utils.client.RenderablePicture;

@OnlyIn(Dist.CLIENT)
public class ImageButton extends Button {
    private final LazyRecomputable<ImageMask> mask, bg;
    private TooltipLabel tooltip;
    private int showTooltip = 0;
    public static final ImageButton.ImageAction baseAction = (img, btn) -> img.rectangle(new Vector3f(btn.getX(), btn.getY(), 0),
            ImageMask.Axis.X, ImageMask.Axis.Y, true, true, btn.getWidth(), btn.getHeight());

    public ImageButton(LazyRecomputable<ImageMask> mask, LazyRecomputable<ImageMask> bg, int x, int y,
                       int width, int height, Component tooltip, OnPress press) {
        super(0, 0, width, height, tooltip, press);
        this.mask = mask;
        this.bg = bg;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.tooltip = new TooltipLabel(new Vec2f(this.x, this.y + this.height + 2), tooltip);
        controlImage(baseAction);
        controlBg(baseAction);
    }

    public ImageButton(LazyRecomputable<ImageMask> mask, int x, int y, int width, int height, Component tooltip, OnPress press) {
        super(0, 0, width, height, tooltip, press);
        this.mask = mask;
        this.bg = null;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.tooltip = new TooltipLabel(new Vec2f(this.x, this.y + this.height + 2), tooltip);
        controlImage(baseAction);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMaskColor(SimpleColor color) {
        this.mask.get().setColor(color);
    }
    public void setBgColor(SimpleColor color) {this.mask.get().setColor(color);}
    public void controlImage(ImageAction action) {
        action.act(this.mask.get(), this);
    }
    public void controlBg(ImageAction action) {
        action.act(this.bg.get(), this);
    }

    public void setTooltipLabelWidth(int width) {
        this.tooltip.setWidth(width);
        this.tooltip.setPosition(new Vec2f(this.x + (float) (this.width - width) / 2, this.y + this.height + 2));
    }

    public void dynamicTooltipLabelWidth() {
        setTooltipLabelWidth(Minecraft.getInstance().font.width(tooltip.getText().getString()) + 4);
    }
    public void setTooltipLabelPos(Vec2f pos) {
        this.tooltip.setPosition(pos);
    }

    public TooltipLabel getTooltipLabel() {
        return tooltip;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;
        if (bg != null) bg.get().renderToGui();
        if (isHovered) {
            if (showTooltip > 40) tooltip.render(pPoseStack, mouseX, mouseY, partialTicks);
            else showTooltip ++;
        } else if (showTooltip > 0) showTooltip --;
        this.mask.get().renderToGui();
    }

    public interface ImageAction {
        void act(ImageMask mask, ImageButton button);
    }
}
