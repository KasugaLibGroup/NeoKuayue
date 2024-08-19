package willow.train.kuayue.initial.registration;

import kasuga.lib.core.util.data_type.Pair;
import kasuga.lib.registrations.Reg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SignLambdaReg {

    private final ResourceLocation location;

    public static final Map<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>> renderLambdaMap = new HashMap<>();

    public static final List<Pair<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>>> registrationList = new ArrayList<>();

    public SignLambdaReg(String registrationKey, ResourceLocation location) {
        this.location = location;
    }

    public static void register(ResourceLocation location, Supplier<SignRenderLambda<? extends BlockEntity>> supplier) {
        registrationList.add(Pair.of(location, supplier));
    }

    public static void registerAll() {
        for (Pair<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>> pair : registrationList) {
            renderLambdaMap.put(pair.getFirst(), pair.getSecond());
        }
        registrationList.clear();
    }

    public static Supplier<SignRenderLambda<? extends BlockEntity>> getRenderLambdaByRL(ResourceLocation location) {
        return renderLambdaMap.get(location);
    }
}
