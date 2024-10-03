package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;

import java.awt.*;
import java.io.IOException;

public class ColorSelector extends AbstractWidget {
    private SimpleColor color;
    private SimpleColor fullColor;
    private float h, s, v;
    private boolean hOn, sOn, iOn;
    private TooltipLabel tooltip;
    float showTooltip = 0;
    LazyRecomputable<ImageMask> bg = LazyRecomputable.of(() -> {
        try {
            return ClientInit.colorPlateBg.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    LazyRecomputable<ImageMask> middleLayer = LazyRecomputable.of(() -> {
        try {
            return ClientInit.colorPlateMiddleLayer.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    LazyRecomputable<ImageMask> plate = LazyRecomputable.of(() -> {
        try {
            return ClientInit.colorPlate.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    LazyRecomputable<ImageMask> button = LazyRecomputable.of(() -> {
        try {
            ImageMask mask = ClientInit.buttons.getImage().get().getMask();
            mask.rectangleUV(0, 0.125f, 0.125f, 0.25f);
            return mask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    public ColorSelector(int pX, int pY) {
        super(pX, pY, 140, 140, Component.empty());
        tooltip = new TooltipLabel(Component.translatable("tooltip.kuayue.color_selector"));
        updatePosition();
        h = 60;
        s = 1;
        v = 1;
        hOn = false;
        sOn = false;
        iOn = false;
        updateColor();
        tooltip.setWidth(100);
        tooltip.setPosition(new Vec2f(this.x + 20, this.y - tooltip.getHeight() - 4));
    }

    public void updateColor() {
        h %= 360;
        while (h < 0) h += 360;
        fullColor = SimpleColor.fromHSV(h, 1, 1);
        color = SimpleColor.fromHSV(h, 1 - s, v);
    }

    public void updatePosition() {
        bg.get().rectangleUV(0, 0, 1, 1);
        bg.get().rectangle(
                new Vector3f(this.x, this.y, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 140, 140);
        plate.get().rectangleUV(0, 0, 1, 1);
        plate.get().rectangle(new Vector3f(this.x + 6, this.y + 6, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 128, 128);
        middleLayer.get().rectangleUV(0, 0, 1, 1);
        middleLayer.get().rectangle(new Vector3f(this.x + 6, this.y + 6, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 128, 128);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        plate.get().renderToGui();
        fill(pPoseStack, x + 54, y + 54, x + 86, y + 86, color.getRGB());
        bg.get().renderToGui();
        middleLayer.get().setColor(fullColor);
        middleLayer.get().renderToGui();
        button.get().rectangle(new Vector3f(this.x + 120, this.y + 61, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 16, 16);
        button.get().setPivot(new Vector3f(this.x + 70, this.y + 69, 0));
        button.get().rotateByPivot(new Vector3f(0, 0, (float) Math.toRadians(-135f + this.h)));
        button.get().renderToGui();
        button.get().rectangle(new Vector3f(this.x + 109, this.y + 61, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 16, 16);
        button.get().rotateByPivot(new Vector3f(0, 0, (float) Math.toRadians(-90 + 360 * this.s)));
        button.get().renderToGui();
        button.get().rectangle(new Vector3f(this.x + 96, this.y + 61, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 16, 16);
        button.get().rotateByPivot(new Vector3f(0, 0, (float) Math.toRadians(-90 + this.v * 360)));
        button.get().renderToGui();
        if (isHovered) {
            if (showTooltip > 40) {
                tooltip.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
            } else {
                showTooltip ++;
            }
        } else if (showTooltip > 0) {
            showTooltip --;
        }
        dealWithRelease((float) pMouseX, (float) pMouseY);
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {

    }

    public Vec2f getWidgetCenter() {
        return new Vec2f(this.x + 70, this.y + 70);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (!visible) return false;
        float angle = getAngle((float) pMouseX, (float) pMouseY);
        if (hOn) {
            this.h = angle - 135f;
        } else if (sOn) {
            this.s = ((angle + 180) % 360) / 360;
        } else if (iOn) {
            this.v = ((angle + 180) % 360) / 360;
        }
        if (hOn || sOn || iOn) updateColor();
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    private float getAngle(float mx, float my) {
        Vec2f center = getWidgetCenter();
        float dx = mx - center.x();
        float dy = my - center.y();
        float orgAngle = (float) Math.toDegrees(Math.atan(dy / dx)) - 90f;
        if (dx < 0) orgAngle += 180f;
        if (orgAngle < 0) orgAngle += 360;
        if (orgAngle >= 360) orgAngle %= 360;
        return orgAngle;
    }

    @Override
    public boolean mouseScrolled(double mx, double my, double pDelta) {
        if (!visible) return false;
        if (iOn || hOn || sOn) return false;
        float distToCenter = getWidgetCenter().distance(new Vec2f((float) mx, (float) my));
        if (distToCenter >= 50 && distToCenter < 66) {
            h -= pDelta;
            if (h < 0) h += 360;
            else if (h > 360) h %= 360;
        } else if (distToCenter >= 39 && distToCenter < 50) {
            s -= pDelta / 360;
            if (s >= 1) s -= 1;
            else if (s < 0) s += 1;
        } else if (distToCenter >= 26 && distToCenter < 39) {
            v -= pDelta / 360;
            if (v > 1) v -= 1;
            else if (v < 0) v += 1;
        }
        updateColor();
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!visible) return false;
        if (button != 0) return false;
        super.mouseClicked(mouseX, mouseY, button);
        float mx = (float) mouseX, my = (float) mouseY;
        dealWithClick(mx, my);
        return true;
    }

    private void dealWithClick(float mx, float my) {
        float distToCenter = getWidgetCenter().distance(new Vec2f(mx, my));
        if (distToCenter >= 50 && distToCenter < 66) {
            hOn = true;
            sOn = false;
            iOn = false;
        } else if (distToCenter >= 39 && distToCenter < 50) {
            sOn = true;
            hOn = false;
            iOn = false;
        } else if (distToCenter >= 26 && distToCenter < 39) {
            iOn = true;
            hOn = false;
            sOn = false;
        }
    }

    private void dealWithRelease(float mx, float my) {
        float scale = .8f, negScale = 2 - scale;
        float distToCenter = getWidgetCenter().distance(new Vec2f(mx, my));
        if (((distToCenter < 50 * scale || distToCenter >= 66 * negScale) && hOn) ||
                ((distToCenter < 39 * scale || distToCenter >= 50 * negScale) && sOn) ||
                ((distToCenter < 26 * scale || distToCenter >= 39 * negScale && iOn))) {
            hOn = false;
            sOn = false;
            iOn = false;
        }
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (!visible) return false;
        hOn = false;
        sOn = false;
        iOn = false;
        return super.mouseReleased(pMouseX, pMouseY, pButton);
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

    public float getH() {
        return h;
    }

    public float getS() {
        return 1 - s;
    }

    public float getV() {
        return v;
    }

    public void setH(float h) {
        while (h < 0) h += 360;
        h %= 360;
        this.h = h;
        updateColor();
    }

    public void setS(float s) {
        this.s = 1 - s;
        updateColor();
    }

    public void setV(float v) {
        this.v = v;
        updateColor();
    }

    public float getR() {
        return color.getfR() * 255;
    }

    public float getG() {
        // return SimpleColor.hsvToRgb(h, 1 - s, v)[1];
        return color.getfG() * 255;
    }

    public float getB() {
        return color.getfB() * 255;
    }

    public String getHex() {
        return "#" + Integer.toHexString(color.getRGB()).toUpperCase().substring(2);
    }

    public void setHex(String hex) {
        hex = hex.replace("#", "");
        int col;
        try {
            col = Integer.valueOf(hex, 16);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        col = Math.max(0, Math.min(col, 0xffffff));
        int[] rgb = splitRGB(col);
        setRGB(rgb[0], rgb[1], rgb[2]);
    }


    public void setR(float r) {
        float g = getG(), b = getB();
        float[] hsv = SimpleColor.rgbToHsv(r, g, b);
        h = hsv[0]; s = hsv[1]; v = hsv[2];
        updateColor();
    }

    public void setG(float g) {
        float r = getR(), b = getB();
        float[] hsv = SimpleColor.rgbToHsv(r, g, b);
        h = hsv[0]; s = hsv[1]; v = hsv[2];
        updateColor();
    }

    public void setB(float b) {
        float g = getG(), r = getR();
        float[] hsv = SimpleColor.rgbToHsv(r, g, b);
        h = hsv[0]; s = hsv[1]; v = hsv[2];
        updateColor();
    }

    public void setRGB(float r, float g, float b) {
        float[] hsv = SimpleColor.rgbToHsv(r, g, b);
        h = hsv[0]; s = 1 - hsv[1]; v = hsv[2];
        updateColor();
    }

    public void setHSV(float h, float s, float v) {
        this.h = h; this.s = 1 - s; this.v = v;
        updateColor();
    }

    public void setRgb(int rgb) {
        String hex = "#" + Integer.toHexString(rgb);
        setHex(hex);
    }

    public static int[] splitRGB(int rgb) {
        int r = rgb >> 16;
        int g = (rgb >> 8) - (r << 8);
        int b = rgb - (g << 8) - (r << 16);
        return new int[]{r, g, b};
    }

    public SimpleColor getColor() {
        return color;
    }
}
