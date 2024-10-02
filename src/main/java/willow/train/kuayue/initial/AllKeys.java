package willow.train.kuayue.initial;

import com.mojang.blaze3d.platform.InputConstants;
import kasuga.lib.registrations.client.KeyBindingReg;
import net.minecraftforge.client.settings.KeyModifier;
import willow.train.kuayue.systems.editable_panel.GetShareKeyHandler;

public class AllKeys {

    public static final KeyBindingReg getShareTemplateKey = new KeyBindingReg("get_share_template_key",
            "kuayue_key")
            .setKeycode(InputConstants.KEY_I, InputConstants.Type.KEYSYM)
            .setModifier(KeyModifier.NONE)
            .setClientHandler(GetShareKeyHandler.clientHandler)
            .setServerHandler(p -> {})
            .submit(AllElements.testRegistry);

    public static void invoke(){}
}
