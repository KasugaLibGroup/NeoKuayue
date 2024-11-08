package willow.train.kuayue.initial.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import static willow.train.kuayue.initial.food.AllEffects.*;

public class AllFoodProperties {

    public static final FoodProperties TRAIN_BOX_LUNCH_PROPS = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.6f).alwaysEat()
            .effect(() -> TRAIN_BOX_LUNCH_EFFECT, 1.0F).build();

    public static final FoodProperties KUA_COLA_PROPS = (new FoodProperties.Builder())
            .nutrition(2).saturationMod(0.2f).alwaysEat()
            .effect(() -> KUA_COLA_EFFECT, 1.0F)
            .build();

    public static final FoodProperties DRIED_INSTANT_NOODLES_PROPS = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.4f).alwaysEat().build();

    public static final FoodProperties SOAKED_INSTANT_NOODLES_PROPS = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.6f).alwaysEat()
            .effect(() -> new MobEffectInstance(WARM.getEffect(), 2400, 0), 1.0F)
            .build();

    public static final FoodProperties SMALL_SNACKS_PROPS = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.3f).alwaysEat()
            .build();

    public static void invoke() {}
}
