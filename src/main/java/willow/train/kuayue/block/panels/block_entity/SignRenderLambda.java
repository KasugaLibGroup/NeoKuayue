package willow.train.kuayue.block.panels.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sk89q.worldedit.world.block.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

@FunctionalInterface
public interface SignRenderLambda<T extends BlockEntity> {

    void render(T blockEntity, BlockState state, CompoundTag nbt, PoseStack stack,
                MultiBufferSource bufferSource, int light, int overlay);
}
