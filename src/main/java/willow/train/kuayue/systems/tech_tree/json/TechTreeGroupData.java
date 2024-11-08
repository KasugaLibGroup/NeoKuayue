package willow.train.kuayue.systems.tech_tree.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kasuga.lib.core.util.ComponentHelper;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TechTreeGroupData {

    public final TechTreeData tree;

    public final String identifier;
    private final String descriptionTranslationKey, nameTranslationKey;
    private final ItemContext icon;
    private final NodeLocation root;
    private final HashMap<NodeLocation, TechTreeNodeData> nodes;
    private final @Nullable HideContext hide;

    @Getter
    private final @Nullable OnUnlockContext unlock;
    public TechTreeGroupData(TechTreeData tree, String identifier, JsonObject json) {
        this.tree = tree;
        this.identifier = identifier;
        this.nameTranslationKey = json.get("name").getAsString();
        this.descriptionTranslationKey = json.get("description").getAsString();
        this.icon = new ItemContext(new ResourceLocation(json.get("icon").getAsString()));
        this.root = new NodeLocation(this.tree.namespace, this.identifier, json.get("root_node").getAsString());

        if (json.has("hide") && json.get("hide").isJsonObject()) {
            hide = new HideContext(this, json.getAsJsonObject("hide"));
        } else hide = null;

        if (json.has("on_unlock") && json.get("on_unlock").isJsonObject()) {
            unlock = new OnUnlockContext(this, json.getAsJsonObject("on_unlock"));
        } else unlock = null;

        nodes = new HashMap<>();
        if (!json.has("nodes") || !json.get("nodes").isJsonObject()) {
            return;
        }
        JsonObject nodesJson = json.getAsJsonObject("nodes");
        for (Map.Entry<String, JsonElement> entry : nodesJson.entrySet()) {
            TechTreeNodeData node = new TechTreeNodeData(this, entry.getKey(), entry.getValue().getAsJsonObject());
            nodes.put(node.getLocation(), node);
        }
    }

    public void loadAllNbt(ResourceManager manager) {
        this.icon.updateNbt(manager);
        if (this.unlock != null) this.unlock.loadAllNbt(manager);
        nodes.forEach((loc, node) -> {
            node.loadAllNbt(manager);
        });
    }

    public boolean isHide() {
        return hide != null;
    }

    @Nullable
    public HideContext getHide() {
        return hide;
    }

    public ResourceLocation getLocation() {
        return new ResourceLocation(this.tree.namespace, this.identifier);
    }

    public String getTitle() {
        return nameTranslationKey;
    }

    public String getDescription() {
        return descriptionTranslationKey;
    }

    public ItemStack getIcon() {
        return icon.getAsLogo();
    }

    public NodeLocation getRootLocation() {
        return root;
    }

    public TechTreeNodeData getNode(NodeLocation location) {
        return nodes.getOrDefault(location, null);
    }

    public boolean containsNode(NodeLocation location) {
        return getNode(location) != null;
    }

    public HashMap<NodeLocation, TechTreeNodeData> getNodes() {
        return nodes;
    }
}
