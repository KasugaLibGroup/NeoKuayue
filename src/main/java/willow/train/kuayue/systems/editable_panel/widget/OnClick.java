package willow.train.kuayue.systems.editable_panel.widget;

import net.minecraft.client.gui.components.AbstractWidget;

public interface OnClick<T extends AbstractWidget> {
    void click(T widget, double mouseX, double mouseY);
}
