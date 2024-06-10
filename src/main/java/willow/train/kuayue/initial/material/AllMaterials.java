package willow.train.kuayue.initial.material;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.quartz.QuartzAngleBlock22;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlock2;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlock4;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlockHalf;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;

public class AllMaterials {

    public static final BlockReg<SlabBlock> CLAY_SLAB =
            new BlockReg<SlabBlock>("clay_slab")
                    .blockType(SlabBlock::new)
                    .material(Material.CLAY)
                    .materialColor(MaterialColor.STONE)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final BlockReg<StairBlock> CLAY_STAIRS =
            new BlockReg<StairBlock>("clay_stairs")
                    .blockType(new BlockReg.BlockBuilder<StairBlock>() {
                        @Override
                        public StairBlock build(BlockBehaviour.Properties properties) {
                            return new StairBlock(Blocks.CLAY::defaultBlockState, BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.STONE));
                        }
                    })
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<QuartzPanelBlock2> SMOOTH_QUARTZ_PANEL_2 =
            new PanelRegistration<QuartzPanelBlock2>("smooth_quartz_panel_2")
                    .block(QuartzPanelBlock2::new)
                    .materialAndColor(Material.STONE, MaterialColor.NONE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<QuartzPanelBlockHalf> SMOOTH_QUARTZ_PANEL_HALF =
            new PanelRegistration<QuartzPanelBlockHalf>("smooth_quartz_panel_half")
                    .block(QuartzPanelBlockHalf::new)
                    .materialAndColor(Material.STONE, MaterialColor.NONE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<QuartzPanelBlock4> SMOOTH_QUARTZ_PANEL_4 =
            new PanelRegistration<QuartzPanelBlock4>("smooth_quartz_panel_4")
                    .block(QuartzPanelBlock4::new)
                    .materialAndColor(Material.STONE, MaterialColor.NONE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<QuartzAngleBlock22> SMOOTH_QUARTZ_ANGLE_22_UP =
            new PanelRegistration<QuartzAngleBlock22>("smooth_quartz_angle_22_up")
                    .block(QuartzAngleBlock22::new)
                    .materialAndColor(Material.STONE, MaterialColor.NONE)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
