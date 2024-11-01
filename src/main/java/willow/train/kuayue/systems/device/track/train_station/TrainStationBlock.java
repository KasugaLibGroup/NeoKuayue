package willow.train.kuayue.systems.device.track.train_station;

import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.systems.device.AllDeviceBlocks;

public class TrainStationBlock extends Block implements IBE<TrainStationBlockEntity> {

    public TrainStationBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    @Override
    public Class<TrainStationBlockEntity> getBlockEntityClass() {
        return TrainStationBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TrainStationBlockEntity> getBlockEntityType() {
        return AllDeviceBlocks.STATION_BLOCK_ENTITY.getType();
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state,level,pos,newState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

}
