package willow.train.kuayue.systems.editable_panel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.AllTags;
import willow.train.kuayue.initial.item.EditablePanelItem;

import java.util.Objects;
import java.util.function.Supplier;

public class SignType {

    private final ResourceLocation location;

    private final TrainPanelProperties.EditType editType;

    private final Supplier<Supplier<SignRenderLambda>> lambdaSupplier;

    // TODO: 这个是你的lambda类，注意这个lambda要有一个BlockEntity, CompoundTag, BlockState的传入值
    // 切记，让这个lambda在注册处就传入
    ? lbd;

    public SignType(String locationKey, TrainPanelProperties.EditType editType, Supplier<Supplier<SignRenderLambda>> lambdaSupplier) {
        this.location = new ResourceLocation(locationKey);
        this.editType = editType;
        this.lambdaSupplier = lambdaSupplier;
    }

    public Supplier<SignRenderLambda> getLambdaSupplier() {
        return this.lambdaSupplier.get();
    }

    public boolean shouldRender(TrainPanelProperties.EditType editType) {
        return editType == this.editType;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public TrainPanelProperties.EditType getEditType() {
        return editType;
    }

    // TODO: 这个方法要返回我们的生成默认字段的lambda(就是上面todo里那个lambda类，需要你自己实现)
    public ? getDefaultTexts() {
        return lbd;
    }
}
