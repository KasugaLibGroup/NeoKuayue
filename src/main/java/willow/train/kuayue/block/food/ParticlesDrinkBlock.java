package willow.train.kuayue.block.food;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ParticlesDrinkBlock extends PlacementFoodBlock{

    public ParticlesDrinkBlock(Properties pProperties, FoodType foodType) {
        super(pProperties, foodType);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        spawnParticles(pLevel, pPos);
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        spawnParticles(pLevel, pPos);
    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;

        double d1 = 0.1 * randomsource.nextFloat();
        double d2 = 0.1 * randomsource.nextFloat();
        double d3 = 0.1 * randomsource.nextFloat();
        pLevel.addParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                pPos.getX() + 0.4 + d1, pPos.getY() + 0.55 + d2, pPos.getZ() + 0.4 + d3,
                0.0D, 0.1D, 0.0D);
    }
}
