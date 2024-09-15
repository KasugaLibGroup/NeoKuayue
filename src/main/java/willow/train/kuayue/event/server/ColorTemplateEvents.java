package willow.train.kuayue.event.server;

import kasuga.lib.core.util.Envs;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.initial.ClientInit;

public class ColorTemplateEvents {

    @SubscribeEvent
    public static void saveEvent(LevelEvent.Save event) {
        // if (Envs.isClient())
            // ClientInit.COLOR_TEMPLATES.writeToFile();
    }

    @SubscribeEvent
    public static void loadEvent(LevelEvent.Load event) {
        // if (Envs.isClient())
            // ClientInit.COLOR_TEMPLATES.readFromFile();
    }

    @SubscribeEvent
    public static void unloadEvent(LevelEvent.Unload event) {
        if (Envs.isClient()) {
            // ClientInit.COLOR_TEMPLATES.writeToFile();
            // ClientInit.COLOR_TEMPLATES.clear();
        }
    }
}
