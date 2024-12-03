package willow.train.kuayue.systems.device;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;

public interface IEntityTrackingMovementBehavior {
    default void onEntityLoad(MovementContext context){}
    default void onEntityLeave(MovementContext context){}
}
