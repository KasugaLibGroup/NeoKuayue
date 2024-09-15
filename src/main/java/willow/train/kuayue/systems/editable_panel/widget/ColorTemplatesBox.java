package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
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
    private static final SimpleColor basicColor = SimpleColor.fromRGBInt(0xff333333);

    private DescriptionLabel title, description, owner;
    public ColorTemplatesBox(int pX, int pY, ColorTemplate template, Component pMessage) {
        super(pX, pY, 120, 40, pMessage);
        this.template = template;
    }

    public void init() {
        int baseY = this.y + this.height / 2 - 16;
        colorCube.get().rectangle(new Vector3f(this.x + 3, baseY + 4, 0),
                ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 24, 24);
        colorCube.get().setColor(SimpleColor.fromRGBInt(template.getColor()));
        title = new DescriptionLabel(new Vec2f(this.x + 38, baseY), 64, 8,
                Component.literal(template.getName()), basicColor);
        description = new DescriptionLabel(new Vec2f(this.x + 38, baseY + 10), 40, 8,
                Component.literal(template.getDocument()), basicColor);
        owner = new DescriptionLabel(new Vec2f(this.x + 85, baseY + 10), 30, 8,
                Component.literal(template.getOwner()), basicColor);
        title.setForceLeftBegin(true);
        description.setForceLeftBegin(true);
        owner.setForceLeftBegin(true);
    }

    public ColorTemplate getTemplate() {
        return template;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        // super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        if (isHovered) {
            renderLines(pPoseStack, this.x, this.y, this.x + width, this.y + height,
                    2, 0x22000000 + this.template.getColor(), 0xff555555);
        } else
            renderLines(pPoseStack, this.x, this.y, this.x + width, this.y + height,
                    2, 0, 0xff555555);
        colorCube.get().renderToGui();
        title.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        description.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        owner.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
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

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public static void renderGuiBg(PoseStack poseStack, int minX, int minY, int maxX, int maxY, int borderWidth, int bgColor, int borderColor) {
        // border
        fill(poseStack, minX, minY, maxX, minY + borderWidth, borderColor);
        fill(poseStack, minX, maxY - borderWidth, maxX, maxY, borderColor);
        fill(poseStack, minX, minY, minX + borderWidth, maxY, borderColor);
        fill(poseStack, maxX - borderWidth, minY, maxX, maxY, borderColor);

        // bg
        fill(poseStack, minX + borderWidth, minY + borderWidth, maxX - borderWidth, maxY - borderWidth, bgColor);
    }

    public static void renderLines(PoseStack poseStack, int minX, int minY, int maxX, int maxY, int borderWidth, int bgColor, int borderColor) {
        fill(poseStack, minX, minY, maxX, minY + borderWidth, borderColor);
        fill(poseStack, minX, maxY - borderWidth, maxX, maxY, borderColor);

        fill(poseStack, minX, minY + borderWidth, maxX, maxY - borderWidth, bgColor);
    }
}
