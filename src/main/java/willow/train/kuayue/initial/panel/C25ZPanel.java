package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.LevelPanelBlock;
import willow.train.kuayue.block.panels.TrainHingePanelBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.block.panels.end_face.CustomRenderedEndfaceBlock;
import willow.train.kuayue.block.panels.slab.HingeSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainLadderBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.LevelWindowBlock;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class C25ZPanel {

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_25Z =
            new PanelRegistration<CustomRenderedDoorBlock>("door_25z")
                    .block(p -> new CustomRenderedDoorBlock(p,
                            Couple.create(
                                    new ResourceLocation(Kuayue.MODID, "carriage/carriage25z/door/original_25z_door_bottom_hinge"),
                                    new ResourceLocation(Kuayue.MODID, "carriage/carriage25z/door/original_25z_door_top_hinge")
                            ), Couple.create(
                            new ResourceLocation(Kuayue.MODID, "carriage/carriage25z/door/original_25z_door_bottom"),
                            new ResourceLocation(Kuayue.MODID, "carriage/carriage25z/door/original_25z_door_top")
                    ), RenderShape.ENTITYBLOCK_ANIMATED, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<CustomRenderedEndfaceBlock> END_FACE_25Z_1 =
            new PanelRegistration<CustomRenderedEndfaceBlock>("end_face_25z_1")
                    .block(properties ->
                            new CustomRenderedEndfaceBlock(
                                    properties, TrainPanelProperties.DoorType.SLIDE_3,
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_right",
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_left",
                                    "carriage/carriage25z/end_face/end_face_original_25z_1"
                            )
                    ).materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<CustomRenderedEndfaceBlock> END_FACE_25Z_2 =
            new PanelRegistration<CustomRenderedEndfaceBlock>("end_face_25z_2")
                    .block(properties ->
                            new CustomRenderedEndfaceBlock(
                                    properties, TrainPanelProperties.DoorType.SLIDE_3,
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_right",
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_left",
                                    "carriage/carriage25z/end_face/end_face_original_25z_2"
                            )
                    ).materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<CustomRenderedEndfaceBlock> END_FACE_25Z_3 =
            new PanelRegistration<CustomRenderedEndfaceBlock>("end_face_25z_3")
                    .block(properties ->
                            new CustomRenderedEndfaceBlock(
                                    properties, TrainPanelProperties.DoorType.SLIDE_3,
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_right",
                                    "carriage/carriage25z/end_face/end_face_door_sliding_original_25z_left",
                                    "carriage/carriage25z/end_face/end_face_original_25z_3"
                            )
                    ).materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_LINE_25Z =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_line_25z")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_SIDE_25Z =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_side_25z")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_BOTTOM_SYMBOL_25Z =
            new PanelRegistration<TrainPanelBlock>("panel_bottom_symbol_25z")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<LevelPanelBlock> PANEL_MIDDLE_25Z =
            new PanelRegistration<LevelPanelBlock>("panel_middle_25z")
                    .block(p -> new LevelPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainHingePanelBlock> PANEL_MIDDLE_SIDE_25Z =
            new PanelRegistration<TrainHingePanelBlock>("panel_middle_side_25z")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_TOP_25Z =
            new PanelRegistration<TrainPanelBlock>("panel_top_25z")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_25Z =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_25z")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<LevelWindowBlock> WINDOW_OC_LEVEL_25Z =
            new PanelRegistration<LevelWindowBlock>("window_oc_level_25z")
                    .block(LevelWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_SMALL_25Z_2 =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_small_25z_2")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_OC_TOILET_25Z =
            new PanelRegistration<TrainSmallWindowBlock>("window_oc_toilet_25z")
                    .block(TrainSmallWindowBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_OC_WIDE_25Z =
            new PanelRegistration<TrainOpenableWindowBlock>("window_oc_wide_25z")
                    .block(p -> new TrainOpenableWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> FLOOR_25Z =
            new SlabRegistration<TrainSlabBlock>("floor_25z")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> TOILET_DD_25Z =
            new SlabRegistration<TrainSlabBlock>("toilet_dd_25z")
                    .block(p -> new TrainSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainLadderBlock> LADDER_25Z =
            new SlabRegistration<TrainLadderBlock>("ladder_25z")
                    .block(TrainLadderBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> CARPORT_25Z =
            new SlabRegistration<TrainSlabBlock>("carport_25z")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> CARPORT_AC_25Z =
            new SlabRegistration<TrainSlabBlock>("carport_ac_25z")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> CARPORT_CENTER_25Z =
            new SlabRegistration<TrainSlabBlock>("carport_center_25z")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<HingeSlabBlock> CARPORT_SIDE_AC_25Z =
            new SlabRegistration<HingeSlabBlock>("carport_side_ac_25z")
                    .block(p -> new HingeSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab )
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
