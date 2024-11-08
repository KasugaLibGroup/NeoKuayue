package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import java.util.HashMap;
import java.util.HashSet;

@Getter
public class ClientTechTreeGroup {

    private final ResourceLocation id;

    private final String titleKey, descriptionKey;

    private final ItemStack icon;

    private final NodeLocation root;

    private final ClientTechTreeNode rootNode;

    private final HashSet<NodeLocation> prev;

    private final HashMap<NodeLocation, ClientTechTreeNode> nodes;
    public ClientTechTreeGroup(FriendlyByteBuf buf) {
        id = buf.readResourceLocation();
        titleKey = buf.readUtf();
        descriptionKey = buf.readUtf();
        icon = buf.readItem();
        root = NodeLocation.readFromByteBuf(buf);

        prev = new HashSet<>();
        int prevSize = buf.readInt();
        for (int i = 0; i < prevSize; i++) {
            prev.add(NodeLocation.readFromByteBuf(buf));
        }

        nodes = new HashMap<>();
        int nodeSize = buf.readInt();
        for (int i = 0; i< nodeSize; i++) {
            ClientTechTreeNode node = new ClientTechTreeNode(buf);
            nodes.put(node.location, node);
        }

        rootNode = nodes.getOrDefault(root, null);
    }
}
