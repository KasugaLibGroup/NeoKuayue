package willow.train.kuayue.systems.device.driver.devices;

import kasuga.lib.core.menu.base.BindingClient;
import kasuga.lib.core.menu.base.GuiBinding;
import kasuga.lib.core.menu.base.GuiMenu;
import kasuga.lib.core.menu.base.GuiMenuType;
import kasuga.lib.core.menu.javascript.JavascriptMenu;
import kasuga.lib.core.menu.targets.Target;
import kasuga.lib.core.menu.targets.WorldRendererTarget;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.driver.combustion.TrainDataHandler;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class LKJ2000Menu extends JavascriptMenu {
    public LKJ2000Menu(GuiMenuType<?> type) {
        super(type);
    }

    @Override
    protected ResourceLocation getServerScriptLocation() {
        return AllElements.testRegistry.asResource("lkj_2000");
    }

    @Override
    protected GuiBinding createBinding(UUID id) {
        return
                new GuiBinding(id)
                        .execute(AllElements.testRegistry.asResource("lkj_2000"))
                        .with(Target.SCREEN);
    }

    @Override
    protected void createGuiInstance() {
        super.createGuiInstance();
        WorldRendererTarget.attach(this);
    }

    @Override
    protected void closeGuiInstance() {
        WorldRendererTarget.detach(this);
        super.closeGuiInstance();
    }

    public void provideTrainData(TrainDataHandler trainDataHandler) {
        provide("train", trainDataHandler);
    }
}
