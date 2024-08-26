package willow.train.kuayue.network.c2s;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/** 这是Packet的接口类，所有数据包类必须实现之 */
public interface KuayuePacket {

    /**
     * 处理的方法
     *
     * @param var1 网络包裹
     * @return handleSucceed? 是否成功处理?
     */
    boolean handle(NetworkEvent.Context var1);

    /**
     * 编码包裹的方法
     *
     * @param var1 空的buffer
     */
    void encode(FriendlyByteBuf var1);
}
