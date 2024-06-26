package willow.train.kuayue.initial.panel;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.FullShapeDirectionalBlock;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.slab.HeightSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class I3DPanel {

    public static final SlabRegistration<TrainSlabBlock> CARPORT_GENERAL_HXD3D =
            new SlabRegistration<TrainSlabBlock>("carport_general_hxd3d")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_RED)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final SlabRegistration<HeightSlabBlock> FLOOR_HXD3D =
            new SlabRegistration<HeightSlabBlock>("floor_hxd3d")
                    .block(p -> new HeightSlabBlock(p, false))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_RED)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_HEXIE_HXD3D =
            new PanelRegistration<TrainPanelBlock>("panel_hexie_hxd3d")
                    .block(p -> new TrainPanelBlock(p, new Vec2(-1, 0), new Vec2(2, 2)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_RED)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);
    public static final PanelRegistration<TrainPanelBlock> PANEL_RED_HXD3D =
            new PanelRegistration<TrainPanelBlock>("panel_red_hxd3d")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_RED)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final BlockReg<FullShapeDirectionalBlock> HEAD_HXD3D =
            new BlockReg<FullShapeDirectionalBlock>("head_hxd3d")
                    .blockType(FullShapeDirectionalBlock::new)
                    .material(Material.METAL).materialColor(MaterialColor.COLOR_BLACK)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);


    public static void invoke(){}
}
