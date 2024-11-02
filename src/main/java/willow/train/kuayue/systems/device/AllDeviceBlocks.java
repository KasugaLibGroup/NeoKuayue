package willow.train.kuayue.systems.device;

import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.track.entry.StationEntryBlock;
import willow.train.kuayue.systems.device.track.entry.StationEntryBlockEntity;
import willow.train.kuayue.systems.device.track.entry.client.StationEntryBlockEntityRenderer;
import willow.train.kuayue.systems.device.track.train_station.TrainStationBlock;
import willow.train.kuayue.systems.device.track.train_station.TrainStationBlockEntity;
import willow.train.kuayue.systems.device.track.train_station.client.TrainStationBlockEntityRenderer;

public class AllDeviceBlocks {
    public static BlockReg<TrainStationBlock> TRAIN_STATION_BLOCK =
            new BlockReg<TrainStationBlock>("train_station")
                .blockType(TrainStationBlock::new)
                .withItem(AllDeviceEdgePoints.TRAIN_STATION.getBlockItemFactory(), null)
                .tabTo(AllElements.neoKuayueDeviceTab)
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
    public static final BlockEntityReg<StationEntryBlockEntity> STATION_ENTRY_BLOCK_ENTITY =
            new BlockEntityReg<StationEntryBlockEntity>("station_entry_block_entity")
                    .blockEntityType(StationEntryBlockEntity::new)
                    .addBlock(STATION_ENTRY)
                    .withRenderer(()-> StationEntryBlockEntityRenderer::new)
                    .submit(AllElements.testRegistry);

    public static void invoke(){
        AllDeviceEdgePoints.invoke();
    }
}
