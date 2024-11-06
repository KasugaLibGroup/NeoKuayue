package willow.train.kuayue.systems.tech_tree.player;

import kasuga.lib.core.util.Envs;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerDataManager {
    public static final PlayerDataManager MANAGER = new PlayerDataManager();

    private final Set<Advancement> advancements;
    private PlayerDataManager() {
        this.advancements = new HashSet<>();
    }

    public void loadAdvancements(Collection<Advancement> list) {
        this.advancements.clear();
        this.advancements.addAll(list);
    }

    public Set<Advancement> getAdvancements() {
        return advancements;
    }
}
