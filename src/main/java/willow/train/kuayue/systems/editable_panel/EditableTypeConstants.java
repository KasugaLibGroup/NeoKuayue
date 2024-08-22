package willow.train.kuayue.systems.editable_panel;

import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.item.EditablePanelItem;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    private static final Map<ResourceLocation, PanelColorType> signColorMap = new HashMap<>();
    private static final Map<ResourceLocation, SignType> signTypeMap = new HashMap<>();

    public static Map<ResourceLocation, PanelColorType> getSignColorMap() {
        return signColorMap;
    }

    public static Map<ResourceLocation, SignType> getSignTypeMap() {
        return signTypeMap;
    }

    public static int getColorByTag(BlockState state) {
        for (PanelColorType colorType : EditableTypeConstants.getSignColorMap().values()) {
            if (state.is(Objects.requireNonNull(colorType.blockTag.tag())))
                return colorType.signColor;
        }
        return YELLOW;
    }

    public static @Nullable PanelColorType getColorTypeByTag(BlockState state) {
        for (PanelColorType colorType : EditableTypeConstants.getSignColorMap().values()) {
            if (state.is(Objects.requireNonNull(colorType.blockTag.tag()))) return colorType;
        }
        return null;
    }

    public static PanelColorType signColorRegister(String locationKey, BlockTagReg blockTag, Integer signColor) {
        PanelColorType panelColorType = new PanelColorType(new ResourceLocation(locationKey), blockTag, signColor);
        EditableTypeConstants.getSignColorMap()
                .put(new ResourceLocation("color_map_" + locationKey), panelColorType);
        return panelColorType;
    }

    public static TrainPanelProperties.EditType getPanelEditType (BlockState state, Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).is(EditablePanelItem.COLORED_BRUSH.getItem())) {
            if (state.is(Objects.requireNonNull(AllTags.BOTTOM_PANEL.tag())))
                return TrainPanelProperties.EditType.TYPE;
            if (state.is(Objects.requireNonNull(AllTags.FLOOR.tag())))
                return TrainPanelProperties.EditType.SPEED;
        }
        if (player.getItemInHand(hand).is(EditablePanelItem.LAQUERED_BOARD.getItem()) && state.is(Objects.requireNonNull(AllTags.BOTTOM_PANEL.tag()))) {
            return TrainPanelProperties.EditType.LAQUERED;
        }
        if (player.getItemInHand(hand).is(EditablePanelItem.STICKER.getItem()) && state.is(Objects.requireNonNull(AllTags.UPPER_PANEL.tag()))) {
            return TrainPanelProperties.EditType.NUM;
        }
        return TrainPanelProperties.EditType.NONE;
    }

    public static SignType signLambdaRegister(String locationKey, TrainPanelProperties.EditType editType, Supplier<Supplier<SignRenderLambda>> supplier) {

        SignType signType = new SignType(locationKey, editType, supplier);

        EditableTypeConstants.getSignTypeMap().put(new ResourceLocation(locationKey), signType);

        return signType;
    }

    public static SignType getSignTypeByKey(ResourceLocation location) {
        return EditableTypeConstants.getSignTypeMap().get(location);
    }

    public static SignType getSignTypeByEditType(TrainPanelProperties.EditType type) {
        for (SignType st : EditableTypeConstants.getSignTypeMap().values()) {
            if (st.shouldRender(type)) return st;
        }
        return null;
    }
}
