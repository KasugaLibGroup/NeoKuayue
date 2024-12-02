package willow.train.kuayue.systems.device;

import kasuga.lib.registrations.create.MovementReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.device.driver.combustion.InternalCombustionDriveControllerBlock;
import willow.train.kuayue.systems.device.driver.combustion.InternalCombustionDriveControllerMovementBehavior;

public class AllDevicesMovementBehaviors {
    public static final MovementReg<InternalCombustionDriveControllerMovementBehavior> INTERNAL_COMBUSTION_CONTROLLER =
            new MovementReg<InternalCombustionDriveControllerMovementBehavior>("internal_combustion_controller_behaviour")
                    .behaviour(new InternalCombustionDriveControllerMovementBehavior())
                    .statePredicate(
                            state -> state.getBlock() instanceof InternalCombustionDriveControllerBlock
                    )
                    .submit(AllElements.testRegistry);
    public static void invoke(){}
}
