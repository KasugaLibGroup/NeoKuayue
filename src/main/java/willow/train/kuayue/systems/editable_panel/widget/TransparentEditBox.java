package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class TransparentEditBox extends EditBox {
    private float scaleX = 1.0f, scaleY = 1.0f;
    public TransparentEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, Component pMessage, String value, int fgColor) {
        super(pFont, pX, pY, pWidth, pHeight, null, pMessage);
        setBordered(false);
        setValue(value);
        setFGColor(fgColor);
    }

    public TransparentEditBox(Font pFont, int pX, int pY, int pWidth, int pHeight, float scaleX,
                              float scaleY, Component pMessage, String value, int fgColor) {
        super(pFont, pX, pY, pWidth, pHeight, null, pMessage);
        setBordered(false);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setValue(value);
        setFGColor(fgColor);
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scale) {
        this.scaleX = scale;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getScaleY() {
        return scaleY;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        pPoseStack.scale(scaleX, scaleY, 1f);
        if (this.visible) {
            this.isHovered = pMouseX >= this.x && pMouseY >= this.y && pMouseX < this.x + this.width * scaleX && pMouseY < this.y + this.height * scaleY;
            this.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        }
        pPoseStack.scale(1/scaleX, 1/scaleY, 1f);
    }
}
