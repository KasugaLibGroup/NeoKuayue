package willow.train.kuayue;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import willow.train.kuayue.initial.AllElements;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Kuayue.MODID)
public class Kuayue {
    public static final String MODID = "kuayue";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static IEventBus BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public Kuayue() {
        BUS.register(this);
        AllElements.invoke();
    }
}
