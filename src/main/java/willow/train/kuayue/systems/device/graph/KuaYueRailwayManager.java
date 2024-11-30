package willow.train.kuayue.systems.device.graph;

import net.minecraft.world.level.LevelAccessor;

public class KuaYueRailwayManager {
    public CRRailwayGraphData SERVER = new CRRailwayGraphData();
    public CRRailwayGraphData CLIENT = new CRRailwayGraphData();

    public CRRailwayGraphData sided(LevelAccessor accessor){
        if(accessor.isClientSide()){
            return CLIENT;
        }
        return SERVER;
    }
}
