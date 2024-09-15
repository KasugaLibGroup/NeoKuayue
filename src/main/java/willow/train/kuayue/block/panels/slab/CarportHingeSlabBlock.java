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
        switch (pState.getValue(FACING)) {
            case EAST -> { return TrainPanelShapes.EXTEND_CARPORT_CENTER_EAST; }
            case WEST -> { return TrainPanelShapes.EXTEND_CARPORT_CENTER_WEST; }
            case SOUTH -> { return TrainPanelShapes.EXTEND_CARPORT_CENTER_SOUTH; }
            case NORTH -> { return TrainPanelShapes.EXTEND_CARPORT_CENTER_NORTH; }
            default -> { return TrainPanelShapes.EXTEND_CARPORT_CENTER_EAST; }
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
