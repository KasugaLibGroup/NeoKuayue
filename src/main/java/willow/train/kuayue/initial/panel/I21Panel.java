package willow.train.kuayue.initial.panel;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.FullShapeDirectionalBlock;
import willow.train.kuayue.initial.AllElements;

public class I21Panel {

    public static final BlockReg<FullShapeDirectionalBlock> HEAD_DF21 =
            new BlockReg<FullShapeDirectionalBlock>("head_df21")
                    .blockType(FullShapeDirectionalBlock::new)
                    .material(Material.METAL).materialColor(MaterialColor.COLOR_BLACK)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueLocoTab )
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
