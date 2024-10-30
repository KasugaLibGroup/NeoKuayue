package willow.train.kuayue.initial;

import kasuga.lib.core.util.Envs;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraftforge.common.MinecraftForge;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.event.client.CarriageInventoryEvents;
import willow.train.kuayue.event.client.RenderArrowEvent;
import willow.train.kuayue.event.server.ColorTemplateEvents;
import willow.train.kuayue.event.server.PlayerJumpEvents;
import willow.train.kuayue.initial.create.*;
import willow.train.kuayue.initial.food.AllFoods;
import willow.train.kuayue.initial.material.AllMaterials;
import willow.train.kuayue.initial.recipe.AllRecipes;

public class AllElements {

    public static final CreateRegistry testRegistry = new CreateRegistry(Kuayue.MODID, Kuayue.BUS);

    public static final CreativeTabReg neoKuayueMainTab = new CreativeTabReg("main")
            .icon(() -> AllBlocks.CR_LOGO.itemInstance().getDefaultInstance())
            .submit(testRegistry);

    public static final CreativeTabReg neoKuayueLocoTab = new CreativeTabReg("loco")
            .icon(() -> AllItems.LOCO_LOGOS.getItem().getDefaultInstance())
            .submit(testRegistry);

    public static final CreativeTabReg neoKuayueCarriageTab = new CreativeTabReg("carriage")
            .icon(() -> AllItems.SERIES_25_LOGOS.getItem().getDefaultInstance())
            .submit(testRegistry);

    public static final CreativeTabReg neoKuayueDietTab = new CreativeTabReg("diet")
            .icon(() -> AllItems.CA_25T.getItem().getDefaultInstance())
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
        AllItems.invoke();
        AllFoods.invoke();
        AllRecipes.invoke();
        AllEntities.invoke();
        if (Envs.isClient()) {
            ClientInit.invoke();
            Kuayue.BUS.addListener(ClientInit::registerHUDOverlays);
            MinecraftForge.EVENT_BUS.addListener(RenderArrowEvent::renderBlockBounds);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::unloadEvent);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::saveEvent);
            MinecraftForge.EVENT_BUS.addListener(ColorTemplateEvents::loadEvent);
            MinecraftForge.EVENT_BUS.addListener(PlayerJumpEvents::playerJumpEvent);
            // MinecraftForge.EVENT_BUS.addListener(RenderPrePosedBlockEvent::renderBlock);
            MinecraftForge.EVENT_BUS.register(new CarriageInventoryEvents());
        }
        testRegistry.submit();
    }
}
