package willow.train.kuayue.initial;

import kasuga.lib.registrations.BundledReg;
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

    public static final ItemReg<Item> LUNCH_BOX =
            new ItemReg<Item>("lunch_box")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final BundledReg<ItemReg<Item>> CIRCUIT_BLUEPRINTS =
            new BundledReg<ItemReg<Item>>("circuit_blueprint")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_circuit_blueprint")
                    .element("head_df21_circuit_blueprint")
                    .element("head_hxd3d_circuit_blueprint")
                    .element("head_cr200j_circuit_blueprint")
                    .element("carriage_25b_circuit_blueprint")
                    .element("carriage_25g_circuit_blueprint")
                    .element("carriage_25k_circuit_blueprint")
                    .element("carriage_25z_circuit_blueprint")
                    .element("carriage_cr200j_circuit_blueprint")
                    .submit(AllElements.testRegistry);


    public static final BundledReg<ItemReg<Item>> BLUEPRINTS =
            new BundledReg<ItemReg<Item>>("blueprint")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_blueprint")
                    .element("head_df21_blueprint")
                    .element("head_hxd3d_blueprint")
                    .element("head_cr200j_blueprint")
                    .element("carriage_25b_blueprint")
                    .element("carriage_25g_blueprint")
                    .element("carriage_25k_blueprint")
                    .element("carriage_25z_blueprint")
                    .element("carriage_25t_blueprint")
                    .element("carriage_cr200j_blueprint")
                    .element("carriage_m1_blueprint")
                    .element("carriage_marshalled_blueprint")
                    .submit(AllElements.testRegistry);


    public static final BundledReg<ItemReg<Item>> CIRCUITS =
            new BundledReg<ItemReg<Item>>("circuit")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_circuit")
                    .element("head_df21_circuit")
                    .element("head_hxd3d_circuit")
                    .element("head_cr200j_circuit")
                    .element("carriage_25b_circuit")
                    .element("carriage_25g_circuit")
                    .element("carriage_25k_circuit")
                    .element("carriage_25z_circuit")
                    .element("carriage_25t_circuit")
                    .element("carriage_cr200j_circuit")
                    .submit(AllElements.testRegistry);

    public static final BundledReg<ItemReg<Item>> CIRCUITS_CONSTRUCTING =
            new BundledReg<ItemReg<Item>>("circuit")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_circuit_constructing")
                    .element("head_df21_circuit_constructing")
                    .element("head_hxd3d_circuit_constructing")
                    .element("head_cr200j_circuit_constructing")
                    .element("carriage_25b_circuit_constructing")
                    .element("carriage_25g_circuit_constructing")
                    .element("carriage_25k_circuit_constructing")
                    .element("carriage_25z_circuit_constructing")
                    .element("carriage_25t_circuit_constructing")
                    .element("carriage_cr200j_circuit_constructing")
                    .submit(AllElements.testRegistry);


    public static final BundledReg<ItemReg<Item>> MOULDS =
            new BundledReg<ItemReg<Item>>("mould")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_mould")
                    .element("head_df21_mould")
                    .element("head_hxd3d_mould")
                    .element("head_cr200j_mould")
                    .element("carriage_25b_mould")
                    .element("carriage_25g_mould")
                    .element("carriage_25k_mould")
                    .element("carriage_25z_mould")
                    .element("carriage_25t_mould")
                    .element("carriage_cr200j_mould")
                    .element("carriage_m1_mould")
                    .element("carriage_marshalled_mould")
                    .submit(AllElements.testRegistry);

    public static final BundledReg<ItemReg<Item>> MOULDS_CONSTRUCTING =
            new BundledReg<ItemReg<Item>>("mould")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMainTab))
                    .element("head_df11g_mould_constructing")
                    .element("head_df21_mould_constructing")
                    .element("head_hxd3d_mould_constructing")
                    .element("head_cr200j_mould_constructing")
                    .element("carriage_25b_mould_constructing")
                    .element("carriage_25g_mould_constructing")
                    .element("carriage_25k_mould_constructing")
                    .element("carriage_25z_mould_constructing")
                    .element("carriage_25t_mould_constructing")
                    .element("carriage_cr200j_mould_constructing")
                    .element("carriage_m1_mould_constructing")
                    .element("carriage_marshalled_mould_constructing")
                    .submit(AllElements.testRegistry);

    public static void invoke() {}
}
