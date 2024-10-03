package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.network.c2s.ColorTemplateC2SPacket;
import willow.train.kuayue.systems.editable_panel.AllColorTemplates;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.widget.*;

import java.io.IOException;
import java.util.ArrayList;

public class ColorTemplateScreen extends AbstractWidget {
    public AllColorTemplates data;
    public ColorTemplatesBox[] temps;
    private Label title;
    private float px, py;
    private int cursor;
    private ImageButton edit, delete, confirm, cancel, share;
    private ColorTemplatesBox chosen;
    protected GetShareTemplateScreen editScreen;

    private static final LazyRecomputable<ImageMask> board = LazyRecomputable.of(
            () -> {
                try {
                    ImageMask mask = ClientInit.writeBoard.getImage().get().getMask();
                    mask.rectangleUV(0, 0, 1, 1);
                    return mask;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
    );

    public static final LazyRecomputable<ImageMask> buttons = LazyRecomputable.of(
            () -> {
                try {
                    return ClientInit.buttons.getImage().get().getMask();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    );

    public final LazyRecomputable<ImageMask> editBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(0.625f, 0.25f, .75f, .375f))
    );

    public final LazyRecomputable<ImageMask> deleteBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(.75f, .125f, .875f, .25f))
    );

    public final LazyRecomputable<ImageMask> shareBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(.875f, .125f, 1f, .25f))
    );

    public final LazyRecomputable<ImageMask> cancelBg = LazyRecomputable.of(
                    () ->  buttons.get().copyWithOp((m) -> m.rectangleUV(0.5f, 0.125f, 0.625f, 0.25f))
    );

    public final LazyRecomputable<ImageMask> confirmBg = LazyRecomputable.of(
                    () -> buttons.get().copyWithOp(m -> m.rectangleUV(0.375f, 0.125f, 0.5f, 0.25f))
    );

    private final ColorTemplatesBox.OnClick clickAction = b -> {
        chosen = b;
    };

    public ColorTemplateScreen(int pX, int pY, int pWidth, int pHeight, Component title) {
        super(pX, pY, pWidth, pHeight, title);
        this.data = ClientInit.COLOR_TEMPLATES;
        this.title = new Label(title);
        cursor = 0;
        chosen = null;
        editScreen = new GetShareTemplateScreen(Component.empty(), null);
    }

    public void init() {
        ImageMask mask = board.get();
        editScreen.init();
        px = this.x + this.width / 2f - 192;
        py = this.y + this.height / 2f - 120;
        mask.rectangle(new Vector3f(px, py, 0),
                ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 384, 240);
        title.setPosition(new Vec2f(px + 60, py + 20));
        title.setColor(0xff222222);
        title.setScale(2f, 2f);
        updateBoxes();
        int btn_x = (int) px + 340;
        int btn_y = (int) py + 50;
        edit = new ImageButton(this.editBg, btn_x, btn_y, 24, 24, Component.literal("edit"), b -> {});
        cancel = new ImageButton(this.cancelBg, btn_x, btn_y + 32, 24, 24, Component.literal("cancel"), b -> {});
        confirm = new ImageButton(this.confirmBg, btn_x, btn_y + 64, 24, 24, Component.literal("confirm"), b -> {});
        delete = new ImageButton(this.deleteBg, btn_x, btn_y + 96, 24, 24, Component.literal("delete"), b -> {
            if (!chosen.getTemplate().isDeleteAble()) return;
            if (data.getTemplates().remove(chosen.getTemplate())) {
                updateBoxes();
                if (data.getTemplates().size() > 0)
                    this.chosen = temps[0];
                else
                    this.chosen = null;
            }
        });
        share = new ImageButton(this.shareBg, btn_x, btn_y + 128, 24, 24, Component.literal("share"), b -> {
            share();
        });
        share.controlImage((m, btn) -> m.setColor(SimpleColor.fromRGBInt(0x222222)));
        edit.dynamicTooltipLabelWidth();
        cancel.dynamicTooltipLabelWidth();
        confirm.dynamicTooltipLabelWidth();
        delete.dynamicTooltipLabelWidth();
        share.dynamicTooltipLabelWidth();
        if (temps.length > 0) chosen = temps[0];

        editScreen.setVisible(false);
        editActions();
    }

    public void updateBoxes() {
        ArrayList<ColorTemplate> templates = data.templates;
        int maxSize = 4;
        cursor = Math.max(0, Math.min(cursor, Math.max(templates.size() - maxSize, 0)));
        temps = new ColorTemplatesBox[maxSize];
        int h = 50, count = 0;
        for (int i = cursor; i < Math.min(cursor + maxSize, templates.size()); i++) {
            ColorTemplatesBox box = new ColorTemplatesBox((int) px + 60, (int) py + h, templates.get(i), Component.empty(), clickAction);
            box.init();
            temps[count] = box;
            count++;
            h += 38;
        }
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
        updateBoxes();
    }

    public int getCursor() {
        return cursor;
    }

    public ColorTemplatesBox getChosenBox() {
        return chosen;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        editScreen.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        board.get().renderToGui();
        title.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        fill(pPoseStack, title.x, title.y + title.getHeight() + 2,
                title.x + (int)(title.getWidth() * 1.1f), title.y + title.getHeight() + 4,
                0xff333333);
        fill(pPoseStack, title.x + 4, title.y + title.getHeight() + 6,
                title.x + (int)(title.getWidth() * 0.9f), title.y + title.getHeight() + 8,
                0xff555555);
        for (ColorTemplatesBox temp : temps) {
            if (temp == null) continue;
            temp.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        }

        int bY = this.title.y + title.getHeight() + 10;
        fill(pPoseStack, (int) px + 182, bY, (int) px + 184,
                (this.title.y + title.getHeight() + 8 + 165), 0xff555555);
        if (edit == null || cancel == null || confirm == null || delete == null || share == null) return;
        edit.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        confirm.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        cancel.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        share.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        delete.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);

        if (data.getTemplates().size() > 4) {
            int height = (this.title.y + title.getHeight() + 8 + 165) - bY;
            int yx = bY + (int) ((float) cursor / (float) (data.templates.size() - 3) * (float) height);
            fill(pPoseStack, (int) px + 180, yx, (int) px + 182,
                    yx + height / Math.max(1, data.templates.size()- 3), 0xff555555);
        }

        // render main box
        if (chosen == null) return;
        ColorTemplate template = chosen.getTemplate();

        // title
        DescriptionLabel titleLabel = new DescriptionLabel(template.getName());
        titleLabel.setHeight(8);
        titleLabel.setPosition(px + 188, bY + 2);
        titleLabel.setScale(2f, 2f);
        titleLabel.setColor(0xff555555);
        titleLabel.setForceLeftBegin(true);
        titleLabel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        fill(pPoseStack, titleLabel.x, bY + titleLabel.getHeight(), titleLabel.x + (titleLabel.getWidth() + 4),
                bY + titleLabel.getHeight() + 2, 0xff000000);

        // RGB
        String rgbString = Integer.toHexString(template.getColor()).toUpperCase();
        rgbString = rgbString.length() < 6 ? "0".repeat(6 - rgbString.length()) + rgbString : rgbString;
        Label colorLabel = new Label("RGB: #" + rgbString);
        colorLabel.setWidth(80);
        colorLabel.setPosition(px + 200, bY + 30);
        colorLabel.setColor(0xff000000 + template.getColor());
        colorLabel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        // description
        DescriptionLabel descriptionLabel = new DescriptionLabel(template.getDocument());
        descriptionLabel.setWidthAndHeight(140, 80);
        descriptionLabel.setPosition(px + 200, bY + 45);
        descriptionLabel.setColor(0xff555555);
        descriptionLabel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);

        // owner
        DescriptionLabel ownerLabel = new DescriptionLabel(template.getOwner());
        ownerLabel.setWidthAndHeight(140, 8);
        ownerLabel.setPosition(px + 200, bY + 135);
        ownerLabel.setColor(0xff555555);
        ownerLabel.setForceLeftBegin(true);
        ownerLabel.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (editScreen.isVisible())
            return editScreen.mouseScrolled(pMouseX, pMouseY, pDelta);
        if (!visible) return false;
        cursor -= ((int) pDelta);
        updateBoxes();
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (editScreen.isVisible())
            return editScreen.mouseClicked(pMouseX, pMouseY, pButton);
        if (!visible) return false;
        boolean result = false;
        for (ColorTemplatesBox box : temps) {
            if (box == null) continue;
            result = box.mouseClicked(pMouseX, pMouseY, pButton) || result;
        }
        result = result || delete.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || confirm.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || edit.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || cancel.mouseClicked(pMouseX, pMouseY, pButton);
        result = result || share.mouseClicked(pMouseX, pMouseY, pButton);
        return super.mouseClicked(pMouseX, pMouseY, pButton) || result;
    }

    @Override
    public boolean mouseReleased(double pMouseX, double pMouseY, int pButton) {
        if (editScreen.isVisible())
            return editScreen.mouseReleased(pMouseX, pMouseY, pButton);
        if (!visible) return false;
        boolean result = false;
        for (ColorTemplatesBox box : temps) {
            if (box == null) continue;
            result = box.mouseReleased(pMouseX, pMouseY, pButton) || result;
        }
        result = result || delete.mouseReleased(pMouseX, pMouseY, pButton);
        result = result || confirm.mouseReleased(pMouseX, pMouseY, pButton);
        result = result || edit.mouseReleased(pMouseX, pMouseY, pButton);
        result = result || cancel.mouseReleased(pMouseX, pMouseY, pButton);
        result = result || share.mouseReleased(pMouseX, pMouseY, pButton);
        return super.mouseReleased(pMouseX, pMouseY, pButton) || result;
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        if (editScreen.isVisible())
            return editScreen.charTyped(pCodePoint, pModifiers);
        return super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if (editScreen.isVisible())
            return editScreen.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (editScreen.isVisible())
            return editScreen.keyPressed(pKeyCode, pScanCode, pModifiers);
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if (editScreen.isVisible())
            return editScreen.keyReleased(pKeyCode, pScanCode, pModifiers);
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    public void share() {
        if (this.chosen == null) return;
        AllPackets.TEMPLATE.sendToServer(new ColorTemplateC2SPacket(chosen.getTemplate()));
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public void onCancelClick(OnClick<ImageButton> clk) {
        this.cancel.setOnClick(clk);
    }

    public void onConfirmClick(OnClick<ImageButton> clk) {
        this.confirm.setOnClick(clk);
    }

    public void onEditClick(OnClick<ImageButton> clk) {
        this.edit.setOnClick(clk);
    }

    public void setConfirmVisible(boolean visible) {
        confirm.visible = visible;
    }

    public void setDeleteVisible(boolean visible) {
        delete.visible = visible;
    }

    public void setEditVisible(boolean visible) {
        edit.visible = visible;
    }

    public void setShareVisible(boolean visible) {
        share.visible = visible;
    }

    public void setCancelVisible(boolean visible) {
        cancel.visible = visible;
    }

    public void editActions() {
        edit.setOnClick((w, x, y) -> {
            if (!chosen.getTemplate().isEditable()) return;
            this.visible = false;
            editScreen.setTemplate(chosen.getTemplate());
            editScreen.setEditMode(true);
            editScreen.onAcceptClick((q, a, b) -> {
                editScreen.setVisible(false);
                editScreen.fillDataToTemplate();
                this.visible = true;
            });
            editScreen.onCancelClick((q, a, b) -> {
                editScreen.setVisible(false);
                this.visible = true;
            });
            editScreen.setVisible(true);
        });
    }
}
