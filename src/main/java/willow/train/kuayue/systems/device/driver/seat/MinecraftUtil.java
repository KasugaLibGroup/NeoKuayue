package willow.train.kuayue.systems.device.driver.seat;

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
        Minecraft.getInstance().setScreen(new InteractiveDriveScreen(contraption, entity));
    }

    public static void closeCurrentDriverInteractiveScreen() {
        if(
                Minecraft.getInstance().screen != null &&
                Minecraft.getInstance().screen instanceof InteractiveDriveScreen interactiveScreen
        ){
            interactiveScreen.onClose();
        }
    }

    public static boolean isLocalPlayer(Entity passenger) {
        return Objects.equals(Minecraft.getInstance().player, passenger);
    }
}
