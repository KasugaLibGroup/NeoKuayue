package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class ClientTechTreeManager {

    public static final ClientTechTreeManager MANAGER = new ClientTechTreeManager();
    public static final ClientNetworkCache CACHE = new ClientNetworkCache();

    private final HashMap<String, ClientTechTree> TREES;
    public ClientTechTreeManager() {
        TREES = new HashMap<>();
    }

    public HashMap<String, ClientTechTree> trees() {
        return TREES;
    }

    public static ClientTechTreeManager getInstance() {
        return MANAGER;
    }

    public @Nullable ClientTechTreeNode getNode(NodeLocation location) {
        String namespace = location.getNamespace();
        ClientTechTree tree = trees().getOrDefault(namespace, null);
        if (tree == null) return null;
        return tree.getNodes().getOrDefault(location, null);
    }
}
