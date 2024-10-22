package willow.train.kuayue.behaviour;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.MovingInteractionBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.block.panels.base.CompanyTrainBlockEntity;

public class CompanyClickBehaviour extends MovingInteractionBehaviour {

    @Override
    public boolean handlePlayerInteraction
            (Player player, InteractionHand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
        Contraption contraption = contraptionEntity.getContraption();
        CompoundTag tag = contraption.getBlocks().get(localPos).nbt();
        BlockPos offset = new BlockPos(tag.getInt("px"), tag.getInt("py"), tag.getInt("pz"));
        BlockPos parentPos = localPos.offset(offset);
        if (parentPos.equals(localPos)) return true;
        return contraptionEntity.handlePlayerInteraction(player, parentPos, Direction.DOWN, activeHand);
    }
}
