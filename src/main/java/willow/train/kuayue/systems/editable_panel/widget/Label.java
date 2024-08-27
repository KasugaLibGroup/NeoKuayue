package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.Vec2f;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;

public class Label {
    private Vec2f position;
    private Component text;
    private SimpleColor color;

    public Label(Vec2f position, Component component, SimpleColor color) {
        this.position = position;
        this.color = color;
        this.text = component;
    }

    public Label(Vec2f position, Component component) {
        this.text = component;
        this.position = position;
        color = SimpleColor.fromRGBInt(0xffffff);
    }

    public Label(Component component) {
        this.position = Vec2f.ZERO;
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

    public void setColor(SimpleColor color) {
        this.color = color;
    }

    public void setColor(int rgb) {
        this.color = SimpleColor.fromRGBInt(rgb);
    }

    public void setText(Component text) {
        this.text = text;
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

    public void renderToGui(PoseStack poseStack, Font font) {
        font.draw(poseStack, text, position.x(), position.y(), color.getRGB());
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, boolean transparent, SimpleColor backGroundColor,
                              int light) {
        font.drawInBatch(text, position.x(), position.y(), color.getRGB(),
                shadow, poseStack.last().pose(), buffer, transparent,
                backGroundColor.getRGB(), light);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer,
                              boolean shadow, int light) {
        font.drawInBatch(text, position.x(), position.y(), color.getRGB(), shadow, poseStack.last().pose(), buffer,
                true, 0, light);
    }

    public void renderToWorld(PoseStack poseStack, Font font, MultiBufferSource buffer, boolean shadow,
                              boolean transparent, int bgColor, int light) {
        font.drawInBatch(text, position.x(), position.y(), color.getRGB(),
                shadow, poseStack.last().pose(), buffer, transparent,
                bgColor, light);
    }
}
