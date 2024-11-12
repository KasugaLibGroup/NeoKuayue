package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.EntityReg;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import willow.train.kuayue.block.seat.M1SeatEntity;
import willow.train.kuayue.block.seat.SeatEntityRenderer;

public class AllEntities {

    public static final EntityReg<M1SeatEntity> M1_SEAT =
            new EntityReg<M1SeatEntity>("m1_seat")
                    .entityType(M1SeatEntity::new)
                    .size(.5f, .5f)
                    .withRenderer(() -> SeatEntityRenderer::new)
                    .addProperty(builder -> {
                        builder.setTrackingRange(4)
                                .setUpdateInterval(20)
                                .setShouldReceiveVelocityUpdates(false)
                                .fireImmune();
                    })
                    .attribute(() -> new AttributeSupplier.Builder().add(Attributes.MAX_HEALTH, 1))
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
