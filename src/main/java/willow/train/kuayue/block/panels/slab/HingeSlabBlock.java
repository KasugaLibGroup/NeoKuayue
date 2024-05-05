package willow.train.kuayue.block.panels.slab;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;

public class HingeSlabBlock extends TrainSlabBlock implements IWrenchable {
    public HingeSlabBlock(Properties pProperties, boolean isCarport) {
        super(pProperties, isCarport);
        registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.EAST)
                .setValue(BlockStateProperties.DOOR_HINGE, DoorHingeSide.LEFT)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(BlockStateProperties.DOOR_HINGE));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(BlockStateProperties.DOOR_HINGE, TrainOpenableWindowBlock.getHinge(pContext));
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level world = context.getLevel();
        BlockState rotated = state.cycle(BlockStateProperties.DOOR_HINGE);
        if (!rotated.canSurvive(world, context.getClickedPos()))
            return InteractionResult.PASS;

        KineticBlockEntity.switchToBlockState(world, context.getClickedPos(), updateAfterWrenched(rotated, context));

        if (world.getBlockState(context.getClickedPos()) != state)
            playRotateSound(world, context.getClickedPos());

        return InteractionResult.SUCCESS;
    }


}
