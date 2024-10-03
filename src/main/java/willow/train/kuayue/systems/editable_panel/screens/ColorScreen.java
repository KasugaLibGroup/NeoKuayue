package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.widget.*;

import java.io.IOException;

public class ColorScreen extends AbstractWidget {
    public final ColorSelector selector;
    public TitledEditBox r, g, b;
    public TitledEditBox h, s, v;
    public TitledEditBox hex;
    public ImageButton cancel, confirm, save, load, template;
    public float rCache, gCache, bCache, hCache, sCache, vCache;
    public String hexCache;
    private boolean visible;
    private Label title;
    private TooltipLabel enterKeyTooltip;

    private final LazyRecomputable<ImageMask> backGround =
            LazyRecomputable.of(() -> {
                try {
                    return ClientInit.colorBg2.getImage().get().getMask();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    private final LazyRecomputable<ImageMask> btn =
            LazyRecomputable.of(() -> {
                try {
                    return ClientInit.buttons.getImage().get().getMask();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

    private final LazyRecomputable<ImageMask> btn_cancel =
            LazyRecomputable.of(() -> btn.get().copyWithOp((m) -> m.rectangleUV(0.5f, 0.125f, 0.625f, 0.25f)));

    private final LazyRecomputable<ImageMask> btn_confirm =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0.375f, 0.125f, 0.5f, 0.25f)));

    private final LazyRecomputable<ImageMask> btn_save =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0.625f, 0f, 0.75f, 0.125f)));

