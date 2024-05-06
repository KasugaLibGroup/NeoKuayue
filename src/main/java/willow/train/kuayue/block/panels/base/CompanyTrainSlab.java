package willow.train.kuayue.block.panels.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CompanyTrainSlab extends CompanyTrainPanel {
    public final boolean isCarport;
    public CompanyTrainSlab(Properties pProperties, boolean isCarport) {
        super(pProperties);
        this.isCarport = isCarport;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return isCarport ? TrainPanelShapes.CARPORT_CENTER : TrainPanelShapes.FLOOR;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }
}
