package willow.train.kuayue.block.bogey.loco;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import willow.train.kuayue.initial.AllCarriageBogeys;
import willow.train.kuayue.initial.AllLocoBogeys;

public class LocoBogeyEntity extends AbstractBogeyBlockEntity {

    public LocoBogeyEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public LocoBogeyEntity(BlockPos pos, BlockState state) {
        this(AllLocoBogeys.locoBogeyEntity.getType(), pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return AllLocoBogeys.locoBogeyGroup.getStyle();
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return super.createRenderBoundingBox();
    }
}
