package willow.train.kuayue.block.panels.block_entity;

import com.simibubi.create.content.equipment.clipboard.ClipboardCloneable;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.AllBlocks;

import java.util.List;

import static willow.train.kuayue.block.panels.TrainPanelBlock.*;

public class EditablePanelEntity extends SmartBlockEntity
        implements MenuProvider, ClipboardCloneable {

    public TrainPanelProperties.EditType editType = TrainPanelProperties.EditType.NONE;

    public Integer signColor = EditableTypeConstants.YELLOW;

    CompoundTag nbt;

    public EditablePanelEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EditablePanelEntity(BlockPos blockPos, BlockState state) {
        this(AllBlocks.EDITABLE_PANEL_ENTITY.getType(), blockPos, state);
        this.editType = state.getValue(EDIT_TYPE);
        this.signColor = state.getValue(SIGN_COLOR);
        nbt = new CompoundTag();
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
    }

    @Override
    public String getClipboardKey() {
        return null;
    }

    @Override
    public boolean writeToClipboard(CompoundTag tag, Direction side) {
        return false;
    }

    @Override
    public boolean readFromClipboard(CompoundTag tag, Player player, Direction side, boolean simulate) {
        return false;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
}
