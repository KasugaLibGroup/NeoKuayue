package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.Kuayue;

public class ItemIconButton extends Button {

    boolean toggled = false;

    int x_offset = 0;

    int y_offset = 0;

    Component message = Component.empty();

    public static final ResourceLocation BUTTON_ICON =
            new ResourceLocation(Kuayue.MODID, "textures/gui/button_background.png");
    public static final ResourceLocation CHOSEN_BUTTON_ICON =
            new ResourceLocation(Kuayue.MODID, "textures/gui/chosen_button_background.png");

    ItemStack icon;

    public ItemIconButton(
            int pX,
            int pY,
            Component message,
            ItemStack icon,
            OnPress pOnPress,
            int x_offset,
            int y_offset) {
        super(pX, pY, 20, 20, Component.empty(), pOnPress);
        this.icon = icon;
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.message = message;
    }

    public void toggle() {
        if (!toggled) this.x -= 20;
        this.toggled = true;
    }

    public void reset() {
        if (toggled) this.x += 20;
        this.toggled = false;
    }

    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;

        Minecraft mc = Minecraft.getInstance();
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        if (toggled) {
            RenderSystem.setShaderTexture(0, CHOSEN_BUTTON_ICON);
        } else {
            RenderSystem.setShaderTexture(0, BUTTON_ICON);
        }

        if (toggled) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        } else {
            RenderSystem.setShaderColor(0.7F, 0.7F, 0.7F, this.alpha);
            ;
        }

        Font font = Minecraft.getInstance().font;

        int x = this.x;
        int y = this.y;

        if (toggled) {
            this.drawTexture(x, y, 0, 0, 40, 20);
            font.draw(pPoseStack, message, x + 20, y + y_offset + 3, 0x4699FF);
        } else {
            this.drawTexture(x, y, 0, 0, 20, 20);
        }

        ItemRenderer renderer = mc.getItemRenderer();
        renderer.blitOffset = 100.0F;
        renderer.renderAndDecorateItem(this.icon, x + x_offset, y + y_offset);
        renderer.renderGuiItemDecorations(mc.font, this.icon, x + x_offset, y + y_offset);
        renderer.blitOffset = 0.0F;
    }

    private void drawTexture(int x, int y, int textureX, int textureY, int width, int height) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(x, y, 0.0).uv(0, 0).endVertex();
        buffer.vertex(x, y + height, 0.0).uv(0, 1).endVertex();
        buffer.vertex(x + width, y + height, 0.0).uv(1, 1).endVertex();
        buffer.vertex(x + width, y, 0.0).uv(1, 0).endVertex();
        BufferUploader.drawWithShader(buffer.end());
    }

    public void renderTooltip(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {}
}
