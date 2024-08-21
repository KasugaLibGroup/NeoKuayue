package willow.train.kuayue.block.panels.base;

import kasuga.lib.core.util.data_type.Pair;
import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;
import willow.train.kuayue.initial.registration.PanelColorType;
import willow.train.kuayue.initial.registration.SignType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EditableTypeConstants {

    public static final Integer
            YELLOW = 16776961,
            YELLOW2 = 16776960,
            RED = 15216648,
            BLUE = 22220,
            BLUE2 = 0x60A0B0,
            BLUE3 = 468326,
            BLACK = 789516;

    private static final Map<ResourceLocation, PanelColorType> signColorMap = new HashMap<>();

    private static final Map<ResourceLocation, SignType> signTypeMap = new HashMap<>();

    public static final SignRenderLambda CARRIAGE_TYPE_RENDER = (blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay) -> {
        System.out.println("车厢类型渲染方法");
    };

    public static final SignRenderLambda CARRIAGE_NO_SIGN = (blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay) -> {
        System.out.println("车厢编号渲染方法");
    };

    public static final SignRenderLambda LAQUERED_BOARD_SIGN = (blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay) -> {
        System.out.println("水牌渲染方法");
    };

    public static final SignRenderLambda TRAIN_SPEED_SIGN = (blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay) -> {
        System.out.println("速度等级渲染方法");
    };

    public static Map<ResourceLocation, PanelColorType> getSignColorMap() {
        return signColorMap;
    }

    public static Map<ResourceLocation, SignType> getSignTypeMap() {
        return signTypeMap;
    }
}
