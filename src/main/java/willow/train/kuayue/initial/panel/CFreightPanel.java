package willow.train.kuayue.initial.panel;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.end_face.FreightEndFaceBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;

public class CFreightPanel {

    public static final PanelRegistration<FreightEndFaceBlock> FREIGHT_C70_END_FACE =
            new PanelRegistration<FreightEndFaceBlock>("freight_c70_end_face")
                    .block(FreightEndFaceBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
