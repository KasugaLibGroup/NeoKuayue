package willow.train.kuayue.initial.recipe;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import willow.train.kuayue.block.recipe.BlueprintBlock;
import willow.train.kuayue.initial.AllElements;

public class AllRecipeBlock {

    public static final BlockReg<BlueprintBlock> BLUEPRINT_TABLE =
            new BlockReg<BlueprintBlock>("blueprint_table")
                    .blockType(BlueprintBlock::new)
                    .materialColor(MapColor.WOOD)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}

}
