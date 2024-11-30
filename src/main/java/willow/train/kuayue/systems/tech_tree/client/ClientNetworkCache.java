package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.FriendlyByteBuf;
import willow.train.kuayue.systems.tech_tree.NetworkState;
import willow.train.kuayue.systems.tech_tree.NodeLocation;

import java.util.*;

public class ClientNetworkCache {

    public static final ClientNetworkCache INSTANCE = new ClientNetworkCache();
    private final HashMap<NodeLocation, ClientTechTreeNode> cachedNodes;
    private final HashMap<String, ClientTechTreeGroup> cachedGroups;
    private ClientTechTree cachedTree;

    @Getter
    private NetworkState state;

    @Getter
    private UUID batchId;
    public ClientNetworkCache() {
        cachedNodes = new HashMap<>();
        cachedGroups = new HashMap<>();
        state = NetworkState.STANDING_BY;
    }

    public void startBatch(UUID batchId) {
        this.batchId = batchId;
        cachedNodes.clear();
        cachedGroups.clear();
        cachedTree = null;
        state = NetworkState.BUSY;
    }

    public void addNode(FriendlyByteBuf buf) {
        ClientTechTreeNode node = new ClientTechTreeNode(buf);
        cachedNodes.put(node.location, node);
    }

    public void addGroup(FriendlyByteBuf buf) {
        ClientTechTreeGroup group = new ClientTechTreeGroup(buf);
        cachedGroups.put(group.getId().getPath(), group);
    }

    public void setTree(FriendlyByteBuf buf) {
        cachedTree = new ClientTechTree(buf);
    }

    public NetworkState queryState(UUID batchId) {
        if (state == NetworkState.STANDING_BY || this.batchId == null) return state;
        if (state == NetworkState.BUSY) {
            return this.batchId.equals(batchId) ? NetworkState.USE_ABLE : NetworkState.BUSY;
        }
        return NetworkState.BUSY;
    }

    public boolean verify() {
        if (cachedTree == null) return false;
        if (cachedGroups.size() < cachedTree.getGroupSize()) return false;

        HashSet<String> grps = new HashSet<>(cachedTree.getGroups().keySet());
        cachedGroups.forEach((path, grp) -> grps.remove(path));
        if (!grps.isEmpty()) return false;

        HashSet<NodeLocation> nodes = new HashSet<>();
        cachedGroups.forEach((path, grp) -> nodes.addAll(grp.getNodes().keySet()));
        cachedNodes.forEach((loc, n) -> nodes.remove(n.location));
        return grps.isEmpty();
    }

    public ClientTechTree construct() {
        Objects.requireNonNull(cachedTree);
        cachedTree.getGroups().putAll(this.cachedGroups);
        cachedGroups.forEach((path, grp) -> {
            for (Map.Entry<NodeLocation, ClientTechTreeNode> entry : cachedNodes.entrySet()) {
                if (entry.getKey().getGroup().equals(path))
                    grp.getNodes().put(entry.getKey(), entry.getValue());
                if (grp.getRoot().equals(entry.getKey()))
                    grp.setRootNode(entry.getValue());
            }
        });
        cachedNodes.forEach((loc, node) -> {
            cachedTree.getNodes().put(loc, node);
            node.getNext().forEach(location -> {
                        if (cachedNodes.containsKey(location))
                            node.getNextNode().add(cachedNodes.get(location));
                    });
        });
        return cachedTree;
    }

    public void reset() {
        this.cachedNodes.clear();
        this.cachedGroups.clear();
        this.cachedTree = null;
        this.batchId = null;
        this.state = NetworkState.STANDING_BY;
    }

}
