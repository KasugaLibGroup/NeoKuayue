package willow.train.kuayue.systems.device.driver.seat;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import kasuga.lib.core.client.interaction.GuiOperatingPerspectiveScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.OnSeatActionPacket;
import willow.train.kuayue.network.c2s.SeatDismountPacket;

import java.util.HashSet;
import java.util.Set;

public class InteractiveDriveScreen extends GuiOperatingPerspectiveScreen {
    private final Contraption contraption;
    private final AbstractContraptionEntity entity;

    private final Set<InteractiveBehaviour.MenuEntry> interactiveSet = new HashSet<>();

    public InteractiveDriveScreen(
            Contraption contraption,
            AbstractContraptionEntity entity
    ) {
        super();
        this.contraption = contraption;
        this.entity = entity;
        contraption.forEachActor(contraption.entity.level, (behaviour, context) -> {
            if(behaviour instanceof InteractiveBehaviour interactiveBehaviour){
                interactiveSet.addAll(interactiveBehaviour.getMenusOf(context));
            }
        });
    }

    @Override
    public void tick() {
        if(
                contraption.disassembled ||
                entity.isRemoved() ||
                Minecraft.getInstance().player == null ||
                !entity.hasPassenger(Minecraft.getInstance().player)
        ){
            this.onClose();
            return;
        }
        MutableComponent component = Component.literal("Menus: ");
        interactiveSet.forEach((i)->component.append(i.displayName()).append(";"));
        Minecraft.getInstance().player.displayClientMessage(component, true);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if(pKeyCode == 256){
            AllPackets.INTERACTION.sendToServer(new SeatDismountPacket());
            this.onClose();
            return true;
        }
        // "A"
        if(pKeyCode == 30 || pKeyCode == 32){
            AllPackets.INTERACTION.sendToServer(new OnSeatActionPacket(DriverSeatActionType.WATCHING_DOOR));
            return true;
        }
        if(pKeyCode == 57){
            AllPackets.INTERACTION.sendToServer(new OnSeatActionPacket(DriverSeatActionType.STAND));
            return true;
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
        if(pKeyCode == 30 || pKeyCode == 32) {
            AllPackets.INTERACTION.sendToServer(new OnSeatActionPacket(DriverSeatActionType.SIT_DOWN));
        }
        return super.keyReleased(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public void onClose() {
        if(Minecraft.getInstance().screen != this)
            return;
        super.onClose();
    }
}
