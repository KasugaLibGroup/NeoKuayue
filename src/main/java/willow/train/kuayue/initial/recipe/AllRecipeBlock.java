package willow.train.kuayue.initial.recipe;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.recipe.BlueprintBlock;
import willow.train.kuayue.initial.AllElements;

public class AllRecipeBlock {

    public static final BlockReg<BlueprintBlock> BLUEPRINT_TABLE =
            new BlockReg<BlueprintBlock>("blueprint_table")
                    .blockType(BlueprintBlock::new)
                    .material(Material.WOOD)
                    .materialColor(MaterialColor.WOOD)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .addProperty(properties -> properties.strength(1.0f, 1.5f))
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}

}
