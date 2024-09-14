package willow.train.kuayue.network;

import kasuga.lib.core.network.S2CPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class ColorTemplateS2CPacket extends S2CPacket {
    private final CompoundTag nbt;
    public ColorTemplateS2CPacket(CompoundTag nbt) {
        this.nbt = nbt;
    }

    public ColorTemplateS2CPacket(FriendlyByteBuf buf) {
        super(buf);
        this.nbt = buf.readNbt();
    }

    @Override
    public void handle(Minecraft minecraft) {
        // TODO: need a hud;
    }

    @Override
    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(nbt);
    }
}
