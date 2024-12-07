package willow.train.kuayue.initial;

import kasuga.lib.registrations.BundledReg;
import kasuga.lib.registrations.common.ItemReg;
import net.minecraft.world.item.Item;
import willow.train.kuayue.systems.device.AllDeviceItems;
import net.minecraft.world.item.crafting.RecipeType;
import willow.train.kuayue.item.FuelItem;

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
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_NUGGET =
            new ItemReg<Item>("weathering_resistant_steel_nugget")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);


    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_BOARD =
            new ItemReg<Item>("weathering_resistant_steel_board")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_DUST =
            new ItemReg<Item>("weathering_resistant_steel_dust")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> WEATHERING_RESISTANT_STEEL_POLE =
            new ItemReg<Item>("weathering_resistant_steel_pole")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> PLASTIC_INGOT =
            new ItemReg<Item>("plastic_ingot")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> PLASTIC_NUGGET =
            new ItemReg<Item>("plastic_nugget")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);


    public static final ItemReg<Item> PLASTIC_BOARD =
            new ItemReg<Item>("plastic_board")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> PLASTIC_POLE =
            new ItemReg<Item>("plastic_pole")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> LUNCH_BOX =
            new ItemReg<Item>("lunch_box")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> EMPTY_CAN =
            new ItemReg<Item>("empty_can")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> EMPTY_CAN_BLUE =
            new ItemReg<Item>("empty_can_blue")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> MONOSODIUM_GLUTAMATE =
            new ItemReg<Item>("monosodium_glutamate")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> SALT =
            new ItemReg<Item>("salt")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueDietTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> SOLIDIFY_SLIME_BALL =
            new ItemReg<>("solidify_slime_ball")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> RESEARCH_REPORT =
            new ItemReg<>("research_report")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> RESEARCH_REPORT_THINKING =
            new ItemReg<>("research_report_thinking")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> CIRCUIT_MOTHERBOARD =
            new ItemReg<>("circuit_motherboard")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);
    public static final ItemReg<Item> COLOR_MASTERBATCH =
            new ItemReg<>("color_masterbatch")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<FuelItem> CARBON_DUST =
            new ItemReg<FuelItem>("carbon_dust")
                    .itemType(properties -> new FuelItem(properties, type -> {
                        if (type == null) return 0;
                        if (type.equals(RecipeType.SMELTING)) return 1600;
                        else if (type.equals(RecipeType.BLASTING)) return 2000;
                        else if (type.equals(RecipeType.SMOKING)) return 1400;
                        else return 0;
                    }))
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> IRON_DUST =
            new ItemReg<>("iron_dust")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> COPPER_DUST =
            new ItemReg<>("copper_dust")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Item> ZINC_DUST =
            new ItemReg<>("zinc_dust")
                    .itemType(Item::new)
                    .tab(AllElements.neoKuayueMaterialTab)
                    .submit(AllElements.testRegistry);

    public static final BundledReg<ItemReg<Item>> INCOMPLETE_TRACKS =
            new BundledReg<ItemReg<Item>>("incomplete_tracks")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
                    .element("incomplete_standard_track")
                    .element("incomplete_ballastless_track")
                    .element("incomplete_tieless_track")
                    .element("incomplete_meter_track")
                    .submit(AllElements.testRegistry);

    public static final BundledReg<ItemReg<Item>> CIRCUIT_BLUEPRINTS =
            new BundledReg<ItemReg<Item>>("circuit_blueprint")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
                    .element("head_df11g_circuit_blueprint")
                    .element("head_df21_circuit_blueprint")
                    .element("head_hxd3d_circuit_blueprint")
                    .element("head_cr200j_circuit_blueprint")
                    .element("carriage_25b_circuit_blueprint")
                    .element("carriage_25g_circuit_blueprint")
                    .element("carriage_25k_circuit_blueprint")
                    .element("carriage_25z_circuit_blueprint")
                    .element("carriage_25t_circuit_blueprint")
                    .element("carriage_cr200j_circuit_blueprint")
                    .submit(AllElements.testRegistry);


    public static final BundledReg<ItemReg<Item>> BLUEPRINTS =
            new BundledReg<ItemReg<Item>>("blueprint")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
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
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
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
            new BundledReg<ItemReg<Item>>("circuit_constructing")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
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
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
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
            new BundledReg<ItemReg<Item>>("mould_constructing")
                    .factory(ItemReg::new)
                    .action(reg -> reg.itemType(Item::new))
                    .action(reg -> reg.tab(AllElements.neoKuayueMaterialTab))
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

    public static void invoke() {
        AllDeviceItems.invoke();
    }
}
