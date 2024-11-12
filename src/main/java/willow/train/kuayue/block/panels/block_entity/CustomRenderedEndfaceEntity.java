package willow.train.kuayue.block.panels.block_entity;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.end_face.CustomRenderedEndfaceBlock;
import willow.train.kuayue.initial.AllBlocks;

import java.util.List;

public class CustomRenderedEndfaceEntity extends SmartBlockEntity implements IContraptionMovementBlockEntity {

    public boolean open = false;
    public TrainPanelProperties.DoorType type = TrainPanelProperties.DoorType.ROTATE;
    public float counter = 0;
    public CustomRenderedEndfaceEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public CustomRenderedEndfaceEntity(BlockPos pos, BlockState state) {
        this(AllBlocks.CUSTOM_RENDERED_ENDFACE_ENTITY.getType(), pos, state);
    }

    // 向磁盘写入数据
    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putBoolean("open", open);
        tag.putString("type", type.getSerializedName());
    }

    // 从磁盘读取数据
    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        open = tag.getBoolean("open");
        type = TrainPanelProperties.DoorType.fromString(tag.getString("type"));
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isRotateDoor() {
        return type == TrainPanelProperties.DoorType.ROTATE ||
                type == TrainPanelProperties.DoorType.ROTATE_SINGLE_SIDED;
    }

    public boolean isSingleSided() {
        return type == TrainPanelProperties.DoorType.ROTATE_SINGLE_SIDED;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    public void tick() {
        if(level == null) return;
        BlockState state = level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof CustomRenderedEndfaceBlock block)) return;
        this.open = level.getBlockState(this.getBlockPos()).getValue(DoorBlock.OPEN);
        this.type = block.DOOR_TYPE;
    }

    public Couple<PartialModel> getModels() {
        if (this.level == null) return null;
        BlockState state = this.level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof CustomRenderedEndfaceBlock block)) return null;
        return block.models;
    }

    public PartialModel getFrameModel() {
        if (this.level == null) return null;
        BlockState state = this.level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof CustomRenderedEndfaceBlock block)) return null;
        return block.frameModel;
    }

    @Override
    public void doMovement(Contraption contraption, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        ((CustomRenderedEndfaceEntity) blockEntity).setOpen(contraption.getBlocks().get(blockPos).state.getValue(DoorBlock.OPEN));
        contraption.presentBlockEntities.put(blockPos, blockEntity);
    }

    @Override
    public boolean dirty(Contraption contraption, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        return open != blockState.getValue(DoorBlock.OPEN);
    }
}
