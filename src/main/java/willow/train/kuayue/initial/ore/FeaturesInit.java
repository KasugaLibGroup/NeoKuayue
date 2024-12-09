package willow.train.kuayue.initial.ore;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import willow.train.kuayue.Kuayue;

import java.util.List;
import java.util.function.Supplier;

import static net.minecraft.core.Registry.CONFIGURED_FEATURE_REGISTRY;

public class FeaturesInit {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(CONFIGURED_FEATURE_REGISTRY, Kuayue.MODID);

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Kuayue.MODID);

    // 主世界生成规则lambda，将石头替换为盐矿石。
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVER_WORLD_SALT_ORES =
            Suppliers.memoize(() ->
                    List.of(OreConfiguration.target(
                                    OreFeatures.STONE_ORE_REPLACEABLES,
                                    AllOres.SALT_ORE.getBlock().defaultBlockState()),
                            OreConfiguration.target(
                                    OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                                    AllOres.DEEPSLATE_SALT_ORE.getBlock().defaultBlockState())
                    ));

    // 注册盐矿石生成方式
    public static final RegistryObject<ConfiguredFeature<?, ?>> SALT_ORE =
            CONFIGURED_FEATURES.register("salt_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OVER_WORLD_SALT_ORES.get(), 9)));

    // 注册盐矿石分布特征
    public static final RegistryObject<PlacedFeature> SALT_ORE_PLACED =
            PLACED_FEATURES.register(
                    "salt_ore_placed",
                    () -> new PlacedFeature(SALT_ORE.getHolder().get(),
                            commonOrePlacement(90,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-80),
                                            VerticalAnchor.absolute(80))
                            )
                    )
            );

    private static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
        return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier2) {
        return orePlacement(CountPlacement.of(count), modifier2);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier modifier2) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), modifier2);
    }

    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);
    }
}
