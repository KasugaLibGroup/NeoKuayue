package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;

public class ColorScreenBundles extends AbstractWidget {

    public static final LazyRecomputable<ImageMask> colorBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.125f, .125f, .25f, .25f)));

    public static final LazyRecomputable<ImageMask> templateBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.875f, 0, 1, .125f)));

    private final ColorScreen colorEditor;
    private final ColorTemplateScreen templateScreen;
    private final GetShareTemplateScreen templateEditor;
    private ImageButton colorBtn, templateBtn;
    private BundleAction open, cancel, success;
    public ColorScreenBundles() {
        super(0, 0, Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                Minecraft.getInstance().getWindow().getGuiScaledHeight(), Component.empty());
        colorEditor = new ColorScreen(64, 32, Component.translatable("tooltip.kuayue.color_screen.title"));
        templateScreen = new ColorTemplateScreen(0, 0, 0, 0, Component.translatable("tooltip.kuayue.color_template_screen.title"));
        templateEditor = new GetShareTemplateScreen(Component.empty(), null);
        open = cancel = success = (a, b, c) -> {};
    }

    public void init() {
        if (Minecraft.getInstance().screen == null) return;
        colorEditor.init();
        templateScreen.init();
        templateEditor.init();
        int sW = this.width;
        int sH = this.height;

        templateScreen.setWidth(sW);
        templateScreen.setHeight(sH);
        templateScreen.init();
        templateScreen.visible = false;

        templateEditor.init();
        colorEditor.init();

        colorEditor.setVisible(false);
        templateEditor.setVisible(false);

        colorBtn = new ImageButton(colorBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            colorEditor.setVisible(true);
            onOpen(colorBtn);
        });

        templateBtn = new ImageButton(templateBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            onOpen(templateBtn);
            defTemplateBtn();
        });


        colorEditor.onCancelClick((btn, x, y) -> {
            colorEditor.setVisible(false);
            onCancel(colorEditor);
        });

        colorEditor.onConfirmClick((btn, x, y) -> {
            colorEditor.setVisible(false);
            ClientInit.COLOR_TEMPLATES.writeToFile();
            onSuccess(colorEditor);
        });

        colorEditor.onTemplateClick((c, px, py) -> {
            colorEditor.setVisible(false);
            defTemplateBtn();
            templateScreen.onCancelClick((w, x, y) -> {
                templateScreen.visible = false;
                colorEditor.setVisible(true);
            });
        });

        colorEditor.onLoadClick((c, px, py) -> {
            colorEditor.setVisible(false);
            templateScreen.visible = true;
            templateScreen.setEditVisible(false);
            templateScreen.setShareVisible(false);
            templateScreen.setDeleteVisible(false);

            templateScreen.onConfirmClick((w, x, y) -> {
                colorEditor.setRgb(templateScreen.getChosenBox().getTemplate().getColor());
                templateScreen.visible = false;
                colorEditor.setVisible(true);
                templateScreen.setEditVisible(true);
                templateScreen.setShareVisible(true);
                templateScreen.setDeleteVisible(true);
            });

            templateScreen.onCancelClick((w, x, y) -> {
                templateScreen.visible = false;
                colorEditor.setVisible(true);
                templateScreen.setEditVisible(true);
                templateScreen.setShareVisible(true);
                templateScreen.setDeleteVisible(true);
            });
        });

        colorEditor.onSaveClick((c, px, py) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            if (player == null) return;
            colorEditor.setVisible(false);
            templateEditor.setTemplate(ColorTemplate.defaultTemplate(colorEditor.getColor().getRGB() - 0xff000000, player.getName().getString()));
            templateEditor.setEditMode(true);
            templateEditor.onAcceptClick((w, x, y) -> {
                templateEditor.setVisible(false);
                templateEditor.fillDataToTemplate();
                ClientInit.COLOR_TEMPLATES.addTemplate(templateEditor.getTemplate());
                templateScreen.visible = true;
                defTemplateBtn();
                ClientInit.COLOR_TEMPLATES.writeToFile();
            });
            templateEditor.onCancelClick((w, x , y) -> {
                templateEditor.setVisible(false);
                colorEditor.setVisible(true);
            });
            templateEditor.setVisible(true);
        });
    }

    public void defTemplateBtn() {
        templateScreen.visible = true;
        templateScreen.onConfirmClick((w, x, y) -> {
            templateScreen.visible = false;
            onSuccess(templateScreen);
        });
        templateScreen.onCancelClick((w, x, y) -> {
            templateScreen.visible = false;
            onCancel(templateScreen);
        });
        templateScreen.onEditClick((w, x, y) -> {
            templateScreen.editScreen.setTemplate(templateScreen.getChosenBox().getTemplate());
            templateScreen.editScreen.setEditMode(true);
            templateScreen.editScreen.onAcceptClick((a, b, c) -> {
                templateScreen.editScreen.setVisible(false);
                templateScreen.editScreen.fillDataToTemplate();
                templateScreen.visible = true;
            });
            templateScreen.editScreen.onCancelClick((a, b, c) -> {
                templateScreen.editScreen.setVisible(false);
                templateScreen.visible = true;
            });
            templateScreen.visible = false;
            templateScreen.editScreen.setVisible(true);
        });
    }


    public void renderButton(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        colorEditor.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        templateScreen.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        templateEditor.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void setTemplateBtnVisible(boolean visible) {
        if (templateBtn == null) return;
        templateBtn.visible = visible;
    }

    public void setColorBtnVisible(boolean visible) {
        if (colorBtn == null) return;
        colorBtn.visible = visible;
    }

    public ImageButton getColorBtn() {
        return colorBtn;
    }

    public ImageButton getTemplateBtn() {
        return templateBtn;
    }

    public void setOpen(@NotNull BundleAction open) {
        this.open = open;
    }

    public void setCancel(@NotNull BundleAction cancel) {
        this.cancel = cancel;
    }

    public void setSuccess(@NotNull BundleAction success) {
        this.success = success;
    }

    private void onSuccess(AbstractWidget now) {
        success.act(colorEditor, templateScreen, now);
        this.visible = false;
    }

    private void onCancel(AbstractWidget now) {
        cancel.act(colorEditor, templateScreen, now);
        this.visible = false;
    }

    private void onOpen(AbstractWidget now) {
        this.visible = true;
        open.act(colorEditor, templateScreen, now);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderButton(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if(!visible || !isMouseOver(pMouseX, pMouseY)) return false;
        boolean result = false;
        result = colorEditor.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || templateScreen.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || templateEditor.mouseClicked(pMouseX, pMouseY, pButton);
        return result;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if(!visible || !isMouseOver(pMouseX, pMouseY)) return false;
        boolean result = false;
        result = colorEditor.mouseReleased(pMouseX, pMouseY, pButton);
        result = result || templateScreen.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || templateEditor.mouseClicked(pMouseX, pMouseY, pButton);
        return result;
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if(!visible || !isMouseOver(pMouseX, pMouseY)) return false;
        boolean result = false;
        result = colorEditor.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        result = result || templateScreen.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        result = result || templateEditor.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return result;
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if(!visible || !isMouseOver(pMouseX, pMouseY)) return false;
        boolean result = false;
        result = colorEditor.mouseScrolled(pMouseX, pMouseY, pDelta);
        result = result || templateScreen.mouseScrolled(pMouseX, pMouseY, pDelta);
        result = result || templateEditor.mouseScrolled(pMouseX, pMouseY, pDelta);
        return result;
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if(!visible) return false;
        boolean result = false;
        result = colorEditor.charTyped(pCodePoint, pModifiers);
        result = result || templateScreen.charTyped(pCodePoint, pModifiers);
        result = result || templateEditor.charTyped(pCodePoint, pModifiers);
        return  result;
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (!visible) return false;
        boolean result = false;
        result = colorEditor.keyPressed(pKeyCode, pScanCode, pModifiers);
        result = result || templateScreen.keyPressed(pKeyCode, pScanCode, pModifiers);
        result = result || templateEditor.keyPressed(pKeyCode, pScanCode, pModifiers);
        return result;
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if(!visible) return false;
        boolean result = false;
        result = colorEditor.keyReleased(pKeyCode, pScanCode, pModifiers);
        result = result || templateScreen.keyReleased(pKeyCode, pScanCode, pModifiers);
        result = result || templateEditor.keyReleased(pKeyCode, pScanCode, pModifiers);
        return result;
    }

    public interface BundleAction {
        void act(ColorScreen colorEditor, ColorTemplateScreen templateScreen, AbstractWidget now);
    }
}
