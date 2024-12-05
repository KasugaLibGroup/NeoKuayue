package willow.train.kuayue.systems.device.driver.seat;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import kasuga.lib.core.menu.base.GuiMenu;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public interface InteractiveBehaviour {
    public List<MenuEntry> getMenusOf(MovementContext context);

    public record MenuEntry(Component displayName, Supplier<Boolean> isAvailable, Supplier<GuiMenu> menuSupplier){}
}
