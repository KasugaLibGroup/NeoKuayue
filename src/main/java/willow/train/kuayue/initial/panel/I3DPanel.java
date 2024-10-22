package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.block.panels.FullShapeDirectionalBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.block.panels.slab.HeightSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class I3DPanel {

    public static final PanelRegistration<CustomRenderedDoorBlock> DOOR_CABIN_HXD3D =
            new PanelRegistration<CustomRenderedDoorBlock>("door_cabin_hxd3d")
                    .block(p -> new CustomRenderedDoorBlock(p,
                            Couple.create(
                                    AllElements.testRegistry.asResource("hxd3d/door/door_bottom_hxd3d_right"),
                                    AllElements.testRegistry.asResource("hxd3d/door/door_upper_hxd3d_right")
                            ), Couple.create(
                            AllElements.testRegistry.asResource("hxd3d/door/door_bottom_hxd3d_left"),
                            AllElements.testRegistry.asResource("hxd3d/door/door_upper_hxd3d_left")
                    ), new Vec3(-.3125, .25, 0), RenderShape.MODEL, false
                    ))
                    .materialAndColor(MapColor.COLOR_GREEN)
                    .noOcclusion()
                    .tab(AllElements.neoKuayueLocoTab)
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> CARPORT_GENERAL_HXD3D =
            new SlabRegistration<TrainSlabBlock>("carport_general_hxd3d")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(MapColor.COLOR_RED)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<HeightSlabBlock> FLOOR_HXD3D =
            new SlabRegistration<HeightSlabBlock>("floor_hxd3d")
                    .block(p -> new HeightSlabBlock(p, false))
                    .materialAndColor(MapColor.COLOR_RED)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_HEXIE_HXD3D =
            new PanelRegistration<TrainPanelBlock>("panel_hexie_hxd3d")
                    .block(p -> new TrainPanelBlock(p, new Vec2(-1, 0), new Vec2(2, 2)))
                    .materialAndColor(MapColor.COLOR_RED)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_RED_HXD3D =
            new PanelRegistration<TrainPanelBlock>("panel_red_hxd3d")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(MapColor.COLOR_RED)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final BlockReg<FullShapeDirectionalBlock> HEAD_HXD3D =
            new BlockReg<FullShapeDirectionalBlock>("head_hxd3d")
                    .blockType(FullShapeDirectionalBlock::new)
                    .materialColor(MapColor.COLOR_BLACK)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueLocoTab)
                    .submit(AllElements.testRegistry);


    public static void invoke(){}
}
