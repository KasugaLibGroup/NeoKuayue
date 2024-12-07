package willow.train.kuayue.systems.device.driver.seat;

import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public interface IContraptionActionSeatBlock {
    public void onAction(ServerPlayer player, Contraption contraption, BlockPos blockPos, StructureTemplate.StructureBlockInfo info, DriverSeatActionType actionType);
}
