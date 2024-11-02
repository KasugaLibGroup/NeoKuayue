package willow.train.kuayue.systems.device;

import kasuga.lib.registrations.common.ItemReg;
import net.minecraft.world.item.Item;
import willow.train.kuayue.initial.AllElements;

public class AllDeviceItems {
    public static ItemReg<Item> ITEM_LOGO = new ItemReg<>("signal_logo")
            .itemType(Item::new)
            .submit(AllElements.testRegistry);

    public static void invoke() {}
}
