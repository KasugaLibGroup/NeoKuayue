package willow.train.kuayue.systems.device.driver.seat;

import com.mojang.blaze3d.systems.RenderSystem;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

public class MinecraftUtil {
    public static Boolean localPlayerOn(Entity vehicle) {
        return vehicle.hasPassenger(Minecraft.getInstance().player);
    }

    public static void createDriverInteractiveScreen(AbstractContraptionEntity entity, Contraption contraption) {
        RenderSystem.recordRenderCall(()->Minecraft.getInstance().setScreen(new InteractiveDriveScreen(contraption, entity)));
    }

    public static void closeCurrentDriverInteractiveScreen() {
        if(
                Minecraft.getInstance().screen != null &&
                Minecraft.getInstance().screen instanceof InteractiveDriveScreen interactiveScreen
        ){
            RenderSystem.recordRenderCall(interactiveScreen::onClose);
        }
    }

    public static boolean isLocalPlayer(Entity passenger) {
        return Objects.equals(Minecraft.getInstance().player, passenger);
    }
}
