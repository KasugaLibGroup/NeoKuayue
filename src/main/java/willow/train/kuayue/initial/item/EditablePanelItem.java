package willow.train.kuayue.initial.item;

import kasuga.lib.registrations.common.ItemReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.item.editable_panels.ColoredBrush;
import willow.train.kuayue.item.editable_panels.LaqueredBoard;
import willow.train.kuayue.item.editable_panels.Sticker;

public class EditablePanelItem {

    public static final ItemReg<ColoredBrush> COLORED_BRUSH =
            new ItemReg<ColoredBrush>("colored_brush")
                    .itemType(ColoredBrush::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<LaqueredBoard> LAQUERED_BOARD =
            new ItemReg<LaqueredBoard>("laquered_board_item")
                    .itemType(LaqueredBoard::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final ItemReg<Sticker> STICKER =
            new ItemReg<Sticker>("sticker_item")
                    .itemType(Sticker::new)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
