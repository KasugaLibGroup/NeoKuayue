package willow.train.kuayue.block.panels.block_entity;

import com.simibubi.create.content.equipment.clipboard.ClipboardCloneable;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.network.KuayueNetworkHandler;
import willow.train.kuayue.network.c2s.signs.CarriageTypeSignUpdatePacket;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditMenu;
import willow.train.kuayue.systems.editable_panel.interfaces.DefaultTextsLambda;
import willow.train.kuayue.systems.editable_panel.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.systems.editable_panel.PanelColorType;
import willow.train.kuayue.systems.editable_panel.SignType;

import java.util.List;

import static willow.train.kuayue.block.panels.TrainPanelBlock.*;

public class EditablePanelEntity extends SmartBlockEntity
        implements MenuProvider, ClipboardCloneable {

    private TrainPanelProperties.EditType editType = TrainPanelProperties.EditType.NONE;
    private Integer signColor = EditableTypeConstants.YELLOW;
    private EditablePanelEditMenu panelEditMenu;
    private CompoundTag nbt;

    public EditablePanelEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EditablePanelEntity(BlockPos blockPos, BlockState state) {
        this(AllBlocks.EDITABLE_PANEL_ENTITY.getType(), blockPos, state);
        this.editType = state.getValue(EDIT_TYPE);
        nbt = new CompoundTag();
        // 获取默认的nbt数据
        getDefaultNbt(state);
    }

    // 修改这里，给SignType加上一个生成默认字段的lambda
    // 具体做法：生成几个String, 使用nbt.putString(Str name, Str value);
    // 把值传进去。
    private void getDefaultNbt(BlockState state) {
        // 根据blockstate中的blockTag获取到对应的车厢标识颜色
        PanelColorType panelColorType = EditableTypeConstants.getColorTypeByTag(state);
        if (panelColorType == null)
            return;
        // 将标识颜色放入nbt
        nbt.putInt("color", panelColorType.signColor);
        // 根据自定义类型获取到对应的SignType类对象，对象中包含了渲染方法的lambda，以及默认字段的lambda。
        SignType signType = EditableTypeConstants.getSignTypeByEditType(editType);
        if (signType == null)
            return;
        // 通过signType设置nbt的默认字段与数据
        DefaultTextsLambda defaultTextsLambda = signType.getDefaultTexts().get();
        defaultTextsLambda.defaultTextComponent(this, state, this.nbt);
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        this.nbt = tag.getCompound("data");
        signColor = nbt.getInt("color");
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        tag.put("data", nbt);
    }

    public TrainPanelProperties.EditType getEditType() {
        return editType;
    }

    public Integer getSignColor() {
        return signColor;
    }

    @Override
    public String getClipboardKey() {
        return "edit_panel";
    }

    // 这个方法向机械动力剪贴板中写入nbt
    @Override
    public boolean writeToClipboard(CompoundTag tag, Direction side) {
        return false;
    }

    // 这个方法从机械动力剪贴板中读取nbt
    @Override
    public boolean readFromClipboard(CompoundTag tag, Player player, Direction side, boolean simulate) {
        return false;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("kuayue:editable_panel");
    }

    public int getColor() {
        return this.nbt.getInt("color");
    }

    public boolean setColor(int mark) {
        if (mark != this.getColor()) {
            this.nbt.putInt("color", mark);
            return true;
        } else {
            return false;
        }
    }

    public CompoundTag getNbt() {
        return nbt;
    }

    public void markUpdated(boolean isClientSide) {
        this.setChanged();
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        write(tag, false);
        return tag;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        this.panelEditMenu =
                new EditablePanelEditMenu(pContainerId, pPlayerInventory, this, new SimpleContainerData(2));
        this.panelEditMenu.setEditablePanelEntity(this);
        return this.panelEditMenu;
    }
}
