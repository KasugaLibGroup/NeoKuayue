package willow.train.kuayue.block.panels.window;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.TrainPanelBlock;

public class TrainUnOpenableWindowBlock extends TrainPanelBlock {
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public TrainUnOpenableWindowBlock(Properties pProperties, int wide) {
        super(pProperties, new Vec2(-wide + 1, 0), new Vec2(1, 1));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TrainPanelBlock.FACING, Direction.EAST)
                .setValue(HINGE, DoorHingeSide.LEFT)
        );
    }

    public TrainUnOpenableWindowBlock(Properties pProperties, int beginPosX, int endPosX) {
        super(pProperties, new Vec2(beginPosX, 0), new Vec2(endPosX, 1));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TrainPanelBlock.FACING, Direction.EAST)
                .setValue(HINGE, DoorHingeSide.LEFT)
        );
    }

    public TrainUnOpenableWindowBlock(Properties pProperties, int wide, int beginPosY, int endPosY) {
        super(pProperties, new Vec2(-wide + 1, beginPosY), new Vec2(1, endPosY));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(TrainPanelBlock.FACING, Direction.EAST)
                .setValue(HINGE, DoorHingeSide.LEFT)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(HINGE));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(HINGE, TrainOpenableWindowBlock.getHinge(pContext));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        return InteractionResult.PASS;
    }
}
