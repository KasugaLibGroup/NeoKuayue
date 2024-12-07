package willow.train.kuayue.mixins.mixin.client;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import willow.train.kuayue.block.seat.YZSeatBlock;
import willow.train.kuayue.systems.device.driver.seat.IDynamicSitBlock;

@Mixin(LivingEntityRenderer.class)
public class MixinLivingEntityRenderer {
    @Redirect(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isPassenger()Z")
    )
    private boolean isPassenger(LivingEntity instance){
        if(
                !(instance instanceof Player player) ||
                !(instance.getVehicle() instanceof AbstractContraptionEntity contraptionEntity)
        ){
            return instance.isPassenger();
        }
        Contraption contraption = contraptionEntity.getContraption();
        BlockPos seat = contraption.getSeatOf(player.getUUID());
        if(seat == null){
            return instance.isPassenger();
        }
        StructureTemplate.StructureBlockInfo info = contraption.getBlocks().get(seat);
        if(!(info.state.getBlock() instanceof YZSeatBlock seatBlock) || !(info.state.getBlock() instanceof IDynamicSitBlock dynamicSitBlock)){
            return instance.isPassenger();
        }
        int size = seatBlock.getSeatSize();
        for(int i=0;i<size;i++){
            if(info.nbt.getBoolean("hasSeat"+i) && info.nbt.getUUID("seat"+i).equals(player.getUUID())){
                return dynamicSitBlock.isSitDown(info.state, i);
            }
        }
        return instance.isPassenger();
    }
}
