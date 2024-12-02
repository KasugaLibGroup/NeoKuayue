package willow.train.kuayue.behaviour;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import static willow.train.kuayue.block.panels.TrainPanelBlock.FACING;
import static willow.train.kuayue.block.panels.carport.DF11GChimneyBlock.LIT;

public class ChimneyMovementBehaviour implements MovementBehaviour {

    @Override
    public boolean renderAsNormalBlockEntity() {
        return true;
    }

    @Override
    public void tick(MovementContext context) {
        System.out.println("method tick is being executed");
        Level pLevel = context.world;
        BlockState pState = context.state;
        if (pLevel == null || !pLevel.isClientSide || context.position == null
                || !pState.getValue(CampfireBlock.LIT) || context.disabled)
            return;

        RandomSource pRandom = pLevel.random;
        Direction direction = pState.getValue(FACING);

        if (pState.getValue(LIT)) {
            for (int i = 0; i < 5; i++) {
                if (pRandom.nextFloat() < 0.5f) {
                    pLevel.addParticle(
                            ParticleTypes.LARGE_SMOKE,
                            context.position.y() + 0.5D,
                            context.position.y() + 1.0D,
                            context.position.y() + 0.5D,
                            (direction == Direction.EAST || direction == Direction.WEST) ?
                                    (double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F,
                            0.2F,
                            (direction == Direction.SOUTH || direction == Direction.NORTH) ?
                                    (double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F);
                }
                pLevel.addParticle(
                        ParticleTypes.LARGE_SMOKE,
                        context.position.y() + 0.5D,
                        context.position.y() + 1.0D,
                        context.position.y() + 0.5D,
                        (direction == Direction.EAST || direction == Direction.WEST) ?
                                -(double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F,
                        0.2F,
                        (direction == Direction.SOUTH || direction == Direction.NORTH) ?
                                -(double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F);
            }
        }
    }
}
