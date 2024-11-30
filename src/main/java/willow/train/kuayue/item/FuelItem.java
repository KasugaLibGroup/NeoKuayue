package willow.train.kuayue.item;

import lombok.Getter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class FuelItem extends Item {

    @Getter
    private final Function<RecipeType<?>, Integer> burnTime;
    public FuelItem(Properties pProperties,
                    Function<RecipeType<?>, Integer> burnTimeFunction) {
        super(pProperties);
        this.burnTime = burnTimeFunction;
    }

    public FuelItem(Properties properties, int burnTime) {
        this(properties, type -> burnTime);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return burnTime.apply(recipeType);
    }
}
