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

    public ColorSelector(int pX, int pY, Component pMessage) {
        super(pX, pY, 140, 140, pMessage);
        updatePosition();
        h = 60;
        s = 1;
        v = 1;
        hOn = false;
        sOn = false;
        iOn = false;
        updateColor();
    }

    public void updateColor() {
        h %= 360;
        float[] rgbs = hsvToRgb(h, 1 - s, v);
        float[] fullRgbs = hsvToRgb(h, 1, 1);
        System.out.println("r=" + rgbs[0] + ", g=" + rgbs[1] + ", b=" + rgbs[2]);
        Color rgb = new Color((int) rgbs[0], (int)rgbs[1], (int)rgbs[2]);
        Color fullRgb = new Color((int) fullRgbs[0], (int) fullRgbs[1], (int) fullRgbs[2]);
        fullColor = SimpleColor.fromRGBInt(fullRgb.getRGB());
        color = SimpleColor.fromRGBInt(rgb.getRGB());
    }

    public void updatePosition() {
        bg.get().rectangleUV(0, 0, 1, 1);
        bg.get().rectangle(
                new Vector3f(this.x, this.y, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 140, 140);
        plate.get().rectangleUV(0, 0, 1, 1);
        plate.get().rectangle(new Vector3f(this.x + 6, this.x + 6, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 128, 128);
        middleLayer.get().rectangleUV(0, 0, 1, 1);
        middleLayer.get().rectangle(new Vector3f(this.x + 6, this.x + 6, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 128, 128);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        plate.get().renderToGui();
        // h += 1f;
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
        return super.mouseScrolled(mx, my, pDelta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
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
        float distToCenter = getWidgetCenter().distance(new Vec2f(mx, my));
        if (((distToCenter < 50 || distToCenter >= 66) && hOn) ||
                ((distToCenter < 39 || distToCenter >= 50) && sOn) ||
                ((distToCenter < 26 || distToCenter >= 39 && iOn))) {
            hOn = false;
            sOn = false;
            iOn = false;
        }
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
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

    /**
     * convert color space from HSI to RGB
     * @param h hue, [0, 360)
     * @param s Saturation , [0, 1]
     * @param i [0, 255]
     * @return
     */
    public static float[] hsiToRgb(float h, float s, float i) {
        while (h < 0) h += 360;
        h %= 360;
        float r, g, b;
        if (h >= 0 && h < 120) {
            b = i * (1 - s);
            r = i * (float) (1 + (s * Math.cos(h)) / Math.cos(Math.PI / 3 - h));
            g = 3 * i - (r + b);
        } else if (h >= 120 && h < 240) {
            h -= 120f;
            r = i * (1 - s);
            g = i * (float) (1 + (s * Math.cos(h)) / Math.cos(Math.PI / 3 - h));
            b = 3 * i - (r + g);
        } else {
            h -= 240f;
            g = i * (1 - s);
            b = i * (float) (1 + (s * Math.cos(h)) / Math.cos(Math.PI / 3 - h));
            r = 3 * i - (g + b);
        }
        return new float[]{r, g, b};
    }

    /**
     * convert color space HSV to RGB
     * @param h Hue, [0, 360)
     * @param s Saturation, [0, 1]
     * @param v Value, [0, 1]
     * @return rgb values, all ranged as [0, 255]
     */
    public static float[] hsvToRgb(float h, float s, float v) {
        while (h < 0) h += 360;
        h %= 360;
        float c = v * s;
        float x = c * (1 - Math.abs((h / 60f) % 2 - 1));
        float m = v - c;
        float r1 = 0, g1 = 0, b1 = 0;
        if (h >= 0 && h < 60) {
            r1 = c;
            g1 = x;
            b1 = 0;
        } else if (h >= 60 && h < 120) {
            r1 = x;
            g1 = c;
            b1 = 0;
        } else if (h >= 120 && h < 180) {
            r1 = 0;
            g1 = c;
            b1 = x;
        } else if (h >= 180 && h < 240) {
            r1 = 0;
            g1 = x;
            b1 = c;
        } else if (h >= 240 && h < 300) {
            r1 = x;
            g1 = 0;
            b1 = c;
        } else if (h >= 300 && h < 360) {
            r1 = c;
            g1 = 0;
            b1 = x;
        }
        return new float[]{(r1 + m) * 255, (g1 + m) * 255, (b1 + m) * 255};
    }
}
