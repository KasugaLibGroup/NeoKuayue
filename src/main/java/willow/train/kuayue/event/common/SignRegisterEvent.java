package willow.train.kuayue.event.common;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sk89q.worldedit.world.block.BlockState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;
import willow.train.kuayue.initial.registration.SignLambdaReg;

import java.util.function.Supplier;

import static willow.train.kuayue.Kuayue.MODID;

@OnlyIn(Dist.CLIENT)
public class SignRegisterEvent {

    @SubscribeEvent
    public static void signRegisterSub(FMLCommonSetupEvent event) {

        System.out.println("测试");

        SignLambdaReg.register(new ResourceLocation("carriage_type_sign"),
                () -> {
                    return new SignRenderLambda<BlockEntity>() {
                        @Override
                        public void render(BlockEntity blockEntity, BlockState state, CompoundTag nbt, PoseStack stack, MultiBufferSource bufferSource, int light, int overlay) {
                            System.out.println("测试");
                        }
                    };
                });

        SignLambdaReg.register(new ResourceLocation("carriage_no_sign"),
                () -> {
                    System.out.println("车厢编号的渲染方法");
                    return null;
                });

        SignLambdaReg.register(new ResourceLocation("laquered_board"),
                () -> {
                    System.out.println("水牌的渲染方法");
                    return null;
                });

        SignLambdaReg.register(new ResourceLocation("train_speed_sign"),
                () -> {
                    System.out.println("速度等级的渲染方法");
                    return null;
                });

        SignLambdaReg.registerAll();
    }
}
