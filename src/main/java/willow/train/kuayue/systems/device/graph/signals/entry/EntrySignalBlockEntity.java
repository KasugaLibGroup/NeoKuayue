package willow.train.kuayue.systems.device.graph.signals.entry;

import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import willow.train.kuayue.initial.AllEdgePoints;
import willow.train.kuayue.systems.device.AllDeviceBlocks;

import java.util.List;

public class EntrySignalBlockEntity extends SmartBlockEntity {
    public TrackTargetingBehaviour<EntrySignal> edgePoint;

    public EntrySignalBlockEntity(BlockPos blockPos, BlockState state) {
        super(AllDeviceBlocks.STATION_BLOCK_ENTITY.getType(), blockPos, state);

    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(edgePoint = new TrackTargetingBehaviour<>(this, AllEdgePoints.ENTRY_SIGNAL));
    }
}
