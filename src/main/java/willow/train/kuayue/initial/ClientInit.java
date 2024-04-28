package willow.train.kuayue.initial;

import kasuga.lib.core.client.render.texture.SimpleTexture;
import net.minecraft.resources.ResourceLocation;

public class ClientInit {
    public static final SimpleTexture

            arrow_left = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 0, 0, 16, 16),
            arrow_right = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 16, 0, 16, 16),
            arrow_up = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 16, 16, 16, 16),
            arrow_down = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 0, 16, 16, 16);

    public static void invoke() {}
}
