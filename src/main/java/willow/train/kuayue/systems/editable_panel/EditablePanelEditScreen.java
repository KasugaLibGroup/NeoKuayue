package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.ImageInit;
import willow.train.kuayue.utils.client.RenderablePicture;

public class EditablePanelEditScreen extends AbstractContainerScreen<EditablePanelEditMenu> {

    private EditablePanelEntity entity;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Kuayue.MODID, "textures/gui/gui.png");

    public static final ImageInit.ImageRegex DICE_REGEX = ImageInit.register("textures/gui/dice_button.png", 0);

    public final RenderablePicture dice;

    public EditablePanelEditScreen(EditablePanelEditMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        loadEntity(pMenu.getEditablePanelEntity());
        dice = new RenderablePicture(DICE_REGEX);
    }

    public void loadEntity(EditablePanelEntity entity) {
        this.entity = entity;
    }

    @Override
    public void init() {
        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(entity.getEditType())) {
                signType.getScreenMethods().get().init(this);
                return;
            }
        }
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(entity.getEditType())) {
                signType.getScreenMethods().get().renderLabels(pPoseStack, pMouseX, pMouseY);
                return;
            }
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        if (this.entity.getEditType() == TrainPanelProperties.EditType.LAQUERED)
            return;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }
}
