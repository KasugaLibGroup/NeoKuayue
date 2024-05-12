package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.ChannelReg;
import willow.train.kuayue.network.ContraptionTagChangedPacket;

public class AllPackets {


    public static final ChannelReg CHANNEL = new ChannelReg("kuayue_channel")
            .brand("v0.4.0")
            .loadPacket(ContraptionTagChangedPacket.class, ContraptionTagChangedPacket::new)
            .submit(AllElements.testRegistry);
    public static void invoke() {}
}
