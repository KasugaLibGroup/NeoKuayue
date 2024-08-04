package willow.train.kuayue.initial;


import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllMovementBehaviours;
import kasuga.lib.core.util.Envs;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.event.client.RenderArrowEvent;
import willow.train.kuayue.event.client.RenderPrePosedBlockEvent;
import willow.train.kuayue.initial.create.*;
import willow.train.kuayue.initial.material.AllMaterials;

public class AllElements {

    public static final CreateRegistry testRegistry = new CreateRegistry(Kuayue.MODID, Kuayue.BUS);

    public static final CreativeTabReg neoKuayueMainTab = new CreativeTabReg("main")
            .icon(() -> AllBlocks.TRACK.asStack(1))
            .submit(testRegistry);
    public static void invoke() {
        AllTags.invoke();
        willow.train.kuayue.initial.AllBlocks.invoke();
        AllTrackMaterial.invoke();
        AllTracks.invoke();
        AllLocoBogeys.invoke();
        AllCarriageBogeys.invoke();
        AllBehaviours.invoke();
        AllPackets.invoke();
        AllMaterials.invoke();
        if (Envs.isClient()) {
            ClientInit.invoke();
            MinecraftForge.EVENT_BUS.addListener(RenderArrowEvent::renderBlockBounds);
            // MinecraftForge.EVENT_BUS.addListener(RenderPrePosedBlockEvent::renderBlock);
        }
        testRegistry.submit();
    }
}
