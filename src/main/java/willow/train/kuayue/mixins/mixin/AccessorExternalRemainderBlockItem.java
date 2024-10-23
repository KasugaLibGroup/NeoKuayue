package willow.train.kuayue.mixins.mixin;

import kasuga.lib.core.base.item_helper.ExternalRemainderBlockItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(ExternalRemainderBlockItem.class)
public interface AccessorExternalRemainderBlockItem {

    @Accessor("craftingRemainder")
    Supplier<Item> getCraftingRemainder();
}
