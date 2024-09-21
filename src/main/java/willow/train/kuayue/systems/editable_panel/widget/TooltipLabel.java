package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.Vec2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

public class TooltipLabel extends Label {
    protected final ArrayList<String> texts;
    protected int borderWidth;
    protected boolean forceLeftBegin;
    public TooltipLabel(Vec2f position, Component component, SimpleColor color) {
        super(position, component, color);
        texts = new ArrayList<>();
        borderWidth = 1;
        forceLeftBegin = false;
    }

    public TooltipLabel(Vec2f position, String str, SimpleColor color) {
        this(position, Component.literal(str), color);
    }

    public TooltipLabel(Vec2f position, Component component) {
        super(position, component);
        texts = new ArrayList<>();
        borderWidth = 1;
        forceLeftBegin = false;
    }

    public TooltipLabel(Vec2f position, String str) {
        this(position, Component.literal(str));
    }

    public TooltipLabel(Component component) {
        super(component);
        texts = new ArrayList<>();
        borderWidth = 1;
    }

    public TooltipLabel(String str) {
        this(Component.literal(str));
    }

    protected void updateStrings() {
        texts.clear();
        if (getText().getString().equals("")) return;
        Font font = Minecraft.getInstance().font;
        String str = this.getText().getString(), cache;
        while (str.length() > 0) {
            cache = font.plainSubstrByWidth(str, this.width - borderWidth * 2 - 2);
            str = str.substring(cache.length());
            texts.add(cache);
        }
        this.height = texts.size() * 8 + borderWidth * 2 + 2;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        updateStrings();
    }

    public void setForceLeftBegin(boolean forceLeftBegin) {
        this.forceLeftBegin = forceLeftBegin;
    }

    public boolean isForceLeftBegin() {
        return forceLeftBegin;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    @Override
    public void setWidth(int pWidth) {
        super.setWidth(pWidth);
        updateStrings();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderToGui(PoseStack poseStack, Font font) {
        if (!visible || texts.isEmpty()) return;
        int w = this.width;
        int h = this.texts.size() * 8;
        if (getHeight() > h) h = getHeight();
        poseStack.translate(this.x, this.y, 0);
        poseStack.scale(this.getScale().x(), this.getScale().y(), 1f);
        renderGuiBg(poseStack, 0, 0, w, h, 0xff000000, 0xffffffff);
        int i = 0;
        if (texts.size() > 1) {
            poseStack.translate(borderWidth + 1, borderWidth + 1, 0);
            for (String s : texts) {
                i++;
                font.draw(poseStack, s, 0, 0, getRGB());
                poseStack.translate(0, 8, 0);
            }
            poseStack.translate(- (float) borderWidth - 1, - i * 8 - borderWidth - 1, 0);
        } else if (!isForceLeftBegin()) {
            String str = texts.get(0);
            float strWidth = font.width(str);
            float offset = (this.width - strWidth) / 2;
            poseStack.translate(offset, borderWidth + 1, 0);
            font.draw(poseStack, str, 0, 0, getRGB());
            poseStack.translate(- offset, - borderWidth - 1, 0);
        } else {
            font.draw(poseStack, texts.get(0), 0, 0, getRGB());
        }
        poseStack.scale(1 / this.getScale().x(), 1 / this.getScale().y(), 1f);
        poseStack.translate(- x, - y, 0);
    }

    public void renderGuiBg(PoseStack poseStack, int minX, int minY, int maxX, int maxY, int bgColor, int borderColor) {
        ColorTemplatesBox.renderGuiBg(poseStack, minX, minY, maxX, maxY, borderWidth, bgColor, borderColor);
    }
}
