package willow.train.kuayue.network.c2s.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.network.c2s.KuayuePacket;

public class CarriageNoSignUpdatePacket implements KuayuePacket {

    private final BlockPos pos;
    private final Component message;
    private final int color;

    private final boolean isLeftSide;

    public CarriageNoSignUpdatePacket(
            BlockPos pos, Component message, int color, boolean isLeftSide) {
        this.pos = pos;
        this.message = message;
        this.color = color;
        this.isLeftSide = isLeftSide;
    }

    public CarriageNoSignUpdatePacket(FriendlyByteBuf buffer) {
        this.pos = buffer.readBlockPos();
        this.color = buffer.readInt();
        this.message = Component.literal(buffer.readUtf());
        this.isLeftSide = buffer.getBoolean(0);
    }

    @Override
    public boolean handle(NetworkEvent.Context var1) {
        var1.enqueueWork(
                () -> {
                    ServerLevel level = var1.getSender().getLevel();
                    BlockEntity entity = level.getBlockEntity(pos);
                    if (entity == null) {
                        return;
                    }

                    if (entity instanceof EditablePanelEntity editablePanelEntity) {
                        if (editablePanelEntity.getEditType() == TrainPanelProperties.EditType.NUM) {
//                            TODO BlockEntity中实现如下方法

//                            editablePanelEntity.setMessage(message);
                            editablePanelEntity.setColor(color);
//                            editablePanelEntity.setLeftSide(isLeftSide);
                            editablePanelEntity.setChanged();
                            editablePanelEntity.sendData();
                        }
                    }
                });
        return true;
    }

    @Override
    public void encode(FriendlyByteBuf var1) {
        var1.writeBlockPos(pos);
        var1.writeInt(color);
        var1.writeUtf(message.getString());
        var1.writeBoolean(isLeftSide);
    }
}
