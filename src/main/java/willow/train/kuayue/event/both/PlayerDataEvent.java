package willow.train.kuayue.event.both;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.initial.AllItems;
import willow.train.kuayue.systems.tech_tree.player.PlayerDataDist;
import willow.train.kuayue.systems.tech_tree.player.PlayerDataManager;
import willow.train.kuayue.systems.tech_tree.server.NetworkCacheManager;
import willow.train.kuayue.systems.tech_tree.server.ServerNetworkCache;
import willow.train.kuayue.systems.tech_tree.server.TechTreeManager;

import java.util.List;

public class PlayerDataEvent {

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (event.getLevel().isClientSide()) return;
        PlayerDataDist.DIST.loadFromDisk((ServerLevel) event.getLevel());
    }

    @SubscribeEvent
    public static void onLevelSave(LevelEvent.Save event) {
        if (event.getLevel().isClientSide()) return;
        PlayerDataDist.DIST.saveToDisk((ServerLevel) event.getLevel());
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) return;
        NetworkCacheManager.MANAGER.stopAll();
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.level.isClientSide) return;

        // TODO: need to be fixed
//        AllPackets.TECH_TREE_CHANNEL.sendToClient(
//                new DistributeTechTreePacket(),
//                (ServerPlayer) player
//        );
//        TechTreeManager.sendAllPayloads((ServerPlayer) player);
        ServerNetworkCache cache = NetworkCacheManager.MANAGER.addCacheFor((ServerPlayer) player);
        TechTreeManager.MANAGER.trees().forEach((s, tree) -> cache.enqueueTree(tree));

        if (PlayerDataManager.MANAGER.containsPlayerData(player)) return;
        PlayerDataManager.MANAGER.createPlayerData(player);
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        if (player.level.isClientSide) return;

        NetworkCacheManager.MANAGER.removeCache((ServerPlayer) player);
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack itemStack = new ItemStack(AllItems.SALT.getItem(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    itemStack,
                    10, 8, 0.02F
            ));
        }
    }
}
