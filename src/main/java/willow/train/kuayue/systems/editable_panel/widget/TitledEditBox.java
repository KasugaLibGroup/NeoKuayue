package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public void setX(int x) {
        super.setX(x);
        editBox.setX(x + (int) Math.ceil(getTitleWidth()) + 3);
    }

    public void setY(int y) {
        super.setY(y);
        editBox.setY(y);
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
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        Component message = getMessage();
        guiGraphics.drawString(Minecraft.getInstance().font, message, getX(), getY(), textColor);
        editBox.renderWidget(guiGraphics, pMouseX, pMouseY, pPartialTick);
        guiGraphics.fill(editBox.getX() - 1, editBox.getY() + 8,
                editBox.getX() + Minecraft.getInstance().font.width(getValue()) + 2,
                editBox.getY() + 9, 0xffffffff);
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
