package willow.train.kuayue.block.panels.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.initial.AllBlocks;

public class SingleSlidingDoorEntity extends CustomRenderedEndfaceEntity implements IContraptionMovementBlockEntity {

    public SingleSlidingDoorEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public SingleSlidingDoorEntity(BlockPos pos, BlockState state) {
        this(AllBlocks.SINGLE_SLIDING_DOOR_ENTITY.getType(), pos, state);
    }
}
