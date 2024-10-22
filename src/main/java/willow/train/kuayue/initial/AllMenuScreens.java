package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.MenuReg;
import willow.train.kuayue.systems.editable_panel.EPScreen;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;

public class AllMenuScreens {

    public static final MenuReg<EditablePanelEditMenu, EPScreen> EDITABLE_PANEL =
            new MenuReg<EditablePanelEditMenu, EPScreen>("editable_panel")
                    .withMenuAndScreen(EditablePanelEditMenu::new, () -> EPScreen::new)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
