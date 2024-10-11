package willow.train.kuayue.block.food.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.jetbrains.annotations.NotNull;

public class WarmEffect extends MobEffect {

    private static final int SATURATION_THRESHOLD = 5;
    public WarmEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    // 当玩家饥饿值不满且饱和度低于一定程度时持续补充饱和度
    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            FoodData foodData = player.getFoodData();
            float saturationLevel = foodData.getSaturationLevel();
            if (foodData.getFoodLevel() < 20 && saturationLevel < SATURATION_THRESHOLD) {
                foodData.setSaturation(saturationLevel + 1);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
