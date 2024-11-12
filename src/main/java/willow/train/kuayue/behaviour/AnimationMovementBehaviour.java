package willow.train.kuayue.behaviour;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.block_entity.IContraptionMovementBlockEntity;

public class AnimationMovementBehaviour implements MovementBehaviour {

    @Override
    public boolean mustTickWhileDisabled() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlockEntity() {
        return true;
    }
}
