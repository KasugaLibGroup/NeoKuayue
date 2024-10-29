package willow.train.kuayue.block.seat;

import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import com.simibubi.create.content.contraptions.actors.seat.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.initial.AllEntities;

public class M1SeatEntity extends SeatEntity {
    public M1SeatEntity(EntityType<?> p_i48580_1_, Level p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    public M1SeatEntity(Level world, BlockPos pos) {
        super(AllEntities.M1_SEAT.getType(), world);
        this.noPhysics = true;
    }

    @Override
    public void tick() {
        if (level.isClientSide)
            return;
        BlockPos pos = blockPosition();
        BlockState state = level.getBlockState(pos.above());
        if (state.getBlock() instanceof M1SeatBlock) return;
        for (Direction direction : Direction.values()) {
            if (direction.equals(Direction.UP) || direction.equals(Direction.DOWN)) continue;
            state = level.getBlockState(pos.above().relative(direction));
            if (state.getBlock() instanceof M1SeatBlock) {
                return;
            }
        }
        this.discard();
    }
}
