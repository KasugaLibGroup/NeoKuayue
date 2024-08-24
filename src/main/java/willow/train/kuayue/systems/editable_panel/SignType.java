package willow.train.kuayue.systems.editable_panel;

import net.minecraft.resources.ResourceLocation;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.systems.editable_panel.interfaces.DefaultTextsLambda;
import willow.train.kuayue.systems.editable_panel.interfaces.IEditScreenMethods;
import willow.train.kuayue.systems.editable_panel.interfaces.SignRenderLambda;

import java.util.function.Supplier;

public class SignType {

    private final ResourceLocation location;

    private final TrainPanelProperties.EditType editType;

    private final Supplier<Supplier<SignRenderLambda>> lambdaSupplier;

    // 这个是你的lambda类，注意这个lambda要有一个BlockEntity, CompoundTag, BlockState的传入值
    // 切记，让这个lambda在注册处就传入
    private final Supplier<DefaultTextsLambda> defaultTextSupplier;

    private final Supplier<IEditScreenMethods> screenMethodsSupplier;

    public SignType(String locationKey,
                    TrainPanelProperties.EditType editType,
                    Supplier<Supplier<SignRenderLambda>> lambdaSupplier,
                    Supplier<DefaultTextsLambda> defaultTextSupplier, Supplier<IEditScreenMethods> screenMethodsSupplier) {
        this.location = new ResourceLocation(locationKey);
        this.editType = editType;
        this.lambdaSupplier = lambdaSupplier;
        this.defaultTextSupplier = defaultTextSupplier;
        this.screenMethodsSupplier = screenMethodsSupplier;
    }

    public Supplier<SignRenderLambda> getLambdaSupplier() {
        return this.lambdaSupplier.get();
    }

    // 判断传入的自定义类型与当前对象中的自定义类型是否一致
    public boolean shouldRender(TrainPanelProperties.EditType editType) {
        return editType == this.editType;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public TrainPanelProperties.EditType getEditType() {
        return editType;
    }

    // 这个方法要返回我们的生成默认字段的lambda(就是上面todo里那个lambda类，需要你自己实现)
    public Supplier<DefaultTextsLambda> getDefaultTexts() {
        return this.defaultTextSupplier;
    }

    public Supplier<IEditScreenMethods> getScreenMethods() {
        return this.screenMethodsSupplier;
    }
}
