package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ImageInit;
import willow.train.kuayue.initial.item.EditablePanelItem;
import willow.train.kuayue.systems.editable_panel.interfaces.DefaultTextsLambda;
import willow.train.kuayue.systems.editable_panel.interfaces.IEditScreenMethods;
import willow.train.kuayue.systems.editable_panel.interfaces.SignRenderLambda;
import willow.train.kuayue.utils.client.RenderablePicture;

import javax.annotation.Nullable;
import java.util.*;
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

//    TODO 各Renderer中的render方法lambda
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

    public static final DefaultTextsLambda CARRIAGE_TYPE_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            Component[] messages =
                new Component[] {
                        Component.literal("XXX"),
                        Component.literal("XXXXXX"),
                        Component.literal("XX"),
                        Component.literal("XXX"),
                        Component.literal("XXXXXX")
                };
            String[] name = new String[] {"a", "b", "c", "d", "e"};
            for (int i = 0; i < 5; i++) {
                nbt.putString(name[i], messages[i].getString());
            }
        }
    };

    public static final DefaultTextsLambda CARRIAGE_NO_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            int color = EditableTypeConstants.RED;
            boolean isLeftSide = true;
            Component message = Component.literal("0");

            nbt.putInt("color", color);
            nbt.putBoolean("left", isLeftSide);
            nbt.putString("message", message.getString());
        }
    };

    public static final DefaultTextsLambda LAQUERED_BOARD_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            int type = 0; // 当前处于的种类
            int backGroundColor = 15216648;
            int forGroundColor = 0x0;
            int beltForGroundColor = 0xffffff; // 背景色和前景色
            double x_offset = 0;
            Component[] messages =
                new Component[] {
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty(),
                        Component.empty()
                };

            nbt.putInt("type", type);
            for (int i = 0; i < messages.length; i++) {
                nbt.putString("content " + i, messages[i].getString());
            }
            nbt.putInt("beltColor", backGroundColor);
            nbt.putInt("textColor", forGroundColor);
            nbt.putInt("pinyinColor", beltForGroundColor);
            nbt.putDouble("x_offset", x_offset);
        }
    };

    public static final DefaultTextsLambda TRAIN_SPEED_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            Component content = Component.literal("xx km/h");
            float x_offset = 0, y_offset = 0;
            boolean x_revert = false, y_revert = false;

            nbt.putString("content", content.getString());
            nbt.putFloat("x_offset", x_offset);
            nbt.putFloat("y_offset", y_offset);
            nbt.putBoolean("x_revert", x_revert);
            nbt.putBoolean("y_revert", y_revert);
        }
    };

//    TODO 各Screen类中方法实现类，CarriageTypeSign被挪到Screen类中。

    public static final IEditScreenMethods CARRIAGE_NO_SIGN_METHODS = new IEditScreenMethods() {
        @Override
        public void init(EditablePanelEditScreen screen, EditablePanelEntity entity) {
            System.out.println("车厢编号");
        }

        @Override
        public void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        }
    };

    public static final IEditScreenMethods LAQUERED_BOARD_METHODS = new IEditScreenMethods() {
        @Override
        public void init(EditablePanelEditScreen screen, EditablePanelEntity entity) {
            System.out.println("水牌");
        }

        @Override
        public void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        }
    };

    public static final IEditScreenMethods TRAIN_SPEED_SIGN_METHODS = new IEditScreenMethods() {
        @Override
        public void init(EditablePanelEditScreen screen, EditablePanelEntity entity) {
            System.out.println("车厢类型");
        }

        @Override
        public void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        }
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

    // 通过tag获取PanelColorType对象
    public static @Nullable PanelColorType getColorTypeByTag(BlockState state) {
        // 遍历所有PanelColorType类对象，每个对象中均包含车厢板类型（blockTag）- 标识颜色（signColor）的映射关系。
        for (PanelColorType colorType : EditableTypeConstants.getSignColorMap().values()) {
            // 当传入的blockstate中包含当前循环的PanelColorType类对象中的blockTag时，直接返回包含对应标识颜色的PanelColorType类对象。
            if (state.is(Objects.requireNonNull(colorType.blockTag.tag())))
                return colorType;
        }
        return null;
    }

    public static PanelColorType signColorRegister(String locationKey, BlockTagReg blockTag, Integer signColor) {

        PanelColorType panelColorType = new PanelColorType(new ResourceLocation(locationKey), blockTag, signColor);

        EditableTypeConstants.getSignColorMap()
                .put(new ResourceLocation("color_map_" + locationKey), panelColorType);

        return panelColorType;
    }

    public static SignType signLambdaRegister(String locationKey,
                                              TrainPanelProperties.EditType editType,
                                              Supplier<Supplier<SignRenderLambda>> supplier,
                                              Supplier<DefaultTextsLambda> defaultTextSupplier,
                                              Supplier<IEditScreenMethods> screenMethodsSupplier) {

        SignType signType = new SignType(locationKey, editType, supplier, defaultTextSupplier, screenMethodsSupplier);

        EditableTypeConstants.getSignTypeMap().put(new ResourceLocation(locationKey), signType);

        return signType;
    }

    public static SignType getSignTypeByKey(ResourceLocation location) {
        return EditableTypeConstants.getSignTypeMap().get(location);
    }

    // 根据自定义类型返回对应包含自定义类型（EditType）- 默认字段lambda映射关系的SignType对象
    public static SignType getSignTypeByEditType(TrainPanelProperties.EditType type) {
        // 遍历所有SignType类对象，当传入的EditType等于当前循环中SignType类对象的EditType时，返回该SignType对象。
        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(type))
                return signType;
        }
        return null;
    }

    // 根据手持的物品与车厢板类型返回自定义类型
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
}
