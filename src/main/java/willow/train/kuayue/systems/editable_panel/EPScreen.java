package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.systems.editable_panel.screens.CustomScreen;

public class EPScreen extends AbstractContainerScreen<EditablePanelEditMenu> {
    private CustomScreen<EditablePanelEditMenu, EditablePanelEntity> subScreen;
    public EPScreen(EditablePanelEditMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        TrainPanelProperties.EditType editType = pMenu.getEditablePanelEntity().getEditType();
        subScreen = EditableTypeConstants.getSignTypeByEditType(editType).getScreenMethods()
                .get(this, pMenu.getEditablePanelEntity().getNbt());
    }

    @Override
    protected void init() {
        super.init();
        subScreen.clearWidgets();
        subScreen.clearLabels();
        subScreen.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);
        subScreen.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        subScreen.renderBackGround(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderTooltip(PoseStack pPoseStack, int pX, int pY) {
        subScreen.renderTooltip(pPoseStack, pX, pY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        this.subScreen.mouseClicked(pMouseX, pMouseY, pButton);
        return true;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        this.subScreen.mouseReleased(pMouseX, pMouseY, pButton);
        return true;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        this.subScreen.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return true;
    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        this.subScreen.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        this.subScreen.mouseScrolled(pMouseX, pMouseY, pDelta);
        return true;
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        this.subScreen.charTyped(pCodePoint, pModifiers);
        return true;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
        if (!subScreen.isKeyEscBlocked() && mouseKey.getValue() == InputConstants.KEY_ESCAPE)
            onClose();
        this.subScreen.keyPressed(pKeyCode, pScanCode, pModifiers);
        return true;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        this.subScreen.keyReleased(pKeyCode, pScanCode, pModifiers);
        return true;
    }

    @Override
    public boolean changeFocus(boolean pFocus) {
        this.subScreen.changeFocus(pFocus);
        return true;
    }

    public void close() {
        this.onClose();
    }

    @Override
    public void onClose() {
        subScreen.onClosed();
        super.onClose();
    }
}
