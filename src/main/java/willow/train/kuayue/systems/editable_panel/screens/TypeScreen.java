package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.NbtC2SPacket;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.EditBar;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.Label;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private int color;
    public boolean revert;
    public Label titleLabel;
    public ImageButton mirrorBtn, cancelBtn, confirmBtn;
    public EditBar editBar;
    public ColorScreenBundles colorEditor;

    public static final LazyRecomputable<ImageMask> cancelBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.cancelImage.get().copyWithOp(p -> p));

    public static final LazyRecomputable<ImageMask> acceptBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.acceptImage.get().copyWithOp(p -> p));

    public static final LazyRecomputable<ImageMask> mirrotBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.125f, .375f, .25f, .5f)));

    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
        editBar = new EditBar(0, 0, Component.empty(), "");
    }

    @Override
    public void init() {
        if (Minecraft.getInstance().screen == null) return;
        colorEditorInit();
        buttonsInit();

        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        EditablePanelEntity entity = getScreen().getMenu().getEditablePanelEntity();
        color = entity.getColor();
        String[] values = new String[5];
        for (int i = 0; i < 5; i++) {
            values[i] = nbt.getString("data" + i);
        }
        revert = nbt.getBoolean("revert");
        innerInit(values, color, font, revert);

        cancelBtn.setOnClick((w, x, y) -> {
            this.close();
        });

        confirmBtn.setOnClick((w, x, y) -> {
            BlockPos pos = entity.getBlockPos();
            nbt.putInt("color", this.color);
            nbt.putBoolean("revert", this.revert);
            TransparentEditBox[] boxes = new TransparentEditBox[5];
            int counter = 0;
            for (Renderable widget : getCustomWidgets()) {
                if (widget instanceof TransparentEditBox box) {
                    boxes[counter] = box;
                    counter++;
                }
            }
            for (int i = 0; i < 5; i++) {
                nbt.putString("data" + i, boxes[i].getValue());
            }
            CompoundTag tag = new CompoundTag();
            tag.put("data", nbt);
            entity.load(tag);
            entity.setChanged();
            AllPackets.CHANNEL.sendToServer(new NbtC2SPacket(pos, tag));
            this.close();
        });
    }

    public void buttonsInit() {
        titleLabel = new Label(Component.translatable("tooltip.kuayue.type_screen.title"));
        addCustomWidget(titleLabel);

        mirrorBtn = new ImageButton(mirrotBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            revert = !revert;
            refresh();
        });

        cancelBtn = new ImageButton(cancelBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});
        confirmBtn = new ImageButton(acceptBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});

        editBar.onCancelClick((w, x, y) -> editBar.visible = false);
        editBar.visible = false;

        addCustomWidget(cancelBtn);
        addCustomWidget(confirmBtn);
        addCustomWidget(editBar);
        addCustomWidget(mirrorBtn);
        addCustomWidget(colorEditor.getColorBtn());
        addCustomWidget(colorEditor.getTemplateBtn());
    }

    public void colorEditorInit() {
        colorEditor = new ColorScreenBundles();
        colorEditor.init();
        colorEditor.setOpen((selector, template, now) -> {
            selector.setRgb(this.color);
            setBoardWidgetVisible(false);
        });
        colorEditor.setCancel((selector, template, now) -> {
            setBoardWidgetVisible(true);
        });
        colorEditor.setSuccess((selector, template, now) -> {
            if (now == template) {
                this.color = template.getChosenBox().getTemplate().getColor();
                this.setTextColor(color);
                setBoardWidgetVisible(true);
            } else {
                this.color = selector.getColor().getRGB();
                setTextColor(color);
                setBoardWidgetVisible(true);
            }
        });
        colorEditor.visible = false;
        addCustomWidget(colorEditor);
    }

    public void buttonsInnerInit(Font font, float textScaleFactor, float size0,
                                 float size1, float size2, float size3, float size4, int sW, int sH) {
        int height = font.lineHeight;
        int labelW = (int) (size1 * 1.05f + size2 + size3 * 1.4f + size4);
        int labelH = (int) (height * 0.18f * textScaleFactor - 23 + textScaleFactor * height * 0.13f);
        int basicX = (sW - labelW) / 2 + 20, basicY = (sH - labelH) / 2 - 10;
        titleLabel.setWidth(font.width(titleLabel.getPlainText()));
        titleLabel.setPosition((float) (sW - titleLabel.getWidth()) / 2, basicY - 20);

        int btnY = basicY + labelH + 20;
        colorEditor.getColorBtn().setPos(basicX, btnY);
        colorEditor.getTemplateBtn().setPos(basicX + 20, btnY);
        cancelBtn.setPos(basicX + labelW - 60, btnY);
        confirmBtn.setPos(basicX + labelW - 80, btnY);
        mirrorBtn.setPos(basicX + 40, btnY);
    }

    private void innerInit(String[] values, int color, Font font, boolean revert) {
        float textScaleFactor = 16f;

        float size0 = ((float) Minecraft.getInstance().font.width(values[0])) * 0.13f * textScaleFactor; // 硬座车
        float size1 = ((float) Minecraft.getInstance().font.width(values[1])) * 0.08f * textScaleFactor; // YINGZUOCHE
        float size2 = ((float) Minecraft.getInstance().font.width(values[2])) * 0.23f * textScaleFactor; // YZ
        float size3 = ((float) Minecraft.getInstance().font.width(values[3])) * 0.12f * textScaleFactor; // 25T
        float size4 = ((float) Minecraft.getInstance().font.width(values[4])) * 0.3f * textScaleFactor; // 25T
        if (Minecraft.getInstance().screen == null) return;
        int sW = Minecraft.getInstance().screen.width;
        int sH = Minecraft.getInstance().screen.height;
        int height = font.lineHeight;
        int labelW = (int) (size1 * 1.05f + size2 + size3 * 1.4f + size4);
        int labelH = (int) (height * 0.18f * textScaleFactor - 23 + textScaleFactor * height * 0.13f);
        int basicX = (sW - labelW) / 2 + 20, basicY = (sH - labelH) / 2 - 10;

        buttonsInnerInit(font, textScaleFactor, size0, size1, size2, size3, size4, sW, sH);

        basicX += revert ? (labelW - size1) *.9f : 0;

        addCustomWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[0]), height, 0.13f * textScaleFactor, 0.18f * textScaleFactor,
                Component.empty(), values[0], color));

        basicX -= (size1 - size0) / 2;
        basicY += height * 0.18f * textScaleFactor;

        addCustomWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[1]), height, 0.08f * textScaleFactor, 0.08f * textScaleFactor,
                Component.empty(), values[1], color));

        basicX += revert ? (- (size2 + size3 + size1 * 0.05) - (size4 + size3 * 0.4 + 20) + 20) : size1 * 1.05;
        basicY -= 23;

        addCustomWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[2]), height, 0.23f * textScaleFactor, 0.25f * textScaleFactor,
                Component.empty(), values[2], color));

        basicX += size2;
        basicY += textScaleFactor * height * 0.13f;
        addCustomWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[3]), height, 0.12f * textScaleFactor, 0.12f * textScaleFactor,
                Component.empty(), values[3], color));

        basicX += size3 * 1.4;
        basicY -= textScaleFactor * height * 0.13f;
        addCustomWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[4]), height, 0.26f * textScaleFactor, 0.3f * textScaleFactor,
                Component.empty(), values[4], color));
    }

    private void refresh() {
        String[] values = new String[5];
        int counter = 0;
        int focus = -1;
        int focusIndex = -1;
        for (Renderable w : getCustomWidgets()) {
            if (!(w instanceof TransparentEditBox box)) continue;
            values[counter] = box.getValue();
            if (box.isFocused()){
                focus = counter;
                focusIndex = box.getCursorPosition();
            }
            counter++;
        }
        clearWidgets();

        addCustomWidget(cancelBtn);
        addCustomWidget(confirmBtn);
        addCustomWidget(titleLabel);
        addCustomWidget(editBar);
        addCustomWidget(mirrorBtn);
        addCustomWidget(colorEditor);
        addCustomWidget(colorEditor.getColorBtn());
        addCustomWidget(colorEditor.getTemplateBtn());

        clearLabels();
        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        int color = getScreen().getMenu().getEditablePanelEntity().getColor();
        innerInit(values, color, font, revert);
        if (focus > -1 && focusIndex > -1) {
            Renderable w = getCustomWidgets().get(focus);
            if (!(w instanceof TransparentEditBox box)) return;
            box.setFocused(true);
            box.setCursorPosition(focusIndex);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partial) {
        super.render(guiGraphics, mouseX, mouseY, partial);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int btn) {
        for (Renderable widget : getCustomWidgets()) {
            if (!(widget instanceof GuiEventListener listener) || !listener.isMouseOver(mouseX, mouseY)) continue;
            if (listener instanceof AbstractWidget widget1 && !widget1.visible) continue;
            if (listener instanceof ColorScreen cs && !cs.getVisible()) continue;
            if (listener instanceof GetShareTemplateScreen screen && !screen.isVisible()) continue;
            if (listener instanceof TransparentEditBox box) {
                editBar.setPosition(box.getX() + ((int) ((float) box.getWidth() * box.getScaleX()) - 200) / 2,
                        box.getY() + (int) ((float) box.getHeight() * box.getScaleY()) + 2);
                editBar.setText(box.getValue());
                editBar.onAcceptClick(
                        (w, x, y) -> {
                            box.setValue(editBar.getText());
                            editBar.visible = false;
                            refresh();
                        }
                );
                editBar.visible = true;
                editBar.setFocused(true);
                return true;
            }
            return listener.mouseClicked(mouseX, mouseY, btn);
        }
        return false;
    }

    @Override
    public void renderBackGround(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen == null) return;
        int sW = Minecraft.getInstance().screen.width;
        int sH = Minecraft.getInstance().screen.height;
        guiGraphics.fill(0, 0, sW, sH, 0x80000000);
    }

    @Override
    public void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {

    }

    @Override
    public boolean charTyped(char code, int modifier) {
        return super.charTyped(code, modifier);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    public void setButtonsVisible(boolean visible) {
        colorEditor.getTemplateBtn().visible = visible;
        colorEditor.getColorBtn().visible = visible;
        this.cancelBtn.visible = visible;
        this.confirmBtn.visible = visible;
        this.titleLabel.visible = visible;
        this.mirrorBtn.visible = visible;
        editBar.visible = false;
        editBar.setFocused(false);
    }

    public void setBoardWidgetVisible(boolean visible) {
        getCustomWidgets().forEach(w -> {
            if (w instanceof TransparentEditBox box) box.visible = visible;
        });
        setButtonsVisible(visible);
    }

    public void setTextColor(int color) {
        getCustomWidgets().forEach(w -> {
            if (w instanceof TransparentEditBox box) box.setTextColor(color);
        });
    }
}
