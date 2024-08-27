package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
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
        this.subScreen.onMouseClicked(pMouseX, pMouseY, pButton);
        return true;
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        this.subScreen.charTyped(pCodePoint, pModifiers);
        return true;
    }

    @Override
    public void onClose() {
        subScreen.onClosed();
        super.onClose();
    }
}
