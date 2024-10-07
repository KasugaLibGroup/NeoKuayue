package willow.train.kuayue.event.server;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.food.instant_noodles.SoakedInstantNoodlesBlockItem;

import static net.minecraft.world.damagesource.DamageSource.HOT_FLOOR;

public class PlayerJumpEvents {

    @SubscribeEvent
    public static void playerJumpEvent(LivingEvent.LivingJumpEvent event) {

        if (event.getEntity() instanceof Player player) {

            Item itemInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
            Item itemInOffHand = player.getItemInHand(InteractionHand.OFF_HAND).getItem();

            if (itemInMainHand instanceof SoakedInstantNoodlesBlockItem
                    || itemInOffHand instanceof SoakedInstantNoodlesBlockItem) {
                String key = "jump_with_soaked_instant_noodles";
                player.hurt(HOT_FLOOR, 0.1F);
                player.displayClientMessage(
                        Component.translatable("msg." + Kuayue.MODID + "." + key), true);
            }
        }
    }
}
