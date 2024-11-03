package willow.train.kuayue.systems.tech_tree;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class OnUnlockContext {
    
    public final String description;
    private final NodeLocation[] unlockNodes;
    private final ResourceLocation[] unlockAdvancements;
    private final HashMap<ResourceLocation, Integer> items;
    
    public OnUnlockContext(TechTreeGroup group, JsonObject json) {
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

        items = new HashMap<>();
        if (json.has("items") && json.get("items").isJsonObject()) {
            JsonObject obj = json.getAsJsonObject("items");
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                items.put(new ResourceLocation(entry.getKey()), entry.getValue().getAsInt());
            }
        }

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
}
