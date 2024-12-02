package willow.train.kuayue.systems.device;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;

public class EntityTrackingListener {
    public static void onEntityUnload(EntityLeaveLevelEvent event){
        if(event.getEntity() instanceof AbstractContraptionEntity contraptionEntity){
            contraptionEntity.getContraption().forEachActor(event.getLevel(), (actor, context) -> {
                if(actor instanceof IEntityTrackingMovementBehavior behavior){
                    behavior.onEntityLeave(context);
                }
            });
        }
    }
}
