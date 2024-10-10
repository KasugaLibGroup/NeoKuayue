package willow.train.kuayue.initial;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.*;
import willow.train.kuayue.block.food.instant_noodles.DriedInstantNoodlesBlockItem;
import willow.train.kuayue.block.food.instant_noodles.SoakedInstantNoodlesBlock;
import willow.train.kuayue.block.food.instant_noodles.SoakedInstantNoodlesBlockItem;
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

    public static final PlacementFoodRegistration<TrainDietBoxBlock, PlacementFoodBlockItem> TRAIN_DIET_1 =
            new PlacementFoodRegistration<TrainDietBoxBlock, PlacementFoodBlockItem>
                    ("train_diet_1", true, true)
                    .block(properties -> new TrainDietBoxBlock(properties, PlacementFoodBlock.FoodType.BOX))
                    .item((reg, properties, hasEffect, hasTooltip) ->
                            new PlacementFoodBlockItem(reg.getBlock(), properties, hasEffect, hasTooltip))
                    .material(Material.CAKE, MaterialColor.COLOR_GRAY)
                    .soundType(SoundType.WOOL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(TRAIN_DIET_A)
                    .craftReminder(AllItems.LUNCH_BOX::getItem)
                    .stackSize(16)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final PlacementFoodRegistration<ParticlesDrinkBlock, PlacementDrinkBlockItem> KUA_COLA =
            new PlacementFoodRegistration<ParticlesDrinkBlock, PlacementDrinkBlockItem>
                    ("kua_cola", true, true)
                    .block(properties -> new ParticlesDrinkBlock(properties, PlacementFoodBlock.FoodType.BOTTLE))
                    .item((reg, properties, hasEffect, hasTooltip) ->
                            new PlacementDrinkBlockItem(reg.getBlock(), properties, hasEffect, hasTooltip))
                    .material(Material.METAL, MaterialColor.COLOR_BLACK)
                    .soundType(SoundType.METAL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(DRINK_COLA)
                    .stackSize(64)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final PlacementFoodRegistration<PlacementFoodBlock, DriedInstantNoodlesBlockItem> DRIED_INSTANT_NOODLES =
            new PlacementFoodRegistration<PlacementFoodBlock, DriedInstantNoodlesBlockItem>
                    ("dried_instant_noodles", true, true)
                    .block(properties -> new PlacementFoodBlock(properties, PlacementFoodBlock.FoodType.BOWL))
                    .item((reg, properties, hasEffect, hasTooltip) ->
                            new DriedInstantNoodlesBlockItem(reg.getBlock(), properties, hasEffect, hasTooltip))
                    .material(Material.WOOL, MaterialColor.COLOR_BLACK)
                    .soundType(SoundType.WOOL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(DRINK_COLA)
                    .stackSize(16)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final PlacementFoodRegistration<SoakedInstantNoodlesBlock, SoakedInstantNoodlesBlockItem> SOAKED_INSTANT_NOODLES =
            new PlacementFoodRegistration<SoakedInstantNoodlesBlock, SoakedInstantNoodlesBlockItem>
                    ("soaked_instant_noodles", true, true)
                    .block(properties -> new SoakedInstantNoodlesBlock(properties, PlacementFoodBlock.FoodType.BOWL))
                    .item((reg, properties, hasEffect, hasTooltip) ->
                            new SoakedInstantNoodlesBlockItem(reg.getBlock(), properties, hasEffect, hasTooltip))
                    .material(Material.WOOL, MaterialColor.COLOR_BLACK)
                    .soundType(SoundType.WOOL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(TRAIN_DIET_A)
                    .stackSize(8)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
