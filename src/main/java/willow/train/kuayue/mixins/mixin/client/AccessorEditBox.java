package willow.train.kuayue.mixins.mixin.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.BiFunction;

@Mixin(EditBox.class)
public interface AccessorEditBox {

    @Accessor
    boolean getCanLoseFocus();
    @Accessor
    boolean getBordered();
    @Accessor
    String getValue();
    @Accessor
    Font getFont();
    @Accessor
    int getDisplayPos();
    @Accessor
    boolean getIsEditable();
    @Accessor
    String getSuggestion();
    @Accessor
    int getFrame();
    @Accessor
    int getCursorPos();
    @Accessor
    BiFunction<String, Integer, FormattedCharSequence> getFormatter();
    @Accessor
    int getTextColor();
    @Accessor
    int getTextColorUneditable();
    @Accessor
    int getHighlightPos();
    @Accessor
    int getMaxLength();
    @Accessor
    boolean getShiftPressed();
}
