package willow.train.kuayue.behaviour;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.block_entity.IContraptionMovementBlockEntity;

public class AnimationMovementBehaviour implements MovementBehaviour {

    @Override
    public void tick(MovementContext context) {
        BlockPos pos = context.localPos;
        BlockEntity be = context.contraption.presentBlockEntities.get(pos);
        BlockState state = context.state;
        if (be instanceof IContraptionMovementBlockEntity icmbe)
            icmbe.doMovement(context.contraption, pos, state, be);
    }

    @Nullable
    @Override
    public ItemStack canBeDisabledVia(MovementContext context) {
        return null;
    }

    @Override
    public boolean isActive(MovementContext context) {
        return true;
    }


    @Override
    public boolean mustTickWhileDisabled() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlockEntity() {
        return true;
    }
}
