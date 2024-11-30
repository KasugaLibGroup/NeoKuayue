package willow.train.kuayue.network.c2s;

import kasuga.lib.core.network.C2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.systems.tech_tree.server.NetworkCacheManager;
import willow.train.kuayue.systems.tech_tree.server.ServerNetworkCache;

public class TechTreeEOFC2SPacket extends C2SPacket {

    public TechTreeEOFC2SPacket() {}

    public TechTreeEOFC2SPacket(FriendlyByteBuf buf) {}
    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.getSender();
            if (player == null) return;
            ServerNetworkCache cache = NetworkCacheManager.MANAGER.getCache(player);
            if (cache == null) return;
            cache.nextTree();
        });
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {}
}
