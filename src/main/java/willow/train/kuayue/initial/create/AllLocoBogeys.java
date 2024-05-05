package willow.train.kuayue.initial.create;

import com.simibubi.create.content.trains.bogey.BogeyBlockEntityRenderer;
import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.create.BogeyBlockReg;
import kasuga.lib.registrations.create.BogeyGroupReg;
import kasuga.lib.registrations.create.BogeySizeReg;
import kasuga.lib.registrations.registry.CreateRegistry;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.bogey.loco.LocoBogeyBlock;
import willow.train.kuayue.block.bogey.loco.LocoBogeyEntity;
import willow.train.kuayue.block.bogey.loco.renderer.DF11GRenderer;
import willow.train.kuayue.block.bogey.loco.renderer.QJMainRenderer;
import willow.train.kuayue.initial.AllElements;

public class AllLocoBogeys {
    public static CreateRegistry testRegistry = AllElements.testRegistry;

    public static final BogeySizeReg df11g = new BogeySizeReg("df11g")
            .size(0.915F / 2F)
            .submit(testRegistry);

    public static final BogeySizeReg df11gBackward = new BogeySizeReg("df11g_backward")
            .size(0.915F / 2F)
            .submit(testRegistry);

    public static final BogeySizeReg qjMain = new BogeySizeReg("qj_main")
            .size(0.915F / 2F)
            .submit(testRegistry);
    public static final BogeyGroupReg locoBogeyGroup = new BogeyGroupReg("loco", "kuayue_bogey")
            .bogey(df11g.getSize(), DF11GRenderer::new, testRegistry.asResource("df11g_bogey"))
            .bogey(df11gBackward.getSize(), DF11GRenderer.Backward::new, testRegistry.asResource("df11g_backward_bogey"))
            .bogey(qjMain.getSize(), QJMainRenderer::new, testRegistry.asResource("qj_bogey"))
            .translationKey("loco_group")
            .submit(testRegistry);

    public static final BogeyBlockReg<LocoBogeyBlock> df11gBogey =
            new BogeyBlockReg<LocoBogeyBlock>("df11g_bogey")
                    .block(LocoBogeyBlock::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.PODZOL)
                    .translationKey("df11g_bogey")
                    .size(df11g)
                    .submit(testRegistry);

    public static final BogeyBlockReg<LocoBogeyBlock> df11gBackwardBogey =
            new BogeyBlockReg<LocoBogeyBlock>("df11g_backward_bogey")
                    .block(LocoBogeyBlock::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.PODZOL)
                    .translationKey("df11g_bogey")
                    .size(df11gBackward)
                    .submit(testRegistry);

    public static final BogeyBlockReg<LocoBogeyBlock> qjMainBogey =
            new BogeyBlockReg<LocoBogeyBlock>("qj_bogey")
                    .block(LocoBogeyBlock::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.PODZOL)
                    .translationKey("qj_bogey")
                    .size(qjMain)
                    .submit(testRegistry);

    public static final BlockEntityReg<LocoBogeyEntity> locoBogeyEntity =
            new BlockEntityReg<LocoBogeyEntity>("loco_bogey_entity")
                    .blockEntityType(LocoBogeyEntity::new)
                    .addBlock(() -> df11gBogey.getEntry().get())
                    .addBlock(() -> df11gBackwardBogey.getEntry().get())
                    .addBlock(() -> qjMainBogey.getEntry().get())
                    .withRenderer(BogeyBlockEntityRenderer::new)
                    .submit(testRegistry);

    public static void invoke() {}
}
