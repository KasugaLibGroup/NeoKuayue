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

public class TrainSpeedSignUpdatePacket implements KuayuePacket {

    private final BlockPos pos;
    private final String content;
    private final int color;
    private final float x_offset, y_offset;
    private final boolean x_revert, y_revert;

    public TrainSpeedSignUpdatePacket(
            BlockPos pos,
            Component content,
            int color,
            float x_offset,
            float y_offset,
            boolean x_revert,
            boolean y_revert) {
        this.pos = pos;
        this.content = content.getString();
        this.color = color;
        this.x_offset = x_offset;
        this.y_offset = y_offset;
        this.x_revert = x_revert;
        this.y_revert = y_revert;
    }

    public TrainSpeedSignUpdatePacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.content = buf.readUtf();
        this.color = buf.readInt();
        this.x_offset = buf.readFloat();
        this.y_offset = buf.readFloat();
        this.x_revert = buf.readBoolean();
        this.y_revert = buf.readBoolean();
    }

    @Override
    public boolean handle(NetworkEvent.Context context) {
        context.enqueueWork(
                () -> {
                    ServerLevel level = (ServerLevel) context.getSender().level();
                    BlockEntity entity = level.getBlockEntity(pos);

                    if (entity instanceof EditablePanelEntity editablePanelEntity) {
                        if (editablePanelEntity.getEditType() == TrainPanelProperties.EditType.SPEED) {
//                            TODO BlockEntity中实现如下方法

//                            editablePanelEntity.setContent(
//                                    Component.literal(content));
//                            editablePanelEntity.setColor(color);
//                            editablePanelEntity.setX_offset(x_offset);
//                            editablePanelEntity.setY_offset(y_offset);
//                            editablePanelEntity.setX_revert(x_revert);
//                            editablePanelEntity.setY_revert(y_revert);
                            editablePanelEntity.setChanged();
                            editablePanelEntity.sendData();
                        }
                    } else {
                        return;
                    }
                });
        return true;
    }

    @Override
    public void encode(FriendlyByteBuf var1) {
        var1.writeBlockPos(pos);
        var1.writeUtf(content);
        var1.writeInt(color);
        var1.writeFloat(x_offset);
        var1.writeFloat(y_offset);
        var1.writeBoolean(x_revert);
        var1.writeBoolean(y_revert);
    }
}
