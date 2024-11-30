package willow.train.kuayue.systems.device;

import kasuga.lib.core.menu.base.GuiMenuType;
import willow.train.kuayue.systems.device.driver.devices.CIRMenu;
import willow.train.kuayue.systems.device.driver.devices.LKJ2000Menu;

public class AllDevicesMenus {
    public static final GuiMenuType<LKJ2000Menu> LKJ2000 = GuiMenuType.createType(LKJ2000Menu::new);
    public static final GuiMenuType<CIRMenu> CIR = GuiMenuType.createType(CIRMenu::new);

}
