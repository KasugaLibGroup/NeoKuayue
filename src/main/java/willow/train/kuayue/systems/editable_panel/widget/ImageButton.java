package willow.train.kuayue.systems.editable_panel.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.utils.client.RenderablePicture;

@OnlyIn(Dist.CLIENT)
public class ImageButton extends Button {

    RenderablePicture picture;

    public ImageButton(RenderablePicture picture, int x, int y, int width, int height, OnPress press) {
        super(0, 0, picture.width(), picture.height(), Component.empty(), press);
        this.picture = picture;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public ImageButton(RenderablePicture picture, OnPress press) {
        super(0, 0, picture.width(), picture.height(), Component.empty(), press);
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
    }

    @Override
    public void renderButton(PoseStack pPoseStack, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;
        picture.setColor(isActive() ? 255 : 127, isActive() ? 255 : 127, isActive() ? 255 : 127, isActive() ? 1 : .5f);
        picture.render(x, y, width, height);
    }
}
