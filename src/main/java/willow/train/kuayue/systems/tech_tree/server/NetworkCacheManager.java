package willow.train.kuayue.systems.tech_tree.server;

import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
import java.util.HashMap;

public class NetworkCacheManager {

    public static final NetworkCacheManager MANAGER = new NetworkCacheManager();

    private final HashMap<ServerPlayer, ServerNetworkCache> caches;
    public NetworkCacheManager() {
        caches = new HashMap<>();
    }

    public ServerNetworkCache addCacheFor(ServerPlayer player) {
        ServerNetworkCache cache = new ServerNetworkCache(player);
        caches.put(player, cache);
        return cache;
    }

    public void removeCache(ServerPlayer player) {
        ServerNetworkCache cache = caches.remove(player);
        if (cache == null) return;
        cache.forceStop();
    }

    public void stopAll() {
        caches.forEach((player, serverNetworkCache) ->
                serverNetworkCache.forceStop());
    }

    public @Nullable ServerNetworkCache getCache(ServerPlayer player) {
        return caches.getOrDefault(player, null);
    }
}
