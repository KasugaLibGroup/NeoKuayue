package willow.train.kuayue.initial.panel;

import com.simibubi.create.foundation.utility.Couple;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import willow.train.kuayue.block.panels.FullShapeDirectionalBlock;
import willow.train.kuayue.block.panels.TrainHingePanelBlock;
import willow.train.kuayue.block.panels.door.CustomRenderedDoorBlock;
import willow.train.kuayue.block.panels.slab.CarportHingeSlabBlock;
import willow.train.kuayue.block.panels.slab.TrainSlabBlock;
import willow.train.kuayue.block.panels.window.TrainUnOpenableWindowBlock;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.registration.PanelRegistration;
import willow.train.kuayue.initial.registration.SlabRegistration;

public class ISS3Panel {

    public static final PanelRegistration<TrainHingePanelBlock> panel_top_ss3 =
            new PanelRegistration<TrainHingePanelBlock>("panel_top_ss3")
                    .block(p -> new TrainHingePanelBlock(p, new Vec2(0, 0), new Vec2(1, 1)))
                    .materialAndColor(Material.METAL, MaterialColor.COLOR_GREEN)
                    .tab(AllElements.neoKuayueLocoTab)
                    .noOcclusion().strengthAndTool(1.5f, 3f)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
