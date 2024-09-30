package willow.train.kuayue;

import kasuga.lib.core.config.SimpleConfig;

public class KuayueConfig {

    public static final SimpleConfig CONFIG = new SimpleConfig()
            .client("Kuayue Client")
            .boolConfig("RECEIVE_COLOR_SHARE", "Should receive color requests.", true)
            .registerConfigs();

    public static void invoke(){}
}
