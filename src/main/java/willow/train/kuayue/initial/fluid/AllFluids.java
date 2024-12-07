package willow.train.kuayue.initial.fluid;

import kasuga.lib.core.base.BucketItem;
import kasuga.lib.registrations.common.FluidReg;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import willow.train.kuayue.block.food.fluid.BlueBullBlock;
import willow.train.kuayue.block.food.fluid.BlueBullFluid;
import willow.train.kuayue.block.food.fluid.ColaBlock;
import willow.train.kuayue.block.food.fluid.ColaFluid;
import willow.train.kuayue.initial.AllElements;

public class AllFluids {

/*
    public static final FluidReg<ColaFluid> COLA_FLUID = new FluidReg<ColaFluid>("cola_fluid")
            .still(ColaFluid::new, "fluid/water_still")
            .flow(ColaFluid.Flowing::new, "fluid/water_flow")
            .numericProperties(1, 8, 3, 10)
            .overlayTexPath("fluid/cola_fluid")
            .bucketItem(BucketItem::new)
            .basicFluidProperties(5, 15, 5, true)
            .defaultSounds()
            .tintColor(0xFFD2691E)
            .fogColor(210, 105, 30)
            .blockType((fluid, properties) ->
                    new ColaBlock(fluid, BlockBehaviour.Properties.copy(Blocks.WATER)))
            .noLootAndOcclusion()
            .setRenderType(RenderType.translucent())
            .tab(AllElements.neoKuayueDietTab)
            .submit(AllElements.testRegistry);
*/

    public static final FluidReg<BlueBullFluid> BLUE_BULL_FLUID = new FluidReg<BlueBullFluid>("blue_bull_fluid")
            .still(BlueBullFluid::new, "fluid/water_still")
            .flow(BlueBullFluid.Flowing::new, "fluid/water_flow")
            .numericProperties(2, 2, 3, 10)
            .overlayTexPath("fluid/cola_fluid")
            .bucketItem(BucketItem::new)
            .basicFluidProperties(2, 15, 5, true)
            .defaultSounds()
            .tintColor(0xFFFFE4B5)
            .fogColor(255, 228, 181)
            .blockType((fluid, properties) ->
                    new BlueBullBlock(fluid, BlockBehaviour.Properties.copy(Blocks.WATER)))
            .noLootAndOcclusion()
            .setTranslucentRenderType()
            .tab(AllElements.neoKuayueDietTab)
            .submit(AllElements.testRegistry);

    public static void invoke() {}
}
