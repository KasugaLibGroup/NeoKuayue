package willow.train.kuayue.systems.tech_tree.player;

import kasuga.lib.core.base.Saved;

public class PlayerDataDist extends Saved<PlayerDataManager> {

    public static final String DIST_KEY = "tech_tree_data";

    public static final PlayerDataDist DIST = new PlayerDataDist(DIST_KEY);

    public PlayerDataDist(String resourceKey) {
        super(resourceKey,
                () -> PlayerDataManager.MANAGER,
                (nbt) -> {
            PlayerDataManager.MANAGER.read(nbt);
            return PlayerDataManager.MANAGER;
        });
    }
}
