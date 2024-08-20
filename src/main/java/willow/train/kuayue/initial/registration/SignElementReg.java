package willow.train.kuayue.initial.registration;

import kasuga.lib.core.util.data_type.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SignElementReg {

    private final ResourceLocation location;

    public static final Integer
            YELLOW = 16776961,
            YELLOW2 = 16776960,
            RED = 15216648,
            BLUE = 22220,
            BLUE2 = 0x60A0B0,
            BLUE3 = 468326,
            BLACK = 789516;

    public static final Map<ResourceLocation, Integer> signColorMap = new HashMap<>();

    public static final List<Pair<ResourceLocation, Integer>> signColorRegList = new ArrayList<>();

    public static final Map<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>> renderLambdaMap = new HashMap<>();

    public static final List<Pair<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>>> lambdaRegList = new ArrayList<>();

    public SignElementReg(String registrationKey, ResourceLocation location) {
        this.location = location;
    }

    public static void signLambdaRegister(ResourceLocation location, Supplier<SignRenderLambda<? extends BlockEntity>> supplier) {
        lambdaRegList.add(Pair.of(location, supplier));
    }

    public static void signColorRegister() {
        signColorRegList.add(Pair.of(new ResourceLocation("yellow"), YELLOW));
        signColorRegList.add(Pair.of(new ResourceLocation("yellow2"), YELLOW2));
        signColorRegList.add(Pair.of(new ResourceLocation("red"), RED));
        signColorRegList.add(Pair.of(new ResourceLocation("blue"), BLUE));
        signColorRegList.add(Pair.of(new ResourceLocation("blue2"), BLUE2));
        signColorRegList.add(Pair.of(new ResourceLocation("blue3"), BLUE3));
        signColorRegList.add(Pair.of(new ResourceLocation("black"), BLACK));
    }

    public static void registerAllElement() {
        for (Pair<ResourceLocation, Supplier<SignRenderLambda<? extends BlockEntity>>> pair : lambdaRegList) {
            renderLambdaMap.put(pair.getFirst(), pair.getSecond());
        }
        lambdaRegList.clear();

        for (Pair<ResourceLocation, Integer> pair : signColorRegList) {
            signColorMap.put(pair.getFirst(), pair.getSecond());
        }
        signColorRegList.clear();
    }

    public static Supplier<SignRenderLambda<? extends BlockEntity>> getRenderLambdaByRL(ResourceLocation location) {
        return renderLambdaMap.get(location);
    }

    public static Integer getSignColorByRL(ResourceLocation location) {
        return signColorMap.get(location);
    }
}
