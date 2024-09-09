package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.Vec2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

public class Label extends AbstractWidget {
    private Vec2f position, scale;
    private Component text;
    private SimpleColor color;

    public Label(Vec2f position, Component component, SimpleColor color) {
        super((int) position.x(), (int) position.y(),
                Minecraft.getInstance().font.width(component.getString()),
                Minecraft.getInstance().font.lineHeight, component);
        this.position = position;
        this.scale = new Vec2f(1f, 1f);
        this.color = color;
        this.text = component;
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

    public Label(Component component) {
        super(0, 0,
                Minecraft.getInstance().font.width(component.getString()),
                Minecraft.getInstance().font.lineHeight, component);
        this.position = Vec2f.ZERO;
        this.scale = new Vec2f(1f, 1f);
        this.text = component;
        color = SimpleColor.fromRGBInt(0xffffff);
    }

    public void setPosition(Vec2f position) {
        this.position = position;
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
        this.setWidth(Minecraft.getInstance().font.width(text));
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

    public void setScale(float x, float y) {
        Font font = Minecraft.getInstance().font;
        float orgX = font.width(text.getString());
        float orgY = font.lineHeight;
        this.scale = new Vec2f(x, y);
        this.setWidth(Math.round(orgX * x));
        this.setHeight(Math.round(orgY * y));
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderToGui(pPoseStack, Minecraft.getInstance().font);
    }

    public void renderToGui(PoseStack poseStack, Font font) {
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.draw(poseStack, text, 0, 0, color.getRGB());
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, boolean transparent, SimpleColor backGroundColor,
                              int light) {
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(),
                shadow, poseStack.last().pose(), buffer, transparent,
                backGroundColor.getRGB(), light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, int light) {
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(), shadow, poseStack.last().pose(), buffer,
                true, 0, light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer, boolean shadow,
                              boolean transparent, int bgColor, int light) {
        poseStack.translate(position.x(), position.y(), 0);
        poseStack.scale(scale.x(), scale.y(), 1f);
        font.drawInBatch(text, 0, 0, color.getRGB(),
                shadow, poseStack.last().pose(), buffer, transparent,
                bgColor, light);
        poseStack.scale(1 / scale.x(), 1 / scale.y(), 1f);
        poseStack.translate(- position.x(), - position.y(), 0);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
        pNarrationElementOutput.add(NarratedElementType.TITLE, this.text.getString());
    }
}
