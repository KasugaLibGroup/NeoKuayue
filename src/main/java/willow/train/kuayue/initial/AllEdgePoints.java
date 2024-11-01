package willow.train.kuayue.initial;

import com.simibubi.create.content.trains.graph.EdgePointType;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignal;
import willow.train.kuayue.systems.device.track.train_station.TrainStation;

public class AllEdgePoints {
    public static EdgePointType<TrainStation> TRAIN_STATION = EdgePointType.register(
        new ResourceLocation("kuayue","train_station"),
        TrainStation::new
    );

    public static EdgePointType<EntrySignal> ENTRY_SIGNAL = EdgePointType.register(
            new ResourceLocation("kuayue","entry_signal"),
            EntrySignal::new
    );
}
