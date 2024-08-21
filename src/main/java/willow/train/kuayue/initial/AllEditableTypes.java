package willow.train.kuayue.initial;

import willow.train.kuayue.block.panels.base.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.item.EditablePanelItem;
import willow.train.kuayue.initial.registration.PanelColorType;
import willow.train.kuayue.initial.registration.SignType;

public class AllEditableTypes {

    public static final PanelColorType C25B_YELLOW2  = PanelColorType
            .signColorRegister("yellow2", AllTags.C25B, EditableTypeConstants.YELLOW2);

    public static final PanelColorType C25G_RED  = PanelColorType
            .signColorRegister("red", AllTags.C25G, EditableTypeConstants.RED);

    public static final PanelColorType C25K_BLUE  = PanelColorType
            .signColorRegister("blue", AllTags.C25K, EditableTypeConstants.BLUE);

    public static final PanelColorType C25Z_BLUE2  = PanelColorType
            .signColorRegister("blue2", AllTags.C25Z, EditableTypeConstants.BLUE2);

    public static final PanelColorType C25T_BLUE3  = PanelColorType
            .signColorRegister("blue3", AllTags.C25T, EditableTypeConstants.BLUE3);

    public static final SignType CARRIAGE_TYPE_SIGN = SignType
            .signLambdaRegister("carriage_type_sign",
            TrainPanelProperties.EditType.TYPE,
            () -> EditableTypeConstants.CARRIAGE_TYPE_RENDER);

    public static final SignType CARRIAGE_NO_SIGN = SignType
            .signLambdaRegister("carriage_no_sign",
            TrainPanelProperties.EditType.NUM,
            () -> EditableTypeConstants.CARRIAGE_NO_SIGN);

    public static final SignType LAQUERED_BOARD_SIGN = SignType
            .signLambdaRegister("laquered_board",
            TrainPanelProperties.EditType.LAQUERED,
            () -> EditableTypeConstants.LAQUERED_BOARD_SIGN);

    public static final SignType TRAIN_SPEED_SIGN = SignType
            .signLambdaRegister("train_speed_sign",
            TrainPanelProperties.EditType.SPEED,
            () -> EditableTypeConstants.TRAIN_SPEED_SIGN);

    public static void invoke() {
        EditablePanelItem.invoke();
    }
}
