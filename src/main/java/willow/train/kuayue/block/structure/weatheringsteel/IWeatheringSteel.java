package willow.train.kuayue.block.structure.weatheringsteel;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.initial.material.AllMaterials;

import java.util.Optional;
import java.util.function.Supplier;

public interface IWeatheringSteel extends IChangeSixOverTimeBlock<IWeatheringSteel.WSWeatherState>{

    public static enum WSWeatherState {
        WS,
        EXPOSED_WS,
        YELLOWING_WS,
        RUSTED_WS,
        STABLY_WS,
        FULLY_WS;
    }

    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap
                .<Block, Block>builder()
                .put(AllMaterials.WEATHERING_STEEL_BLOCK.getBlock(),
                        AllMaterials.EXPOSED_WEATHERING_STEEL_BLOCK.getBlock())
                .put(AllMaterials.EXPOSED_WEATHERING_STEEL_BLOCK.getBlock(),
                        AllMaterials.YELLOWING_WEATHERING_STEEL_BLOCK.getBlock())
                .put(AllMaterials.YELLOWING_WEATHERING_STEEL_BLOCK.getBlock(),
                        AllMaterials.RUSTED_WEATHERING_STEEL_BLOCK.getBlock())
                .put(AllMaterials.RUSTED_WEATHERING_STEEL_BLOCK.getBlock(),
                        AllMaterials.STABLY_RUSTED_WEATHERING_STEEL_BLOCK.getBlock())
                .put(AllMaterials.STABLY_RUSTED_WEATHERING_STEEL_BLOCK.getBlock(),
                        AllMaterials.FULLY_RUSTED_WEATHERING_STEEL_BLOCK.getBlock())
                .build();
    });

    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> {
        return NEXT_BY_BLOCK.get().inverse();
    });

    static Optional<Block> getPrevious(Block pBlock) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(pBlock));
    }

    static Block getFirst(Block pBlock) {
        Block block = pBlock;

        for(Block block1 = PREVIOUS_BY_BLOCK.get().get(pBlock); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState pState) {
        return getPrevious(pState.getBlock()).map((mapper) -> {
            return mapper.withPropertiesOf(pState);
        });
    }

    static Optional<Block> getNext(Block pBlock) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(pBlock));
    }

    static BlockState getFirst(BlockState pState) {
        return getFirst(pState.getBlock()).withPropertiesOf(pState);
    }

    default Optional<BlockState> getNext(BlockState pState) {
        return getNext(pState.getBlock()).map((mapper) -> {
            return mapper.withPropertiesOf(pState);
        });
    }

    default float getChanceModifier() {
        return this.getAge() == WSWeatherState.WS ? 0.75F : 1.0F;
    }
}
