package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.EntityReg;
import willow.train.kuayue.block.seat.M1SeatEntity;
import willow.train.kuayue.block.seat.SeatEntityRenderer;

public class AllEntities {

    public static final EntityReg<M1SeatEntity> M1_SEAT =
            new EntityReg<M1SeatEntity>("m1_seat")
                    .entityType(M1SeatEntity::new)
                    .size(.5f, .5f)
                    .withRenderer(() -> SeatEntityRenderer::new)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
