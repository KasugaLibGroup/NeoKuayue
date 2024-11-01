package willow.train.kuayue.systems.device.graph.track;

import com.simibubi.create.content.trains.graph.TrackGraph;
import net.minecraft.nbt.CompoundTag;
import willow.train.kuayue.initial.AllEdgePoints;
import willow.train.kuayue.systems.device.graph.station.GraphStation;
import willow.train.kuayue.systems.device.track.train_station.TrainStation;

import java.util.UUID;

public class StationTrack {
    GraphStation graphStation;
    TrainStation station;
    UUID id;

    public StationTrack(UUID id) {
        this.id = id;
    }

    public GraphStation getGraphStation() {
        return graphStation;
    }

    public TrainStation getStation() {
        return station;
    }

    public UUID getId() {
        return id;
    }

    public CompoundTag write() {
        CompoundTag nbt = new CompoundTag();
        nbt.putUUID("Id", id);
        nbt.putUUID("EdgePointId", station.getId());
        return nbt;
    }

    public static StationTrack read(TrackGraph graph, CompoundTag trackTag) {
        StationTrack track = new StationTrack(trackTag.getUUID("Id"));
        UUID stationUUID = trackTag.getUUID("EdgePointId");
        track.station = graph.getPoint(AllEdgePoints.TRAIN_STATION, stationUUID);
        return track;
    }
}
