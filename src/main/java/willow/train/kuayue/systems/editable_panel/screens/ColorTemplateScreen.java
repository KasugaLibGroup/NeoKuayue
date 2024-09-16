package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.client.render.texture.Vec2f;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.AllColorTemplates;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.widget.ColorTemplatesBox;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.Label;

import java.io.IOException;
import java.util.ArrayList;

public class ColorTemplateScreen extends AbstractWidget {
    public AllColorTemplates data;
    public ColorTemplatesBox[] temps;
    private Label title;
    private float px, py;
    private int cursor;
    private ImageButton edit, delete, confirm, cancel, share;

    private final LazyRecomputable<ImageMask> board = LazyRecomputable.of(
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

    private final LazyRecomputable<ImageMask> buttons = LazyRecomputable.of(
            () -> {
                try {
                    return ClientInit.buttons.getImage().get().getMask();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    );

    private final LazyRecomputable<ImageMask> editBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(0.625f, 0.25f, .75f, .375f))
    );

    private final LazyRecomputable<ImageMask> deleteBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(.75f, .125f, .875f, .25f))
    );

    private final LazyRecomputable<ImageMask> shareBg = LazyRecomputable.of(
            () -> buttons.get().copyWithOp(mask -> mask.rectangleUV(.875f, .125f, 1f, .25f))
    );

    private final LazyRecomputable<ImageMask> cancelBg = LazyRecomputable.of(
                    () ->  buttons.get().copyWithOp((m) -> m.rectangleUV(0.5f, 0.125f, 0.625f, 0.25f))
    );

    private final LazyRecomputable<ImageMask> confirmBg = LazyRecomputable.of(
                    () -> buttons.get().copyWithOp(m -> m.rectangleUV(0.375f, 0.125f, 0.5f, 0.25f))
    );

    public ColorTemplateScreen(int pX, int pY, int pWidth, int pHeight, Component title) {
        super(pX, pY, pWidth, pHeight, title);
        this.data = ClientInit.COLOR_TEMPLATES;
        this.title = new Label(title);
        cursor = 0;
    }

    public void init() {
        ImageMask mask = board.get();
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
        delete = new ImageButton(this.deleteBg, btn_x, btn_y + 96, 24, 24, Component.literal("delete"), b -> {});
        share = new ImageButton(this.shareBg, btn_x, btn_y + 128, 24, 24, Component.literal("share"), b -> {});
        share.controlImage((m, btn) -> m.setColor(SimpleColor.fromRGBInt(0x222222)));
        edit.dynamicTooltipLabelWidth();
        cancel.dynamicTooltipLabelWidth();
        confirm.dynamicTooltipLabelWidth();
        delete.dynamicTooltipLabelWidth();
        share.dynamicTooltipLabelWidth();
    }

    public void updateBoxes() {
        ArrayList<ColorTemplate> templates = data.templates;
        int maxSize = 4;
        cursor = Math.max(0, Math.min(cursor, Math.max(templates.size() - maxSize, 0)));
        temps = new ColorTemplatesBox[templates.size()];
        int h = 50, count = 0;
        for (int i = cursor; i < Math.min(cursor + maxSize, templates.size()); i++) {
            ColorTemplatesBox box = new ColorTemplatesBox((int) px + 60, (int) py + h, templates.get(i), Component.empty());
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
        return temps[cursor];
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
        fill(pPoseStack, (int) px + 182, this.title.y + title.getHeight() + 10,
                (int) px + 184, (this.title.y + title.getHeight() + 8 + 165), 0xff555555);
        if (edit == null || cancel == null || confirm == null || delete == null || share == null) return;
        edit.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        confirm.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        cancel.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        share.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        delete.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
