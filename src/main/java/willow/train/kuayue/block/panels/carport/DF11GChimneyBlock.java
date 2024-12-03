package willow.train.kuayue.block.panels.carport;

import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.block.panels.block_entity.DF11GChimneyEntity;
import willow.train.kuayue.block.panels.slab.HingeSlabBlock;
import willow.train.kuayue.initial.AllBlocks;

public class DF11GChimneyBlock extends HingeSlabBlock implements IBE<DF11GChimneyEntity> {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public DF11GChimneyBlock(Properties properties, boolean isCarport) {
        super(properties, isCarport);
        registerDefaultState(this.getStateDefinition().any()
                .setValue(LIT, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(LIT));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext)
                .setValue(LIT, true);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if(!level.isClientSide()) {
            level.setBlock(pos,state.cycle(LIT),3);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if(!level.isClientSide()) {
            level.setBlock(pos,state.cycle(BlockStateProperties.DOOR_HINGE),3);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        spawnParticles(pState, pLevel, pPos);
    }

    private static void spawnParticles(BlockState pState, Level pLevel, BlockPos pPos) {
        RandomSource pRandom = pLevel.random;
        Direction direction = pState.getValue(FACING);

        if (pState.getValue(LIT)) {
            for (int i = 0; i < 3; i++) {
                if (pRandom.nextFloat() < 0.5f) {
                    pLevel.addParticle(
                            ParticleTypes.LARGE_SMOKE,
                            (double)pPos.getX() + 0.5D,
                            (double)pPos.getY() + 1.0D,
                            (double)pPos.getZ() + 0.5D,
                            (direction == Direction.EAST || direction == Direction.WEST) ?
                                    (double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F,
                            0.2F,
                            (direction == Direction.SOUTH || direction == Direction.NORTH) ?
                                    (double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F);
                }
                pLevel.addParticle(
                        ParticleTypes.LARGE_SMOKE,
                        (double)pPos.getX() + 0.5D,
                        (double)pPos.getY() + 1.0D,
                        (double)pPos.getZ() + 0.5D,
                        (direction == Direction.EAST || direction == Direction.WEST) ?
                                -(double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F,
                        0.2F,
                        (direction == Direction.SOUTH || direction == Direction.NORTH) ?
                                -(double)(0.05F + pRandom.nextFloat() / 10.0F) : 0.0F);
            }
        }
    }

    @Override
    public Class<DF11GChimneyEntity> getBlockEntityClass() {
        return DF11GChimneyEntity.class;
    }

    @Override
    public BlockEntityType<? extends DF11GChimneyEntity> getBlockEntityType() {
        return AllBlocks.DF11G_CHIMNEY_ENTITY.getType();
    }
}
