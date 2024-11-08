package willow.train.kuayue.systems.tech_tree.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kasuga.lib.core.util.ComponentHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.systems.tech_tree.NodeLocation;
import willow.train.kuayue.systems.tech_tree.NodeType;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TechTreeNodeData {

    public final TechTreeGroupData group;
    private final String identifier, description;
    private final @Nullable HideContext hide;
    private final @Nullable OnUnlockContext unlock;
    private final NodeLocation[] nextNodes;
    private final ResourceLocation[] nextGroups;
    private final NodeType type;
    private final ItemContext logo;
    private final int exp, level;
    private final ItemContext[] itemConsume, blueprints, itemReward;
    private final ResourceLocation[] advancements;
    public TechTreeNodeData(TechTreeGroupData group, String identifier, JsonObject jsonObject) {
        this.group = group;
        this.identifier = identifier;

        if (!jsonObject.has("hide") ||
                !jsonObject.get("hide").isJsonObject()) {
            hide = null;
        } else {
            hide = new HideContext(this.group, jsonObject.getAsJsonObject("hide"));
        }

        if (!jsonObject.has("on_unlock") ||
                !jsonObject.get("on_unlock").isJsonObject()) {
            unlock = null;
        } else {
            unlock = new OnUnlockContext(group, jsonObject.getAsJsonObject("on_unlock"));
        }

        JsonArray nextNodeArray = jsonObject.getAsJsonArray("next_nodes");
        nextNodes = new NodeLocation[nextNodeArray.size()];
        for (int i = 0; i < nextNodeArray.size(); i++) {
            nextNodes[i] = new NodeLocation(getNamespaceName(), getGroupName(), nextNodeArray.get(i).getAsString());
        }

        if (jsonObject.has("next_groups") && jsonObject.get("next_groups").isJsonArray()) {
            JsonArray array = jsonObject.getAsJsonArray("next_groups");
            nextGroups = new ResourceLocation[array.size()];
            for (int i = 0; i < array.size(); i++) {
                nextGroups[i] = new ResourceLocation(array.get(i).getAsString());
            }
        } else {
            nextGroups = new ResourceLocation[0];
        }

        description = jsonObject.get("description").getAsString();
        if (jsonObject.has("type")) {
            this.type = NodeType.getType(jsonObject.get("type").getAsString());
        } else {
            this.type = NodeType.PLAIN;
        }

        logo = new ItemContext(new ResourceLocation(jsonObject.get("logo").getAsString()));
        exp = jsonObject.get("exp").getAsInt();
        level = jsonObject.get("level").getAsInt();

        JsonObject consumption = jsonObject.getAsJsonObject("item_consume");
        itemConsume = new ItemContext[consumption.size()];
        int consumeCounter = 0;
        for (Map.Entry<String, JsonElement> entry : consumption.entrySet()) {
            itemConsume[consumeCounter] = new ItemContext(entry);
            consumeCounter++;
        }

        if (jsonObject.has("advancements") && jsonObject.get("advancements").isJsonArray()) {
            JsonArray array = jsonObject.getAsJsonArray("advancements");
            advancements = new ResourceLocation[array.size()];
            for (int i = 0; i < array.size(); i++)
                advancements[i] = new ResourceLocation(array.get(i).getAsString());
        } else {
            advancements = new ResourceLocation[0];
        }

        if (jsonObject.has("blueprint") && jsonObject.get("blueprint").isJsonObject()) {
            JsonObject blueprintJson = jsonObject.getAsJsonObject("blueprint");
            blueprints = new ItemContext[blueprintJson.size()];
            int counter = 0;
            for (Map.Entry<String, JsonElement> entry :
                    blueprintJson.entrySet()) {
                blueprints[counter] = new ItemContext(entry);
                counter++;
            }
        } else {
            blueprints = new ItemContext[0];
        }


        if (jsonObject.has("item") && jsonObject.get("item").isJsonObject()) {
            JsonObject rewardJson = jsonObject.getAsJsonObject("item");
            itemReward = new ItemContext[rewardJson.size()];
            int counter = 0;
            for (Map.Entry<String, JsonElement> entry : rewardJson.entrySet()) {
                itemReward[counter] = new ItemContext(entry);
                counter++;
            }
        } else {
            itemReward = new ItemContext[0];
        }
    }

    public String getNamespaceName() {
        return this.group.tree.namespace;
    }

    public void loadAllNbt(ResourceManager manager) {
        logo.updateNbt(manager);
        if (this.unlock != null) this.unlock.loadAllNbt(manager);
        for (ItemContext context : itemConsume) {
            context.updateNbt(manager);
        }
        for (ItemContext context : blueprints) {
            context.updateNbt(manager);
        }
        for (ItemContext context : itemReward) {
            context.updateNbt(manager);
        }
    }

    @Nullable
    public HideContext getHide() {
        return hide;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public ItemStack getLogo() {
        return logo.getAsLogo();
    }

    public Set<ItemStack> getItemConsume() {
        Set<ItemStack> result = new HashSet<>();
        for (ItemContext context : itemConsume)
            result.addAll(context.getItem());
        return result;
    }

    public Set<ItemStack> getBlueprint() {
        Set<ItemStack> result = new HashSet<>();
        for (ItemContext context : blueprints)
            result.addAll(context.getItem());
        return result;
    }

    public Set<ItemStack> getItemRewards() {
        Set<ItemStack> result = new HashSet<>();
        for (ItemContext context : itemReward)
            result.addAll(context.getItem());
        return result;
    }

    public ResourceLocation[] getAdvancements() {
        return advancements;
    }

    @Nullable
    public OnUnlockContext getUnlock() {
        return unlock;
    }

    public boolean isHide() {
        return hide != null;
    }

    public String getGroupName() {
        return this.group.identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TechTreeGroupData getGroup() {
        return group;
    }

    public NodeLocation getLocation() {
        return new NodeLocation(this);
    }

    public Component getDescription() {
        return ComponentHelper.translatable(this.description);
    }

    public NodeLocation[] getNextNodes() {
        return nextNodes;
    }

    public ResourceLocation[] getNextGroups() {
        return nextGroups;
    }
}
