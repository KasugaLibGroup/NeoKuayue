package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.NineSlicedImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.network.c2s.NbtC2SPacket;
import willow.train.kuayue.systems.editable_panel.AllColorTemplates;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.EditBar;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.Label;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

import java.io.IOException;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private int color;
    ColorScreen colorScreen;
    ColorTemplateScreen cts;
    boolean revert;
    GetShareTemplateScreen gsts;
    Label label;
    ImageButton colorBtn, templateBtn, cancelBtn, confirmBtn;
    EditBar editBar;

    public static final LazyRecomputable<ImageMask> colorBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.125f, .125f, .25f, .25f)));

    public static final LazyRecomputable<ImageMask> templateBtnImage =
            new LazyRecomputable<>(() -> ColorTemplateScreen.buttons.get().copyWithOp(p -> p.rectangleUV(.875f, 0, 1, .125f)));

    public static final LazyRecomputable<ImageMask> cancelBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.cancelImage.get().copyWithOp(p -> p));

    public static final LazyRecomputable<ImageMask> acceptBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.acceptImage.get().copyWithOp(p -> p));

    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
        colorScreen = new ColorScreen(64, 32, Component.translatable("tooltip.kuayue.color_screen.title"));
        cts = new ColorTemplateScreen(0, 0, 0, 0, Component.translatable("tooltip.kuayue.color_template_screen.title"));
        gsts = new GetShareTemplateScreen(Component.empty(), null);
        editBar = new EditBar(0, 0, Component.empty(), "");
    }

    @Override
    public void init() {
        if (Minecraft.getInstance().screen == null) return;
        int sW = Minecraft.getInstance().screen.width;
        int sH = Minecraft.getInstance().screen.height;
        cts.setWidth(sW);
        cts.setHeight(sH);
        cts.init();
        gsts.init();
        cts.visible = false;
        colorScreen.init();
        label = new Label(Component.translatable("tooltip.kuayue.type_screen.title"));
        addWidget(label);
        colorBtn = new ImageButton(colorBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            colorScreen.setVisible(true);
            setBoardWidgetVisible(false);
            colorScreen.setRgb(this.color);
        });

        templateBtn = new ImageButton(templateBtnImage, 0, 0, 16, 16, Component.empty(), b -> {
            defTemplateBtn();
        });

        cancelBtn = new ImageButton(cancelBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});
        confirmBtn = new ImageButton(acceptBtnImage, 0, 0, 16, 16, Component.empty(), b -> {});

        editBar.onCancelClick((w, x, y) -> editBar.visible = false);
        editBar.visible = false;

        addWidget(colorBtn);
        addWidget(templateBtn);
        addWidget(cancelBtn);
        addWidget(confirmBtn);
        addWidget(colorScreen);
        addWidget(gsts);
        addWidget(cts);
        addWidget(editBar);

        gsts.setVisible(false);
        colorScreen.setVisible(false);
        colorScreen.onCancelClick((btn, x, y) -> {
            colorScreen.setVisible(false);
            setBoardWidgetVisible(true);
        });

        colorScreen.onConfirmClick((btn, x, y) -> {
            this.color = colorScreen.getColor().getRGB();
            colorScreen.setVisible(false);
            setBoardWidgetVisible(true);
            setTextColor(color);
        });

        colorScreen.onTemplateClick((c, px, py) -> {
            colorScreen.setVisible(false);
            defTemplateBtn();
            cts.onCancelClick((w, x, y) -> {
                cts.visible = false;
                colorScreen.setVisible(true);
            });
        });

        colorScreen.onLoadClick((c, px, py) -> {
            colorScreen.setVisible(false);
            cts.visible = true;
            cts.setEditVisible(false);
            cts.setShareVisible(false);
            cts.setDeleteVisible(false);

            cts.onConfirmClick((w, x, y) -> {
                colorScreen.setRgb(cts.getChosenBox().getTemplate().getColor());
                cts.visible = false;
                colorScreen.setVisible(true);
                cts.setEditVisible(true);
                cts.setShareVisible(true);
                cts.setDeleteVisible(true);
            });

            cts.onCancelClick((w, x, y) -> {
                cts.visible = false;
                colorScreen.setVisible(true);
                cts.setEditVisible(true);
                cts.setShareVisible(true);
                cts.setDeleteVisible(true);
            });
        });

        colorScreen.onSaveClick((c, px, py) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Player player = minecraft.player;
            if (player == null) return;
            colorScreen.setVisible(false);
            gsts.setTemplate(ColorTemplate.defaultTemplate(colorScreen.getColor().getRGB() - 0xff000000, player.getName().getString()));
            gsts.setEditMode(true);
            gsts.onAcceptClick((w, x, y) -> {
                gsts.setVisible(false);
                gsts.fillDataToTemplate();
                ClientInit.COLOR_TEMPLATES.addTemplate(gsts.getTemplate());
                cts.visible = true;
                defTemplateBtn();
            });
            gsts.onCancelClick((w, x ,y) -> {
                gsts.setVisible(false);
                colorScreen.setVisible(true);
            });
            gsts.setVisible(true);
        });

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
            AllPackets.CHANNEL.sendToServer(new NbtC2SPacket(pos, tag));
            this.close();
        });
    }

    public void defTemplateBtn() {
        cts.visible = true;
        setBoardWidgetVisible(false);
        cts.onConfirmClick((w, x, y) -> {
            this.color = cts.getChosenBox().getTemplate().getColor();
            cts.visible = false;
            setBoardWidgetVisible(true);
            setTextColor(color);
        });
        cts.onCancelClick((w, x, y) -> {
            cts.visible = false;
            setBoardWidgetVisible(true);
        });
        cts.onEditClick((w, x, y) -> {
            cts.editScreen.setTemplate(cts.getChosenBox().getTemplate());
            cts.editScreen.setEditMode(true);
            cts.editScreen.onAcceptClick((a, b, c) -> {
                cts.editScreen.setVisible(false);
                cts.editScreen.fillDataToTemplate();
                cts.visible = true;
            });
            cts.editScreen.onCancelClick((a, b, c) -> {
                cts.editScreen.setVisible(false);
                cts.visible = true;
            });
            cts.visible = false;
            cts.editScreen.setVisible(true);
        });
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
        label.setWidth(font.width(label.getPlainText()));
        label.setPosition((float) (sW - label.getWidth()) / 2, basicY - 20);

        int btnY = basicY + labelH + 20;
        colorBtn.setPos(basicX, btnY);
        templateBtn.setPos(basicX + 20, btnY);
        cancelBtn.setPos(basicX + labelW - 60, btnY);
        confirmBtn.setPos(basicX + labelW - 80, btnY);

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[0]), height, 0.13f * textScaleFactor, 0.18f * textScaleFactor,
                Component.empty(), values[0], color));

        basicX -= (size1 - size0) / 2;
        basicY += height * 0.18f * textScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[1]), height, 0.08f * textScaleFactor, 0.08f * textScaleFactor,
                Component.empty(), values[1], color));

        basicX += revert ? - (size2 + size3 + size1 * 0.05) : size1 * 1.05;
        basicY -= 23;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[2]), height, 0.23f * textScaleFactor, 0.25f * textScaleFactor,
                Component.empty(), values[2], color));

        basicX += size2;
        basicY += textScaleFactor * height * 0.13f;
        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[3]), height, 0.12f * textScaleFactor, 0.12f * textScaleFactor,
                Component.empty(), values[3], color));

        basicX += revert ? - (size4 + size3 * 0.4) : size3 * 1.4;
        basicY -= textScaleFactor * height * 0.13f;
        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[4]), height, 0.26f * textScaleFactor, 0.3f * textScaleFactor,
                Component.empty(), values[4], color));
    }

    private void refresh() {
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

        addWidget(colorBtn);
        addWidget(templateBtn);
        addWidget(cancelBtn);
        addWidget(confirmBtn);
        addWidget(colorScreen);
        addWidget(label);
        addWidget(cts);
        addWidget(gsts);
        addWidget(editBar);

        clearLabels();
        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        int color = getScreen().getMenu().getEditablePanelEntity().getColor();
        innerInit(values, color, font, nbt.getBoolean("revert"));
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
        // colorScreen.render(pose, mouseX, mouseY, partial);
        // cts.render(pose, mouseX, mouseY, partial);
        // gsts.render(pose, mouseX, mouseY, partial);
    }

    public void updateData() {
        CompoundTag nbt = getNbt();
        int i = 0;
        for (Widget widget : getWidgets()) {
            if (widget instanceof EditBox box) {
                nbt.putString("data" + i, box.getValue());
                i++;
            }
        }
        nbt.putInt("color", color);
        nbt.putBoolean("revert", revert);
        getBlockEntity().setChanged();
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
        // if (label.visible)
            // refresh();
    }

    @Override
    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        super.keyReleased(pKeyCode, pScanCode, pModifiers);
        InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
        // if ((mouseKey.getValue() == InputConstants.KEY_DELETE ||
                // mouseKey.getValue() == InputConstants.KEY_BACKSPACE) && label.visible)
            // refresh();
    }

    public void setButtonsVisible(boolean visible) {
        this.colorBtn.visible = visible;
        this.templateBtn.visible = visible;
        this.cancelBtn.visible = visible;
        this.confirmBtn.visible = visible;
        this.label.visible = visible;
        editBar.visible = false;
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
