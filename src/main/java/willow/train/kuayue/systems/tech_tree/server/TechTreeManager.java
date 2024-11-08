package willow.train.kuayue.systems.tech_tree.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.*;
import org.jetbrains.annotations.NotNull;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.json.TechTreeData;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TechTreeManager implements ResourceManagerReloadListener {
    private final HashMap<String, TechTree> trees;
    @Getter
    private final Set<String> namespaces;
    public static final TechTreeManager MANAGER = new TechTreeManager();

    protected TechTreeManager() {
        trees = new HashMap<>();
        namespaces = new HashSet<>();
    }

    public HashMap<String, TechTree> trees() {
        return trees;
    }

    public @Nullable TechTreeNode getNode(NodeLocation location) {
        String namespace = location.getNamespace();
        TechTree tree = trees.getOrDefault(namespace, null);
        if (tree == null) return null;
        return tree.getNodes().getOrDefault(location, null);
    }

    public boolean containsNode(NodeLocation location) {
        return getNode(location) != null;
    }

    public @Nullable TechTree getTree(String namespace) {
        return trees.getOrDefault(namespace, null);
    }

    public boolean containsTree(String namespace) {
        return trees.containsKey(namespace);
    }

    public @Nullable TechTreeGroup getGroup(ResourceLocation groupLocation) {
        TechTree tree = getTree(groupLocation.getNamespace());
        if (tree == null) return null;
        return tree.getGroups().getOrDefault(groupLocation.getPath(), null);
    }

    public boolean containsGroup(ResourceLocation location) {
        return getGroup(location) != null;
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        this.namespaces.clear();
        this.namespaces.addAll(resourceManager.getNamespaces());
    }

    public void loadData(@NotNull ResourceManager manager) {
        namespaces.clear();
        namespaces.addAll(manager.getNamespaces());
        for (String namespace : namespaces) {
            Optional<Resource> resource = manager.getResource(
                    new ResourceLocation(namespace, "tech_tree/tech_tree.json")
            );
            if (resource.isEmpty()) continue;
            try {
                JsonElement element = JsonParser.parseReader(resource.get().openAsReader());
                if (!element.isJsonObject()) continue;
                JsonObject object = element.getAsJsonObject();
                TechTreeData data = new TechTreeData(namespace, object);
                TechTree tree = new TechTree(data);
                tree.grepNbt(manager);
                this.trees.put(namespace, tree);
            } catch (IOException e) {
                Kuayue.LOGGER.error("Failed to read tech tree json: " + namespace, e);
            } catch (NullPointerException e) {
                Kuayue.LOGGER.error("Failed to parse tech tree json: " + namespace, e);
            } catch (Exception e) {
                Kuayue.LOGGER.error("Why? Failed to read tech tree json: " + namespace, e);
            }
        }
        connectNodes();
    }

    public void connectNodes() {
        trees.forEach((namespace, tree) -> {
            tree.compileConnections();
        });
    }

}
