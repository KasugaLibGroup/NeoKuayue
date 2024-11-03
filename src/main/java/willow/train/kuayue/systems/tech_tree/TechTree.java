package willow.train.kuayue.systems.tech_tree;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

import java.util.Map;

public class TechTree {

    public final String namespace;
    public final String version;
    private final Map<String, TechTreeGroup> groups;
    public TechTree(String namespace, String version, Map<String, TechTreeGroup> groups) {
        this.version = version;
        this.namespace = namespace;
        this.groups = groups;
    }

    public TechTree(String namespace, JsonObject json) {
        this.namespace = namespace;
        this.version = json.get("version").getAsString();
        this.groups  = new HashMap<>();
        JsonObject groupsJson = json.getAsJsonObject("groups");
        for (Map.Entry<String, JsonElement> entry : groupsJson.entrySet()) {
            TechTreeGroup group = new TechTreeGroup(this, entry.getKey(), entry.getValue().getAsJsonObject());
            groups.put(group.identifier, group);
        }
    }

    public Map<String, TechTreeGroup> getGroups() {
        return groups;
    }
}
