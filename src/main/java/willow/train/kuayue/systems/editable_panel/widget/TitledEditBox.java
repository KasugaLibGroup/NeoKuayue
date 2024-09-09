package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class TitledEditBox extends AbstractWidget {
    private final EditBox editBox;
    private int textColor;
    public TitledEditBox(int pX, int pY, int pWidth, int pHeight,
                         Component title, EditBoxSupplier<EditBox> editBoxSupplier) {
        super(pX, pY, pWidth, pHeight, title);
        editBox = editBoxSupplier.get(pX, pY, pWidth, pHeight, title);
        textColor = 0xffffff;
        setX(pX);
        setWidth(pWidth);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
        pNarrationElementOutput.add(NarratedElementType.TITLE, getMessage().getString());
    }

    @Override
    public void setWidth(int pWidth) {
        super.setWidth(pWidth);
        if (pWidth < getTitleWidth() * 1.2f + 5)
            pWidth = (int) (getTitleWidth() * 1.2f) + 5;
        float boxWidth = pWidth - getTitleWidth() - 3;
        editBox.setWidth((int) boxWidth);
    }

    @Override
    public void setHeight(int value) {
        super.setHeight(value);
        editBox.setHeight(value);
    }

    public void setX(int x) {
        this.x = x;
        editBox.setX(x + (int) Math.ceil(getTitleWidth()) + 3);
    }

    public void setY(int y) {
        this.y = y;
        editBox.y = y;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        editBox.setTextColor(getTextColor());
    }

    public void setValue(String value) {
        editBox.setValue(value);
    }

    public String getValue() {
        return editBox.getValue();
    }

    public int getTextColor() {
        return textColor;
    }

    public float getTitleWidth() {
        return Minecraft.getInstance().font.width(getMessage().getString());
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        Component message = getMessage();
        Minecraft.getInstance().font.draw(pPoseStack, message, x, y, textColor);
        editBox.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        fill(pPoseStack, editBox.x - 1, editBox.y + 8,
                editBox.x + Minecraft.getInstance().font.width(getValue()) + 2,
                editBox.y + 9, 0xffffffff);
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
        editBox.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton) &
                editBox.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY) &
                editBox.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        return super.mouseReleased(pMouseX, pMouseY, pButton) &
                editBox.mouseReleased(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta) &
                editBox.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers) &
                editBox.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers) &
                editBox.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers) &
                editBox.charTyped(pCodePoint, pModifiers);
    }

    public interface EditBoxSupplier<T extends EditBox> {
        T get(int x, int y, int width, int height, Component title);
    }
}
