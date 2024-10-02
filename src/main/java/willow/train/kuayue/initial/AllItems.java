package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.ItemReg;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class AllItems {

    public static final ItemReg<Item> SERIES_25_LOGOS =
            new ItemReg<Item>("series25_logos")
                    .itemType(Item::new)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> LOCO_LOGOS =
            new ItemReg<Item>("loco_logos")
                    .itemType(Item::new)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> LOGO_A25T =
            new ItemReg<Item>("logo_a25t")
                    .itemType(Item::new)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> CA_25T =
            new ItemReg<Item>("ca_25t")
                    .itemType(Item::new)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_INGOT =
            new ItemReg<Item>("weathering_resistant_steel_ingot")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_NUGGET =
            new ItemReg<Item>("weathering_resistant_steel_nugget")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);


    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_BOARD =
            new ItemReg<Item>("weathering_resistant_steel_board")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
