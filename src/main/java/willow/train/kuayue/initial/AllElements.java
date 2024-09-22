package willow.train.kuayue.initial;

import kasuga.lib.core.util.Envs;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraftforge.common.MinecraftForge;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.event.client.CarriageInventoryEvents;
import willow.train.kuayue.event.client.RenderArrowEvent;
import willow.train.kuayue.event.server.ColorTemplateEvents;
import willow.train.kuayue.initial.create.*;
import willow.train.kuayue.initial.material.AllMaterials;
import willow.train.kuayue.initial.panel.CR200JPanel;
import willow.train.kuayue.initial.panel.I11GPanel;

public class AllElements {

    public static final CreateRegistry testRegistry = new CreateRegistry(Kuayue.MODID, Kuayue.BUS);

    public static final CreativeTabReg neoKuayueMainTab = new CreativeTabReg("main")
            .icon(() -> AllTracks.standardTrack.getTrackItem().getDefaultInstance())
            .submit(testRegistry);

    public static final CreativeTabReg neoKuayueLocoTab = new CreativeTabReg("loco")
            .icon(() -> I11GPanel.HEAD_DF11G_2.itemInstance().getDefaultInstance())
            .submit(testRegistry);

    public static final CreativeTabReg neoKuayueCarriageTab = new CreativeTabReg("carriage")
            .icon(() -> CR200JPanel.END_FACE_MARSHALLED_CR200J.item.getItem().getDefaultInstance())
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
        AllEditableTypes.invoke();
        AllMenuScreens.invoke();
        if (Envs.isClient()) {
            ClientInit.invoke();
            MinecraftForge.EVENT_BUS.addListener(RenderArrowEvent::renderBlockBounds);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::unloadEvent);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::saveEvent);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::loadEvent);
            // MinecraftForge.EVENT_BUS.addListener(RenderPrePosedBlockEvent::renderBlock);
            MinecraftForge.EVENT_BUS.register(new CarriageInventoryEvents());
        }
        testRegistry.submit();
    }
}
