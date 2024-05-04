package willow.train.kuayue.block.panels;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;

public class TrainHingePanelBlock extends TrainPanelBlock {
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public TrainHingePanelBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos) {
        super(pProperties, beginPos, endPos);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST).setValue(HINGE, DoorHingeSide.LEFT));
    }

    public TrainHingePanelBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.EAST).setValue(HINGE, DoorHingeSide.LEFT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(HINGE));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(HINGE, TrainOpenableWindowBlock.getHinge(pContext));
    }
}
