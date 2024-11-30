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
import willow.train.kuayue.systems.device.driver.combustion.InternalCombustionDriveControllerBlock;

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

    public static BlockReg<InternalCombustionDriveControllerBlock> INTERNAL_COMBUSTION_DRIVE_CONTROLLER =
            new BlockReg<InternalCombustionDriveControllerBlock>("internal_combustion_drive_controller")
                .blockType(InternalCombustionDriveControllerBlock::new)
                .defaultBlockItem()
                .tabTo(AllElements.neoKuayueDeviceTab)
                .submit(AllElements.testRegistry);

    public static void invoke(){
        AllDeviceBlockEntities.invoke();
        AllDeviceEdgePoints.invoke();
    }
}
