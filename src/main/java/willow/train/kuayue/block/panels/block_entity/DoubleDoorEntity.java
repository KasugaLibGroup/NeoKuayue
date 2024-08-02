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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.door.DoubleDoorBlock;
import willow.train.kuayue.initial.AllBlocks;

import java.util.List;

public class DoubleDoorEntity extends SmartBlockEntity implements IContraptionMovementBlockEntity{

    public boolean open = false;

    public TrainPanelProperties.DoorType type = TrainPanelProperties.DoorType.SLIDE;

    public float counter = 0;

    public DoubleDoorEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DoubleDoorEntity(BlockPos pos, BlockState state) {
        this(AllBlocks.DOUBLE_DOOR_ENTITY.getType(), pos, state);
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.putBoolean("open", open);
        tag.putString("type", type.getSerializedName());
    }

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
        return type == TrainPanelProperties.DoorType.ROTATE;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

    @Override
    public void tick() {
        if(level == null) return;
        BlockState state = level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof DoubleDoorBlock block)) return;
        this.open = level.getBlockState(this.getBlockPos()).getValue(DoorBlock.OPEN);
        this.type = block.DOOR_TYPE;
    }

    public Couple<PartialModel> getModels() {
        if (this.level == null) return null;
        BlockState state = this.level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof DoubleDoorBlock block)) return null;
        return block.models;
    }

    public PartialModel getFrameModel() {
        if (this.level == null) return null;
        BlockState state = this.level.getBlockState(this.getBlockPos());
        if (!(state.getBlock() instanceof DoubleDoorBlock block)) return null;
        return block.frameModel;
    }

    @Override
    public void doMovement(Contraption contraption, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity) {
        ((DoubleDoorEntity) blockEntity).setOpen(contraption.getBlocks().get(blockPos).state.getValue(DoorBlock.OPEN));
        contraption.presentBlockEntities.put(blockPos, blockEntity);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        // 以当前方块实体为中心，5×5×5格范围内均渲染方块实体。
        return AABB.ofSize(Vec3.atCenterOf(this.getBlockPos()), 5, 5, 5);
    }
}
