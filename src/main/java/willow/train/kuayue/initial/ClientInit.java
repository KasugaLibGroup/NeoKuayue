package willow.train.kuayue.initial;

import kasuga.lib.core.client.render.texture.StaticImageHolder;
import kasuga.lib.registrations.client.ModelReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.systems.editable_panel.AllColorTemplates;
import willow.train.kuayue.systems.editable_panel.overlay.GetShareOverlay;

public class ClientInit {
    public static final AllColorTemplates COLOR_TEMPLATES =
            new AllColorTemplates(Kuayue.LOCAL_FILE.getPath("color_templates.nbt"));
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
    public static final StaticImageHolder writeBoard =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/write_board.png"));
    public static final StaticImageHolder colorBg2 =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/editable/color_bg_2.png"));
    public static final StaticImageHolder carriageEventRegexUp =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/up_button.png"));
    public static final StaticImageHolder carriageEventRegexDown =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/down_button.png"));
    public static final StaticImageHolder toast =
            new StaticImageHolder(new ResourceLocation("minecraft", "textures/gui/toasts.png"));
    public static final StaticImageHolder recipeBook =
            new StaticImageHolder(new ResourceLocation("minecraft", "textures/gui/recipe_book.png"));
    public static final StaticImageHolder advancementWidgets =
            new StaticImageHolder(new ResourceLocation("minecraft", "textures/gui/advancements/widgets.png"));
    public static final StaticImageHolder offsetEditor =
            new StaticImageHolder(AllElements.testRegistry.asResource("textures/gui/offset_editor.png"));
    public static final ModelReg testModel =
            new ModelReg("test_model", AllElements.testRegistry.asResource("block/test_block"))
                    .submit(AllElements.testRegistry);

    @SubscribeEvent
    public static void registerHUDOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("get_template_share_overlay", new GetShareOverlay());
    }

    public static void invoke() {
        AllKeys.invoke();
    }
}
