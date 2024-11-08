package willow.train.kuayue.systems.tech_tree.player;

import kasuga.lib.core.base.NbtSerializable;
import kasuga.lib.core.util.Envs;
import lombok.Getter;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

@Getter
public class PlayerDataManager extends SavedData implements NbtSerializable {

    public static final PlayerDataManager MANAGER = new PlayerDataManager();

    private final Set<Advancement> advancements;

    private final HashMap<UUID, PlayerData> playerData;

    private PlayerDataManager() {
        this.advancements = new HashSet<>();
        this.playerData = new HashMap<>();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag) {
        this.write(compoundTag);
        return compoundTag;
    }

    public @Nullable PlayerData getPlayerData(UUID id) {
        return playerData.getOrDefault(id, null);
    }

    public @Nullable PlayerData getPlayerData(Player player) {
        return getPlayerData(player.getUUID());
    }

    public PlayerData getOrCreatePlayerData(UUID id) {
        PlayerData result = playerData.getOrDefault(id, null);
        if (result == null) result = new PlayerData(id);
        return result;
    }

    public PlayerData getOrCreatePlayerData(Player player) {
        return getOrCreatePlayerData(player.getUUID());
    }

    public PlayerData createPlayerData(UUID id) {
        PlayerData result = new PlayerData(id);
        playerData.put(id, result);
        return result;
    }

    public PlayerData createPlayerData(Player player) {
        return createPlayerData(player.getUUID());
    }

    public boolean containsPlayerData(UUID uuid) {
        return playerData.containsKey(uuid);
    }

    public boolean containsPlayerData(Player player) {
        return containsPlayerData(player.getUUID());
    }

    public void loadAdvancements(Collection<Advancement> list) {
        this.advancements.clear();
        this.advancements.addAll(list);
    }

    public void write(CompoundTag nbt) {
        playerData.forEach((id, data) -> {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("id", id);
            CompoundTag dataTag = new CompoundTag();
            data.write(dataTag);
            tag.put("data", dataTag);
            nbt.put(id.toString(), tag);
        });
    }

    public void read(CompoundTag nbt) {
        for (String key : nbt.getAllKeys()) {
            CompoundTag tag = nbt.getCompound(key);
            UUID id = tag.getUUID("id");
            CompoundTag data = tag.getCompound("data");
            PlayerData playerData = new PlayerData(id);
            playerData.read(data);
            this.playerData.put(id, playerData);
        }
    }

}
