package willow.train.kuayue.systems.tech_tree;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;

public class HideContext {
    private final NodeLocation[] needNodes;
    private final ResourceLocation[] needAdvancements;
    private final String[] needNamespaces;
    public HideContext(TechTreeGroup group, JsonObject json) {
        if (!json.has("condition") ||
                !json.get("condition").isJsonObject()) {
            needNodes = new NodeLocation[0];
            needAdvancements = new ResourceLocation[0];
            needNamespaces = new String[0];
            return;
        }

        JsonObject condition = json.getAsJsonObject("condition");
        if (condition.has("advancements") && condition.get("advancements").isJsonArray()) {
            JsonArray array = condition.getAsJsonArray("advancements");
            needAdvancements = new ResourceLocation[array.size()];
            for (int i = 0; i < array.size(); i++) {
                needAdvancements[i] = new ResourceLocation(array.get(i).getAsString());
            }
        } else {
            needAdvancements = new ResourceLocation[0];
        }

        if (condition.has("contains_namespaces") && condition.get("contains_namespaces").isJsonArray()) {
            JsonArray array = condition.getAsJsonArray("contains_namespaces");
            needNamespaces = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                needNamespaces[i] = array.get(i).getAsString();
            }
        } else {
            needNamespaces = new String[0];
        }

        if (condition.has("nodes") && condition.get("nodes").isJsonArray()) {
            JsonArray array = condition.getAsJsonArray("nodes");
            needNodes = new NodeLocation[array.size()];
            for (int i = 0; i < array.size(); i++) {
                needNodes[i] = new NodeLocation(group.tree.namespace, group.identifier, array.get(i).getAsString());
            }
        } else {
            needNodes = new NodeLocation[0];
        }
    }
}
