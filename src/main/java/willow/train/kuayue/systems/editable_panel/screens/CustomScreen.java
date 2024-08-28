package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.systems.editable_panel.widget.Label;

import java.util.ArrayList;

public abstract class CustomScreen<T extends AbstractContainerMenu, K extends BlockEntity> {
    private K blockEntity = null;
    private final AbstractContainerScreen<T> screen;
    private final CompoundTag nbt;
    private Font font = Minecraft.getInstance().font;
    private boolean dirty = false;
    private final ArrayList<Label> labels;
    private final ArrayList<Widget> widgets;
    private boolean blockE = false;
    private boolean blockEsc = false;

    public CustomScreen(AbstractContainerScreen<T> screen) {
        this.nbt = new CompoundTag();
        this.screen = screen;
        this.labels = new ArrayList<>();
        this.widgets = new ArrayList<>();
    }

    public CustomScreen(AbstractContainerScreen<T> screen, CompoundTag nbt) {
        this.nbt = nbt;
        this.screen = screen;
        this.labels = new ArrayList<>();
        this.widgets = new ArrayList<>();
    }

    public CompoundTag getNbt() {
        return nbt;
    }

    public void markDirty() {
        this.dirty = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public AbstractContainerScreen<T> getScreen() {
        return screen;
    }

    public K getBlockEntity() {return blockEntity;}
    public void setBlockEntity(K blockEntity) {this.blockEntity = blockEntity;}
    public abstract void init();
    public abstract void renderBackGround(PoseStack pose, int mouseX, int mouseY, float partialTick);

    public void render(PoseStack pose, int mouseX, int mouseY, float partial) {
        renderLabels(pose, mouseX, mouseY);
        widgets.forEach(widget -> widget.render(pose, mouseX, mouseY, partial));
    }
    public void renderLabels(PoseStack pose, int mouseX, int mouseY) {
        labels.forEach(label -> label.renderToGui(pose, font));
    }
    public abstract void renderTooltip(PoseStack pose, int mouseX, int mouseY);

    public void addLabel(Label label) {
        this.labels.add(label);
    }

    public void addWidget(Widget widget) {
        this.widgets.add(widget);
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public ArrayList<Widget> getWidgets() {
        return widgets;
    }

    public void clearLabels() {
        this.labels.clear();
    }

    public void clearWidgets() {
        this.widgets.clear();
    }

    public void onMouseClicked(double mouseX, double mouseY, int btn) {
        for(Widget w : widgets) {
            if (w instanceof GuiEventListener) ((GuiEventListener) w).mouseClicked(mouseX, mouseY, btn);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int btn) {
        for (Widget w : widgets)
            if (w instanceof GuiEventListener listener) listener.mouseReleased(mouseX, mouseY, btn);
    }

    public void mouseDragged(double mouseX, double mouseY, int btn, double dragX, double dragY) {
        for (Widget w : widgets)
            if (w instanceof GuiEventListener listener) listener.mouseDragged(mouseX, mouseY, btn, dragX, dragY);
    }

    public void mouseScrolled(double mouseX, double mouseY, double delta) {
        for (Widget w : widgets)
            if (w instanceof GuiEventListener listener) listener.mouseScrolled(mouseX, mouseY, delta);
    }

    public void charTyped(char code, int modifier) {
        for (Widget w : widgets) {
            if (w instanceof GuiEventListener box) box.charTyped(code, modifier);
        }
    }

    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Widget w : widgets) {
            if (w instanceof GuiEventListener box) box.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    public void keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        for (Widget w : widgets) {
            if (w instanceof GuiEventListener box) box.keyReleased(pKeyCode, pScanCode, pModifiers);
        }
    }

    public void changeFocus(boolean focus) {
        for (Widget w : widgets) {
            if (w instanceof GuiEventListener box) box.changeFocus(focus);
        }
    }

    public boolean isKeyEBlocked() {
        return blockE;
    }

    public boolean isKeyEscBlocked() {
        return blockEsc;
    }

    public void setBlockKeyE(boolean block) {
        blockE = block;
    }

    public void setBlockKeyEsc(boolean block) {
        blockEsc = block;
    }

    public void onClosed() {}
}
