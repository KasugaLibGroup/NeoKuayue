package willow.train.kuayue.initial.ore;

import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.initial.AllElements;

public class AllOres {

    public static final BlockReg<DropExperienceBlock> SALT_ORE =
            new BlockReg<DropExperienceBlock>("salt_ore")
                    .blockType(props ->
                            new DropExperienceBlock(props, UniformInt.of(3, 7)))
                    .material(Material.STONE)
                    .materialColor(MaterialColor.STONE)
                    .addProperty(properties -> properties.strength(1.5f, 6.0F))
                    .addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
