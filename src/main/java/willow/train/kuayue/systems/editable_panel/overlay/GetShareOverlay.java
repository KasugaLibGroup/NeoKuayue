package willow.train.kuayue.systems.editable_panel.overlay;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.joml.Vector3f;
import willow.train.kuayue.initial.AllKeys;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;
import willow.train.kuayue.systems.editable_panel.screens.GetShareTemplateScreen;

import java.io.IOException;

public class GetShareOverlay implements IGuiOverlay {

    private ColorTemplate template;
    private int time, tickCache;

    public static final LazyRecomputable<ImageMask> toast = LazyRecomputable.of(() -> {
        try {
            ImageMask mask = ClientInit.toast.getImage().get().getMask();
            mask.rectangleUV(0, .25f, .625f, .375f);
            return mask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    public GetShareOverlay() {
        time = 0;
        this.template = null;
    }

    public GetShareOverlay(ColorTemplate template) {
        this.template = template;
        time = 0;
    }

    public void setTemplate(ColorTemplate template) {
        this.template = template;
    }

    public ColorTemplate getTemplate() {
        return template;
    }

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partial, int screenWidth, int screenHeight) {
        if (this.template == null) return;

        ImageMask mask = toast.get();
        Vector3f toastPos = new Vector3f(screenWidth + getXOffset(partial), 0, 0);
        mask.rectangle(toastPos, ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 160, 32);
        mask.renderToGui();

        if (time > 19 && time < 121) {
            int t = time - 20;
            int x = (int) toastPos.x() + 4;
            int y = (int) toastPos.y() + 27;
            int w = (int) (152f * (1 - Math.pow((float) (100 - t) / 100f, 2)));
            guiGraphics.fill(x, y, x + w, y + 1, 0xffcecece);
        }

        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.drawCenteredString(minecraft.font, Component.translatable("tooltip.kuayue.get_share_overlay_toast.title"),
                (int) toastPos.x() + 80, (int) toastPos.y() + 7, 0xffffffff);
        guiGraphics.drawCenteredString(minecraft.font, Component.translatable("tooltip.kuayue.get_share_overlay_toast.content",
                        AllKeys.getShareTemplateKey.getMapping().getTranslatedKeyMessage()),
                (int) toastPos.x() + 80, (int) toastPos.y() + 18, 0xffffffff);

        tick(gui.getGuiTicks());
    }

    private void tick(int tick) {
        if (tick == tickCache) return;
        if (time >= 140) {
            destroy();
            return;
        }
        time ++;
        tickCache = tick;
    }

    private float getXOffset(float partial) {
        if (time < 20) {
            return - (time) * 8f;
        } else if (time < 120) {
            return - 160f;
        } else if (time < 140) {
            return - 160f + (time - 120f) * 8f;
        } else {
            return 0;
        }
    }

    private void destroy() {
        this.template = null;
        this.time = 0;
    }

    public int getTime() {
        return time;
    }
}
