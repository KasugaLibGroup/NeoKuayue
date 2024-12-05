package willow.train.kuayue.systems.device.driver.seat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.seat.M1SeatEntity;
import willow.train.kuayue.block.seat.SeatBlockEntity;
import willow.train.kuayue.systems.device.AllDeviceBlockEntities;

public class DoubleDriverSeatBlockEntity extends SeatBlockEntity {
    public DoubleDriverSeatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DoubleDriverSeatBlockEntity(BlockPos pos, BlockState state) {
        this(AllDeviceBlockEntities.DOUBLE_DRIVER_SEAT.getType(), pos, state);
    }
}
