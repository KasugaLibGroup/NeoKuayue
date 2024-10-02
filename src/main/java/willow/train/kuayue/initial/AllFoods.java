package willow.train.kuayue.initial;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.initial.registration.PlacementFoodRegistration;

public class AllFoods {

    public static final MobEffectInstance TRAIN_DIET_EFFECT =
            new MobEffectInstance(MobEffects.REGENERATION, 100, 0);

    public static final FoodProperties TRAIN_DIET_A = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.6f)
            .effect(() -> TRAIN_DIET_EFFECT, 1.0F).build();

    public static final PlacementFoodRegistration<PlacementFoodBlock> TRAIN_DIET_1 =
            new PlacementFoodRegistration<PlacementFoodBlock>("train_diet_1")
                    .block(properties -> new PlacementFoodBlock(properties, PlacementFoodBlock.FoodType.BOX))
                    .material(Material.CAKE, MaterialColor.COLOR_GRAY)
                    .soundType(SoundType.WOOL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(TRAIN_DIET_A)
                    .stackSize(16)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
