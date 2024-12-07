package willow.train.kuayue.initial.food;

import kasuga.lib.registrations.common.ItemReg;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.*;
import willow.train.kuayue.block.food.instant_noodles.DriedInstantNoodlesBlockItem;
import willow.train.kuayue.block.food.instant_noodles.SoakedInstantNoodlesBlock;
import willow.train.kuayue.block.food.instant_noodles.SoakedInstantNoodlesBlockItem;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.AllItems;
import willow.train.kuayue.initial.registration.PlacementFoodRegistration;

import static willow.train.kuayue.initial.AllItems.EMPTY_CAN;
import static willow.train.kuayue.initial.AllItems.EMPTY_CAN_BLUE;
import static willow.train.kuayue.initial.food.AllFoodProperties.*;

public class AllFoods {

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
                    .foodProperties(TRAIN_BOX_LUNCH_PROPS)
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
                    .foodProperties(KUA_COLA_PROPS)
                    .craftReminder(EMPTY_CAN::getItem)
                    .stackSize(16)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final PlacementFoodRegistration<ParticlesDrinkBlock, PlacementDrinkBlockItem> BLUE_BULL =
            new PlacementFoodRegistration<ParticlesDrinkBlock, PlacementDrinkBlockItem>
                    ("blue_bull", true, true)
                    .block(properties ->
                            new ParticlesDrinkBlock(properties, PlacementFoodBlock.FoodType.BOTTLE, false))
                    .item((reg, properties, hasEffect, hasTooltip) ->
                            new PlacementDrinkBlockItem(reg.getBlock(), properties, hasEffect, hasTooltip))
                    .material(Material.METAL, MaterialColor.COLOR_BLACK)
                    .soundType(SoundType.METAL)
                    .strength(0.5F)
                    .noOcclusion()
                    .foodProperties(BLUE_BULL_PROPS)
                    .craftReminder(EMPTY_CAN_BLUE::getItem)
                    .stackSize(16)
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
                    .foodProperties(DRIED_INSTANT_NOODLES_PROPS)
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
                    .foodProperties(SOAKED_INSTANT_NOODLES_PROPS)
                    .stackSize(8)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> HAM_SAUSAGE =
            new ItemReg<HandHeldFoodItem>("ham_sausage")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(HAM_SAUSAGE_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> MARINATED_EGG =
            new ItemReg<HandHeldFoodItem>("marinated_egg")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(MARINATED_EGG_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> HALF_MARINATED_EGG =
            new ItemReg<HandHeldFoodItem>("half_marinated_egg")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(HALF_MARINATED_EGG_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> CALCIUM_MILK_BISCUITS =
            new ItemReg<HandHeldFoodItem>("calcium_milk_biscuits")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(CALCIUM_MILK_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> SPICY_FISH_TOFU =
            new ItemReg<HandHeldFoodItem>("spicy_fish_tofu")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(SPICY_FISH_TOFU_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<HandHeldFoodItem> SALT_BAKED_DRUMSTICKS =
            new ItemReg<HandHeldFoodItem>("salt_baked_drumsticks")
                    .itemType(HandHeldFoodItem::new)
                    .withProperty(properties -> properties.food(SALT_BAKED_DRUMSTICKS_PROPS))
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {
        AllEffects.invoke();
        AllFoodProperties.invoke();
    }
}
