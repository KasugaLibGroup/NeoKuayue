package willow.train.kuayue.event.both;

import kasuga.lib.core.util.Envs;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.systems.tech_tree.player.PlayerDataDist;
import willow.train.kuayue.systems.tech_tree.player.PlayerDataManager;

public class PlayerDataEvent {

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) return;
        PlayerDataDist.DIST.loadFromDisk((ServerLevel) event.getLevel());
    }

    @SubscribeEvent
    public static void onLevelSave(LevelEvent.Save event) {
        if (event.getLevel().isClientSide()) return;
        PlayerDataDist.DIST.saveToDisk((ServerLevel) event.getLevel());
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) return;
        PlayerDataManager.MANAGER.createPlayerData(player);
    }
}
