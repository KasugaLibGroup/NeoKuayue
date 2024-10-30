package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import kasuga.lib.core.client.render.SimpleColor;
import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.Envs;
import kasuga.lib.core.util.LazyRecomputable;
import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.initial.item.EditablePanelItem;
import willow.train.kuayue.systems.editable_panel.interfaces.DefaultTextsLambda;
import willow.train.kuayue.systems.editable_panel.interfaces.SignRenderLambda;
import willow.train.kuayue.systems.editable_panel.screens.CustomScreen;
import willow.train.kuayue.systems.editable_panel.widget.Label;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

public class EditableTypeConstants {

    @OnlyIn(Dist.CLIENT)
    public static final LazyRecomputable<ImageMask> image = new LazyRecomputable<>(
            () -> ClientInit.noSignTexture.getImageSafe().get().getMask()
                    .rectangleUV(0, 0, 1, 1)
    );
    public static final Integer
            YELLOW = 16776961,
            YELLOW2 = 16776960,
            RED = 15216648,
            BLUE = 22220,
            BLUE2 = 0x60A0B0,
            BLUE3 = 468326,
            BLACK = 789516;

//    TODO 各Renderer中的render方法lambda
    public static final SignRenderLambda CARRIAGE_TYPE_RENDER = (blockEntity, partialTick, pose, bufferSource, packedLight, packedOverlay) -> {
        PoseStack poseStack = pose.getPoseStack();
        BlockState blockstate = blockEntity.getBlockState();
        boolean revert = blockEntity.getNbt().getBoolean("revert");
        Label[] label = new Label[5];
        for (int i = 0; i < 5; i++) {
            label[i] = new Label(Component.literal(blockEntity.getNbt().getString("data" + i)));
            label[i].setColor(blockEntity.getColor());
        }
        poseStack.pushPose();

        poseStack.translate(0.5d, 0.5d, 0.5d);
        float f = -blockstate.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        poseStack.translate(0.0d, -0.3d, -0.42d);
        poseStack.translate(blockEntity.getNbt().getFloat("offset_x"),
                blockEntity.getNbt().getFloat("offset_y"), 0);

        // width 1.2，height 0.5
        // scale 0.133

        float size0 = ((float) Minecraft.getInstance().font.width(label[0].getText())) * 0.13f; // 硬座车
        float size1 = ((float) Minecraft.getInstance().font.width(label[1].getText())) * 0.08f; // YINGZUOCHE
        float size2 = ((float) Minecraft.getInstance().font.width(label[2].getText())) * 0.23f; // YZ
        float size3 = ((float) Minecraft.getInstance().font.width(label[3].getText())) * 0.12f; // 25T
        float size4 = ((float) Minecraft.getInstance().font.width(label[4].getText())) * 0.30f; // 345674

        if (revert) {
            poseStack.translate(0.4d - size1 * 0.133f * 0.5f, 0.0, 0.0);
        } else {
            poseStack.translate(-0.4d, 0.0d, 0.0d);
        }
        poseStack.scale(0.133f * 0.55f, -0.133f * 0.55f, 0.133f * 0.55f); // standard size

        poseStack.scale(0.08f, 0.08f, 1.0f);
        label[1].renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);  // 硬座车
        poseStack.scale(12.5f, 12.5f, 1.0f);

        poseStack.translate((size1 - size0) / 2, -1.7, 0);

