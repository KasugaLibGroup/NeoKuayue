package willow.train.kuayue.systems.tech_tree.player;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.gui.screens.advancements.AdvancementTab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;
import willow.train.kuayue.mixins.mixin.AccessorPlayerAdvancement;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.json.HideContext;
import willow.train.kuayue.systems.tech_tree.server.TechTreeGroup;
import willow.train.kuayue.systems.tech_tree.server.TechTreeManager;
import willow.train.kuayue.systems.tech_tree.server.TechTreeNode;

import javax.annotation.Nullable;
import java.util.*;

public class PlayerData {
    public final UUID playerID;
    public final Set<NodeLocation> unlocked;
    public PlayerData(Player player) {
        this.playerID = player.getUUID();
        unlocked = new HashSet<>();
    }

    public void read(CompoundTag nbt) {
        ListTag list = nbt.getList("data", Tag.TAG_STRING);
        unlocked.clear();
        list.forEach(tag -> unlocked.add(new NodeLocation(tag.getAsString())));
    }

    public void write(CompoundTag nbt) {
        ListTag list = new ListTag();
        unlocked.forEach(loc -> list.add(StringTag.valueOf(loc.toString())));
        nbt.put("data", list);
    }

    public boolean canUnlock(ServerPlayer player, TechTreeGroup group) {
        if (!canBeSeen(player, group.getHideContext())) return false;
        for (TechTreeNode node : group.getPrev()) {
            if (!unlocked.contains(node.getLocation())) return false;
        }
        return true;
    }

    public boolean canUnlock(ServerPlayer player, TechTreeNode node) {
        if (!canBeSeen(player, node.getHideContext())) return false;
        for (TechTreeNode n : node.getPrev()) {
            if (!unlocked.contains(n.getLocation())) return false;
        }
        Inventory inventory = player.getInventory();

        return true;
    }

    public boolean unlock(ServerPlayer player, TechTreeNode node) {
        if (!canUnlock(player, node)) return false;
        unlocked.add(node.getLocation());
        return false;
    }

    public boolean canBeSeen(ServerPlayer player, @Nullable HideContext hide) {
        if (hide == null) return true;
        // check namespaces;
        HashSet<String> needNamespaces = new HashSet<>(Arrays.asList(hide.getNeedNamespaces()));
        TechTreeManager.MANAGER.getNamespaces().forEach(needNamespaces::remove);
        if (!needNamespaces.isEmpty()) return false;

        // check advancements
        PlayerAdvancements adv = player.getAdvancements();
        Map<Advancement, AdvancementProgress> allAdvancements = ((AccessorPlayerAdvancement) adv).getAdvancements();
        Set<ResourceLocation> needAdvancements = new HashSet<>(Arrays.asList(hide.getNeedAdvancements()));
        allAdvancements.forEach((a, ap) -> {
            if (needAdvancements.contains(a.getId()) && ap.isDone()) needAdvancements.remove(a.getId());
        });
        if (!needAdvancements.isEmpty()) return false;

        // check nodes;
        HashSet<NodeLocation> needNodes = new HashSet<>(Arrays.asList(hide.getNeedNodes()));
        unlocked.forEach(needNodes::remove);
        return needNodes.isEmpty();
    }
}
