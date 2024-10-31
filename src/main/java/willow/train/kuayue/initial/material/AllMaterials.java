package willow.train.kuayue.initial.material;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.deco.DirectionalCarpetBlock;
import willow.train.kuayue.block.panels.quartz.QuartzAngleBlock22;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlock2;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlock4;
import willow.train.kuayue.block.panels.quartz.QuartzPanelBlockHalf;
import willow.train.kuayue.block.structure.weatheringsteel.FullWeatheringSteelBlock;
import willow.train.kuayue.block.structure.weatheringsteel.IWeatheringSteel;
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

    //耐候钢块
    public static final BlockReg<FullWeatheringSteelBlock> WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("weathering_resistant_steel_block_0")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    //斑驳的耐候钢块
    public static final BlockReg<FullWeatheringSteelBlock> EXPOSED_WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("exposed_weathering_steel_block")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.EXPOSED_WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    //泛黄的耐候钢块
    public static final BlockReg<FullWeatheringSteelBlock> YELLOWING_WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("yellowing_weathering_steel_block")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.YELLOWING_WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    //耐候钢锈块
    public static final BlockReg<FullWeatheringSteelBlock> RUSTED_WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("rusted_weathering_steel_block")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.RUSTED_WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    //稳定的耐候钢锈块
    public static final BlockReg<FullWeatheringSteelBlock> STABLY_RUSTED_WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("stably_rusted_weathering_steel_block")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.STABLY_WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    //致密的耐候钢锈块
    public static final BlockReg<FullWeatheringSteelBlock> FULLY_RUSTED_WEATHERING_STEEL_BLOCK =
            new BlockReg<FullWeatheringSteelBlock>("fully_rusted_weathering_steel_block")
                    .blockType(p -> new FullWeatheringSteelBlock(IWeatheringSteel.WSWeatherState.FULLY_WS, p))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(p -> p.strength(2.0f, 3.0f))
                    .withSound(SoundType.COPPER)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    // 原色25z地毯
    public static final BlockReg<DirectionalCarpetBlock> ORIGINAL_25Z_FLOOR_CARPET =
            new BlockReg<DirectionalCarpetBlock>("original_25z_floor_carpet")
                    .blockType(DirectionalCarpetBlock::new)
                    .material(Material.WOOL)
                    .materialColor(MaterialColor.NONE)
                    .addProperty(properties -> properties.strength(.1f, .1f))
                    .addProperty(properties -> properties.sound(SoundType.WOOL))
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
