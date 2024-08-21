package willow.train.kuayue.initial.registration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.SignRenderLambda;

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
