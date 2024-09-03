package willow.train.kuayue.systems.train_carriage_package;

import willow.train.kuayue.initial.panel.C25BPanel;
import willow.train.kuayue.initial.panel.C25Panel;
import willow.train.kuayue.initial.panel.CR200JPanel;

import java.util.List;

public class Carriage25BGZK {

    public static List getList() {
        return List.of(
                // general
                C25Panel.CARPORT_25BGZK.item.getItem().getDefaultInstance()
        );
    }
}
