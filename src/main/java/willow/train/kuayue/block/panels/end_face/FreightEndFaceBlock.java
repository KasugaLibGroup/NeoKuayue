package willow.train.kuayue.block.panels.end_face;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.EndFaceShapes;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;

public class FreightEndFaceBlock extends TrainPanelBlock {

    private final FreightType FREIGHT_TYPE;

    public FreightEndFaceBlock(Properties properties, FreightType freightType) {
        super(properties);
        this.FREIGHT_TYPE = freightType;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (FREIGHT_TYPE) {
            case C70 -> EndFaceShapes.getC70EndFaceShape(pState.getValue(FACING).getOpposite());
            case NX70 -> EndFaceShapes.getNX70EndFaceShape(pState.getValue(FACING).getOpposite());
            default -> EndFaceShapes.getC70EndFaceShape(pState.getValue(FACING).getOpposite());
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }

    public enum FreightType {
        C70, NX70;
    }
}
