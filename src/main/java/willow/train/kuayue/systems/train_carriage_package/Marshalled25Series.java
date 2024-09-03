package willow.train.kuayue.systems.train_carriage_package;

import willow.train.kuayue.initial.panel.C25BPanel;
import willow.train.kuayue.initial.panel.C25Panel;

import java.util.List;

public class Marshalled25Series {

    public static List getList() {
        return List.of(
                C25Panel.PANEL_BOTTOM_LINE_MARSHALLED_25.item.getItem().getDefaultInstance()

                // general
                //
                // BlockInit.ORIGINAL_COLOR_WINDOW_25.get().asItem().getDefaultInstance(),
                //
                // BlockInit.ORIGINAL_COLOR_WINDOW_25_SEALED.get().asItem().getDefaultInstance(),
                //                    BlockInit.CARPORT_25BGZK.get().asItem().getDefaultInstance(),
                //
                // BlockInit.CARPORT_25BGZKT_CENTRE.get().asItem().getDefaultInstance()
                );
    }
}
