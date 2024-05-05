package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.*;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.block.panels.slab.TrainLadderBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

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
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25B =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25b")
                    .block(TrainSmallWindowBlock::new)
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

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_25B =
            new PanelRegistration<CustomRenderedDoorBlock>("door_25b")
                    .block(p -> new CustomRenderedDoorBlock(p,
                            Couple.create(
                                    AllElements.testRegistry.asResource("door/original_25b_door_bottom"),
                                    AllElements.testRegistry.asResource("door/original_25b_door_top")
                            ), Couple.create(
                                    AllElements.testRegistry.asResource("door/original_25b_door_bottom_hinge"),
                                    AllElements.testRegistry.asResource("door/original_25b_door_top_hinge")
                            ), RenderShape.ENTITYBLOCK_ANIMATED, false
                    ))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> FLOOR_25B =
            new SlabRegistration<TrainSlabBlock>("floor_25b")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> TOILET_DD_25B =
            new SlabRegistration<TrainSlabBlock>("toilet_dd_25b")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainLadderBlock> LADDER_25B =
            new SlabRegistration<TrainLadderBlock>("ladder_25b")
                    .block(TrainLadderBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
