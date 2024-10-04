package willow.train.kuayue.block.food;

import com.mojang.math.Vector3f;
import kasuga.lib.core.client.render.SimpleColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ParticlesDrinkBlock extends PlacementFoodBlock{

    public static final Vector3f COLA_PARTICLE_COLOR =
            new Vector3f(Vec3.fromRGB24(getRGB24FromRGB(139, 69, 19)));
    public static final DustParticleOptions KUA_COLA = new DustParticleOptions(COLA_PARTICLE_COLOR, 0.5F);

    public ParticlesDrinkBlock(Properties pProperties, FoodType foodType) {
        super(pProperties, foodType);
    }

    public static int getRGB24FromRGB(int r, int g, int b) {
        return SimpleColor.fromRGBA(r, g, b, 1).getRGB();
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
        pLevel.addParticle(ParticleTypes.BUBBLE, (double)pPos.getX(), (double)pPos.getY(), (double)pPos.getZ(), 0.0D, 0.0D, 0.0D);
    }
}
