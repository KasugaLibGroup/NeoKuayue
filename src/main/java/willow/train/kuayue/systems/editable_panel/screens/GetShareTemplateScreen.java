package willow.train.kuayue.systems.editable_panel.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;

import java.io.IOException;

public class GetShareTemplateScreen extends Screen {

    public static final LazyRecomputable<ImageMask> getShareBg =
            new LazyRecomputable<>(() -> {
                try {
                    ImageMask mask = ClientInit.recipeBook.getImage().get().getMask();
                    mask.rectangleUV(1 / 256f, 1/256f, 148f/256f, 167f/256f);
                    return mask;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

    public GetShareTemplateScreen(Component pTitle) {
        super(pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        ImageMask mask = getShareBg.get();
        if (minecraft == null) return;
        Screen screen = minecraft.screen;
        if (screen == null) return;
        int width = screen.width;
        int height = screen.height;
        Vector3f pos = new Vector3f((width - 147f) / 2, (height - 166f) / 2, 0);
        mask.rectangle(pos, ImageMask.Axis.X, ImageMask.Axis.Y, true, true, 147, 166);
        mask.renderToGui();
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
