package willow.train.kuayue.systems.train_carriage_package;

import willow.train.kuayue.initial.panel.CR200JPanel;

import java.util.List;

public class CarriageCR200j {
    public static List getList() {
        return List.of(
                CR200JPanel.DOOR_CR200J.item.getItem().getDefaultInstance()
        );
    }
}
