package willow.train.kuayue.block.panels.block_entity;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.initial.AllBlocks;

import java.util.List;

public class CustomRenderedDoorEntity extends SmartBlockEntity implements IContraptionMovementBlockEntity {
    boolean isSlideDoor = false, open = false;
    Vec3 offset;
    public float animation_controller;

    public CustomRenderedDoorEntity(BlockPos pPos, BlockState pBlockState) {
        super(AllBlocks.CUSTOM_RENDERED_DOOR_ENTITY.getType(), pPos, pBlockState);
        if(pBlockState.getBlock() instanceof CustomRenderedDoorBlock block) {
            this.isSlideDoor = block.isSlideDoor();
            this.offset = block.getOffset();
        }
    }

    public boolean isLeftSide(BlockState state) {
        return state.getValue(DoorBlock.HINGE) == DoorHingeSide.LEFT;
    }


    public boolean isSlideDoor() {
        return isSlideDoor;
    }

    @Override
    public void tick() {
        if(level == null) return;
        if(this.getBlockState().getBlock() instanceof CustomRenderedDoorBlock)
            this.open = level.getBlockState(this.getBlockPos()).getValue(DoorBlock.OPEN);
    }

    public boolean isOpen() {
        return open;
    }

    @OnlyIn(Dist.CLIENT)
    public Couple<PartialModel> getLeftModels(BlockState state) {
        return ((CustomRenderedDoorBlock) state.getBlock()).getLeftDoorModels();
    }

    @OnlyIn(Dist.CLIENT)
    public Couple<PartialModel> getRightModels(BlockState state) {
        return ((CustomRenderedDoorBlock) state.getBlock()).getRightDoorModels();
    }

    @OnlyIn(Dist.CLIENT)
    public Couple<PartialModel> getModels(BlockState state) {
        return isLeftSide(state) ? getLeftModels(state) : getRightModels(state);
    }

    public Vec3 getOffset() {
        return offset;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    public void doMovement(Contraption contraption, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        ((CustomRenderedDoorEntity) blockEntity).setOpen(blockState.getValue(DoorBlock.OPEN));
        contraption.presentBlockEntities.put(blockPos, blockEntity);
    }

    @Override
    public boolean dirty(Contraption contraption, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        return open != blockState.getValue(DoorBlock.OPEN);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return AABB.ofSize(Vec3.atCenterOf(this.getBlockPos()), 5, 5, 5);
    }
}
