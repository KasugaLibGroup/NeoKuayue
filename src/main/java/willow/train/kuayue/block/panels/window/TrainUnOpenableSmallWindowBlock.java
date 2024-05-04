package willow.train.kuayue.block.panels.window;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;

public class TrainUnOpenableSmallWindowBlock extends TrainPanelBlock {

    public TrainUnOpenableSmallWindowBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos) {
        super(pProperties, beginPos, endPos);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST));
    }

    public TrainUnOpenableSmallWindowBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext);
    }
}
