package willow.train.kuayue.network.c2s;

import kasuga.lib.core.network.C2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.network.s2c.TechTreeHandShakeS2CPacket;
import willow.train.kuayue.systems.tech_tree.NetworkState;
import willow.train.kuayue.systems.tech_tree.server.NetworkCacheManager;
import willow.train.kuayue.systems.tech_tree.server.ServerNetworkCache;

public class TechTreeHandShakeC2SPacket extends C2SPacket {

    private final NetworkState state;
    public TechTreeHandShakeC2SPacket(NetworkState state) {
        this.state = state;
    }

    public TechTreeHandShakeC2SPacket(FriendlyByteBuf buf) {
        state = NetworkState.fromCode(buf.readInt());
    }
    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerNetworkCache cache = NetworkCacheManager.MANAGER.getCache(player);
            if (cache == null) return;
            cache.collectClientNetworkState(this.state);
        });
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(state.getCode());
    }
}
