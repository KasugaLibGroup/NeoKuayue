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
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

import java.io.IOException;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private int color;
    ColorScreen colorScreen;
    ColorTemplateScreen cts;
    boolean revert;
    private final LazyRecomputable<NineSlicedImageMask> image = LazyRecomputable.of(() -> {
        try {
            NineSlicedImageMask mask = ClientInit.editableBg.getImage().get().getNineSlicedMask();
            mask.rectangleUV(new Vec2f(8f/128f, 12f/128f), new Vec2f(119f/128f, 119f/128f));
            mask.setBorders(25, 16, 0, 0);
            mask.setBordersDirectly(25f / 128f, 103f / 128f, 0f, 1f);
            mask.setScalingFactor(2f);
            return mask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
        colorScreen = new ColorScreen(32, 32, Component.translatable("tooltip.kuayue.color_screen.title"));
    }

    @Override
    public void init() {
        colorScreen.init();
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
        int labelW = (int) (size1 * 1.05f + size2 + size3 * 1.4f + size4);
        int labelH = (int) (height * 0.18f * textScaleFactor - 23 + textScaleFactor * height * 0.13f);
        image.get().rectangle(
                new Vector3f((sW - (float) labelW * 1.1f) / 2, (sH - 108f) / 2, 0),
                ImageMask.Axis.X, ImageMask.Axis.Y, true, true, labelW * 1.1f, 108f
        );
        image.get().updateMatrix();
        int basicX = (sW - labelW) / 2 + 20, basicY = (sH - labelH) / 2 - 10;

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
        addWidget(colorScreen);
        cts = new ColorTemplateScreen(0, 0, sW, sH, Component.literal("Color Templates"));
        cts.init();
        addWidget(cts);
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

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partial) {
        // super.render(pose, mouseX, mouseY, partial);
        colorScreen.render(pose, mouseX, mouseY, partial);
        // cts.render(pose, mouseX, mouseY, partial);
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
        if (Minecraft.getInstance().screen == null) return;
        int sW = Minecraft.getInstance().screen.width;
        int sH = Minecraft.getInstance().screen.height;
        GuiComponent.fill(pose, 0, 0, sW, sH, 0x80000000);
        // image.get().renderToGui();
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
