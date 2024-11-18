package willow.train.kuayue.systems.tech_tree.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import kasuga.lib.core.util.data_type.Vec2i;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.tech_tree.client.ClientTechTreeNode;

@Getter
public class TechTreeLabel extends ImageButton {

    private final ClientTechTreeNode node;
    public TechTreeLabel(LazyRecomputable<ImageMask> mask, ClientTechTreeNode node, int x, int y, Component tooltip) {
        super(mask, x, y, 20, 20, tooltip, b -> {});
        this.node = node;
    }

    public Vec2i getCenterPos() {
        return new Vec2i(x + 10, y + 10);
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(pPoseStack, mouseX, mouseY, partialTicks);

        // render item
        ItemStack logo = node.getLogo();
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer renderer = mc.getItemRenderer();
        renderer.blitOffset = 100.0F;
        renderer.renderAndDecorateItem(logo, x + 2, y + 2);
        renderer.renderGuiItemDecorations(mc.font, logo, x + 2, y + 2);
        renderer.blitOffset = 0.0F;
    }
}
