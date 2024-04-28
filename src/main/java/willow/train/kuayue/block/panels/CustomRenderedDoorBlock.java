package willow.train.kuayue.block.panels;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorShapes;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import willow.train.kuayue.block.panels.block_entity.ContraptionAnimatableBlockEntity;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedDoorEntity;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.initial.AllElements;

import java.util.List;

public class CustomRenderedDoorBlock extends TrainDoorBlock implements IBE<CustomRenderedDoorEntity> {
    boolean isSlideDoor;
    final Couple<PartialModel> leftDoorModels;
    final Couple<PartialModel> rightDoorModels;
    final RenderShape renderShape;

    public CustomRenderedDoorBlock(Properties pProperties,
                                   Couple<ResourceLocation> leftDoorModels,
                                   Couple<ResourceLocation> rightDoorModels,
                                   RenderShape renderShape, boolean isSlideDoor) {
        super(pProperties);
        this.leftDoorModels = Couple.create(block(leftDoorModels.get(true).getPath()), block(leftDoorModels.get(false).getPath()));
        this.rightDoorModels = Couple.create(block(rightDoorModels.get(true).getPath()), block(rightDoorModels.get(false).getPath()));
        this.renderShape = renderShape;
        this.isSlideDoor = isSlideDoor;
    }

    public CustomRenderedDoorBlock(Properties pProperties,
                                   CustomRenderedDoorBlock modelFrom,
                                   RenderShape renderShape, boolean isSlideDoor) {
        super(pProperties);
        this.leftDoorModels = modelFrom.leftDoorModels;
        this.rightDoorModels = modelFrom.rightDoorModels;
        this.renderShape = renderShape;
        this.isSlideDoor = isSlideDoor;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(!isSlideDoor || !pState.getValue(OPEN))
            return super.getShape(pState, pLevel, pPos, pContext);
        else
            return SlidingDoorShapes.get(pState.getValue(DoorBlock.FACING), pState.getValue(DoorBlock.HINGE) == DoorHingeSide.RIGHT, false);
    }

    public Couple<PartialModel> getLeftDoorModels() {
        return leftDoorModels;
    }

    public Couple<PartialModel> getRightDoorModels() {
        return rightDoorModels;
    }

    private static PartialModel block(String path) {
        return new PartialModel(AllElements.testRegistry.asResource("block/" + path));
    }

    @Override
    public boolean isPathfindable(
            BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return true;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }

    @Override
    public Class<CustomRenderedDoorEntity> getBlockEntityClass() {
        return CustomRenderedDoorEntity.class;
    }

    @Override
    public BlockEntityType<CustomRenderedDoorEntity> getBlockEntityType() {
        return AllBlocks.CUSTOM_RENDERED_DOOR_ENTITY.getType();
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return renderShape;
    }

    public boolean isSlideDoor() {
        return isSlideDoor;
    }
}
