package willow.train.kuayue.initial.fluid;

import kasuga.lib.core.base.BucketItem;
import kasuga.lib.registrations.common.FluidReg;
import willow.train.kuayue.block.food.fluid.ColaBlock;
import willow.train.kuayue.block.food.fluid.ColaFluid;
import willow.train.kuayue.initial.AllElements;

public class AllFluids {

    public static final FluidReg<ColaFluid> COLA_FLUID = new FluidReg<ColaFluid>("cola_fluid")
            .still(ColaFluid::new, "fluid/water_still")
            .flow(ColaFluid.Flowing::new, "fluid/water_flow")
            .tintColor(0xFFD2691E)
            .numericProperties(1, 8, 3, 10)
            .overlayTexPath("fluid/cola_fluid")
            .bucketItem(BucketItem::new)
            .blockType(ColaBlock::new)
            .tab(AllElements.neoKuayueDietTab)
            .submit(AllElements.testRegistry);

    public static void invoke() {}
}
