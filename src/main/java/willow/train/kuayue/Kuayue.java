package willow.train.kuayue;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.tech_tree.server.TechTreeManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Kuayue.MODID)
public class Kuayue {
    public static final String MODID = "kuayue";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static IEventBus BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public static final LocalFileEnv LOCAL_FILE = new LocalFileEnv("./kuayue");
    public static final TechTreeManager TECH_TREE = TechTreeManager.MANAGER;

    public Kuayue() {
        BUS.register(this);
        AllElements.invoke();
        KuayueConfig.invoke();
    }
}
