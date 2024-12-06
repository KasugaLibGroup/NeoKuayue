package willow.train.kuayue.event.client;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.EntityMountEvent;
import willow.train.kuayue.systems.device.driver.seat.IContraptionSeatListenerBlock;

import java.util.Objects;

public class ClientPassengerEvent {
    public static void onMountEvent(EntityMountEvent entityMountEvent){
        if(!(entityMountEvent.getEntityBeingMounted() instanceof AbstractContraptionEntity contraptionEntity)){
            return;
        }
        if(!(entityMountEvent.getEntity() instanceof LocalPlayer localPlayer)){
            return;
        }
        if(!Objects.equals(Minecraft.getInstance().player, localPlayer)){
            return;
        }

        if(entityMountEvent.isMounting()){
            ClientTickScheduler.onNextTick(()->{
                BlockPos pos = contraptionEntity.getContraption().getSeatOf(localPlayer.getUUID());
                Block targetBlock = contraptionEntity.getContraption().getBlocks().get(pos).state.getBlock();
                if(!(targetBlock instanceof IContraptionSeatListenerBlock listenerBlock))
                    return;
                listenerBlock.onCurrentPlayerSitDownOnContraption(localPlayer, contraptionEntity);
            });
        }
    }
}
