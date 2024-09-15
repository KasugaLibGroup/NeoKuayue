package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
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
import willow.train.kuayue.systems.editable_panel.widget.Label;

import java.util.ArrayList;

public class ColorTemplateScreen extends AbstractWidget {
    public AllColorTemplates data;
    public ColorTemplatesBox[] temps;
    private Label title;
    private float px, py;
    private int cursor;
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
    }

    public void updateBoxes() {
        ArrayList<ColorTemplate> templates = data.templates;
        int maxSize = 4;
        cursor = Math.max(0, Math.min(cursor, Math.max(templates.size() - maxSize, 0)));
        temps = new ColorTemplatesBox[templates.size()];
        int h = 50, count = 0;
        for (ColorTemplate t : data.templates) {
            ColorTemplatesBox box = new ColorTemplatesBox((int) px + 60, (int) py + h, t, Component.empty());
            box.init();
            temps[count] = box;
            count++;
            h += 40;
        }
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
        fill(pPoseStack, (int) px + 192, this.title.y + title.getHeight() + 10,
                (int) px + 194, (this.title.y + title.getHeight() + 8 + 165), 0xff555555);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
