package willow.train.kuayue.initial.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import static willow.train.kuayue.initial.food.AllEffects.*;

public class AllFoodProperties {

    public static final FoodProperties TRAIN_BOX_LUNCH_PROPS = (new FoodProperties.Builder())
            .nutrition(9).saturationMod(0.5f).alwaysEat()
            .effect(() -> TRAIN_BOX_LUNCH_EFFECT, 1.0F).build();

    public static final FoodProperties KUA_COLA_PROPS = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.5f).alwaysEat()
            .effect(() -> KUA_COLA_EFFECT, 1.0F).build();

    public static final FoodProperties DRIED_INSTANT_NOODLES_PROPS = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.5f).build();

    public static final FoodProperties SOAKED_INSTANT_NOODLES_PROPS = (new FoodProperties.Builder())
            .nutrition(10).saturationMod(0.5f).alwaysEat()
            .effect(() -> new MobEffectInstance(WARM.getEffect(), 2400, 0), 1.0F).build();

    public static final FoodProperties HAM_SAUSAGE_PROPS = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.5f).fast().build();

    public static final FoodProperties MARINATED_EGG_PROPS = (new FoodProperties.Builder())
            .nutrition(8).saturationMod(0.75f).fast().build();

    public static final FoodProperties HALF_MARINATED_EGG_PROPS = (new FoodProperties.Builder())
            .nutrition(5).saturationMod(1.0f).fast().build();

    public static final FoodProperties CALCIUM_MILK_PROPS = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.5f).fast().build();

    public static final FoodProperties SPICY_FISH_TOFU_PROPS = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.5f).fast().build();

    public static final FoodProperties SALT_BAKED_DRUMSTICKS_PROPS = (new FoodProperties.Builder())
            .nutrition(8).saturationMod(0.75f).fast().build();

    public static void invoke() {}
}
