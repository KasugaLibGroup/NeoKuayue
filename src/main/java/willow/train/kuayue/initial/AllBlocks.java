package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.base.CompanyTrainBlockEntity;
import willow.train.kuayue.block.panels.base.CompanyTrainDoor;
import willow.train.kuayue.block.panels.base.CompanyTrainPanel;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedDoorEntity;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedDoorRenderer;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedEndFaceRenderer;
import willow.train.kuayue.block.panels.block_entity.CustomRenderedEndfaceEntity;
import willow.train.kuayue.initial.panel.*;

public class AllBlocks {

    public static final BlockReg<CompanyTrainPanel> COMPANY_TRAIN_PANEL =
            new BlockReg<CompanyTrainPanel>("company_panel_block")
                    .blockType(CompanyTrainPanel::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.COLOR_GRAY)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .submit(AllElements.testRegistry);

    public static final BlockReg<CompanyTrainDoor> COMPANY_TRAIN_DOOR =
            new BlockReg<CompanyTrainDoor>("company_door_block")
                    .blockType(CompanyTrainDoor::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.COLOR_GRAY)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .submit(AllElements.testRegistry);

    public static final BlockReg<CompanyTrainDoor.Sliding> COMPANY_SLIDING_DOOR =
            new BlockReg<CompanyTrainDoor.Sliding>("company_sliding_door")
                    .blockType(CompanyTrainDoor.Sliding::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.COLOR_GRAY)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CompanyTrainBlockEntity> COMPANY_TRAIN_BLOCK_ENTITY =
            new BlockEntityReg<CompanyTrainBlockEntity>("company_panel")
                    .blockEntityType(CompanyTrainBlockEntity::new)
                    .addBlock(COMPANY_TRAIN_PANEL)
                    .addBlock(COMPANY_TRAIN_DOOR)
                    .addBlock(COMPANY_SLIDING_DOOR)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CustomRenderedDoorEntity> CUSTOM_RENDERED_DOOR_ENTITY =
            new BlockEntityReg<CustomRenderedDoorEntity>("custom_rendered_door")
                    .blockEntityType(CustomRenderedDoorEntity::new)
                    .withRenderer(CustomRenderedDoorRenderer::new)
                    .addBlock(CR200JPanel.DOOR_CR200J.block)
                    .addBlock(CR200JPanel.DOOR_CR200J_2.block)
                    .addBlock(CR200JPanel.DOOR_CABIN_MARSHALLED_CR200J.block)
                    .addBlock(C25BPanel.DOOR_25B.block)
                    .addBlock(C25GPanel.DOOR_25G.block)
                    .addBlock(C25KPanel.DOOR_25K.block)
                    .addBlock(C25TPanel.DOOR_25T.block)
                    .addBlock(C25TPanel.DOOR_SLIDING_25T.block)
                    .addBlock(C25ZPanel.DOOR_25Z.block)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CustomRenderedEndfaceEntity> CUSTOM_RENDERED_ENDFACE_ENTITY =
            new BlockEntityReg<CustomRenderedEndfaceEntity>("custom_rendered_end_face")
                    .blockEntityType(CustomRenderedEndfaceEntity::new)
                    .withRenderer(CustomRenderedEndFaceRenderer::new)
                    .addBlock(CR200JPanel.END_FACE_MARSHALLED_CR200J.block)
                    .addBlock(CR200JPanel.END_FACE_MARSHALLED_CR200J_2.block)
                    .submit(AllElements.testRegistry);

    public static void invoke() {
        C25GPanel.invoke();
        C25BPanel.invoke();
        C25KPanel.invoke();
        C25TPanel.invoke();
        C25ZPanel.invoke();
        CR200JPanel.invoke();
    }
}
