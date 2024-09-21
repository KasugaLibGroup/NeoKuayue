package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.Vec2f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class DescriptionLabel extends TooltipLabel {
    public DescriptionLabel(Vec2f position, int width, int height, Component component, SimpleColor color) {
        super(position, component, color);
        this.width = width;
        this.height = height;
        updateStrings();
    }

    public DescriptionLabel(Vec2f position, Component component) {
        super(position, component);
    }

    public DescriptionLabel(Component component) {
        super(component);
    }

    public DescriptionLabel(String str) {
        super(str);
    }

    @Override
    public void setHeight(int value) {
        super.setHeight(value);
        updateStrings();
    }

    public void setWidthAndHeight(int width, int height) {
        this.width = width;
        this.height = height;
        updateStrings();
    }

    @Override
    protected void updateStrings() {
        texts.clear();
        if (getText().getString().equals("")) return;
        Font font = Minecraft.getInstance().font;
        String str = this.getText().getString(), cache;
        int h = 0;
        while (str.length() > 0) {
            cache = font.plainSubstrByWidth(str, this.width - borderWidth * 2 - 2);
            if (h + 8 > height && cache.length() < str.length()) {
                if (cache.length() < 3) return;
                cache = cache.substring(0, cache.length() - 3) + "...";
                texts.add(cache);
                return;
            }
            str = str.substring(cache.length());
            texts.add(cache);
            h += 8;
        }
    }

    @Override
    public void renderGuiBg(PoseStack poseStack, int minX, int minY, int maxX, int maxY, int bgColor, int borderColor) {}
}
