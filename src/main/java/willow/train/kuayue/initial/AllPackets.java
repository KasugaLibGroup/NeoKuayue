package willow.train.kuayue.initial;

import kasuga.lib.core.network.Packet;
import kasuga.lib.registrations.common.ChannelReg;
import willow.train.kuayue.network.ColorTemplateC2SPacket;
import willow.train.kuayue.network.ColorTemplateS2CPacket;
import willow.train.kuayue.network.ContraptionTagChangedPacket;

import java.util.LinkedList;

public class AllPackets {
    public static final LinkedList<Packet> CACHE = new LinkedList<>();
    public static final String KUAYUE_NETWORK_VERSION = "v0.4.0";

    public static final ChannelReg CHANNEL = new ChannelReg("kuayue_channel")
            .brand(KUAYUE_NETWORK_VERSION)
            .loadPacket(ContraptionTagChangedPacket.class, ContraptionTagChangedPacket::new)
            .submit(AllElements.testRegistry);

    public static final ChannelReg TEMPLATE = new ChannelReg("kuayue_template_channel")
            .brand(KUAYUE_NETWORK_VERSION)
            .loadPacket(ColorTemplateS2CPacket.class, ColorTemplateS2CPacket::new)
            .loadPacket(ColorTemplateC2SPacket.class, ColorTemplateC2SPacket::new)
            .submit(AllElements.testRegistry);
    public static void invoke() {}
}
