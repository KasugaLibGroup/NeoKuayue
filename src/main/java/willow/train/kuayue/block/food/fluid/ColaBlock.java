package willow.train.kuayue.block.food.fluid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class ColaBlock extends LiquidBlock {
    public ColaBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        spawnParticles(pLevel, pPos);
    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;

        double d1 = 0.5 * randomsource.nextFloat();
        double d2 = 0.5 * randomsource.nextFloat();
        double d3 = 0.5 * randomsource.nextFloat();
        pLevel.addParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                pPos.getX() + 0.4 + d1, pPos.getY() + 1.05 + d2, pPos.getZ() + 0.4 + d3,
                0.0D, 0.1D, 0.0D);
    }
}
