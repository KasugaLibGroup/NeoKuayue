package willow.train.kuayue.initial.create;

import com.jozufozu.flywheel.core.PartialModel;
import kasuga.lib.core.create.SimpleTrackBlock;
import kasuga.lib.registrations.create.TrackMaterialReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import willow.train.kuayue.initial.AllElements;

public class AllTrackMaterial {

    public static final SimpleTrackBlock.Builder builder =
            new SimpleTrackBlock.Builder(() -> AllCarriageBogeys.carriageBlockBundle.getElement("pk209p_bogey").getEntry().get());

    public static final SimpleTrackBlock.Builder meterBuilder =
            new SimpleTrackBlock.Builder(() -> AllCarriageBogeys.meterCarriageBlockBundle.getElement("mkz_bogey").getEntry().get());

    public static final TrackMaterialReg standardMaterial = new TrackMaterialReg("standard")
            .lang("standard_track")
            .block(() -> AllTracks.standardTrack)
            .trackParticle(new ResourceLocation("minecraft", "block/palettes/stone_types/polished/andesite_cut_polished"))
            .type(AllElements.testRegistry.asResource("standard"), builder::build)
            .customModel(
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/tie")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_left")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_right"))
            )
            .simpleTrackModelOffset(0.755f)
            .sleeper(Blocks.ANDESITE_SLAB)
            .submit(AllElements.testRegistry);

    public static final TrackMaterialReg tielessMaterial = new TrackMaterialReg("tieless")
            .lang("tieless_track")
            .block(() -> AllTracks.tielessTrack)
            .trackParticle(new ResourceLocation("minecraft", "block/palettes/stone_types/polished/andesite_cut_polished"))
            .type(AllElements.testRegistry.asResource("tieless"), builder::build)
            .customModel(
                    () -> new PartialModel(new ResourceLocation("kasuga_lib", "empty_model")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_left")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_right"))
            )
            .simpleTrackModelOffset(0.755f)
            .sleeper(Blocks.ANDESITE_SLAB)
            .submit(AllElements.testRegistry);

    public static final TrackMaterialReg ballastlessMaterial = new TrackMaterialReg("ballastless")
            .lang("ballastless_track")
            .block(() -> AllTracks.ballastlessTrack)
            .trackParticle(new ResourceLocation("minecraft", "block/palettes/stone_types/polished/andesite_cut_polished"))
            .type(AllElements.testRegistry.asResource("ballastless"), builder::build)
            .customModel(
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/ballastless/tie")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_left")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_right"))
            )
            .simpleTrackModelOffset(0.755f)
            .sleeper(Blocks.ANDESITE_SLAB)
            .submit(AllElements.testRegistry);

    public static final TrackMaterialReg meterMaterial = new TrackMaterialReg("meter")
            .lang("meter_track")
            .block(() -> AllTracks.meterTrack)
            .trackParticle(new ResourceLocation("minecraft", "block/palettes/stone_types/polished/andesite_cut_polished"))
            .type(AllElements.testRegistry.asResource("meter"), meterBuilder::build)
            .customModel(
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/meter/tie")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_left")),
                    () -> new PartialModel(AllElements.testRegistry.asResource("block/track/standard/segment_right"))
            )
            .simpleTrackModelOffset(0.525f)
            .sleeper(Blocks.ANDESITE_SLAB)
            .submit(AllElements.testRegistry);

    public static void invoke(){}
}
