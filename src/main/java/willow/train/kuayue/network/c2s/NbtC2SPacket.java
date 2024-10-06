package willow.train.kuayue.network.c2s;

import kasuga.lib.core.network.C2SPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class NbtC2SPacket extends C2SPacket {
    private final BlockPos pos;
    private final CompoundTag nbt;
    public NbtC2SPacket(BlockPos pos, BlockEntity be) {
        super();
        this.pos = pos;
        this.nbt = be.saveWithoutMetadata();
    }

    public NbtC2SPacket(BlockPos pos, CompoundTag nbt) {
        super();
        this.pos = pos;
        this.nbt = nbt;
    }

    public NbtC2SPacket(FriendlyByteBuf buf) {
        super(buf);
        this.pos = buf.readBlockPos();
        this.nbt = buf.readNbt();
    }
    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if (player == null) return;
        ServerLevel level = player.getLevel();
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity != null) {
            entity.load(nbt);
            entity.setChanged();
        }
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf
                .writeBlockPos(pos)
                .writeNbt(nbt);
    }
}
