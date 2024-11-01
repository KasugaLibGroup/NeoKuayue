package willow.train.kuayue.systems.device;

import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.track.TrackTargetingBlockItem;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;
import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignalBlockEntity;
import willow.train.kuayue.systems.device.track.entry.StationEntryBlock;
import willow.train.kuayue.systems.device.track.train_station.TrainStationBlock;
import willow.train.kuayue.systems.device.track.train_station.TrainStationBlockEntity;
import willow.train.kuayue.systems.device.track.train_station.client.TrainStationBlockEntityRenderer;

import java.util.function.Function;

public class AllDeviceBlocks {
    public static Function<EdgePointType<?>,NonNullBiFunction<? super Block, Item.Properties, TrackTargetingBlockItem>>
            registerTrackTargetingBlockItem =
            Util.memoize(TrackTargetingBlockItem::ofType);

    public static BlockReg<TrainStationBlock> TRAIN_STATION_BLOCK = new BlockReg<TrainStationBlock>("train_station")
            .blockType(TrainStationBlock::new)
            .tabTo(AllElements.neoKuayueDeviceTab)
            .withItem(AllDeviceEdgePoints.TRAIN_STATION.getBlockItemFactory(), null)
            .submit(AllElements.testRegistry);

    public static BlockReg<StationEntryBlock> STATION_ENTRY =
            new BlockReg<StationEntryBlock>("station_entry")
                    .blockType(StationEntryBlock::new)
                    .withItem(AllDeviceEdgePoints.STATION_ENTRY.getBlockItemFactory(), null)
                    .tabTo(AllElements.neoKuayueDeviceTab)
                    .submit(AllElements.testRegistry);
    public static BlockEntityReg<TrainStationBlockEntity> STATION_BLOCK_ENTITY =
            new BlockEntityReg<TrainStationBlockEntity>("train_station_block_entity")
                    .blockEntityType(TrainStationBlockEntity::new)
                    .addBlock(TRAIN_STATION_BLOCK)
                    .withRenderer(()-> TrainStationBlockEntityRenderer::new)
                    .submit(AllElements.testRegistry);
    public static final BlockEntityReg<EntrySignalBlockEntity> ENTRY_SIGNAL_BLOCK_ENTITY =
            new BlockEntityReg<EntrySignalBlockEntity>("station_entry_block_entity")
                    .blockEntityType(EntrySignalBlockEntity::new)
                    .addBlock(STATION_ENTRY)
                    .submit(AllElements.testRegistry);

    public static void invoke(){
        AllDeviceEdgePoints.invoke();
    }
}
