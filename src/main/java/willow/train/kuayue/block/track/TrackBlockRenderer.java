package willow.train.kuayue.block.track;

import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.content.trains.track.TrackShape;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import kasuga.lib.core.base.CustomBlockRenderer;
import kasuga.lib.core.create.SimpleTrackBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.openjdk.nashorn.internal.ir.Block;

import java.util.function.Supplier;

public class TrackBlockRenderer extends CustomBlockRenderer {
    private final PartialModel tie, left, right;

    public TrackBlockRenderer(Supplier<SimpleTrackBlock> blockSupplier, TrackMaterial material) {
        super(blockSupplier);
        tie = material.getModelHolder().tie();
        left = material.getModelHolder().segment_left();
        right = material.getModelHolder().segment_right();
    }

    @Override
    public void render(BlockState state, BlockPos pos, BlockAndTintGetter level,
                       PoseStack stack, VertexConsumer consumer, RenderType type, int light) {}
}
