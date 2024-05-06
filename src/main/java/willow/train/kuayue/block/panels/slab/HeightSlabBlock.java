package willow.train.kuayue.block.panels.slab;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

public class HeightSlabBlock extends TrainSlabBlock {

    public HeightSlabBlock(Properties pProperties, boolean isCarport) {
        super(pProperties, isCarport);
        registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.EAST)
                .setValue(TrainPanelProperties.FLOOR_HEIGHT, TrainPanelProperties.FloorHeight.BOTTOM)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(TrainPanelProperties.FLOOR_HEIGHT));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(TrainPanelProperties.FLOOR_HEIGHT)) {
            case DOUBLE -> Shapes.block();
            case BOTTOM -> TrainPanelShapes.CARPORT_CENTER;
            case TOP -> TrainPanelShapes.FLOOR;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
