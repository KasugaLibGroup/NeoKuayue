package willow.train.kuayue.network.c2s;

import kasuga.lib.core.network.C2SPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class DiscardChangeC2SPacket extends C2SPacket {

    public final BlockPos pos;
    public DiscardChangeC2SPacket(BlockPos pos) {
        this.pos = pos;
    }

    public DiscardChangeC2SPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if (player == null) return;
        ServerLevel level = (ServerLevel) player.level();
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity != null) {
            entity.setChanged();
            level.getChunkSource().blockChanged(pos);
        }
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(pos);
    }
}
