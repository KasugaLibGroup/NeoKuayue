package willow.train.kuayue.initial;

import kasuga.lib.core.client.render.model.SimpleModel;
import kasuga.lib.core.client.render.texture.StaticImage;
import kasuga.lib.core.client.render.texture.StaticImageHolder;
import kasuga.lib.registrations.client.ModelReg;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.util.function.Supplier;

public class ClientInit {
    /*
    public static final SimpleTexture

            arrow_left = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 0, 0, 16, 16),
            arrow_right = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 16, 0, 16, 16),
            arrow_up = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 16, 16, 16, 16),
            arrow_down = new SimpleTexture(AllElements.testRegistry.asResource("textures/overlay/arrows.png")
                    , 0, 16, 16, 16);

     */

    public static final StaticImageHolder arrow =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/overlay/arrows.png"));

    public static final StaticImageHolder editableBg =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/background.png"));
    public static final StaticImageHolder colorPlate =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/color_plate.png"));

    public static final StaticImageHolder colorPlateBg =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/color_plate_bg.png"));

    public static final StaticImageHolder colorPlateMiddleLayer =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/color_plate_middle_layer.png"));

    public static final StaticImageHolder buttons =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/buttons.png"));


    public static final ModelReg testModel =
            new ModelReg("test_model", AllElements.testRegistry.asResource("block/test_block"))
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
