package willow.train.kuayue.event.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import willow.train.kuayue.initial.registration.SignElementReg;

public class SignRegisterEvent {

    @SubscribeEvent
    public static void signRegisterSub(FMLCommonSetupEvent event) {

        System.out.println("测试");

        SignElementReg.signLambdaRegister(new ResourceLocation("carriage_type_sign"),
                () -> {
                    return (blockEntity, blockState, nbt, stack, bufferSource, light, overlay) -> {
                        System.out.println("车厢类型渲染方法");
                    };
                });

        SignElementReg.signLambdaRegister(new ResourceLocation("carriage_no_sign"),
                () -> {
                    return (blockEntity, blockState, nbt, stack, bufferSource, light, overlay) -> {
                        System.out.println("车厢编号渲染方法");
                    };
                });

        SignElementReg.signLambdaRegister(new ResourceLocation("laquered_board"),
                () -> {
                    return (blockEntity, blockState, nbt, stack, bufferSource, light, overlay) -> {
                        System.out.println("水牌渲染方法");
                    };
                });

        SignElementReg.signLambdaRegister(new ResourceLocation("train_speed_sign"),
                () -> {
                    return (blockEntity, blockState, nbt, stack, bufferSource, light, overlay) -> {
                        System.out.println("速度等级渲染方法");
                    };
                });

        SignElementReg.signColorRegister();

        SignElementReg.registerAllElement();
    }
}
