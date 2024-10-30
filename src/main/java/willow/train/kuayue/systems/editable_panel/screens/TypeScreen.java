package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import kasuga.lib.core.util.data_type.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.NbtC2SPacket;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.*;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private int color;
    public boolean revert;
    public Label titleLabel;
    public ImageButton mirrorBtn, cancelBtn, confirmBtn;
    public EditBar editBar;
    public ColorScreenBundles colorEditor;
    private OffsetEditor offsetEditor;

    private final LazyRecomputable<ImageMask> cancelBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.cancelImage.get().copyWithOp(p -> p));

    private final LazyRecomputable<ImageMask> acceptBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.acceptImage.get().copyWithOp(p -> p));

    private final LazyRecomputable<ImageMask> mirrorBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.125f, .375f, .25f, .5f)));

    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
        setBlockEntity(screen.getMenu().getEditablePanelEntity());
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
            for (Widget widget : getWidgets()) {
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
            entity.markUpdated();
            AllPackets.CHANNEL.sendToServer(new NbtC2SPacket(pos, tag));
            this.close();
        });
    }

    public void buttonsInit() {
        titleLabel = new Label(Component.translatable("tooltip.kuayue.type_screen.title"));
        addWidget(titleLabel);

        mirrorBtn = new ImageButton(mirrorBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            revert = !revert;
            refresh();
        });

        offsetEditor = new OffsetEditor(0, 0, Component.literal("offset"),
                -.5f, .5f, -.5f, .5f, 0f, 0f);
        offsetEditor.setPosition((Minecraft.getInstance().screen.width - offsetEditor.getWidth()) / 2,
                (Minecraft.getInstance().screen.height - offsetEditor.getHeight()) / 2);
        offsetEditor.visible = false;

        cancelBtn = new ImageButton(cancelBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});
        confirmBtn = new ImageButton(acceptBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});

        editBar.onCancelClick((w, x, y) -> editBar.visible = false);
        editBar.visible = false;
        offsetEditor.onCancelBtnClick(((widget, mouseX, mouseY) -> {
            setBoardWidgetVisible(true);
            offsetEditor.visible = false;
        }));
        offsetEditor.onEditorBtnClick((widget, mouseX, mouseY) -> {
            setBoardWidgetVisible(false);
            offsetEditor.visible = true;
            offsetEditor.setCursorPosition(getNbt().getFloat("offset_x"), getNbt().getFloat("offset_y"));
        });
        offsetEditor.onAcceptBtnClick((widget, mouseX, mouseY) -> {
            setBoardWidgetVisible(true);
            Pair<Float, Float> offset = offsetEditor.getCursorPosition();
            getNbt().putFloat("offset_x", offset.getFirst());
            getNbt().putFloat("offset_y", offset.getSecond());
            getBlockEntity().saveNbt(this.getNbt());
            offsetEditor.visible = false;
        });

        addWidget(cancelBtn);
        addWidget(confirmBtn);
        addWidget(editBar);
        addWidget(mirrorBtn);
        addWidget(colorEditor.getColorBtn());
        addWidget(colorEditor.getTemplateBtn());
        addWidget(offsetEditor);
        addWidget(offsetEditor.getEditorBtn());
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
        addWidget(colorEditor);
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
        offsetEditor.getEditorBtn().setPos(basicX + labelW - 100, btnY);
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

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[0]), height, 0.13f * textScaleFactor, 0.18f * textScaleFactor,
                Component.empty(), values[0], color));

        basicX -= (size1 - size0) / 2;
        basicY += height * 0.18f * textScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[1]), height, 0.08f * textScaleFactor, 0.08f * textScaleFactor,
                Component.empty(), values[1], color));

        basicX += revert ? (- (size2 + size3 + size1 * 0.05) - (size4 + size3 * 0.4 + 20) + 20) : size1 * 1.05;
        basicY -= 23;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[2]), height, 0.23f * textScaleFactor, 0.25f * textScaleFactor,
                Component.empty(), values[2], color));

        basicX += size2;
        basicY += textScaleFactor * height * 0.13f;
        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[3]), height, 0.12f * textScaleFactor, 0.12f * textScaleFactor,
                Component.empty(), values[3], color));

        basicX += size3 * 1.4;
        basicY -= textScaleFactor * height * 0.13f;
        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[4]), height, 0.26f * textScaleFactor, 0.3f * textScaleFactor,
                Component.empty(), values[4], color));
    }

    private void refresh() {
        if (editBar.visible) return;
        String[] values = new String[5];
        int counter = 0;
        int focus = -1;
        int focusIndex = -1;
        for (Widget w : getWidgets()) {
            if (!(w instanceof TransparentEditBox box)) continue;
            values[counter] = box.getValue();
            if (box.isFocused()){
                focus = counter;
                focusIndex = box.getCursorPosition();
            }
            counter++;
        }
        clearWidgets();

        addWidget(cancelBtn);
        addWidget(confirmBtn);
        addWidget(titleLabel);
        addWidget(editBar);
        addWidget(mirrorBtn);
        addWidget(colorEditor);
        addWidget(colorEditor.getColorBtn());
        addWidget(colorEditor.getTemplateBtn());

        clearLabels();
        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        int color = getScreen().getMenu().getEditablePanelEntity().getColor();
        innerInit(values, color, font, revert);
        if (focus > -1 && focusIndex > -1) {
            Widget w = getWidgets().get(focus);
            if (!(w instanceof TransparentEditBox box)) return;
            box.setFocus(true);
            box.setCursorPosition(focusIndex);
        }
    }

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partial) {
        super.render(pose, mouseX, mouseY, partial);
        if (offsetEditor == null) return;
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int btn) {
        for (Widget widget : getWidgets()) {
            if (!(widget instanceof GuiEventListener listener)) continue;
            if (!listener.isMouseOver(mouseX, mouseY)) continue;
            if (listener instanceof AbstractWidget widget1 && !widget1.visible) continue;
            if (listener instanceof ColorScreen cs && !cs.getVisible()) continue;
            if (listener instanceof GetShareTemplateScreen screen && !screen.isVisible()) continue;
            if (widget instanceof TransparentEditBox box) {
                editBar.setPosition(box.x + ((int) ((float) box.getWidth() * box.getScaleX()) - 200) / 2,
                        box.y + (int) ((float) box.getHeight() * box.getScaleY()) + 2);
                editBar.setText(box.getValue());
                editBar.onAcceptClick(
                        (w, x, y) -> {
                            box.setValue(editBar.getText());
                            editBar.visible = false;
                            refresh();
                            getBlockEntity().saveNbt(this.getNbt());
                        }
                );
                editBar.visible = true;
                editBar.setFocused(true);
                return;
            }
            listener.mouseClicked(mouseX, mouseY, btn);
            return;
        }

    }

    @Override
    public void renderBackGround(PoseStack pose, int mouseX, int mouseY, float partialTick) {
        if (Minecraft.getInstance().screen == null) return;
        int sW = Minecraft.getInstance().screen.width;
        int sH = Minecraft.getInstance().screen.height;
        GuiComponent.fill(pose, 0, 0, sW, sH, 0x80000000);
    }

    @Override
    public void renderTooltip(PoseStack pose, int mouseX, int mouseY) {

    }

    @Override
    public void charTyped(char code, int modifier) {
        super.charTyped(code, modifier);
    }

    @Override
    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    public void setButtonsVisible(boolean visible) {
        colorEditor.getTemplateBtn().visible = visible;
        colorEditor.getColorBtn().visible = visible;
        this.cancelBtn.visible = visible;
        this.confirmBtn.visible = visible;
        this.titleLabel.visible = visible;
        this.mirrorBtn.visible = visible;
        editBar.visible = false;
        this.offsetEditor.getEditorBtn().visible = visible;
        editBar.setFocused(false);
    }

    public void setBoardWidgetVisible(boolean visible) {
        getWidgets().forEach(w -> {
            if (w instanceof TransparentEditBox box) box.visible = visible;
        });
        setButtonsVisible(visible);
    }

    public void setTextColor(int color) {
        getWidgets().forEach(w -> {
            if (w instanceof TransparentEditBox box) box.setTextColor(color);
        });
    }
}
