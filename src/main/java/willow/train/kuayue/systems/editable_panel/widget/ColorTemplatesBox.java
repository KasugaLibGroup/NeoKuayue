package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.joml.Vector3f;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;

import java.io.IOException;

public class ColorTemplatesBox extends AbstractWidget {
    private final ColorTemplate template;
    private final LazyRecomputable<ImageMask> colorCube = LazyRecomputable.of(
            () -> {
                try {
                    ImageMask mask = ClientInit.buttons.getImage().get().getMask();
                    mask.rectangleUV(.625f, .125f, .75f, .25f);
                    return mask;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    );
    private final OnClick onClick;
    private static final SimpleColor basicColor = SimpleColor.fromRGBInt(0xff333333);

    private DescriptionLabel title, description, owner;
    public ColorTemplatesBox(int pX, int pY, ColorTemplate template, Component pMessage, OnClick onClick) {
        super(pX, pY, 120, 40, pMessage);
        this.template = template;
        this.onClick = onClick;
    }

    public void init() {
        int baseY = this.getY() + this.height / 2 - 16;
        colorCube.get().rectangle(new Vector3f(this.getX() + 3, baseY + 4, 0),
                ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 24, 24);
        colorCube.get().setColor(SimpleColor.fromRGBInt(template.getColor()));
        title = new DescriptionLabel(new Vec2f(this.getX() + 38, baseY), 80, 8,
                Component.literal(template.getName()), basicColor);
        description = new DescriptionLabel(new Vec2f(this.getX() + 38, baseY + 10), 40, 8,
                Component.literal(template.getDocument()), basicColor);
        owner = new DescriptionLabel(new Vec2f(this.getX() + 85, baseY + 10), 30, 8,
                Component.literal(template.getOwner()), basicColor);
        title.setForceLeftBegin(true);
        description.setForceLeftBegin(true);
        owner.setForceLeftBegin(true);
    }

    public ColorTemplate getTemplate() {
        return template;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        // super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        if (isHovered) {
            renderLines(guiGraphics, this.getX(), this.getY(), this.getX() + width, this.getY() + height,
                    2, 0x22000000 + this.template.getColor(), 0xff555555);
        } else
            renderLines(guiGraphics, this.getX(), this.getY(), this.getX() + width, this.getY() + height,
                    2, 0, 0xff555555);
        colorCube.get().renderToGui();
        title.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        description.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        owner.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }

    public static void renderGuiBg(GuiGraphics guiGraphics, int minX, int minY, int maxX, int maxY, int borderWidth, int bgColor, int borderColor) {
        // border
        guiGraphics.fill(minX, minY, maxX, minY + borderWidth, borderColor);
        guiGraphics.fill(minX, maxY - borderWidth, maxX, maxY, borderColor);
        guiGraphics.fill(minX, minY, minX + borderWidth, maxY, borderColor);
        guiGraphics.fill(maxX - borderWidth, minY, maxX, maxY, borderColor);

        // bg
        guiGraphics.fill(minX + borderWidth, minY + borderWidth, maxX - borderWidth, maxY - borderWidth, bgColor);
    }

    public static void renderLines(GuiGraphics guiGraphics, int minX, int minY, int maxX, int maxY, int borderWidth, int bgColor, int borderColor) {
        guiGraphics.fill(minX, minY, maxX, minY + borderWidth, borderColor);
        guiGraphics.fill(minX, maxY - borderWidth, maxX, maxY, borderColor);

        guiGraphics.fill(minX, minY + borderWidth, maxX, maxY - borderWidth, bgColor);
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        super.onClick(pMouseX, pMouseY);
        this.onClick.action(this);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public interface OnClick {
        void action(ColorTemplatesBox box);
    }
}
