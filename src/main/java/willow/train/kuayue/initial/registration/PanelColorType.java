package willow.train.kuayue.initial.registration;

import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.block.panels.base.EditableTypeConstants;

import java.util.Objects;

import static willow.train.kuayue.block.panels.base.EditableTypeConstants.*;

public class PanelColorType {

    private final ResourceLocation location;

    public final BlockTagReg blockTag;

    public final Integer signColor;

    public static int getColorByTag(BlockState state) {
        for (PanelColorType colorType : EditableTypeConstants.getSignColorMap().values()) {
            if (state.is(Objects.requireNonNull(colorType.blockTag.tag())))
                return colorType.signColor;
        }
        return YELLOW;
    }

    public static PanelColorType signColorRegister(String locationKey, BlockTagReg blockTag, Integer signColor) {

        PanelColorType panelColorType = new PanelColorType(new ResourceLocation(locationKey), blockTag, signColor);

        EditableTypeConstants.getSignColorMap()
                .put(new ResourceLocation("color_map_" + locationKey), panelColorType);

        return panelColorType;
    }

    public PanelColorType(ResourceLocation location, BlockTagReg blockTag, Integer signColor) {
        this.location = location;
        this.blockTag = blockTag;
        this.signColor = signColor;
    }
}
