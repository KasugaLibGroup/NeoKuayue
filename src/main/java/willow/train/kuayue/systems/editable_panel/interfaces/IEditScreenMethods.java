package willow.train.kuayue.systems.editable_panel.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.systems.editable_panel.EditablePanelEditScreen;

public interface IEditScreenMethods {

    void init(EditablePanelEditScreen screen, EditablePanelEntity entity);

    void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY);
}
