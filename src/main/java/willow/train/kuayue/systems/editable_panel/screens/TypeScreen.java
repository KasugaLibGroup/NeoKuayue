package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

public class TypeScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {

    public TypeScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
    }

    @Override
    public void init() {
        Font font = Minecraft.getInstance().font;
        CompoundTag nbt = getNbt();
        int color = getScreen().getMenu().getEditablePanelEntity().getColor();
        String[] values = new String[5];
        int offsetScaleFactor = 6;
        float textScaleFactor = 16f;
        for (int i = 0; i < 5; i++) {
            values[i] = nbt.getString("data" + i);
        }
        boolean revert = nbt.getBoolean("revert");

        float size0 = ((float) Minecraft.getInstance().font.width(values[0])) * 0.13f; // 硬座车
        float size1 = ((float) Minecraft.getInstance().font.width(values[1])) * 0.08f; // YINGZUOCHE
        float size2 = ((float) Minecraft.getInstance().font.width(values[2])) * 0.23f; // YZ
        float size3 = ((float) Minecraft.getInstance().font.width(values[3])) * 0.12f; // 25T
        float size4 = ((float) Minecraft.getInstance().font.width(values[4])) * 0.30f; // 345674

        int basicX = ((int) (revert ? 0.4d - size1 * 0.133f * 0.5f : -0.4d)) * offsetScaleFactor,
                basicY = 32, height = font.lineHeight;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[1]), height, 0.08f * textScaleFactor, 0.08f * textScaleFactor,
                Component.empty(), values[1], color));

        basicX += (int)((size1 - size0) / 2) * offsetScaleFactor;
        basicY -= 2 * offsetScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[0]), height, 0.13f * textScaleFactor, 0.18f * textScaleFactor,
                Component.empty(), values[0], color));

        basicX += (revert ? (-size2 - size4 - size3 - 1) : size1) * offsetScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[2]), height, 0.23f * textScaleFactor, 0.32f * textScaleFactor,
                Component.empty(), values[2], color));

        basicX += (int) size2 * offsetScaleFactor;
        basicY += 2 * offsetScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[3]), height, 0.12f * textScaleFactor, 0.12f * textScaleFactor,
                Component.empty(), values[3], color));

        basicX += (int) (size3 + 1) * offsetScaleFactor;
        basicY += -2 * offsetScaleFactor;

        addWidget(new TransparentEditBox(font, basicX, basicY,
                font.width(values[4]), height, 0.26f * textScaleFactor, 0.32f * textScaleFactor,
                Component.empty(), values[4], color));
    }

    @Override
    public void renderBackGround(PoseStack pose, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    public void renderTooltip(PoseStack pose, int mouseX, int mouseY) {

    }
}
