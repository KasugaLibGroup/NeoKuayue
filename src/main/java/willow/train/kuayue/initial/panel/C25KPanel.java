package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.TrainHingePanelBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.block.panels.slab.TrainLadderBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableSmallWindowBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class C25KPanel {

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_25K =
            new PanelRegistration<CustomRenderedDoorBlock>("door_25k")
                    .block(p -> new CustomRenderedDoorBlock(p,
                            Couple.create(
                                    new ResourceLocation(Kuayue.MODID, "door/original_25k_door_bottom_hinge"),
                                    new ResourceLocation(Kuayue.MODID, "door/original_25k_door_top_hinge")
                            ), Couple.create(
                            new ResourceLocation(Kuayue.MODID, "door/original_25k_door_bottom"),
                            new ResourceLocation(Kuayue.MODID, "door/original_25k_door_top")
                    ), RenderShape.ENTITYBLOCK_ANIMATED, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_25K =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_25k")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_LINE_25K =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_line_25k")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_25K =
            new PanelRegistration<TrainPanelBlock>("panel_middle_25k")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainHingePanelBlock> PANEL_SYMBOL_25K =
            new PanelRegistration<TrainHingePanelBlock>("panel_symbol_25k")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainHingePanelBlock> PANEL_SYMBOL_MARSHALLED_25K =
            new PanelRegistration<TrainHingePanelBlock>("panel_symbol_marshalled_25k")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25K =
            new PanelRegistration<TrainPanelBlock>("panel_top_25k")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainUnOpenableSmallWindowBlock> WINDOW_OC_SEALED_SMALL_25K =
            new PanelRegistration<TrainUnOpenableSmallWindowBlock>("window_oc_sealed_small_25k")
                    .block(TrainUnOpenableSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25K =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25k")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25K =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25k")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_OC_WIDE_25K =
            new PanelRegistration<TrainOpenableWindowBlock>("window_oc_wide_25k")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainUnOpenableWindowBlock> WINDOW_OC_WIDE_SEALED_25K =
            new PanelRegistration<TrainUnOpenableWindowBlock>("window_oc_wide_sealed_25k")
                    .block(p -> new TrainUnOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> FLOOR_25K =
            new SlabRegistration<TrainSlabBlock>("floor_25k")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> TOILET_DD_25K =
            new SlabRegistration<TrainSlabBlock>("toilet_dd_25k")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainLadderBlock> LADDER_SLIDING_25K =
            new SlabRegistration<TrainLadderBlock>("ladder_sliding_25k")
                    .block(TrainLadderBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
