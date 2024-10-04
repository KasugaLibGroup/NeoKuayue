package willow.train.kuayue.initial;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.ParticlesDrinkBlock;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.block.food.TrainDietBoxBlock;
import willow.train.kuayue.initial.registration.PlacementFoodRegistration;

public class AllFoods {

    public static final MobEffectInstance TRAIN_DIET_EFFECT =
            new MobEffectInstance(MobEffects.REGENERATION, 100, 0);

    public static final FoodProperties TRAIN_DIET_A = (new FoodProperties.Builder())
            .nutrition(6).saturationMod(0.6f).alwaysEat()
            .effect(() -> TRAIN_DIET_EFFECT, 1.0F).build();

    public static final FoodProperties DRINK_COLA = (new FoodProperties.Builder())
            .nutrition(4).saturationMod(0.4f).alwaysEat()
            .effect(() -> TRAIN_DIET_EFFECT, 1.0F).build();

    public static final PlacementFoodRegistration<TrainDietBoxBlock> TRAIN_DIET_1 =
            new PlacementFoodRegistration<TrainDietBoxBlock>("train_diet_1", false)
                    .block(properties -> new TrainDietBoxBlock(properties, PlacementFoodBlock.FoodType.BOX))
                    .material(Material.CAKE, MaterialColor.COLOR_GRAY)
                    .soundType(SoundType.WOOL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(TRAIN_DIET_A)
                    .craftReminder(AllItems.LUNCH_BOX::getItem)
                    .stackSize(16)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final PlacementFoodRegistration<ParticlesDrinkBlock> KUA_COLA =
            new PlacementFoodRegistration<ParticlesDrinkBlock>("kua_cola", true)
                    .block(properties -> new ParticlesDrinkBlock(properties, PlacementFoodBlock.FoodType.BOTTLE))
                    .material(Material.METAL, MaterialColor.COLOR_BLACK)
                    .soundType(SoundType.METAL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(DRINK_COLA)
                    .stackSize(64)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
