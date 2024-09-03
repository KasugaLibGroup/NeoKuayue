package willow.train.kuayue.systems.train_carriage_package;

import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.panel.C25BPanel;

import java.util.List;

public class Carriage25B {

    public static List getList() {
        return List.of(
                // proprietary
                C25BPanel.PANEL_BOTTOM_25B.item.getItem().getDefaultInstance()
        );
    }
}
