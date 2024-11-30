package willow.train.kuayue.systems.device;

import com.simibubi.create.content.trains.signal.TrackEdgePoint;
import kasuga.lib.registrations.create.TrackEdgePointReg;
import kasuga.lib.registrations.create.TrackSegmentReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.track.entry.StationEntry;
import willow.train.kuayue.systems.device.track.entry.StationSegment;
import willow.train.kuayue.systems.device.track.train_station.TrainStation;

public class AllDeviceEdgePoints {
    public static final TrackEdgePointReg<StationEntry> STATION_ENTRY =
            new TrackEdgePointReg<StationEntry>("station_entry")
                    .use(StationEntry::new)
                    .submit(AllElements.testRegistry);

    public static final TrackEdgePointReg<TrainStation> TRAIN_STATION =
            new TrackEdgePointReg<TrainStation>("train_station")
                    .use(TrainStation::new)
                    .submit(AllElements.testRegistry);

    public static final TrackSegmentReg STATION =
            new TrackSegmentReg("station")
                    .boundaryEdgePoint(STATION_ENTRY)
                    .segmentType(StationSegment::new)
                    .submit(AllElements.testRegistry);
    public static void invoke(){

    }
}
