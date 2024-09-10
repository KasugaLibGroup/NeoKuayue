package willow.train.kuayue.mixins.mixin.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.BiFunction;

@Mixin(value = EditBox.class)
public interface AccessorEditBox {

    @Accessor("canLoseFocus")
    boolean getCanLoseFocus();
    @Accessor("bordered")
    boolean getBordered();
    @Accessor("value")
    String getValue();
    @Accessor("font")
    Font getFont();
    @Accessor("displayPos")
    int getDisplayPos();
    @Accessor("isEditable")
    boolean getIsEditable();
    @Accessor("suggestion")
    String getSuggestion();
    @Accessor("frame")
    int getFrame();
    @Accessor("cursorPos")
    int getCursorPos();
    @Accessor("formatter")
    BiFunction<String, Integer, FormattedCharSequence> getFormatter();
    @Accessor("textColor")
    int getTextColor();
    @Accessor("textColorUneditable")
    int getTextColorUneditable();
    @Accessor("highlightPos")
    int getHighlightPos();
    @Accessor("maxLength")
    int getMaxLength();
    @Accessor("shiftPressed")
    boolean getShiftPressed();
}
