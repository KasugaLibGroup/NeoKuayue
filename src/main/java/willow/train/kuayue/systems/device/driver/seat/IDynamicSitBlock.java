package willow.train.kuayue.systems.device.driver.seat;

import net.minecraft.world.level.block.state.BlockState;

public interface IDynamicSitBlock {
    boolean isSitDown(BlockState state, int i);
}
