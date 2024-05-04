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

public class C25GPanel {

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_25G =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_25G =
            new PanelRegistration<TrainPanelBlock>("panel_middle_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainHingePanelBlock> PANEL_SYMBOL_MARSHALLED_25G =
            new PanelRegistration<TrainHingePanelBlock>("panel_symbol_marshalled_25g")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25G =
            new PanelRegistration<TrainPanelBlock>("panel_top_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableSmallWindowBlock> WINDOW_OC_SEALED_SMALL_25G =
            new PanelRegistration<TrainUnOpenableSmallWindowBlock>("window_oc_sealed_small_25g")
                    .block(TrainUnOpenableSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableWindowBlock> WINDOW_OC_SEALED_WIDE_25G =
            new PanelRegistration<TrainUnOpenableWindowBlock>("window_oc_sealed_wide_25g")
                    .block(p -> new TrainUnOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25G =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25g")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25G_2 =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25g_2")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25G =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25g")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_OC_WIDE_25G =
            new PanelRegistration<TrainOpenableWindowBlock>("window_oc_wide_25g")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
