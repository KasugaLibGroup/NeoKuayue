package willow.train.kuayue.initial.panel;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.end_face.FreightEndFaceBlock;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;

public class CFreightPanel {

    public static final PanelRegistration<FreightEndFaceBlock> FREIGHT_C70_END_FACE =
            new PanelRegistration<FreightEndFaceBlock>("freight_c70_end_face")
                    .block(FreightEndFaceBlock::new)
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion().strengthAndTool(1.5f, 3f)
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> FREIGHT_C70_SLAB_BOTTOM =
            new PanelRegistration<TrainPanelBlock>("freight_c70_slab_bottom")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(2, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion().strengthAndTool(1.5f, 3f)
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> FREIGHT_C70_SLAB_TOP =
            new PanelRegistration<TrainOpenableWindowBlock>("freight_c70_slab_top")
                    .block(p -> new TrainOpenableWindowBlock(p, -1, 1, 1))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion().strengthAndTool(1.5f, 3f)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
