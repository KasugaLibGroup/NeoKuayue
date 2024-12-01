package willow.train.kuayue.systems.device.driver.combustion;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.systems.device.AllDeviceBlockEntities;

public class InternalCombustionDriveControllerBlock extends Block implements IBE<InternalCombustionDriveControllerBlockEntity> {
    public InternalCombustionDriveControllerBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    @Override
    public Class<InternalCombustionDriveControllerBlockEntity> getBlockEntityClass() {
        return InternalCombustionDriveControllerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends InternalCombustionDriveControllerBlockEntity> getBlockEntityType() {
        return AllDeviceBlockEntities.INTERNAL_COMBUSTION_DRIVE_CONTROLLER_BLOCK_ENTITY.getType();
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state, level, pos, newState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    private static VoxelShape EMPTY_SHAPE = Block.box(0, 0, 0, 0.1, 0.1, 0.1);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pContext.isHoldingItem(AllItems.WRENCH.get()) ? super.getShape(pState, pLevel, pPos, pContext) : EMPTY_SHAPE;
    }
}
