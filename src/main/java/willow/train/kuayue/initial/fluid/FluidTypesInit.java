package willow.train.kuayue.initial.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.Kuayue;

import java.util.function.Consumer;

public class FluidTypesInit {

    public static final ResourceLocation COLA_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation COLA_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation COLA_OVERLAY_RL =
            new ResourceLocation(Kuayue.MODID, "fluid/cola_fluid");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Kuayue.MODID);

    public static final RegistryObject<FluidType> COLA_FLUID_TYPE = register("cola_fluid",
            FluidType.Properties.create()
                    .canExtinguish(true)
                    .supportsBoating(true)
                    .lightLevel(5)
                    .density(15)
                    .viscosity(5)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties){

        return FLUID_TYPES.register(name,
                () -> new BaseFluidType(properties, COLA_STILL_RL, COLA_FLOWING_RL,
                        COLA_OVERLAY_RL,
                        0xFF5E1C10,
                        new Vector3f(210f / 255f, 105f / 255f, 30f / 255f)));
        // tintColor参数每个8位分别表示alpha、红色、绿色和蓝色通道。
    }

    public static void register(IEventBus eventBus){
        FLUID_TYPES.register(eventBus);
    }

    public static class BaseFluidType extends FluidType {

        private final ResourceLocation stillTexture;
        private final ResourceLocation flowingTexture;
        private final ResourceLocation overlayTexture;
        private final int tintColor;
        private final Vector3f fogColor;

        public BaseFluidType(final Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture,
                             ResourceLocation overlayTexture, int tintColor, Vector3f fogColor) {
            super(properties);
            this.stillTexture = stillTexture;
            this.flowingTexture = flowingTexture;
            this.overlayTexture = overlayTexture;
            this.tintColor = tintColor;
            this.fogColor = fogColor;
        }

        public ResourceLocation getStillTexture() {
            return stillTexture;
        }

        public ResourceLocation getFlowingTexture() {
            return flowingTexture;
        }

        public ResourceLocation getOverlayTexture() {
            return overlayTexture;
        }

        public int getTintColor() {
            return tintColor;
        }

        public Vector3f getFogColor() {
            return fogColor;
        }

        @Override
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            super.initializeClient(consumer);
            consumer.accept(new IClientFluidTypeExtensions() {
                @Override
                public int getTintColor() {
                    return tintColor;
                }

                @Override
                public ResourceLocation getStillTexture() {
                    return stillTexture;
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return flowingTexture;
                }

                @Override
                public @Nullable ResourceLocation getOverlayTexture() {
                    return overlayTexture;
                }

                @Override
                public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                    return fogColor;
                }

                @Override
                public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                    RenderSystem.setShaderFogStart(1f);
                    RenderSystem.setShaderFogEnd(6f);
                }
            });
        }
    }
}
