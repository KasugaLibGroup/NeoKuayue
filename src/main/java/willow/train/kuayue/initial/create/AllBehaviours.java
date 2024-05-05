package willow.train.kuayue.initial.create;

import com.simibubi.create.foundation.block.IBE;
import kasuga.lib.registrations.create.InteractionReg;
import kasuga.lib.registrations.create.MovementReg;
import willow.train.kuayue.behaviour.AnimationMovementBehaviour;
import willow.train.kuayue.behaviour.CompanyClickBehaviour;
import willow.train.kuayue.block.panels.block_entity.IContraptionMovementBlockEntity;
import willow.train.kuayue.initial.AllBlocks;
import willow.train.kuayue.initial.AllElements;

public class AllBehaviours {

    public static final InteractionReg<CompanyClickBehaviour> COMPANY_CLICK_BEHAVIOUR =
            new InteractionReg<CompanyClickBehaviour>("company_click_behaviour")
                    .behaviour(new CompanyClickBehaviour())
                    .sortByBlocks(
                            AllBlocks.COMPANY_TRAIN_PANEL,
                            AllBlocks.COMPANY_TRAIN_DOOR,
                            AllBlocks.COMPANY_SLIDING_DOOR
                    )
                    .submit(AllElements.testRegistry);

    public static final MovementReg<AnimationMovementBehaviour> ANIMATION_MOVEMENT_BEHAVIOUR =
            new MovementReg<AnimationMovementBehaviour>("animation_movement_behaviour")
                    .behaviour(new AnimationMovementBehaviour())
                    .statePredicate(
                            state -> state.getBlock() instanceof IBE<?> ibe &&
                                    IContraptionMovementBlockEntity.class.isAssignableFrom(ibe.getBlockEntityClass())
                    )
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
