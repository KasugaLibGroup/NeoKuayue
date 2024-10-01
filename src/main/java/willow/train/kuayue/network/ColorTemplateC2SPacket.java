package willow.train.kuayue.network;

import kasuga.lib.core.network.C2SPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.systems.editable_panel.ColorTemplate;

public class ColorTemplateC2SPacket extends C2SPacket {
    private final CompoundTag nbt;
    public ColorTemplateC2SPacket(ColorTemplate template) {
        nbt = new CompoundTag();
        template.write(nbt);
    }
    public ColorTemplateC2SPacket(FriendlyByteBuf buf) {
        super(buf);
        nbt = buf.readNbt();
    }
    @Override
    public void handle(NetworkEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;
            ServerLevel level = player.getLevel();
            // Broadcast to all clients;
            // AllPackets.TEMPLATE.sendToClient(new ColorTemplateS2CPacket(this.nbt), player);
            AllPackets.TEMPLATE.boardcastToClients(new ColorTemplateS2CPacket(this.nbt), level, player.getOnPos());
        });
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }
}
