package willow.train.kuayue.initial.panel;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.FullShapeDirectionalBlock;
import willow.train.kuayue.block.panels.slab.HingeSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class I21Panel {

    public static final BlockReg<FullShapeDirectionalBlock> HEAD_DF21 =
            new BlockReg<FullShapeDirectionalBlock>("head_df21")
                    .blockType(FullShapeDirectionalBlock::new)
                    .material(Material.METAL).materialColor(MaterialColor.COLOR_BLACK)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueLocoTab)
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<TrainSlabBlock> EXHAUST_FAN_DF21 =
            new SlabRegistration<TrainSlabBlock>("exhaust_fan_df21")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_RED)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<HingeSlabBlock> DYNAMIC_CARPORT_DF21 =
            new SlabRegistration<HingeSlabBlock>("dynamic_carport_df21")
                    .block(p -> new HingeSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final SlabRegistration<HingeSlabBlock> DYNAMIC_TRANSITION_CARPORT_DF21 =
            new SlabRegistration<HingeSlabBlock>("dynamic_transition_carport_df21")
                    .block(p -> new HingeSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
