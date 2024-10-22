package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.Vec2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

public class Label extends AbstractWidget {
    private Vec2f position, scale;
    public Component text;
    private SimpleColor color;
    private OnClick<Label> clk;

    public Label(Vec2f position, Component component, SimpleColor color) {
        super((int) position.x(), (int) position.y(),
                Minecraft.getInstance().font.width(component.getString()),
                Minecraft.getInstance().font.lineHeight, component);
        this.position = position;
        this.scale = new Vec2f(1f, 1f);
        this.color = color;
        this.text = component;
        clk = (a, b, c) -> {};
    }

    public Label(Vec2f position, String str, SimpleColor color) {
        this(position, Component.literal(str), color);
    }

    public Label(Vec2f position, Component component) {
        super((int) position.x(), (int) position.y(),
                Minecraft.getInstance().font.width(component.getString()),
                Minecraft.getInstance().font.lineHeight, component);
        this.text = component;
        this.scale = new Vec2f(1f, 1f);
        this.position = position;
        color = SimpleColor.fromRGBInt(0xffffff);
    }

    public Label(Vec2f position, String str) {
        this(position, Component.literal(str));
    }

    public Label(Component component) {
        super(0, 0,
                Minecraft.getInstance().font.width(component.getString()),
                Minecraft.getInstance().font.lineHeight, component);
        this.position = Vec2f.ZERO;
        this.scale = new Vec2f(1f, 1f);
        this.text = component;
        color = SimpleColor.fromRGBInt(0xffffff);
    }

    public Label(String str) {
        this(Component.literal(str));
    }

    public void setPosition(Vec2f position) {
        this.position = position;
        this.setX((int) position.x());
        this.setY((int) position.y());
    }

    public void setPosition(float x, float y) {
        this.position = new Vec2f(x, y);
        this.setX((int) x);
        this.setY((int) y);
    }

    public SimpleColor getColor() {
        return color;
    }

    public int getRGB() {
        return color.getRGB();
    }

    public void setColor(SimpleColor color) {this.color = color;}

    public void setColor(int rgb) {
        this.color = SimpleColor.fromRGBInt(rgb);
    }

    public void setText(Component text) {
        this.text = text;
        this.setWidth(Minecraft.getInstance().font.width(text) + 4);
    }

    public void move(float x, float y) {
        this.move(new Vec2f(x, y));
    }

    public void move(Vec2f offset) {
        position = position.add(offset);
    }

    public Component getText() {
        return text;
    }

    public Vec2f getPosition() {
        return position;
    }

    public String getPlainText() {
        return text.getString();
    }

    public Vec2f getScale() {
        return scale;
    }

    public void setScale(float x, float y) {
        Font font = Minecraft.getInstance().font;
        float orgX = font.width(text.getString());
        float orgY = font.lineHeight;
        this.scale = new Vec2f(x, y);
        this.setWidth(Math.round(orgX * x));
        this.setHeight(Math.round(orgY * y));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if (!visible) return;
        renderToGui(guiGraphics, Minecraft.getInstance().font);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void renderToGui(GuiGraphics guiGraphics, Font font) {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        guiGraphics.drawString(font, text, 0, 0, color.getRGB(), false);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, SimpleColor backGroundColor,
                              int light) {
        if (!visible) return;
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(),
                shadow, poseStack.last().pose(), buffer, Font.DisplayMode.NORMAL,
                backGroundColor.getRGB(), light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, int light) {
        if (!visible) return;
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(), shadow, poseStack.last().pose(), buffer,
                Font.DisplayMode.NORMAL, 0, light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer, boolean shadow,
                              Font.DisplayMode mode, int bgColor, int light) {
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(),
                shadow, poseStack.last().pose(), buffer, mode,
                bgColor, light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void setOnCLick(OnClick<Label> clk) {
        this.clk = clk;
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (this.clk == null) return;
        this.clk.click(this, pMouseX, pMouseY);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
