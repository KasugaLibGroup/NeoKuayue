package willow.train.kuayue.systems.device.driver.combustion;

import com.simibubi.create.content.trains.entity.Train;
import kasuga.lib.core.javascript.engine.HostAccess;

public class TrainDataHandler {

    private final Train train;

    public TrainDataHandler(Train train) {
        this.train = train;
    }

    @HostAccess.Export
    public double getSpeed(){
        return train.speed;
    }

    @HostAccess.Export
    public String getSignal(){
        return "UU";
    }
}
