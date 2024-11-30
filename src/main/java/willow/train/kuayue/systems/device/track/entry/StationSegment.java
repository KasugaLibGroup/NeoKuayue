package willow.train.kuayue.systems.device.track.entry;

import kasuga.lib.core.create.boundary.CustomTrackSegment;

import java.util.UUID;

public class StationSegment extends CustomTrackSegment {
    public StationSegment(UUID segmentId) {
        super(segmentId);
    }

    public UUID getSegmentId(){
        return segmentId;
    }
}
