package willow.train.kuayue.mixins.mixin;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import willow.train.kuayue.block.panels.door.TrainDoorBlock;

@Mixin(SimpleBlockMovingInteraction.class)
public abstract class MixinSimpleBlockMovingInteraction {

    @Shadow
    protected abstract BlockState handle(Player player, Contraption contraption, BlockPos pos,
                                         BlockState currentState);

    @Shadow
    public abstract boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos,
                                                    AbstractContraptionEntity contraptionEntity);

    @Inject(method = "handlePlayerInteraction", at = @At(value = "RETURN"), remap = false)
    public void doHandle(Player player, InteractionHand activeHand, BlockPos localPos, AbstractContraptionEntity contraptionEntity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) return;
        StructureTemplate.StructureBlockInfo info = contraptionEntity.getContraption().getBlocks().get(localPos);
        if (!(info.state().getBlock() instanceof TrainDoorBlock door && door.endPos.y > 1)) return;
        handlePlayerInteraction(player, activeHand, localPos.above(), contraptionEntity);
    }
}
