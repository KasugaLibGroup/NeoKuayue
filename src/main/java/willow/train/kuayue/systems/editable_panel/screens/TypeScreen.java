package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private int color;
    boolean revert;
    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
    }

    @Override
    public void init() {
        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        color = getScreen().getMenu().getEditablePanelEntity().getColor();
        String[] values = new String[5];
        for (int i = 0; i < 5; i++) {
            values[i] = nbt.getString("data" + i);
        }
        revert = nbt.getBoolean("revert");
        innerInit(values, color, font, revert);
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
        int labelW = (int) ((size0 - size1) / 2 +
                (revert ? - (size2 + size3 + size1 * 0.05) : size1 * 1.05) +
                size2 + (revert ? - (size4 + size3 * 0.4) : size3 * 1.4) + size4);
        int labelH = (int) (height * 0.18f * textScaleFactor - 23 + textScaleFactor * height * 0.13f);
        int basicX = (sW - labelW) / 2, basicY = (sH - labelH) / 2;

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
    public void renderBackGround(PoseStack pose, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    public void renderTooltip(PoseStack pose, int mouseX, int mouseY) {

    }

    @Override
    public void charTyped(char code, int modifier) {
        super.charTyped(code, modifier);
        refresh();
    }

    @Override
    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        super.keyReleased(pKeyCode, pScanCode, pModifiers);
        InputConstants.Key mouseKey = InputConstants.getKey(pKeyCode, pScanCode);
        if (mouseKey.getValue() == InputConstants.KEY_DELETE ||
                mouseKey.getValue() == InputConstants.KEY_BACKSPACE)
            refresh();
    }
}
