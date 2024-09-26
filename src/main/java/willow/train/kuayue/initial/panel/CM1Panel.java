package willow.train.kuayue.initial.panel;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.TrainOpenableWindowBlock;
import willow.train.kuayue.block.panels.window.TrainSmallWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class CM1Panel {

    public static final SlabRegistration<TrainSlabBlock> FLOOR_25B =
            new SlabRegistration<TrainSlabBlock>("floor_m1")
                    .block(p -> new TrainSlabBlock(p, true))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainPanelBlock> BOTTOM_SLAB_M1 =
            new PanelRegistration<TrainPanelBlock>("bottom_slab_m1")
                    .block(p -> new TrainPanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_BLUE)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainOpenableWindowBlock> WINDOW_LARGE_M1 =
            new PanelRegistration<TrainOpenableWindowBlock>("window_large_m1")
                    .block(p -> new TrainOpenableWindowBlock(p, -1, 1, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<TrainSmallWindowBlock> WINDOW_LARGE_BETWEEN_M1 =
            new PanelRegistration<TrainSmallWindowBlock>("window_large_between_m1")
                    .block(p -> new TrainSmallWindowBlock(p, 2))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueCarriageTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
