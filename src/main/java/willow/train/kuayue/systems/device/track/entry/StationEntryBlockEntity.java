package willow.train.kuayue.systems.device.track.entry;

import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.systems.device.AllDeviceEdgePoints;

import java.util.List;

public class StationEntryBlockEntity extends SmartBlockEntity{
    private TrackTargetingBehaviour<StationEntry> edgePoint;

    public StationEntryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> list) {
        list.add(edgePoint = new TrackTargetingBehaviour<>(this, AllDeviceEdgePoints.STATION_ENTRY.getType()));
    }
}
