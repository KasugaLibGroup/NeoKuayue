package willow.train.kuayue.systems.tech_tree.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

import java.util.Map;

public class TechTreeData {

    public final String namespace;
    public final String version;
    private final Map<String, TechTreeGroupData> groups;
    public TechTreeData(String namespace, String version, Map<String, TechTreeGroupData> groups) {
        this.version = version;
        this.namespace = namespace;
        this.groups = groups;
    }

    public TechTreeData(String namespace, JsonObject json) {
        this.namespace = namespace;
        this.version = json.get("version").getAsString();
        this.groups  = new HashMap<>();
        JsonObject groupsJson = json.getAsJsonObject("groups");
        for (Map.Entry<String, JsonElement> entry : groupsJson.entrySet()) {
            TechTreeGroupData group = new TechTreeGroupData(this, entry.getKey(), entry.getValue().getAsJsonObject());
            groups.put(group.identifier, group);
        }
    }

    public Map<String, TechTreeGroupData> getGroups() {
        return groups;
    }
}
