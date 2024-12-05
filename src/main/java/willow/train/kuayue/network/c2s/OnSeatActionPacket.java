package willow.train.kuayue.network.c2s;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import kasuga.lib.core.network.C2SPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.block.seat.YZSeatBlock;
import willow.train.kuayue.systems.device.driver.seat.DriverSeatActionType;
import willow.train.kuayue.systems.device.driver.seat.IContraptionActionSeatBlock;
import willow.train.kuayue.systems.device.driver.seat.IDynamicSitBlock;

public class OnSeatActionPacket extends C2SPacket {
    private final DriverSeatActionType actionType;

    public OnSeatActionPacket(DriverSeatActionType actionType) {
        this.actionType = actionType;
    }

    public OnSeatActionPacket(FriendlyByteBuf byteBuf) {
        this.actionType = DriverSeatActionType.fromName(byteBuf.readUtf());
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if(player == null)
            return;
        if(!(player.getVehicle() instanceof AbstractContraptionEntity contraptionEntity))
            return;
        BlockPos pos = contraptionEntity.getContraption().getSeatOf(player.getUUID());
        if(pos == null)
            return;
        Block block = contraptionEntity.getContraption().getBlocks().get(pos).state.getBlock();
        if(!(block instanceof IContraptionActionSeatBlock seatActionBlock) || (!(block instanceof YZSeatBlock)))
            return;

        seatActionBlock.onAction(
                player,
                contraptionEntity.getContraption(),
                pos,
                contraptionEntity.getContraption().getBlocks().get(pos),
                actionType
        );
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUtf(actionType.getSerializedName());
    }
}
