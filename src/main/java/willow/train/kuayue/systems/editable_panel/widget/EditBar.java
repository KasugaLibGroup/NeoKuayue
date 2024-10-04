package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.screens.GetShareTemplateScreen;

import java.io.IOException;

public class EditBar extends AbstractWidget {

    public static final LazyRecomputable<ImageMask> editBarBg =
            new LazyRecomputable<>(() -> {
                try {
                    ImageMask mask = ClientInit.advancementWidgets.getImage().get().getMask();
                    mask.rectangleUV(0, 55f / 256f, 200f / 256f, 75f/256f);
                    return mask;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
    private TransparentEditBox editBox;
    private ImageButton accept, cancel;
    private final LazyRecomputable<ImageMask> cancelImage =
            LazyRecomputable.of(() -> GetShareTemplateScreen.cancelImage.get().copyWithOp(p -> p));
    private final LazyRecomputable<ImageMask> acceptImage =
            LazyRecomputable.of(() -> GetShareTemplateScreen.acceptImage.get().copyWithOp(p -> p));

    public EditBar(int pX, int pY, Component pMessage, String defaultValue) {
        super(pX, pY, 200, 20, pMessage);
        ImageMask mask = editBarBg.get();
        mask.rectangle(new Vector3f(pX, pY, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 200, 20);
        editBox = new TransparentEditBox(Minecraft.getInstance().font, this.x + 6, this.y + 6,
                150, 14, pMessage, defaultValue, 0xffffffff);
        cancel = new ImageButton(cancelImage, pX + 160, 2, 16, 16, Component.literal("cancel"), b -> {});
        accept = new ImageButton(acceptImage, pX + 180, 2, 16, 16, Component.literal("accept"), b -> {});
    }

    public void setX(int x) {
        this.x = x;
        editBarBg.get().rectangle(new Vector3f(x, getY(), 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 200, 20);
        cancel.setX(x + 160);
        accept.setX(x + 180);
        editBox.setX(x + 6);
    }

    public void setY(int y) {
        this.y = y;
        editBarBg.get().rectangle(new Vector3f(getX(), y, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 200, 20);
        cancel.setY(y + 2);
        accept.setY(y + 2);
        editBox.setY(y + 6);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        editBarBg.get().rectangle(new Vector3f(x, y, 0), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 200, 20);
        editBox.setX(x + 6);
        editBox.setY(y + 6);
        cancel.setPos(x + 160, y + 2);
        accept.setPos(x + 180, y + 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        editBarBg.get().renderToGui();
        editBox.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        accept.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        cancel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void onClick(double pMouseX, double pMouseY) {
        if (cancel.isMouseOver(pMouseX, pMouseY)) {
            cancel.onClick(pMouseX, pMouseY);
        } else if (accept.isMouseOver(pMouseX, pMouseY)) {
            accept.onClick(pMouseX, pMouseY);
        }
        super.onClick(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        editBox.mouseClicked(pMouseX, pMouseY, pButton);
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        editBox.mouseReleased(pMouseX, pMouseY, pButton);
        return super.mouseReleased(pMouseX, pMouseY, pButton);
    }

    public void onCancelClick(OnClick<ImageButton> clk) {
        this.cancel.setOnClick(clk);
    }

    public void onAcceptClick(OnClick<ImageButton> clk) {
        this.accept.setOnClick(clk);
    }

    public String getText() {
        return editBox.getValue();
    }

    public void setText(String text) {
        editBox.setValue(text);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return editBox.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return editBox.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return editBox.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
    public void setFocused(boolean focused) {
        super.setFocused(focused);
        editBox.changeFocus(focused);
    }
}
