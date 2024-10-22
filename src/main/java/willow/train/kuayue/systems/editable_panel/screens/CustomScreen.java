package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.systems.editable_panel.widget.Label;

import java.util.ArrayList;

public abstract class CustomScreen<T extends AbstractContainerMenu, K extends BlockEntity> extends Screen {
    private K blockEntity = null;
    private final AbstractContainerScreen<T> screen;
    private final CompoundTag nbt;
    private Font font = Minecraft.getInstance().font;
    private boolean dirty = false;
    private final ArrayList<Label> labels;
    private final ArrayList<Renderable> widgets;
    private boolean blockE = false;
    private boolean blockEsc = false;

    public CustomScreen(AbstractContainerScreen<T> screen) {
        super(Component.empty());
        this.nbt = new CompoundTag();
        this.screen = screen;
        this.labels = new ArrayList<>();
        this.widgets = new ArrayList<>();
    }

    public CustomScreen(AbstractContainerScreen<T> screen, CompoundTag nbt) {
        super(Component.empty());
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
    public abstract void renderBackGround(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partial) {
        renderLabels(guiGraphics, mouseX, mouseY);
        widgets.forEach(widget -> widget.render(guiGraphics, mouseX, mouseY, partial));
    }
    public void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        labels.forEach(label -> label.renderToGui(guiGraphics, font));
    }
    public abstract void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY);

    public void addLabel(Label label) {
        this.labels.add(label);
    }

    public void addCustomWidget(Renderable widget) {
        this.widgets.add(widget);
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public ArrayList<Renderable> getCustomWidgets() {
        return widgets;
    }

    public void clearLabels() {
        this.labels.clear();
    }

    public void clearWidgets() {
        this.widgets.clear();
    }

    public boolean mouseClicked(double mouseX, double mouseY, int btn) {
        for(Renderable w : widgets) {
            if (w != null) ((GuiEventListener) w).mouseClicked(mouseX, mouseY, btn);
        }
        return true;
    }

    public boolean mouseReleased(double mouseX, double mouseY, int btn) {
        for (Renderable w : widgets)
            if (w instanceof AbstractWidget listener) listener.mouseReleased(mouseX, mouseY, btn);
        return true;
    }

    public void mouseMoved(double mouseX, double mouseY) {
        for (Renderable w : widgets) {
            if (w instanceof AbstractWidget widget) widget.mouseMoved(mouseX, mouseY);
        }
    }

    public boolean mouseDragged(double mouseX, double mouseY, int btn, double dragX, double dragY) {
        for (Renderable w : widgets)
            if (w instanceof GuiEventListener listener) listener.mouseDragged(mouseX, mouseY, btn, dragX, dragY);
        return true;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        for (Renderable w : widgets)
            if (w instanceof GuiEventListener listener) listener.mouseScrolled(mouseX, mouseY, delta);
        return true;
    }

    public boolean charTyped(char code, int modifier) {
        for (Renderable w : widgets) {
            if (w instanceof GuiEventListener box) box.charTyped(code, modifier);
        }
        return true;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (Renderable w : widgets) {
            if (w instanceof GuiEventListener box) box.keyPressed(keyCode, scanCode, modifiers);
        }
        return true;
    }

    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        for (Renderable w : widgets) {
            if (w instanceof GuiEventListener box) box.keyReleased(pKeyCode, pScanCode, pModifiers);
        }
        return true;
    }

    public boolean changeFocus(boolean focus) {
        for (Renderable w : widgets) {
            if (w instanceof GuiEventListener box) box.setFocused(focus);
        }
        return true;
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

    public void close() {
        screen.onClose();
    }
}
