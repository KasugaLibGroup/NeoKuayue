package willow.train.kuayue.block.panels.block_entity;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.decoration.bracket.BracketedBlockEntityBehaviour;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.behaviour.ChimneyMovementBehaviour;
import willow.train.kuayue.initial.AllBlocks;

import java.util.List;

public class DF11GChimneyBlockEntity extends SmartBlockEntity implements IContraptionMovementBlockEntity{
    public DF11GChimneyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public DF11GChimneyBlockEntity(BlockPos pos, BlockState state) {
        this(AllBlocks.DF11G_CHIMNEY_BLOCK_ENTITY.getType(), pos, state);
    }

    @Override
    public void update(StructureTemplate.StructureBlockInfo info, Player player, BlockPos pos, AbstractContraptionEntity entity) {

    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        // behaviours.add(new ChimneyMovementBehaviour());
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return AABB.ofSize(Vec3.atCenterOf(this.getBlockPos()), 5, 5, 5);
    }
}