        poseStack.scale(0.13f, 0.18f, 1.0f);
        label[0].renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);  // YINGZUOCHE
        poseStack.scale(7.6923076924f, 5.555555555555f, 1.0f);


        if (revert) {
            poseStack.translate(-size2 - size4 - size3 - 1, 0, 0);
        } else {
            poseStack.translate(size1, 0, 0);
        }

        poseStack.scale(0.23f, 0.32f, 1.0f);
        label[2].renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);  // YZ
        poseStack.scale(4.347826086956f, 3.1250f, 1.0f);

        poseStack.translate(size2, 1.6, 0.0);

        poseStack.scale(0.12f, 0.12f, 1.0f);
        label[3].renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);  // 25k
        poseStack.scale(8.333333333333f, 8.333333333333f, 1.0f);

        poseStack.translate(size3 + 1, -1.6, 0.0);

        poseStack.scale(0.26f, 0.32f, 1.0f);
        label[4].renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);  // 345674

        poseStack.popPose();
    };

    public static final SignRenderLambda CARRIAGE_NO_SIGN = (blockEntity, partialTick, pose, bufferSource, packedLight, packedOverlay) -> {
        PoseStack poseStack = pose.getPoseStack();
        MultiBufferSource buffer = bufferSource.getBuffer();
        Font font = Minecraft.getInstance().font;
        BlockState blockState = blockEntity.getBlockState();
        CompoundTag nbt = blockEntity.getNbt();
        String content = nbt.getString("content");
        Label label = new Label(content);

        poseStack.pushPose();

        poseStack.translate(0.5d, -0.5d, 0.5d);
        float f = -blockState.getValue(TrainPanelBlock.FACING).toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        poseStack.translate(0.0, -0.9d, -0.43d);
        poseStack.translate(nbt.getFloat("offset_x"), nbt.getFloat("offset_y"), 0);

        // poseStack.translate(0.45d, 0.0d, 0.0d);
        poseStack.scale(1.5f, 1.5f, 1.5f);
        poseStack.translate(-0.03f, -0.55f, 0.0f);

        image.get().rectangle(new Vector3f(-.5675f, -1.575f, -.5f), ImageMask.Axis.X, ImageMask.Axis.Y, true, true, .125f, .125f);
        image.get().renderToWorld(poseStack, buffer, RenderType.text(image.get().getImage().id), false, packedLight);
        poseStack.translate(0.03f, 0.55f, 0.0f);

        poseStack.scale(0.6666666667f, 0.6666666667f, 0.6666666667f);
        poseStack.translate(0.0, 1.48d, 0.001d);

        poseStack.translate(
                -(0.125f - ((float) font.width(content) * 0.133f * 0.08f)) / 2 + 0.02, 0.0d, 0.001d);
        poseStack.translate(
                -((float) font.width(content) * 0.133f * 0.08f), 0.0d, 0.0d);
        poseStack.scale(0.133f, -0.133f, 0.133f); // standard size
        poseStack.scale(0.08f, 0.08f, 0.08f);
        label.setColor(nbt.getInt("color"));
        label.renderToWorld(poseStack, font, buffer, false, packedLight);

        poseStack.popPose();
    };

    public static final SignRenderLambda LAQUERED_BOARD_SIGN = (blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay) -> {
        // System.out.println("水牌渲染方法");
    };

    public static final SignRenderLambda TRAIN_SPEED_SIGN = (blockEntity, partialTick, pose, bufferSource, packedLight, packedOverlay) -> {
        BlockState blockState = blockEntity.getBlockState();
        PoseStack poseStack = pose.getPoseStack();
        CompoundTag nbt = blockEntity.getNbt();

        float x_offset = nbt.getFloat("offset_x");
        float y_offset = nbt.getFloat("offset_y");

        String str = nbt.getString("content");
        Label label = new Label(str);

        float text_length = Minecraft.getInstance().font.width(str);
        float text_height = Minecraft.getInstance().font.lineHeight;

        poseStack.pushPose();
        poseStack.translate(0.5d, 0.5d, 0.5d);
        float f = -blockState.getValue(TrainPanelBlock.FACING).getOpposite().toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(f));

        poseStack.translate(0.0d, 0.1d, 0.501d);
        poseStack.translate(x_offset, y_offset, 0);
        poseStack.scale(1.0f, -1.0f, 1.0f);
        poseStack.scale(.2f, .2f, 1.0f); // standard_size
        poseStack.scale(.1f, .1f, 1.0f);
        poseStack.translate(-text_length / 2, -text_height / 2, 0);

        label.setColor(nbt.getInt("color"));
        label.renderToWorld(poseStack, Minecraft.getInstance().font, bufferSource.getBuffer(), false, packedLight);
        poseStack.popPose();
    };

    public static final DefaultTextsLambda CARRIAGE_TYPE_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            nbt.putString("data0", "硬座车");
            nbt.putString("data1", "YINGZUOCHE");
            nbt.putString("data2", "YZ");
            nbt.putString("data3", "25B");
            nbt.putString("data4", "345676");
            nbt.putFloat("offset_x", 0f);
            nbt.putFloat("offset_y", 0f);
        }
    };

    public static final DefaultTextsLambda CARRIAGE_NO_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            Component content = Component.literal("1");

            nbt.putInt("color", RED);
            nbt.putString("content", content.getString());
            nbt.putFloat("offset_x", 0);
            nbt.putFloat("offset_y", 0);
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
            nbt.putInt("type", type);
            for (int i = 0; i < 16; i++) {
                nbt.putString("content " + i, "");
            }
            nbt.putInt("beltColor", backGroundColor);
            nbt.putInt("textColor", forGroundColor);
            nbt.putInt("pinyinColor", beltForGroundColor);
        }
    };

    public static final DefaultTextsLambda TRAIN_SPEED_SIGN_MESSAGES = new DefaultTextsLambda() {
        @Override
        public void defaultTextComponent(BlockEntity blockEntity, BlockState blockState, CompoundTag nbt) {
            Component content = Component.literal("120 km/h");

            nbt.putInt("color", 0xffffff);
            nbt.putString("content", content.getString());
            nbt.putFloat("offset_x", 0);
            nbt.putFloat("offset_y", 0);
        }
    };

//    TODO 各 Screen类中方法实现类，CarriageTypeSign被挪到Screen类中。
/*
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

 */

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
                                              Supplier<SignType.CustomScreenSupplier<EditablePanelEditMenu, CustomScreen<EditablePanelEditMenu, EditablePanelEntity>>> screenMethodsSupplier) {

        SignType signType = new SignType(locationKey, editType, supplier, defaultTextSupplier, Envs.isClient() ? screenMethodsSupplier.get() : null);

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
