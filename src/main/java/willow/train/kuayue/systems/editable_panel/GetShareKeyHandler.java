package willow.train.kuayue.systems.editable_panel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.NamedGuiOverlay;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.systems.editable_panel.overlay.GetShareOverlay;
import willow.train.kuayue.systems.editable_panel.screens.GetShareTemplateScreen;

import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class GetShareKeyHandler {

    public static final Consumer<LocalPlayer> clientHandler = player -> {
        NamedGuiOverlay overlay = GuiOverlayManager.findOverlay(AllElements.testRegistry.asResource("get_template_share_overlay"));
        GetShareOverlay screen = (GetShareOverlay) overlay.overlay();
        if (screen == null) return;
        ColorTemplate template = screen.getTemplate();
        if (template == null) return;
        GetShareTemplateScreen gsts = new GetShareTemplateScreen(Component.empty(), screen.getTemplate());
        gsts.init();
        Minecraft minecraft = Minecraft.getInstance();
        gsts.onCancelClick((w, x, y) -> {
            minecraft.setScreen(null);
        });
        gsts.onAcceptClick((w, x, y) -> {
            ClientInit.COLOR_TEMPLATES.addTemplate(template);
            minecraft.setScreen(null);
            ClientInit.COLOR_TEMPLATES.writeToFile();
        });
        minecraft.setScreen(gsts);
    };
}
