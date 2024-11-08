package willow.train.kuayue.systems.tech_tree.player;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Getter
@OnlyIn(Dist.CLIENT)
public class ClientPlayerData {

    public static PlayerData data = null;

    public static void updateDataFromNetwork(FriendlyByteBuf buf) {
        if (Minecraft.getInstance().player == null) return;
        if (!buf.readUUID().equals(Minecraft.getInstance().player.getUUID())) return;
        if (data == null) {
            data = new PlayerData(buf.readUUID());
        }
        data.fromNetwork(buf);
    }
}
