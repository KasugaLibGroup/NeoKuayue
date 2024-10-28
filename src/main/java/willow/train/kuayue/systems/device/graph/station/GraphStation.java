package willow.train.kuayue.systems.device.graph.station;

import com.simibubi.create.Create;
import com.simibubi.create.content.trains.graph.TrackGraph;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import willow.train.kuayue.initial.AllEdgePoints;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignal;
import willow.train.kuayue.systems.device.graph.track.StationTrack;
import willow.train.kuayue.systems.device.station.train_station.TrainStation;

import java.util.ArrayList;
import java.util.UUID;

public class GraphStation {
    public UUID networkId;
    public final UUID uuid;
    public String name;
    public String shortenCode;
    public ArrayList<TrainStation> stations = new ArrayList<>();
    public ArrayList<EntrySignal> entries = new ArrayList<>();
    public ArrayList<StationTrack> stationTracks = new ArrayList<>();

    public GraphStation(UUID uuid) {
        this.uuid = uuid;
    }
    public GraphStation(){
        this.uuid = UUID.randomUUID();
    }
    public boolean isOrphan(){
        return stations.isEmpty() && entries.isEmpty() && stationTracks.isEmpty();
    }


    public CompoundTag write(){
        CompoundTag tag = new CompoundTag();
        tag.putUUID("NetworkId", networkId);
        tag.putUUID("StationId", uuid);
        tag.putString("Name", name);
        tag.putString("ShortenCode", shortenCode);
        ListTag entriesTag = new ListTag();
        for (EntrySignal entry : entries) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putUUID("Id", entry.getId());
            entriesTag.add(entryTag);
        }
        tag.put("Entries", entriesTag);

        ListTag edgePointsTag = new ListTag();
        for (TrainStation station: stations) {
            CompoundTag edgePointTag = new CompoundTag();
            edgePointTag.putUUID("Id", station.getId());
            edgePointsTag.add(edgePointTag);
        }
        tag.put("EdgePoints", edgePointsTag);


        ListTag stationTracks = new ListTag();
        for (StationTrack stationTrack : this.stationTracks) {
            stationTracks.add(stationTrack.write());
        }
        tag.put("Tracks", stationTracks);

        return tag;
    }

    public static GraphStation read(LevelAccessor side,CompoundTag tag){
        UUID uuid = tag.getUUID("StationId");
        GraphStation station = new GraphStation(uuid);
        station.onUpdateData(side, tag);
        return station;
    }

    private void onUpdateData(LevelAccessor side, CompoundTag nbt) {
        this.networkId = nbt.getUUID("NetworkId");
        TrackGraph network = Create.RAILWAYS.sided(side).trackNetworks.get(networkId);
        this.name = nbt.getString("Name");
        this.shortenCode = nbt.getString("ShortenCode");

        this.entries.clear();
        ListTag tag = nbt.getList("Entries", Tag.TAG_COMPOUND);
        for(int i=0;i<tag.size();i++){
            CompoundTag entryTag = tag.getCompound(i);
            UUID entryId = entryTag.getUUID("Id");
            EntrySignal entrySignal = network.getPoint(AllEdgePoints.ENTRY_SIGNAL, entryId);
            if(entrySignal != null){
                this.entries.add(entrySignal);
            }
        }

        this.stations.clear();
        ListTag edgePointsTags = nbt.getList("EdgePoints", Tag.TAG_COMPOUND);
        for(int i=0;i<edgePointsTags.size();i++){
            CompoundTag edgePointsTag = tag.getCompound(i);
            UUID entryId = edgePointsTag.getUUID("Id");
            TrainStation trainStation = network.getPoint(AllEdgePoints.TRAIN_STATION, entryId);
            if(trainStation != null){
                this.stations.add(trainStation);
            }
        }

        this.stationTracks.clear();
        ListTag trackTags = nbt.getList("Tracks", Tag.TAG_COMPOUND);
        for(int i=0;i<trackTags.size();i++){
            CompoundTag trackTag = tag.getCompound(i);
            StationTrack track = StationTrack.read(network, trackTag);
            this.stationTracks.add(track);
        }
    }
}
