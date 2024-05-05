package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.*;
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

public class C25GPanel {

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_25G =
            new PanelRegistration<CustomRenderedDoorBlock>("door_25g")
                    .block(p -> new CustomRenderedDoorBlock(p,
                            Couple.create(
                                    new ResourceLocation(Kuayue.MODID, "door/original_25g_door_bottom_hinge"),
                                    new ResourceLocation(Kuayue.MODID, "door/original_25g_door_top_hinge")
                            ), Couple.create(
                                    new ResourceLocation(Kuayue.MODID, "door/original_25g_door_bottom"),
                                    new ResourceLocation(Kuayue.MODID, "door/original_25g_door_top")
                            ), RenderShape.ENTITYBLOCK_ANIMATED, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_25G =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_MIDDLE_25G =
            new PanelRegistration<TrainPanelBlock>("panel_middle_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainHingePanelBlock> PANEL_SYMBOL_MARSHALLED_25G =
            new PanelRegistration<TrainHingePanelBlock>("panel_symbol_marshalled_25g")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25G =
            new PanelRegistration<TrainPanelBlock>("panel_top_25g")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableSmallWindowBlock> WINDOW_OC_SEALED_SMALL_25G =
            new PanelRegistration<TrainUnOpenableSmallWindowBlock>("window_oc_sealed_small_25g")
                    .block(TrainUnOpenableSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainUnOpenableWindowBlock> WINDOW_OC_SEALED_WIDE_25G =
            new PanelRegistration<TrainUnOpenableWindowBlock>("window_oc_sealed_wide_25g")
                    .block(p -> new TrainUnOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25G =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25g")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25G_2 =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25g_2")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25G =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25g")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_OC_WIDE_25G =
            new PanelRegistration<TrainOpenableWindowBlock>("window_oc_wide_25g")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    
    public static final SlabRegistration<TrainSlabBlock> FLOOR_25G =
            new SlabRegistration<TrainSlabBlock>("floor_25g")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> TOILET_DD_25G =
            new SlabRegistration<TrainSlabBlock>("toilet_dd_25g")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainLadderBlock> LADDER_25G =
            new SlabRegistration<TrainLadderBlock>("ladder_25g")
                    .block(TrainLadderBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
