package willow.train.kuayue.initial;

import willow.train.kuayue.systems.editable_panel.EditableTypeConstants;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.initial.item.EditablePanelItem;
import willow.train.kuayue.systems.editable_panel.PanelColorType;
import willow.train.kuayue.systems.editable_panel.SignType;

public class AllEditableTypes {

    public static final PanelColorType C25B_YELLOW2  = EditableTypeConstants
            .signColorRegister("yellow2", AllTags.C25B, EditableTypeConstants.YELLOW2);

    public static final PanelColorType C25G_RED  = EditableTypeConstants
            .signColorRegister("red", AllTags.C25G, EditableTypeConstants.RED);

    public static final PanelColorType C25K_BLUE  = EditableTypeConstants
            .signColorRegister("blue", AllTags.C25K, EditableTypeConstants.BLUE);

    public static final PanelColorType C25Z_BLUE2  = EditableTypeConstants
            .signColorRegister("blue2", AllTags.C25Z, EditableTypeConstants.BLUE2);

    public static final PanelColorType C25T_BLUE3  = EditableTypeConstants
            .signColorRegister("blue3", AllTags.C25T, EditableTypeConstants.BLUE3);

    public static final SignType CARRIAGE_TYPE_SIGN = EditableTypeConstants
            .signLambdaRegister("carriage_type_sign",
            TrainPanelProperties.EditType.TYPE,
                    () -> () -> EditableTypeConstants.CARRIAGE_TYPE_RENDER);

    public static final SignType CARRIAGE_NO_SIGN = EditableTypeConstants
            .signLambdaRegister("carriage_no_sign",
            TrainPanelProperties.EditType.NUM,
                    () -> () -> EditableTypeConstants.CARRIAGE_NO_SIGN);

    public static final SignType LAQUERED_BOARD_SIGN = EditableTypeConstants
            .signLambdaRegister("laquered_board",
            TrainPanelProperties.EditType.LAQUERED,
                    () -> () -> EditableTypeConstants.LAQUERED_BOARD_SIGN);

    public static final SignType TRAIN_SPEED_SIGN = EditableTypeConstants
            .signLambdaRegister("train_speed_sign",
            TrainPanelProperties.EditType.SPEED,
                    () -> () -> EditableTypeConstants.TRAIN_SPEED_SIGN);

    public static void invoke() {
        EditablePanelItem.invoke();
    }
}
