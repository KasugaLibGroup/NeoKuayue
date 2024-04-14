package willow.train.kuayue.block.bogey.carriage;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import willow.train.kuayue.initial.AllCarriageBogeys;

public class CarriageBogeyEntity extends AbstractBogeyBlockEntity {

    public CarriageBogeyEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CarriageBogeyEntity(BlockPos pos, BlockState state) {
        this(AllCarriageBogeys.carriageBogeyEntity.getType(), pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return AllCarriageBogeys.carriageBogeyGroup.getStyle();
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox();
    }
}
