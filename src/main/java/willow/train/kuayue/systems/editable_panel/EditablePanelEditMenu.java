package willow.train.kuayue.systems.editable_panel;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeMenuType;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllMenuScreens;

public class EditablePanelEditMenu extends AbstractContainerMenu {

    EditablePanelEntity editablePanelEntity;

    public EditablePanelEditMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);

        this.addDataSlot(
                new DataSlot() {
                    @Override
                    public int get() {
                        return 0;
                    }

                    @Override
                    public void set(int pValue) {}
                });
    }

    public EditablePanelEditMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
        this(containerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()),
                new SimpleContainerData(2));
    }

    public EditablePanelEditMenu(int containerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(AllMenuScreens.EDITABLE_PANEL.getMenuType(), containerId);
        this.editablePanelEntity = (EditablePanelEntity) entity;
    }

    public EditablePanelEditMenu setEditablePanelEntity(EditablePanelEntity editablePanelEntity) {
        this.editablePanelEntity = editablePanelEntity;
        return this;
    }

    public EditablePanelEntity getEditablePanelEntity() {
        return editablePanelEntity;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
