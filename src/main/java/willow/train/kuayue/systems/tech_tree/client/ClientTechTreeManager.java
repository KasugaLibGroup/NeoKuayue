package willow.train.kuayue.systems.tech_tree.client;

import lombok.Getter;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class ClientTechTreeManager {

    public static final ClientTechTreeManager MANAGER = new ClientTechTreeManager();
    public static final ClientNetworkCache CACHE = new ClientNetworkCache();

    @Getter
    private final HashMap<String, ClientTechTree> TREES;
    public ClientTechTreeManager() {
        TREES = new HashMap<>();
    }

    public static ClientTechTreeManager getInstance() {
        return MANAGER;
    }


}
