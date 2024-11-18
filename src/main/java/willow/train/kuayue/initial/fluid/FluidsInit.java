package willow.train.kuayue.initial.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.initial.AllItems;

public class FluidsInit {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Kuayue.MODID);

    public static final RegistryObject<FlowingFluid> STILL_COLA = FLUIDS.register("cola_still_fluid",
            () -> new ForgeFlowingFluid.Source(FluidsInit.COLA_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_COLA = FLUIDS.register("cola_flowing_fluid",
            () -> new ForgeFlowingFluid.Flowing(FluidsInit.COLA_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties COLA_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypesInit.COLA_FLUID_TYPE, STILL_COLA, FLOWING_COLA)
            .slopeFindDistance(2).levelDecreasePerBlock(2)
            .block(AllBlocks.COLA_BLOCK).bucket(AllItems.COLA_BUCKET);

    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
