package willow.train.kuayue.block.structure.weatheringsteel;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public interface IChangeSixOverTimeBlock<T extends Enum<T>> {

    int SCAN_DISTANCE = 6;

    Optional<BlockState> getNext(BlockState blockState);

    float getChanceModifier();

    default void onRandomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        float f = 0.05688889F;
        if (pRandom.nextFloat() < 0.05688889F) {
            this.applyChangeOverTime(pState, pLevel, pPos, pRandom);
        }
    }

    T getAge();

    default void applyChangeOverTime(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

        int i = this.getAge().ordinal();
        int j = 0;
        int k = 0;

        for(BlockPos blockpos : BlockPos.withinManhattan(pPos, 4, 4, 4)) {
            int l = blockpos.distManhattan(pPos);
            if (l > 6) {
                break;
            }

            if (!blockpos.equals(pPos)) {
                BlockState blockstate = pLevel.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof IChangeSixOverTimeBlock) {
                    Enum<?> oenum = ((IChangeSixOverTimeBlock) block).getAge();
                    if (this.getAge().getClass() == oenum.getClass()) {
                        int i1 = oenum.ordinal();
                        if (i1 < i) {
                            return;
                        }

                        if (i1 > i) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float f1 = f * f * this.getChanceModifier();
        if (pRandom.nextFloat() < f1) {
            this.getNext(pState).ifPresent((state) -> {
                pLevel.setBlockAndUpdate(pPos, state);
            });
        }
    }
}
