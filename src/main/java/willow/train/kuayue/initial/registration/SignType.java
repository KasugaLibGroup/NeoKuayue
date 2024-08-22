package willow.train.kuayue.initial.registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.item.EditablePanelItem;

import java.util.Objects;
import java.util.function.Supplier;

public class SignType {

    private final ResourceLocation location;

    private final TrainPanelProperties.EditType editType;

    private final Supplier<SignRenderLambda> lambdaSupplier;

    public static SignType signLambdaRegister(String locationKey, TrainPanelProperties.EditType editType, Supplier<SignRenderLambda> supplier) {

        SignType signType = new SignType(locationKey, editType, supplier);

        EditableTypeConstants.getSignTypeMap().put(new ResourceLocation(locationKey), signType);

        return signType;
    }

    public static SignType getSignTypeByKey(String locationKey) {
        return EditableTypeConstants.getSignTypeMap().get(new ResourceLocation(locationKey));
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

    public SignType(String locationKey, TrainPanelProperties.EditType editType, Supplier<SignRenderLambda> lambdaSupplier) {
        this.location = new ResourceLocation(locationKey);
        this.editType = editType;
        this.lambdaSupplier = lambdaSupplier;
    }

    public Supplier<SignRenderLambda> getLambdaSupplier() {
        return this.lambdaSupplier;
    }

    public boolean shouldRender(TrainPanelProperties.EditType editType) {
        return editType == this.editType;
    }
}
