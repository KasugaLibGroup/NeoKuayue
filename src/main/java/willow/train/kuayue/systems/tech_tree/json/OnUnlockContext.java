package willow.train.kuayue.systems.tech_tree.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kasuga.lib.core.util.ComponentHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import java.util.HashMap;
import java.util.Map;

public class OnUnlockContext {
    
    public final String description;
    private final NodeLocation[] unlockNodes;
    private final ResourceLocation[] unlockAdvancements;
    private final ItemContext[] items;
    
    public OnUnlockContext(TechTreeGroupData group, JsonObject json) {
        description = json.get("description").getAsString();

        if (json.has("advancements") && json.get("advancements").isJsonArray()) {
            JsonArray array = json.getAsJsonArray("advancements");
            unlockAdvancements = new ResourceLocation[array.size()];
            for (int i = 0; i < array.size(); i++) {
                unlockAdvancements[i] = new ResourceLocation(array.get(i).getAsString());
            }
        } else {
            unlockAdvancements = new ResourceLocation[0];
        }

        if (json.has("items") && json.get("items").isJsonObject()) {
            JsonObject obj = json.getAsJsonObject("items");
            items = new ItemContext[obj.size()];
            int counter = 0;
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                items[counter] = new ItemContext(entry);
                counter++;
            }
        } else items = new ItemContext[0];

        if (json.has("nodes") && json.get("nodes").isJsonArray()) {
            JsonArray array = json.getAsJsonArray("nodes");
            unlockNodes = new NodeLocation[array.size()];
            for (int i = 0; i < array.size(); i++) {
                unlockNodes[i] = new NodeLocation(group.tree.namespace, group.identifier, array.get(i).getAsString());
            }
        } else {
            unlockNodes = new NodeLocation[0];
        }
    }

    public ItemContext[] getItems() {
        return items;
    }

    public NodeLocation[] getUnlockNodes() {
        return unlockNodes;
    }

    public ResourceLocation[] getUnlockAdvancements() {
        return unlockAdvancements;
    }

    public Component getDescription() {
        return ComponentHelper.translatable(description);
    }
}
