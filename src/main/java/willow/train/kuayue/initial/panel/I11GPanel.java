package willow.train.kuayue.initial.panel;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.TrainHingePanelBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.slab.HingeSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableSmallWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class I11GPanel {

    public static final PanelRegistration<TrainPanelBlock> PANEL_CR_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_cr_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(-1, 0), new Vec2(2, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_GENERAL_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_general_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_KUAYUE_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_kuayue_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(-1, 0), new Vec2(2, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainHingePanelBlock> PANEL_MIDDLE_FRONT_DF11G =
            new PanelRegistration<TrainHingePanelBlock>("panel_middle_front_df11g")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainHingePanelBlock> PANEL_MIDDLE_FRONT_DF11G_2 =
            new PanelRegistration<TrainHingePanelBlock>("panel_middle_front_df11g_2")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 2)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_GENERAL_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_middle_general_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_SHADES_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_middle_shades_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_SHADES_DF11G =
            new PanelRegistration<TrainPanelBlock>("panel_shades_df11g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainUnOpenableSmallWindowBlock> WINDOW_MIDDLE_DF11G =
            new PanelRegistration<TrainUnOpenableSmallWindowBlock>("window_middle_df11g")
                    .block(TrainUnOpenableSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<HingeSlabBlock> CARPORT_DF11G =
            new SlabRegistration<HingeSlabBlock>("carport_df11g")
                    .block(p -> new HingeSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<HingeSlabBlock> CARPORT_DF11G_2 =
            new SlabRegistration<HingeSlabBlock>("carport_df11g_2")
                    .block(p -> new HingeSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<TrainSlabBlock> CARPORT_GENERAL_DF11G =
            new SlabRegistration<TrainSlabBlock>("carport_general_df11g")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<TrainSlabBlock> CARPORT_KUAYUE_DF11G =
            new SlabRegistration<TrainSlabBlock>("carport_kuayue_df11g")
                    .block(p -> new TrainSlabBlock(p, true, -1, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<TrainSlabBlock> CARPORT_LOUVER_DF11G =
            new SlabRegistration<TrainSlabBlock>("carport_louver_df11g")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<TrainSlabBlock> CARPORT_LOUVER_DF11G_2 =
            new SlabRegistration<TrainSlabBlock>("carport_louver_df11g_2")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<TrainSlabBlock> CARPORT_LOUVER_DF11G_3 =
            new SlabRegistration<TrainSlabBlock>("carport_louver_df11g_3")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
