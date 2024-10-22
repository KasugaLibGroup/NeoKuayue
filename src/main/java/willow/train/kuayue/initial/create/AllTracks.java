package willow.train.kuayue.initial.create;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.trains.track.TrackBlock;
import kasuga.lib.core.create.SimpleTrackBlock;
import kasuga.lib.core.create.TrackStateGenerator;
import kasuga.lib.registrations.create.TrackReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import willow.train.kuayue.initial.AllElements;

public class AllTracks {
    public static final CreateRegistry testRegistry = AllElements.testRegistry;
    public static final TrackStateGenerator.Builder stateBuilder =
            TrackStateGenerator.Builder.of("block/track/standard/")
                    .xRotation((state) -> 0)
                    .yRotation((state -> state.getValue(TrackBlock.SHAPE).getModelRotation()))
                    .addModelContext(
                            TrackStateGenerator.ModelBuilderContext.of
                                    (TrackStateGenerator.ModelActionType.PARENT,
                                            "track/standard",
                                            testRegistry.asResource("track/standard")))
                    .addModelContext(TrackStateGenerator.ModelBuilderContext.of
                            (TrackStateGenerator.ModelActionType.TEXTURE, "particle", null));

    public static final TrackReg<SimpleTrackBlock> standardTrack =
            new TrackReg<SimpleTrackBlock>("standard_track")
                    .trackState(stateBuilder)
                    .trackNameSuffix("Train Track")
                    .trackMaterial(AllTrackMaterial.standardMaterial::getMaterial)
                    .pickaxeOnly()
                    .addTags(AllTags.AllBlockTags.TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(testRegistry);

    public static final TrackReg<SimpleTrackBlock> tielessTrack =
            new TrackReg<SimpleTrackBlock>("tieless_track")
                    .trackState(stateBuilder)
                    .trackNameSuffix("Tieless Track")
                    .trackMaterial(AllTrackMaterial.tielessMaterial::getMaterial)
                    .pickaxeOnly()
                    .addTags(AllTags.AllBlockTags.TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(testRegistry);

    public static final TrackReg<SimpleTrackBlock> ballastlessTrack =
            new TrackReg<SimpleTrackBlock>("ballastless_track")
                    .trackState(stateBuilder)
                    .trackNameSuffix("Ballastless Track")
                    .trackMaterial(AllTrackMaterial.ballastlessMaterial::getMaterial)
                    .pickaxeOnly()
                    .addTags(AllTags.AllBlockTags.TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(testRegistry);

    public static final TrackReg<SimpleTrackBlock> meterTrack =
            new TrackReg<SimpleTrackBlock>("meter_track")
                    .trackState(stateBuilder)
                    .trackNameSuffix("Meter Track")
                    .trackMaterial(AllTrackMaterial.meterMaterial::getMaterial)
                    .pickaxeOnly()
                    .addTags(AllTags.AllBlockTags.TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .addTags(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tab(AllElements.neoKuayueMainTab)
                    .submit(testRegistry);

    public static void invoke(){}
}
