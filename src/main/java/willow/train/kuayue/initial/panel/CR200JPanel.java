package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.*;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.PanelRegistration;

public class CR200JPanel {
    public static final MaterialColor CR200J_COLOR = MaterialColor.COLOR_GREEN;
    public static final Material CR200J_MATERIAL = Material.METAL;
    public static final CreateRegistry registry = AllElements.testRegistry;

    public static final PanelRegistration<TrainHingePanelBlock> WIDE_PANEL_CR200J_LOGO =
            new PanelRegistration<TrainHingePanelBlock>("panel_wide_logo_cr_marshalled_cr200j")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(-1, 0), new Vec2(2, 2)))
                    .materialAndColor(CR200J_MATERIAL, CR200J_COLOR)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainHingePanelBlock> WIDE_PANEL_CR200J =
            new PanelRegistration<TrainHingePanelBlock>("panel_wide_marshalled_cr200j")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(-1, 0), new Vec2(2, 2)))
                    .materialAndColor(CR200J_MATERIAL, CR200J_COLOR)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> WIDE_WINDOW_CR200J =
            new PanelRegistration<TrainOpenableWindowBlock>("window_wide_marshalled_cr200j")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(CR200J_MATERIAL, CR200J_COLOR)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableWindowBlock> WIDE_WINDOW_CR200J_SEALED =
            new PanelRegistration<TrainUnOpenableWindowBlock>("window_wide_sealed_marshalled_cr200j")
                    .block(p -> new TrainUnOpenableWindowBlock(p, 2))
                    .materialAndColor(CR200J_MATERIAL, CR200J_COLOR)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_CR200J =
            new PanelRegistration<CustomRenderedDoorBlock>("door_marshalled_cr200j")
                    .block(properties ->
                            new CustomRenderedDoorBlock(properties, Couple.create(
                                    registry.asResource("carriage/carriage_marshalled_cr200j/door/door_bottom_lh"),
                                    registry.asResource("carriage/carriage_marshalled_cr200j/door/door_top_lh"))
                            , Couple.create(
                                    registry.asResource("carriage/carriage_marshalled_cr200j/door/door_bottom"),
                                    registry.asResource("carriage/carriage_marshalled_cr200j/door/door_top")),
                            RenderShape.ENTITYBLOCK_ANIMATED, false))
                    .materialAndColor(CR200J_MATERIAL, CR200J_COLOR)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_MARSHALLED_CR200J =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_marshalled_cr200j")
            .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
            .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
            .tab(AllElements.neoKuayueMainTab)
            .noOcclusion()
            .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainHingePanelBlock> PANEL_MIDDLE_LOGO_CR_MARSHALLED_CR200J =
            new PanelRegistration<TrainHingePanelBlock>("panel_middle_logo_cr_marshalled_cr200j")
            .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
            .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
            .tab(AllElements.neoKuayueMainTab)
            .noOcclusion()
            .submit(AllElements.testRegistry);

    public static void invoke(){}
}
