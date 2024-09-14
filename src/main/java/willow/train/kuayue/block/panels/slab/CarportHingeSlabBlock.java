package willow.train.kuayue.block.panels.slab;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.DOOR_HINGE;

public class CarportHingeSlabBlock extends HingeSlabBlock{
    public CarportHingeSlabBlock(Properties pProperties, boolean isCarport) {
        super(pProperties, isCarport);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (!carport)
            return TrainPanelShapes.FLOOR;
        /*if (pState.getValue(DOOR_HINGE) == DoorHingeSide.RIGHT)
            return TrainPanelShapes.EXTEND_CARPORT_CENTER;
        return TrainPanelShapes.CARPORT_CENTER;*/
        return TrainPanelShapes.EXTEND_CARPORT_CENTER;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
