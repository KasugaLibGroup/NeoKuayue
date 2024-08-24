package willow.train.kuayue.systems.editable_panel.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditScreen;

public interface IEditScreenMethods {

    void init(EditablePanelEditScreen screen);

    void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY);
}
