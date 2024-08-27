package willow.train.kuayue.block.panels.door;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.base.TrainPanelShapes;
import willow.train.kuayue.block.panels.block_entity.DoubleDoorEntity;
import willow.train.kuayue.block.panels.end_face.TrainEndfaceBlock;
import willow.train.kuayue.initial.AllBlocks;

public class DoubleDoorBlock extends TrainEndfaceBlock implements IBE<DoubleDoorEntity> {

    public final Couple<PartialModel> models;
    public final PartialModel frameModel;

    public DoubleDoorBlock(Properties pProperties, Vec2 beginPos, Vec2 endPos, TrainPanelProperties.DoorType doorType,
                           String frameModel, String leftModel, String rightModel) {
        super(pProperties, beginPos, endPos, doorType);
        this.models = Couple.create(
                leftModel == null ? null : new PartialModel(new ResourceLocation(Kuayue.MODID,"block/" + leftModel)),
                rightModel == null ? null : new PartialModel(new ResourceLocation(Kuayue.MODID,"block/" + rightModel)));
        this.frameModel = new PartialModel(new ResourceLocation(Kuayue.MODID, "block/" + frameModel));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pPos, BlockState pState) {
        return getBlockEntityType().create(pPos, pState);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.getDoubleDoorCloseShape(pState.getValue(FACING).getOpposite());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TrainPanelShapes.getDoubleDoorShape(pState.getValue(FACING).getOpposite(), pState.getValue(OPEN));
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public Class<DoubleDoorEntity> getBlockEntityClass() {
        return DoubleDoorEntity.class;
    }

    @Override
    public BlockEntityType<? extends DoubleDoorEntity> getBlockEntityType() {
        return AllBlocks.DOUBLE_DOOR_ENTITY.getType();
    }
}
