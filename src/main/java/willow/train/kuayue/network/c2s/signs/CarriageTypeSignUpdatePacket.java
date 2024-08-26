package willow.train.kuayue.network.c2s.signs;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.network.c2s.KuayuePacket;

public class CarriageTypeSignUpdatePacket implements KuayuePacket {
    private final BlockPos pos;

    private int color;
    private final String[] lines;

    /**
     * 这个构造器是给自己调用的时候用的
     *
     * @param pPos 方块位置(必填)
     * @param pLine0 这些都是选填，即你需要传递的其他数据
     * @param pLine1 选填
     * @param pLine2 选填
     * @param pLine3 选填
     * @param pLine4 选填
     */
    public CarriageTypeSignUpdatePacket(
            BlockPos pPos,
            String pLine0,
            String pLine1,
            String pLine2,
            String pLine3,
            String pLine4,
            int pColor) {
        this.pos = pPos;
        this.lines = new String[] {pLine0, pLine1, pLine2, pLine3, pLine4};
        this.color = pColor;
    }

    /**
     * 这个构造器是给fml自动注册用的
     *
     * @param pBuffer 传入的带有数据的buffer
     */
    public CarriageTypeSignUpdatePacket(FriendlyByteBuf pBuffer) {
        this.pos = pBuffer.readBlockPos();
        this.color = pBuffer.readInt();
        this.lines = new String[5];

        for (int i = 0; i < 5; ++i) {
            this.lines[i] = pBuffer.readUtf(384);
        }
    }

    public BlockPos getPos() {
        return this.pos;
    }

    /**
     * 这个方法是包裹里处理的方法，在这里，我们定义ServerSide的实体如何根据ClientSide的实体做出反应
     *
     * @param context 网络包裹
     * @return handleSucceed? 是否成功处理
     */
    @Override
    public boolean handle(NetworkEvent.Context context) {

        context.enqueueWork(
                () -> {
                    ServerLevel level = context.getSender().getLevel();
                    BlockEntity entity = level.getBlockEntity(pos);
                    if (entity == null) {
                        return;
                    }
                    if (entity instanceof EditablePanelEntity editablePanelEntity) {
                        if (editablePanelEntity.getEditType() == TrainPanelProperties.EditType.TYPE) {
//                            TODO BlockEntity中实现如下方法

//                            editablePanelEntity.setMessages(this.lines);
                            editablePanelEntity.setColor(color);
                            editablePanelEntity.setChanged();
                            editablePanelEntity.sendData();
                        }
                    }
                });
        return true;
    }

    /**
     * 这个方法非常重要，是两个构造器之间进行传递的方法 需要把第一个构造器的内容encode进一个FriendlyByteBuf里，如下例
     * 这个encode后的buffer将会传递给第二个构造器
     *
     * @param pBuffer 传入的空buffer
     */
    @Override
    public void encode(FriendlyByteBuf pBuffer) {
        pBuffer.writeBlockPos(this.pos);
        pBuffer.writeInt(color);

        for (int i = 0; i < 5; ++i) {
            pBuffer.writeUtf(this.lines[i]);
        }
    }
}
