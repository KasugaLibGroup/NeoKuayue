package willow.train.kuayue.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.network.c2s.*;
import willow.train.kuayue.network.c2s.signs.CarriageNoSignUpdatePacket;
import willow.train.kuayue.network.c2s.signs.CarriageTypeSignUpdatePacket;
import willow.train.kuayue.network.c2s.signs.LaqueredBoardPacket;
import willow.train.kuayue.network.c2s.signs.TrainSpeedSignUpdatePacket;

import java.util.function.Function;

public class KuayueNetworkHandler {

    private static SimpleChannel INSTANCE;

    private static int ID = 0;

    public static void registerPackets() {
        INSTANCE =
                NetworkRegistry.newSimpleChannel(
                        new ResourceLocation(Kuayue.MODID, "main"),
                        () -> "1.0",
                        s -> true,
                        s -> true);

        registerC2S(CarriageTypeSignUpdatePacket.class, CarriageTypeSignUpdatePacket::new);
        registerC2S(CarriageNoSignUpdatePacket.class, CarriageNoSignUpdatePacket::new);
        registerC2S(LaqueredBoardPacket.class, LaqueredBoardPacket::new);
        registerC2S(TrainSpeedSignUpdatePacket.class, TrainSpeedSignUpdatePacket::new);
    }

    private static <T extends KuayuePacket> void registerC2S(
            Class<T> type, Function<FriendlyByteBuf, T> decoder) {
        INSTANCE.messageBuilder(type, ID++)
                .encoder(KuayuePacket::encode)
                .decoder(decoder)
                .consumerNetworkThread(((first, second) -> first.handle(second.get()) || false))
                .add();
    }

    public static void sendToClient(KuayuePacket packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void boardcastToClientsWatching(
            KuayuePacket packet, ServerLevel level, ChunkPos pos) {
        level.getChunkSource()
                .chunkMap
                .getPlayers(pos, false)
                .forEach(
                        player -> {
                            sendToClient(packet, player);
                        });
    }

    public static void sendToServer(KuayuePacket packet) {
        INSTANCE.sendToServer(packet);
    }
}
