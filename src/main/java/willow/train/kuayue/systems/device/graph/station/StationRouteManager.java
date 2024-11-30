package willow.train.kuayue.systems.device.graph.station;

import willow.train.kuayue.systems.device.graph.route.AutomaticRoute;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignal;
import willow.train.kuayue.systems.device.graph.track.StationTrack;

import java.util.ArrayList;
import java.util.HashMap;

public class StationRouteManager {
    public HashMap<StationTrack, ArrayList<AutomaticRoute<EntrySignal>>> departureRoutes = new HashMap<>();
    public HashMap<EntrySignal, ArrayList<AutomaticRoute<StationTrack>>> stopRoutes = new HashMap<>();
    public HashMap<EntrySignal, ArrayList<AutomaticRoute<EntrySignal>>> passRoutes = new HashMap<>();
}
