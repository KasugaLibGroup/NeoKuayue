package willow.train.kuayue.event.server;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.systems.tech_tree.server.TechTreeManager;

public class ServerResourceReloadEvent {

    @SubscribeEvent
    public static void onServerResourceReload(ServerStartedEvent event) {
        ResourceManager manager = event.getServer().getServerResources().resourceManager();
        TechTreeManager.MANAGER.loadData(manager);
    }
}
