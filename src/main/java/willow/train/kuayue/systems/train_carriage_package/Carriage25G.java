package willow.train.kuayue.systems.train_carriage_package;

import willow.train.kuayue.initial.panel.C25GPanel;

import java.util.List;

public class Carriage25G {

    public static List getList() {
        return List.of(
                C25GPanel.DOOR_25G.item.getItem().getDefaultInstance()

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
