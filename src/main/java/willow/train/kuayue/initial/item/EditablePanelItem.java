package willow.train.kuayue.initial.item;

import kasuga.lib.registrations.common.ItemReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.item.editable_panels.ColoredBrush;

public class EditablePanelItem {

    public static final ItemReg<ColoredBrush> COLORED_BRUSH =
            new ItemReg<ColoredBrush>("colored_brush")
                    .itemType(ColoredBrush::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
