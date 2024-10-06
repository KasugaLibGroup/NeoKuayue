package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.Label;
import willow.train.kuayue.systems.editable_panel.widget.TransparentEditBox;

public class SpeedScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private ColorScreenBundles colorSelector;
    public Label contentLabel;
    private int color;
    public SpeedScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
    }

    @Override
    public void init() {
        colorSelector = new ColorScreenBundles();
        colorSelector.init();
        colorSelector.setOpen((selector, template, now) -> {
            setWidgetsVisible(false);
            selector.setRgb(this.color);
        });
        colorSelector.setSuccess((selector, template, now) -> {
            if (now == template) {
                this.color = template.getChosenBox().getTemplate().getColor();
                contentLabel.setColor(this.color);
                setWidgetsVisible(true);
            } else {
                this.color = selector.getColor().getRGB();
                contentLabel.setColor(this.color);
                setWidgetsVisible(true);
            }
        });
        colorSelector.setCancel((selector, template, now) -> {
            setWidgetsVisible(true);
        });
        addWidget(colorSelector);

        CompoundTag nbt = getNbt();
        color = nbt.getInt("color");
        Font font = Minecraft.getInstance().font;
        contentLabel = new Label(nbt.getString("content"));
        contentLabel.setScale(2, 2);
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();
        int widthOfText = font.width(contentLabel.text);
        contentLabel.setPosition((float) (window.getGuiScaledWidth() / 2) - widthOfText,
                (float) (window.getGuiScaledHeight() / 2) - 8);
        addWidget(contentLabel);

        colorSelector.getColorBtn().setPos(contentLabel.x + 10, contentLabel.y + 20);
        colorSelector.getTemplateBtn().setPos(contentLabel.x + 30, contentLabel.y + 20);

        addWidget(colorSelector.getColorBtn());
        addWidget(colorSelector.getTemplateBtn());
    }

    @Override
    public void renderBackGround(PoseStack pose, int mouseX, int mouseY, float partialTick) {

    }

    @Override
    public void renderTooltip(PoseStack pose, int mouseX, int mouseY) {

    }

    public void setWidgetsVisible(boolean visible) {
        contentLabel.visible = visible;
        colorSelector.getColorBtn().visible = visible;
        colorSelector.getTemplateBtn().visible = visible;
    }
}
