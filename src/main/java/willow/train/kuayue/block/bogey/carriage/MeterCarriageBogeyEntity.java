package willow.train.kuayue.block.bogey.carriage;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import willow.train.kuayue.initial.create.AllCarriageBogeys;

public class MeterCarriageBogeyEntity extends AbstractBogeyBlockEntity {
    public MeterCarriageBogeyEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public MeterCarriageBogeyEntity(BlockPos pos, BlockState state) {
        super(AllCarriageBogeys.meterCarriageBogeyEntity.getType(), pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return AllCarriageBogeys.meterCarriageBogeyGroup.getStyle();
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox();
    }
}
