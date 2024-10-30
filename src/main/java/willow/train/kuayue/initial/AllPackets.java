package willow.train.kuayue.initial;

import kasuga.lib.core.network.Packet;
import kasuga.lib.registrations.common.ChannelReg;
import willow.train.kuayue.network.c2s.ColorTemplateC2SPacket;
import willow.train.kuayue.network.c2s.DiscardChangeC2SPacket;
import willow.train.kuayue.network.c2s.NbtC2SPacket;
import willow.train.kuayue.network.s2c.ColorTemplateS2CPacket;
import willow.train.kuayue.network.s2c.ContraptionTagChangedPacket;

import java.util.LinkedList;

public class AllPackets {
    public static final String KUAYUE_NETWORK_VERSION = "v1.0.0";

    public static final ChannelReg CHANNEL = new ChannelReg("kuayue_main_channel")
            .brand(KUAYUE_NETWORK_VERSION)
            .loadPacket(ContraptionTagChangedPacket.class, ContraptionTagChangedPacket::new)
            .loadPacket(ColorTemplateS2CPacket.class, ColorTemplateS2CPacket::new)
            .loadPacket(ColorTemplateC2SPacket.class, ColorTemplateC2SPacket::new)
            .loadPacket(DiscardChangeC2SPacket.class, DiscardChangeC2SPacket::new)
            .loadPacket(NbtC2SPacket.class, NbtC2SPacket::new)
            .submit(AllElements.testRegistry);

    public static void invoke() {}
}
