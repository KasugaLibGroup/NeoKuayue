package willow.train.kuayue.network.c2s;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import kasuga.lib.core.network.C2SPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class SeatDismountPacket extends C2SPacket {
    public SeatDismountPacket() {
    }
    public SeatDismountPacket(FriendlyByteBuf byteBuf){}
    @Override
    public void handle(NetworkEvent.Context context) {
        ServerPlayer player = context.getSender();
        if(player == null)
            return;
        ServerLevel level = player.getLevel();
        if(player.getVehicle() instanceof AbstractContraptionEntity){
            player.stopRiding();
        }
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {}
}
