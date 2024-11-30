package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import kasuga.lib.core.util.data_type.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.DiscardChangeC2SPacket;
import willow.train.kuayue.network.c2s.NbtC2SPacket;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.widget.*;

public class SpeedScreen extends CustomScreen<EditablePanelEditMenu, EditablePanelEntity> {
    private ColorScreenBundles colorSelector;
    private EditBar editBar;
    public Label contentLabel;
    private OffsetEditor offsetEditor;
    private int color;
    private ImageButton cancelBtn, confirmBtn;

    private final LazyRecomputable<ImageMask> cancelBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.cancelImage.get().copyWithOp(p -> p));

    private final LazyRecomputable<ImageMask> acceptBtnImage =
            new LazyRecomputable<>(() -> GetShareTemplateScreen.acceptImage.get().copyWithOp(p -> p));

    public SpeedScreen(AbstractContainerScreen<EditablePanelEditMenu> screen, CompoundTag nbt) {
        super(screen, nbt);
        setBlockEntity(screen.getMenu().getEditablePanelEntity());
    }

    @Override
    public void init() {
        CompoundTag nbt = getNbt();
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
            getBlockEntity().saveNbt(nbt);
        });

        colorSelector.setCancel((selector, template, now) -> {
            setWidgetsVisible(true);
        });
        addWidget(colorSelector);

        color = nbt.getInt("color");
        Font font = Minecraft.getInstance().font;
        contentLabel = new Label(nbt.getString("content"));
        contentLabel.setScale(2, 2);
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();
        int widthOfText = font.width(contentLabel.text);
        contentLabel.setPosition((float) (window.getGuiScaledWidth() / 2) - widthOfText,
                (float) (window.getGuiScaledHeight() / 2) - 8);

        offsetEditor = new OffsetEditor(0, 0, Component.literal("offset"),
                -.5f, .5f, -.5f, .5f, 0f, 0f);
        offsetEditor.setPosition((Minecraft.getInstance().screen.width - offsetEditor.getWidth()) / 2,
                (Minecraft.getInstance().screen.height - offsetEditor.getHeight()) / 2);

        offsetEditor.onCancelBtnClick(
                (w, x ,y) -> {
                    setWidgetsVisible(true);
                    offsetEditor.visible = false;
                }
        );
        offsetEditor.onAcceptBtnClick(
                (w, x, y) -> {
                    setWidgetsVisible(true);
                    offsetEditor.visible = false;
                    Pair<Float, Float> offset = offsetEditor.getCursorPosition();
                    nbt.putFloat("offset_x", offset.getFirst());
                    nbt.putFloat("offset_y", offset.getSecond());
                    getBlockEntity().saveNbt(nbt);
                }
        );
        offsetEditor.onEditorBtnClick(
                (w, x, y) -> {
                    setWidgetsVisible(false);
                    offsetEditor.visible = true;
                }
        );
        offsetEditor.visible = false;

        addWidget(offsetEditor);
        addWidget(contentLabel);

        editBar = new EditBar(0, 0, Component.empty(), contentLabel.getPlainText());
        editBar.onCancelClick((widget, mouseX, mouseY) -> editBar.visible = false);
        editBar.onAcceptClick((widget, mouseX, mouseY) -> {
            editBar.visible = false;
            nbt.putString("content", editBar.getText());
            contentLabel.setText(Component.literal(editBar.getText()));
            getBlockEntity().saveNbt(nbt);
        });
        editBar.visible = false;

        contentLabel.setOnCLick((widget, mouseX, mouseY) -> {
            editBar.setPosition(contentLabel.x - (editBar.getWidth() - contentLabel.getWidth()) / 2, contentLabel.y - 20);
            editBar.visible = true;
        });
        contentLabel.setColor(nbt.getInt("color"));
        int q = (Minecraft.getInstance().screen.width - 105) / 2;
        colorSelector.getColorBtn().setPos(q + 5, contentLabel.y + 20);
        colorSelector.getTemplateBtn().setPos(q + 25, contentLabel.y + 20);
        offsetEditor.getEditorBtn().setPos(q + 45, contentLabel.y + 20);

        confirmBtn = new ImageButton(acceptBtnImage, q + 85, contentLabel.y + 20,
                16, 16, Component.empty(), b -> {
            BlockEntity be = getBlockEntity();
            CompoundTag tag = new CompoundTag();
            tag.put("data", nbt);
            be.load(tag);
            be.setChanged();
            AllPackets.CHANNEL.sendToServer(new NbtC2SPacket(be.getBlockPos(), tag));
            this.close();
        });

        cancelBtn = new ImageButton(cancelBtnImage, q + 65, contentLabel.y + 20,
                16, 16, Component.empty(), b -> {
            AllPackets.CHANNEL.sendToServer(new DiscardChangeC2SPacket(getBlockEntity().getBlockPos()));
            this.close();
        });

        offsetEditor.setCursorPosition(nbt.getFloat("offset_x"), nbt.getFloat("offset_y"));

        addWidget(colorSelector.getColorBtn());
        addWidget(colorSelector.getTemplateBtn());
        addWidget(offsetEditor.getEditorBtn());
        addWidget(editBar);
        addWidget(confirmBtn);
        addWidget(cancelBtn);
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

    public void setWidgetsVisible(boolean visible) {
        contentLabel.visible = visible;
        colorSelector.getColorBtn().visible = visible;
        colorSelector.getTemplateBtn().visible = visible;
        editBar.visible = false;
        // offsetEditor.visible = visible;
        offsetEditor.getEditorBtn().visible = visible;
        confirmBtn.visible = visible;
        cancelBtn.visible = visible;
    }
}