    private final LazyRecomputable<ImageMask> btn_load =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0.75f, 0, 0.875f, 0.125f)));

    private final LazyRecomputable<ImageMask> btn_temp =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0.875f, 0, 1f, 0.125f)));

    private final LazyRecomputable<ImageMask> btn_bg =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0.25f, 0.125f, 0.375f, 0.25f)));
    private final LazyRecomputable<ImageMask> title_bg =
            LazyRecomputable.of(() -> btn.get().copyWithOp(m -> m.rectangleUV(0, 0.25f, 0.625f, 0.375f)));

    public ColorScreen(int x, int y, Component title) {
        super(x, y, 350, 200, title);
        selector = new ColorSelector((x + width - 160), (y + height/2 - 70));
        rCache = selector.getR();
        gCache = selector.getG();
        bCache = selector.getB();
        hCache = selector.getH();
        sCache = selector.getS();
        vCache = selector.getV();
        hexCache = selector.getHex();
        visible = true;
    }

    public void init() {
        int x = this.x, y = this.y;
        int xOffset = 33, yOffset = 80;
        Font font = Minecraft.getInstance().font;
        r = new TitledEditBox(x + xOffset, y + yOffset, 50, 8, Component.literal("R:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getR()), 0xff0000);
        });
        g = new TitledEditBox(x + 55 + xOffset, y + yOffset, 50, 8, Component.literal("G:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getG()), 0xff00);
        });
        b = new TitledEditBox(x + 110 + xOffset, y + yOffset, 50, 8, Component.literal("B:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getB()), 0xff);
        });
        h = new TitledEditBox(x + xOffset, y + 16 + yOffset, 50, 8, Component.literal("H:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getH()), 0xffffff);
        });
        s = new TitledEditBox(x + 55 + xOffset, y + 16 + yOffset, 50, 8, Component.literal("S:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getS()), 0xffffff);
        });
        v = new TitledEditBox(x + 110 + xOffset, y + 16 + yOffset, 50, 8, Component.literal("V:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, String.valueOf(selector.getV()), 0xffffff);
        });
        hex = new TitledEditBox(x + 40 + xOffset, y + 32 + yOffset, 80, 8, Component.literal("HEX:"), (px, py, pw, ph, t) -> {
            return new TransparentEditBox(font, px, py, pw, ph, t, selector.getHex(), 0xffffff);
        });
        r.setTextColor(0xff0000);
        g.setTextColor(0xff00);
        b.setTextColor(0xff);

        ImageMask mask = backGround.get();
        mask.rectangle(new Vector3f(this.x + 3, this.y + this.height / 2f - 37, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 200, 75);
        mask.rectangleUV(0, 0, 1, 1);

        ImageMask titleMask = title_bg.get();
        titleMask.rectangle(new Vector3f(this.x + 125, this.y + this.height / 2f - 53, 0), ImageMask.Axis.X, ImageMask.Axis.Y,
                true, true, 80, 16);

        title = new Label(this.getMessage());
        title.setPosition(new Vec2f(this.x + 165 - title.getWidth() / 2f, this.y + this.height / 2f - 49));

        int baseY = this.y + this.height / 2 + 36;
        int baseX = this.x + 50;
        int labelSize = 20;
        save = new ImageButton(this.btn_save, LazyRecomputable.of(() -> this.btn_bg.get().copyWithOp(m -> m)),
                baseX, baseY, labelSize, labelSize, Component.translatable("tooltip.kuayue.color_screen.save"), (b) -> {});
        load = new ImageButton(this.btn_load, LazyRecomputable.of(() -> this.btn_bg.get().copyWithOp(m -> m)),
                baseX + 30, baseY, labelSize, labelSize, Component.translatable("tooltip.kuayue.color_screen.load"), (b) -> {});
        template = new ImageButton(this.btn_temp, LazyRecomputable.of(() -> this.btn_bg.get().copyWithOp(m -> m)),
                baseX + 60, baseY, labelSize, labelSize, Component.translatable("tooltip.kuayue.color_screen.temp"), (b) -> {});
        cancel = new ImageButton(this.btn_cancel, LazyRecomputable.of(() -> this.btn_bg.get().copyWithOp(m -> m)),
                baseX + 90, baseY, labelSize, labelSize, Component.translatable("tooltip.kuayue.color_screen.cancel"), (b) -> {});
        confirm = new ImageButton(this.btn_confirm, LazyRecomputable.of(() -> this.btn_bg.get().copyWithOp(m -> m)),
                baseX + 120, baseY, labelSize, labelSize, Component.translatable("tooltip.kuayue.color_screen.confirm"), (b) -> {});
        save.dynamicTooltipLabelWidth();
        load.dynamicTooltipLabelWidth();
        confirm.dynamicTooltipLabelWidth();
        template.dynamicTooltipLabelWidth();
        cancel.dynamicTooltipLabelWidth();

        enterKeyTooltip = new TooltipLabel(new Vec2f(this.x + 5, this.y + this.height / 2f - 50), Component.translatable("tooltip.kuayue.color_screen.key_enter"));
        enterKeyTooltip.setWidth(font.width(enterKeyTooltip.getText().getString()) + 4);
        updateBox();
    }

    public void updateFromSelectorToBox() {
        if (selector.getR() != front3(rCache)) {
            rCache = selector.getR();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (selector.getG() != front3(gCache)) {
            gCache = selector.getG();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (selector.getB() != front3(bCache)) {
            bCache = selector.getB();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (selector.getH() != front3(hCache)) {
            hCache = selector.getH();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        } else if (selector.getS() != front3(sCache)) {
            sCache = selector.getS();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        } else if (selector.getV() != front3(vCache)) {
            vCache = selector.getV();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        }
        updateBox();
    }

    public void updateFromBoxToSelector() throws Exception {
        float rc = Float.parseFloat(r.getValue()), gc = Float.parseFloat(g.getValue()), bc = Float.parseFloat(b.getValue());
        float hc = Float.parseFloat(h.getValue()), sc = Float.parseFloat(s.getValue()), vc = Float.parseFloat(v.getValue());
        rc = range(0, rc, 255);
        gc = range(0, gc, 255);
        bc = range(0, bc, 255);
        hc = range(0, hc, 360);
        sc = range(0, sc, 1);
        vc = range(0, vc, 1);
        if (rc != front3(rCache)) {
            rCache = rc;
            updateSelectorFromRgb();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (gc != front3(gCache)) {
            gCache = gc;
            updateSelectorFromRgb();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (bc != front3(bCache)) {
            bCache = bc;
            updateSelectorFromRgb();
            updateHsvFromSelector();
            hexCache = selector.getHex();
        } else if (hc != front3(hCache)) {
            hCache = hc;
            updateSelectorFromHsv();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        } else if (sc != front3(sCache)) {
            sCache = sc;
            updateSelectorFromHsv();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        } else if (vc != front3(vCache)) {
            vCache = vc;
            updateSelectorFromHsv();
            updateRgbFromSelector();
            hexCache = selector.getHex();
        } else if (!hex.getValue().equals(hexCache)) {
            hexCache = hex.getValue();
            selector.setHex(hexCache);
            updateRgbFromSelector();
            updateHsvFromSelector();
        }
        updateBox();
    }

    public static float range(float min, float value, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public static float front3(float num) {
        String str = String.valueOf(num);
        if (str.contains(".") && str.length() > str.indexOf(".") + 3) {
            str = str.substring(0, str.indexOf(".") + 3);
        }
        return Float.parseFloat(str);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        r.visible = this.visible;
        g.visible = this.visible;
        b.visible = this.visible;
        h.visible = this.visible;
        s.visible = this.visible;
        v.visible = this.visible;
        hex.visible = this.visible;
        selector.visible = this.visible;
    }

    public boolean getVisible() {
        return visible;
    }

    private void updateHsvFromSelector() {
        hCache = selector.getH();
        sCache = selector.getS();
        vCache = selector.getV();
    }

    private void updateRgbFromSelector() {
        rCache = selector.getR();
        gCache = selector.getG();
        bCache = selector.getB();
    }

    private void updateSelectorFromHsv() {
        selector.setHSV(hCache, sCache, vCache);
    }

    public void updateSelectorFromRgb() {
        selector.setRGB(rCache, gCache, bCache);
    }

    private void updateBox() {
        r.setValue(String.valueOf((int) rCache));
        g.setValue(String.valueOf((int) gCache));
        b.setValue(String.valueOf((int) bCache));
        String hn = String.valueOf(hCache < 0 ? hCache + 360f : hCache);
        if (hn.contains(".") && hn.length() > hn.indexOf(".") + 3) hn = hn.substring(0, hn.indexOf(".") + 3);
        h.setValue(hn);
        String sn = String.valueOf(sCache);
        if (sn.contains(".") && sn.length() > sn.indexOf(".") + 3) sn = sn.substring(0, sn.indexOf(".") + 3);
        s.setValue(sn);
        String vn = String.valueOf(vCache);
        if (vn.contains(".") && vn.length() > vn.indexOf(".") + 3) vn = vn.substring(0, vn.indexOf(".") + 3);
        v.setValue(vn);
        hex.setValue(hexCache);
    }

    private void updateHsvFromBox() {
        hCache = Float.parseFloat(h.getValue());
        sCache = Float.parseFloat(s.getValue());
        vCache = Float.parseFloat(v.getValue());
    }

    private void updateRgbFromBox() {
        rCache = Float.parseFloat(r.getValue());
        gCache = Float.parseFloat(g.getValue());
        bCache = Float.parseFloat(b.getValue());
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (!visible) return;

        save.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        load.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        template.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        cancel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        confirm.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        title_bg.get().renderToGui();
        title.renderToGui(pPoseStack, Minecraft.getInstance().font);
        backGround.get().renderToGui();
        selector.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        r.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        g.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        b.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        h.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        s.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        v.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        hex.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        if (r.isHoveredOrFocused() || g.isHoveredOrFocused() || b.isHoveredOrFocused() ||
        h.isHoveredOrFocused() || s.isHoveredOrFocused() || v.isHoveredOrFocused() ||
        hex.isHoveredOrFocused())
            enterKeyTooltip.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    public void setHex(String hex) {
        hexCache = hex;
        selector.setHex(hexCache);
        updateRgbFromSelector();
        updateHsvFromSelector();
    }

    public void setRgb(int rgb) {
        this.selector.setRgb(rgb);
        try {
            updateFromSelectorToBox();
        } catch (Exception e) {
            Kuayue.LOGGER.error("Unexpected Error.", e);
        }
    }

    public SimpleColor getColor() {
        return selector.getColor();
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (!visible) return false;
        r.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        g.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        b.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        h.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        s.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        v.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        hex.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        boolean flag = selector.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        if (flag) updateFromSelectorToBox();
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY) & flag;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (!visible) return false;
        r.mouseReleased(pMouseX, pMouseY, pButton);
        g.mouseReleased(pMouseX, pMouseY, pButton);
        b.mouseReleased(pMouseX, pMouseY, pButton);
        h.mouseReleased(pMouseX, pMouseY, pButton);
        s.mouseReleased(pMouseX, pMouseY, pButton);
        v.mouseReleased(pMouseX, pMouseY, pButton);
        hex.mouseReleased(pMouseX, pMouseY, pButton);
        boolean flag = selector.mouseReleased(pMouseX, pMouseY, pButton);
        if (flag) updateFromSelectorToBox();
        return super.mouseReleased(pMouseX, pMouseY, pButton) & flag;
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (!visible) return false;
        r.mouseClicked(pMouseX, pMouseY, pButton);
        g.mouseClicked(pMouseX, pMouseY, pButton);
        b.mouseClicked(pMouseX, pMouseY, pButton);
        h.mouseClicked(pMouseX, pMouseY, pButton);
        s.mouseClicked(pMouseX, pMouseY, pButton);
        v.mouseClicked(pMouseX, pMouseY, pButton);
        hex.mouseClicked(pMouseX, pMouseY, pButton);
        return super.mouseClicked(pMouseX, pMouseY, pButton) &
                selector.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (!visible) return;
        if (this.template.visible && template.isMouseOver(pMouseX, pMouseY)) this.template.onClick(pMouseX, pMouseY);
        else if (this.load.visible && load.isMouseOver(pMouseX, pMouseY)) this.load.onClick(pMouseX, pMouseY);
        else if (this.save.visible && save.isMouseOver(pMouseX, pMouseY)) this.save.onClick(pMouseX, pMouseY);
        else if (this.cancel.visible && cancel.isMouseOver(pMouseX, pMouseY)) this.cancel.onClick(pMouseX, pMouseY);
        else if (this.confirm.visible && confirm.isMouseOver(pMouseX, pMouseY)) this.confirm.onClick(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (!visible) return false;
        boolean flag = selector.mouseScrolled(pMouseX, pMouseY, pDelta);
        if (flag) updateFromSelectorToBox();
        return super.mouseScrolled(pMouseX, pMouseY, pDelta) & flag;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (!visible) return false;
        r.keyPressed(pKeyCode, pScanCode, pModifiers);
        g.keyPressed(pKeyCode, pScanCode, pModifiers);
        b.keyPressed(pKeyCode, pScanCode, pModifiers);
        h.keyPressed(pKeyCode, pScanCode, pModifiers);
        s.keyPressed(pKeyCode, pScanCode, pModifiers);
        v.keyPressed(pKeyCode, pScanCode, pModifiers);
        hex.keyPressed(pKeyCode, pScanCode, pModifiers);
        return super.keyPressed(pKeyCode, pScanCode, pModifiers) &
                selector.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (!visible) return false;
        r.keyReleased(pKeyCode, pScanCode, pModifiers);
        g.keyReleased(pKeyCode, pScanCode, pModifiers);
        b.keyReleased(pKeyCode, pScanCode, pModifiers);
        h.keyReleased(pKeyCode, pScanCode, pModifiers);
        s.keyReleased(pKeyCode, pScanCode, pModifiers);
        v.keyReleased(pKeyCode, pScanCode, pModifiers);
        hex.keyReleased(pKeyCode, pScanCode, pModifiers);
        // operate key "ENTER"
        if (pKeyCode == 257) {
            try {
                updateFromBoxToSelector();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.keyReleased(pKeyCode, pScanCode, pModifiers) &
                selector.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (!visible) return false;
        r.charTyped(pCodePoint, pModifiers);
        g.charTyped(pCodePoint, pModifiers);
        b.charTyped(pCodePoint, pModifiers);
        h.charTyped(pCodePoint, pModifiers);
        s.charTyped(pCodePoint, pModifiers);
        v.charTyped(pCodePoint, pModifiers);
        hex.charTyped(pCodePoint, pModifiers);
        return super.charTyped(pCodePoint, pModifiers) &
                selector.charTyped(pCodePoint, pModifiers);
    }

    public void onConfirmClick(OnClick<ImageButton> clk) {
        this.confirm.setOnClick(clk);
    }

    public void onCancelClick(OnClick<ImageButton> clk) {
        this.cancel.setOnClick(clk);
    }

    public void onSaveClick(OnClick<ImageButton> clk) {
        this.save.setOnClick(clk);
    }

    public void onLoadClick(OnClick<ImageButton> clk) {
        this.load.setOnClick(clk);
    }

    public void onTemplateClick(OnClick<ImageButton> clk) {
        this.template.setOnClick(clk);
    }

    public void setConfirmVisible(boolean visible) {
        confirm.visible = visible;
    }

    public void setCancelVisible(boolean visible) {
        cancel.visible = visible;
    }

    public void setSaveVisible(boolean visible) {
        save.visible = visible;
    }

    public void setLoadVisible(boolean visible) {
        load.visible = visible;
    }

    public void setTemplateVisible(boolean visible) {
        template.visible = visible;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
