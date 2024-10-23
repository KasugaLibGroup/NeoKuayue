package willow.train.kuayue.mixins.mixin.client;

import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import willow.train.kuayue.initial.AllBlocks;

import java.util.function.Supplier;

@Mixin(BlockReg.class)
public class MixinBlockReg<T extends Block> {

    /*
    @Redirect(method = "submit(Lkasuga/lib/registrations/registry/SimpleRegistry;)Lkasuga/lib/registrations/common/BlockReg;",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/DeferredRegister;register(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/minecraftforge/registries/RegistryObject;"),
            remap = false)
    public RegistryObject<T> doSubmit(DeferredRegister<T> instance, String s, Supplier<? extends T> name) {
        // AllBlocks.generateDrops(s);
        return instance.register(s, name);
    }
     */


}
