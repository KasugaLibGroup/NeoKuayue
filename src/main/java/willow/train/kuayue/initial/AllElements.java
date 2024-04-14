package willow.train.kuayue.initial;


import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import willow.train.kuayue.Kuayue;

public class AllElements {

    public static final CreateRegistry testRegistry = new CreateRegistry(Kuayue.MODID, Kuayue.BUS);

    public static final CreativeTabReg neoKuayueMainTab = new CreativeTabReg("main")
            .icon(() -> AllBlocks.TRACK.asStack(1))
            .submit(testRegistry);
    public static void invoke() {
        AllTrackMaterial.invoke();
        AllTracks.invoke();
        AllLocoBogeys.invoke();
        AllCarriageBogeys.invoke();
        testRegistry.submit();
    }
}
