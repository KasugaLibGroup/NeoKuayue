package willow.train.kuayue.initial.panel;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.*;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableSmallWindowBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.PanelRegistration;

public class C25BPanel {

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_25B =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_25b")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_25B =
            new PanelRegistration<TrainPanelBlock>("panel_middle_25b")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainHingePanelBlock> PANEL_SYMBOL_MARSHALLED_25B =
            new PanelRegistration<TrainHingePanelBlock>("panel_symbol_marshalled_25b")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25B =
            new PanelRegistration<TrainPanelBlock>("panel_top_25b")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25B_2 =
            new PanelRegistration<TrainPanelBlock>("panel_top_25b_2")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_25B =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_25b")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25B =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25b")
                    .block(p -> new TrainSmallWindowBlock(p))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25B =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25b")
                    .block(p -> new TrainSmallWindowBlock(p))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_OC_WIDE_25B =
            new PanelRegistration<TrainOpenableWindowBlock>("window_oc_wide_25b")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableWindowBlock> WINDOW_OC_WIDE_SEALED_25B =
            new PanelRegistration<TrainUnOpenableWindowBlock>("window_oc_wide_sealed_25b")
                    .block(p -> new TrainUnOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
