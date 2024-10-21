package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.Vec2;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.block.panels.base.*;
import willow.train.kuayue.block.panels.block_entity.*;
import willow.train.kuayue.block.panels.door.DoubleDoorBlock;
import willow.train.kuayue.block.seat.SeatBlockEntity;
import willow.train.kuayue.block.structure.platform.PlatformWallBlock;
import willow.train.kuayue.initial.panel.*;
import willow.train.kuayue.initial.registration.PanelRegistration;

import static willow.train.kuayue.initial.panel.CM1Panel.END_FACE_MIDDLE_M1;

public class AllBlocks {

    public static final BlockReg<Block> CR_LOGO =
            new BlockReg<Block>("cr_logo")
                    .blockType(Block::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.PLANT)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .addProperty(properties -> properties.sound(SoundType.POLISHED_DEEPSLATE))
                    .addProperty(properties -> properties.strength(1.5f, 3.0f))
                    .defaultBlockItem()
                    // .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

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

    public static final BlockReg<CompanyTrainSlab> COMPANY_FLOOR =
            new BlockReg<CompanyTrainSlab>("company_floor")
                    .blockType(p -> new CompanyTrainSlab(p, false))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.COLOR_GRAY)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .submit(AllElements.testRegistry);

    public static final BlockReg<CompanyTrainSlab> COMPANY_CARPORT =
            new BlockReg<CompanyTrainSlab>("company_carport")
                    .blockType(p -> new CompanyTrainSlab(p, true))
                    .material(Material.METAL)
                    .materialColor(MaterialColor.COLOR_GRAY)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .submit(AllElements.testRegistry);

    public static final PanelRegistration<DoubleDoorBlock> PLATFORM_DOOR =
            new PanelRegistration<DoubleDoorBlock>("platform_door")
                    .block(properties ->
                            new DoubleDoorBlock(
                                    properties, new Vec2(0, 0), new Vec2(1, 1),
                                    TrainPanelProperties.DoorType.SLIDE,
                                    "door/platform_door/platform_door_side",
                                    "door/platform_door/platform_door_left",
                                    "door/platform_door/platform_door_right"
                            )
                    ).materialAndColor(Material.METAL, MaterialColor.METAL)
                    .tab(AllElements.neoKuayueMainTab)
                    .noOcclusion()
                    .submit(AllElements.testRegistry);

    public static final BlockReg<PlatformWallBlock> PLATFORM_WALL =
            new BlockReg<PlatformWallBlock>("platform_wall")
                    .blockType(PlatformWallBlock::new)
                    .material(Material.METAL)
                    .materialColor(MaterialColor.METAL)
                    .addProperty(BlockBehaviour.Properties::noOcclusion)
                    .defaultBlockItem()
                    .tabTo(AllElements.neoKuayueMainTab)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CompanyTrainBlockEntity> COMPANY_TRAIN_BLOCK_ENTITY =
            new BlockEntityReg<CompanyTrainBlockEntity>("company_panel")
                    .blockEntityType(CompanyTrainBlockEntity::new)
                    .addBlock(COMPANY_TRAIN_PANEL)
                    .addBlock(COMPANY_TRAIN_DOOR)
                    .addBlock(COMPANY_SLIDING_DOOR)
                    .addBlock(COMPANY_FLOOR)
                    .addBlock(COMPANY_CARPORT)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CustomRenderedDoorEntity> CUSTOM_RENDERED_DOOR_ENTITY =
            new BlockEntityReg<CustomRenderedDoorEntity>("custom_rendered_door")
                    .blockEntityType(CustomRenderedDoorEntity::new)
                    .withRenderer(() -> CustomRenderedDoorRenderer::new)
                    .addBlock(CR200JPanel.DOOR_CR200J.block)
                    .addBlock(CR200JPanel.DOOR_CR200J_2.block)
                    .addBlock(CR200JPanel.DOOR_CABIN_MARSHALLED_CR200J.block)
                    .addBlock(C25BPanel.DOOR_25B.block)
                    .addBlock(C25GPanel.DOOR_25G.block)
                    .addBlock(C25KPanel.DOOR_25K.block)
                    .addBlock(C25KPanel.DOOR_SLIDING_25K.block)
                    .addBlock(C25TPanel.DOOR_25T.block)
                    .addBlock(C25TPanel.DOOR_SLIDING_25T.block)
                    .addBlock(C25ZPanel.DOOR_25Z.block)
                    .addBlock(I11GPanel.DOOR_CABIN_DF11G.block)
                    .addBlock(I11GPanel.DOOR_EQUIP_ROOM_DF11G.block)
                    .addBlock(I3DPanel.DOOR_CABIN_HXD3D.block)
                    .addBlock(I21Panel.DOOR_CABIN_DF21.block)
                    .addBlock(I21Panel.DOOR_EQUIP_DF21.block)
                    .addBlock(CM1Panel.DOOR_M1.block)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<CustomRenderedEndfaceEntity> CUSTOM_RENDERED_ENDFACE_ENTITY =
            new BlockEntityReg<CustomRenderedEndfaceEntity>("custom_rendered_end_face")
                    .blockEntityType(CustomRenderedEndfaceEntity::new)
                    .withRenderer(() -> CustomRenderedEndFaceRenderer::new)
                    .addBlock(CR200JPanel.END_FACE_MARSHALLED_CR200J.block)
                    .addBlock(CR200JPanel.END_FACE_MARSHALLED_CR200J_2.block)
                    .addBlock(C25BPanel.END_FACE_25B_1.block)
                    .addBlock(C25BPanel.END_FACE_25B_2.block)
                    .addBlock(C25BPanel.END_FACE_25B_3.block)
                    .addBlock(C25GPanel.END_FACE_25G_1.block)
                    .addBlock(C25GPanel.END_FACE_25G_2.block)
                    .addBlock(C25GPanel.END_FACE_25G_3.block)
                    .addBlock(C25KPanel.END_FACE_25K_1.block)
                    .addBlock(C25KPanel.END_FACE_25K_2.block)
                    .addBlock(C25KPanel.END_FACE_25K_3.block)
                    .addBlock(C25KPanel.END_FACE_SLIDING_25K_1.block)
                    .addBlock(C25KPanel.END_FACE_SLIDING_25K_2.block)
                    .addBlock(C25KPanel.END_FACE_SLIDING_25K_3.block)
                    .addBlock(C25TPanel.END_FACE_25T_1.block)
                    .addBlock(C25TPanel.END_FACE_25T_2.block)
                    .addBlock(C25TPanel.END_FACE_25T_3.block)
                    .addBlock(C25TPanel.END_FACE_SLIDING_25T_1.block)
                    .addBlock(C25TPanel.END_FACE_SLIDING_25T_2.block)
                    .addBlock(C25TPanel.END_FACE_SLIDING_25T_3.block)
                    .addBlock(C25ZPanel.END_FACE_25Z_1.block)
                    .addBlock(C25ZPanel.END_FACE_25Z_2.block)
                    .addBlock(C25ZPanel.END_FACE_25Z_3.block)
                    .addBlock(I11GPanel.END_FACE_DF11G.block)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<DoubleDoorEntity> DOUBLE_DOOR_ENTITY =
            new BlockEntityReg<DoubleDoorEntity>("double_door")
                    .blockEntityType(DoubleDoorEntity::new)
                    .withRenderer(() -> DoubleDoorRenderer::new)
                    .addBlock(PLATFORM_DOOR.block)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<SingleSlidingDoorEntity> SINGLE_SLIDING_DOOR_ENTITY =
            new BlockEntityReg<SingleSlidingDoorEntity>("single_sliding_door")
                    .blockEntityType(SingleSlidingDoorEntity::new)
                    .withRenderer(() -> SingleSlidingDoorRenderer::new)
                    .addBlock(END_FACE_MIDDLE_M1.block)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<SeatBlockEntity> SEAT_BLOCK_ENTITY =
            new BlockEntityReg<SeatBlockEntity>("seat_block_entity")
                    .blockEntityType(SeatBlockEntity::new)
                    .addBlock(AllDecoBlocks.YZ_SEAT_BLUE)
                    .addBlock(AllDecoBlocks.YZ_SEAT_2)
                    .submit(AllElements.testRegistry);

    public static final BlockEntityReg<EditablePanelEntity> EDITABLE_PANEL_ENTITY =
            new BlockEntityReg<EditablePanelEntity>("editable_panel_entity")
                    .blockEntityType(EditablePanelEntity::new)
                    .withRenderer(() -> EditablePanelRenderer::new)
                    .blockPredicates((location, block) -> block instanceof TrainPanelBlock)
                    .submit(AllElements.testRegistry);

//    public static final MenuReg<EditablePanelEditMenu, EditablePanelEditScreen, EditablePanelEditScreen> EDITABLE_PANEL_EDIT_MENU =
//            new MenuReg<EditablePanelEditMenu, EditablePanelEditScreen, EditablePanelEditScreen>("editable_panel_edit_menu")
//                    .withMenuAndScreen(EditablePanelEditMenu::new, (component) -> new EditablePanelEditScreen())
//                    .submit(AllElements.testRegistry);

    public static void invoke() {
        C25GPanel.invoke();
        C25BPanel.invoke();
        C25KPanel.invoke();
        C25TPanel.invoke();
        C25ZPanel.invoke();
        C25Panel.invoke();
        CM1Panel.invoke();
        I11GPanel.invoke();
        I3DPanel.invoke();
        I21Panel.invoke();
        AllDecoBlocks.invoke();
        CR200JPanel.invoke();
    }
}
