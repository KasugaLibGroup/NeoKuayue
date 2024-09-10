package willow.train.kuayue.mixins.mixin.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.BiFunction;

@Mixin(EditBox.class)
public class MixinEditBox {

    @Shadow
    private boolean canLoseFocus;
    @Shadow
    private boolean bordered;
    @Shadow
    private String value;
    @Final
    @Shadow
    private Font font;
    @Shadow
    private int displayPos;
    @Shadow
    private boolean isEditable;
    @Shadow
    private String suggestion;
    @Shadow
    private int frame;
    @Shadow
    private int cursorPos;
    @Shadow
    private BiFunction<String, Integer, FormattedCharSequence> formatter;
    @Shadow
    private int textColor;
    @Shadow
    private int textColorUneditable;
    @Shadow
    private int highlightPos;
    @Shadow
    private int maxLength;
    @Shadow
    private boolean shiftPressed;

    @Unique
    public boolean getCanLoseFocus() {
        return canLoseFocus;
    }
    @Unique
    public boolean getBordered() {
        return bordered;
    }
    @Unique
    public String getValue() {
        return value;
    }
    @Unique
    public Font getFont() {
        return font;
    }
    @Unique
    public int getDisplayPos() {
        return displayPos;
    }
    @Unique
    public boolean getIsEditable() {
        return isEditable;
    }
    @Unique
    public String getSuggestion() {
        return suggestion;
    }
    @Unique
    public int getFrame() {
        return frame;
    }
    @Unique
    public int getCursorPos() {
        return cursorPos;
    }
    @Unique
    public BiFunction<String, Integer, FormattedCharSequence> getFormatter() {
        return formatter;
    }
    @Unique
    public int getTextColor() {
        return textColor;
    }
    @Unique
    public int getTextColorUneditable() {
        return textColorUneditable;
    }
    @Unique
    public int getHighlightPos() {
        return highlightPos;
    }
    @Unique
    public int getMaxLength() {
        return maxLength;
    }
    @Unique
    public boolean getShiftPressed() {
        return shiftPressed;
    }
}
