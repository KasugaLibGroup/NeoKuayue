package willow.train.kuayue.initial.food;

import kasuga.lib.registrations.common.EffectReg;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import willow.train.kuayue.block.food.effect.WarmEffect;
import willow.train.kuayue.initial.AllElements;

public class AllEffects {

    public static final EffectReg<WarmEffect> WARM = new EffectReg<WarmEffect>("warm")
            .effectType(WarmEffect::new)
            .color(127, 0, 0)
            .category(MobEffectCategory.BENEFICIAL)
            .submit(AllElements.testRegistry);

    public static final MobEffectInstance TRAIN_BOX_LUNCH_EFFECT =
            new MobEffectInstance(MobEffects.REGENERATION, 100, 0);

    public static final MobEffectInstance KUA_COLA_EFFECT =
            new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3600, 1);

    public static final MobEffectInstance BLUE_BULL_EFFECT =
            new MobEffectInstance(MobEffects.JUMP, 7200, 1);

    public static void invoke() {}
}
