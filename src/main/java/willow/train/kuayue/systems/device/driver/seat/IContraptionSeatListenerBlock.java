package willow.train.kuayue.systems.device.driver.seat;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import net.minecraft.world.entity.Entity;

public interface IContraptionSeatListenerBlock {
    public void onCurrentPlayerSitDownOnContraption(Entity passenger, AbstractContraptionEntity contraptionEntity);
    public void onCurrentPlayerStandUpOnContraption(Entity passenger, AbstractContraptionEntity contraptionEntity);
}
