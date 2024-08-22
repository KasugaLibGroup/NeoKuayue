package willow.train.kuayue.systems.editable_panel;

import kasuga.lib.registrations.common.BlockTagReg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

import static willow.train.kuayue.systems.editable_panel.EditableTypeConstants.*;

public class PanelColorType {
    private final ResourceLocation location;
    public final BlockTagReg blockTag;
    public final Integer signColor;

    public PanelColorType(ResourceLocation location, BlockTagReg blockTag, Integer signColor) {
        this.location = location;
        this.blockTag = blockTag;
        this.signColor = signColor;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public BlockTagReg getBlockTag() {
        return blockTag;
    }

    public Integer getSignColor() {
        return signColor;
    }

    public boolean valid(BlockState blockState) {
        return blockState.is(Objects.requireNonNull(blockTag.tag()));
    }
}
