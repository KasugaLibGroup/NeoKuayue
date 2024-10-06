package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import willow.train.kuayue.initial.ClientInit;

import java.io.IOException;

public class OffsetEditor extends AbstractWidget {
    private float minX, maxX, minY, maxY, cursorX, cursorY;
    public static final LazyRecomputable<ImageMask> editor =
            new LazyRecomputable<>(() -> {
               try {
                   return ClientInit.offsetEditor.getImage().get().getMask();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
            });

    public static final LazyRecomputable<ImageMask> largeBg =
            new LazyRecomputable<>(() -> {
                return editor.get().copyWithOp(imageMask -> imageMask);
            });
    public OffsetEditor(int x, int y, int a, Component pMessage) {
        super(x, y, a + 40, a, pMessage);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @Override
    public void mouseMoved(double pMouseX, double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        return super.mouseScrolled(pMouseX, pMouseY, pDelta);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return super.charTyped(pCodePoint, pModifiers);
    }
}
