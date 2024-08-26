package willow.train.kuayue.systems.editable_panel;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.block.panels.base.TrainPanelProperties;
import willow.train.kuayue.block.panels.block_entity.EditablePanelEntity;
import willow.train.kuayue.initial.ImageInit;
import willow.train.kuayue.systems.editable_panel.interfaces.IEditScreenMethods;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.utils.client.RenderablePicture;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static willow.train.kuayue.systems.editable_panel.EditableTypeConstants.*;

public class EditablePanelEditScreen extends AbstractContainerScreen<EditablePanelEditMenu> {

    private EditablePanelEntity entity;

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Kuayue.MODID, "textures/gui/gui.png");

    public EditablePanelEditScreen(EditablePanelEditMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        loadEntity(pMenu.getEditablePanelEntity());
    }

    public void loadEntity(EditablePanelEntity entity) {
        this.entity = entity;
    }

    @Override
    public void init() {
        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(entity.getEditType())) {
                signType.getScreenMethods().get().init(this, entity);
                return;
            }
        }
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        for (SignType signType : EditableTypeConstants.getSignTypeMap().values()) {
            if (signType.shouldRender(entity.getEditType())) {
                signType.getScreenMethods().get().renderLabels(pPoseStack, pMouseX, pMouseY);
                return;
            }
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        if (this.entity.getEditType() == TrainPanelProperties.EditType.LAQUERED)
            return;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    // TODO CarriageTypeSign的Screen方法实现类
    public static final IEditScreenMethods CARRIAGE_TYPE_SIGN_METHODS = new IEditScreenMethods() {

        private static final Component[] TEXT =
                new Component[] {
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.type_chs")
                        , // 类型(中文) 0
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.type_chs_py"), // 类型(拼音) 1
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.type_abbr"), // 类型(缩写) 2
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.sub_type"), // 子类 3
                        Component.translatable(
                                "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.no"), // 编号 4
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.color"), // 颜色label 5
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.predefine_color"), // 预定义颜色 6
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.confirm"), // 确认 7
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".laquered_board_edit_menu.edit_color"), // 编辑颜色 8
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.cancel"), // 取消 9
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".laquered_board_edit_menu.edit_text") // 编辑字体 10
                };

        Component random_confirm =
                Component.translatable(
                        "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.random_confirm");
        Component random_cancel =
                Component.translatable(
                        "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.random_cancel");
        Component random_generate =
                Component.translatable(
                        "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.random_generate");
        Component random_title_l =
                Component.translatable(
                        "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.random_title_left");
        Component random_title_c =
                Component.translatable(
                        "container."
                                + Kuayue.MODID
                                + ".carriage_type_sign_edit_menu.random_title_center");
        Component random_title_r =
                Component.translatable(
                        "container."
                                + Kuayue.MODID
                                + ".carriage_type_sign_edit_menu.random_title_right");
        Component[] random_tooltips =
                new Component[] {
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_title"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_yz"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_rz"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_yw"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_rw"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_xl"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_ca"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_kd"),
                        Component.translatable(
                                "container."
                                        + Kuayue.MODID
                                        + ".carriage_type_sign_edit_menu.random_tooltip_yoz")
                };

        private boolean editColor = false;
        private boolean randomPanel = false;
        private static int editBoxWidth = 200;
        private static int editBoxHeight = 20;
        private static int offsetX = 70;
        private static int offsetY = 15;
        EditBox TypeChs, TypePinyin, TypeAbbr, SubType, No, Color, random_box, random_max, random_min;

        private Button[] buttons = new Button[3];
        private Button[] random_buttons = new Button[2];
        private Button[] colorButtons = new Button[7];
        private ImageButton random;
        private Button generate;
        public static final ImageInit.ImageRegex DICE_REGEX = ImageInit.register("textures/gui/dice_button.png", 0);
        public final RenderablePicture dice = new RenderablePicture(DICE_REGEX);

        @Override
        public void init(EditablePanelEditScreen screen, EditablePanelEntity entity) {
            int windowWidth = Objects.requireNonNull(screen.getMinecraft().screen).width;
            int centreX = windowWidth / 2;

            int xPos = centreX - editBoxWidth / 2 - offsetX;

            Minecraft minecraft = Minecraft.getInstance();
            TypeChs =
                    new EditBox(
                            minecraft.font, xPos, 45 - offsetY, editBoxWidth, editBoxHeight, TEXT[0]);
            TypePinyin =
                    new EditBox(
                            minecraft.font, xPos, 80 - offsetY, editBoxWidth, editBoxHeight, TEXT[1]);
            TypeAbbr =
                    new EditBox(
                            minecraft.font, xPos, 115 - offsetY, editBoxWidth, editBoxHeight, TEXT[2]);
            SubType =
                    new EditBox(
                            minecraft.font, xPos, 150 - offsetY, editBoxWidth, editBoxHeight, TEXT[3]);
            No =
                    new EditBox(
                            minecraft.font,
                            xPos,
                            185 - offsetY,
                            editBoxWidth - 30,
                            editBoxHeight,
                            TEXT[4]);
            Color =
                    new EditBox(
                            minecraft.font, xPos, 45 - offsetY, editBoxWidth, editBoxHeight, TEXT[6]);

            random_min =
                    new EditBox(
                            minecraft.font,
                            xPos,
                            50 - offsetY,
                            editBoxWidth / 3 - 10,
                            editBoxHeight,
                            Component.empty());
            random_box =
                    new EditBox(
                            minecraft.font,
                            xPos + editBoxWidth / 3 + 5,
                            50 - offsetY,
                            editBoxWidth / 3 - 10,
                            editBoxHeight,
                            Component.empty());
            random_max =
                    new EditBox(
                            minecraft.font,
                            xPos + editBoxWidth * 2 / 3 + 10,
                            50 - offsetY,
                            editBoxWidth / 3 - 10,
                            editBoxHeight,
                            Component.empty());

            TypeChs.setValue(
                    entity.getTypeSignMessage(0, false).getString().equals("XXX")
                            ? ""
                            : entity.getTypeSignMessage(0, false).getString());
            TypePinyin.setValue(
                    entity.getTypeSignMessage(1, false).getString().equals("XXXXXX")
                            ? ""
                            : entity.getTypeSignMessage(1, false).getString());
            TypeAbbr.setValue(
                    entity.getTypeSignMessage(2, false).getString().equals("XX")
                            ? ""
                            : entity.getTypeSignMessage(2, false).getString());
            SubType.setValue(
                    entity.getTypeSignMessage(3, false).getString().equals("XXX")
                            ? ""
                            : entity.getTypeSignMessage(3, false).getString());
            No.setValue(
                    entity.getTypeSignMessage(4, false).getString().equals("XXXXXX")
                            ? ""
                            : entity.getTypeSignMessage(4, false).getString());
            Color.setValue(entity.getColor() + "");
            random_box.setValue(No.getValue());

            for (int i = 0; i < colorButtons.length; i++) {
                colorButtons[i] =
                        new Button(
                                xPos + (i % 2) * editBoxWidth / 2 + 5,
                                90 - offsetY + ((int) (((float) i) / 2)) * 25,
                                editBoxWidth / 2 - 10,
                                editBoxHeight,
                                predefineColorMapping(i),
                                b -> {
                                    for (int bx = 0; bx < colorButtons.length; bx++) {
                                        if (colorButtons[bx].equals(b)) {
                                            Color.setValue(predefineColorMapping2(bx) + "");
                                        }
                                    }
                                });
            }

            generate =
                    new Button(
                            xPos,
                            80 - offsetY,
                            editBoxWidth,
                            editBoxHeight,
                            random_generate,
                            b -> {
                                Random random1 = new Random();
                                // random_box.setValue(No.getValue());
                                try {
                                    int min = 0;
                                    int bound = 0;
                                    switch (random_box.getValue().toLowerCase(Locale.ROOT)) {
                                        case "yz" -> {
                                            min = 300000;
                                            bound = 309999;
                                            break;
                                        } // 硬座
                                        case "rz" -> {
                                            min = 110000;
                                            bound = 119999;
                                            break;
                                        } // 软座
                                        case "yw" -> {
                                            min = 600000;
                                            bound = 699999;
                                            break;
                                        } // 硬卧
                                        case "rw" -> {
                                            min = 500000;
                                            bound = 599999;
                                            break;
                                        } // 软卧
                                        case "xl" -> {
                                            min = 200000;
                                            bound = 299999;
                                            break;
                                        } // 行李
                                        case "ca" -> {
                                            min = 800000;
                                            bound = 899999;
                                            break;
                                        } // 餐车
                                        case "kd" -> {
                                            min = 900000;
                                            bound = 999999;
                                            break;
                                        } // 空电
                                        case "uz" -> {
                                            min = 8000;
                                            bound = 8999;
                                            break;
                                        } // 邮政
                                        default -> {
                                            min = Integer.parseInt(random_min.getValue());
                                            bound = Integer.parseInt(random_max.getValue());
                                        }
                                    }
                                    random_box.setValue(random1.nextInt(min, bound) + "");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

            random_box.setValue(No.getValue());

            random =
                    new ImageButton(
                            dice,
                            xPos + editBoxWidth - 25,
                            185 - offsetY,
                            20,
                            20,
                            b -> {
                                removeTextPanel(screen);
                                removeMainButtons(screen);
                                renderRandomPanel(screen);
                                editColor = false;
                                randomPanel = true;
                            });

            for (int i = 0; i < 2; i++) {
                random_buttons[i] =
                        new Button(
                                xPos + i * editBoxWidth / 2 + 5,
                                230 - offsetY,
                                editBoxWidth / 2 - 10,
                                editBoxHeight,
                                i == 0 ? random_confirm : random_cancel,
                                b -> {
                                    if (b.equals(random_buttons[0])) {
                                        try {
                                            Integer.parseInt(random_box.getValue());
                                        } catch (Exception e) {
                                            return;
                                        }
                                        No.setValue(random_box.getValue());
                                    }
                                    removeRandomPanel(screen);
                                    renderMainButtons(screen);
                                    renderTextPanel(screen);
                                    editColor = false;
                                    randomPanel = false;
                                });
            }

        /*
           这个位置是定义按钮逻辑的
        */
            for (int i = 0; i < 3; i++) {
                buttons[i] =
                        screen.addRenderableWidget(
                                new Button(
                                        xPos + i * editBoxWidth / 3 + 5,
                                        200,
                                        editBoxWidth / 3 - 10,
                                        editBoxHeight,
                                        TEXT[i + 7],
                                        b -> {
                                            if (b.equals(buttons[0])) {
                                                if (entity != null) {
                                                    boolean error = false;
                                                    int c = entity.getColor();
                                                    Color.setValue(
                                                            Color.getValue().replaceAll(" ", ""));
                                                    if (Color.getValue().equals(""))
                                                        Color.setValue(entity.getColor() + "");
                                                    try {
                                                        if (Color.getValue()
                                                                .toLowerCase(Locale.ROOT)
                                                                .startsWith("0x"))
                                                            c =
                                                                    Integer.parseInt(
                                                                            Color.getValue()
                                                                                    .toLowerCase(
                                                                                            Locale.ROOT)
                                                                                    .substring(2),
                                                                            16);
                                                        else c = Integer.parseInt(Color.getValue());
                                                    } catch (Exception e) {
                                                        error = true;
                                                    }
                                                    if (!error) {
                                                        entity.setColor(c);
                                                        if (!isEmpty(
                                                                TypeChs,
                                                                TypePinyin,
                                                                TypePinyin,
                                                                SubType,
                                                                No)) {
                                                            entity.setTypeSignMessages(
                                                                    new String[] {
                                                                            TypeChs.getValue(),
                                                                            TypePinyin.getValue(),
                                                                            TypeAbbr.getValue(),
                                                                            SubType.getValue(),
                                                                            No.getValue()
                                                                    });
                                                        }
                                                        entity.markUpdated(true);
                                                        screen.onClose();
                                                    }
                                                }
                                            } else if (b.equals(buttons[1])) {
                                                editColor = !editColor;
                                                randomPanel = false;
                                                if (editColor) {
                                                    b.setMessage(TEXT[10]);
                                                    removeTextPanel(screen);
                                                    renderColorPanel(screen);
                                                } else {
                                                    b.setMessage(TEXT[8]);
                                                    removeColorPanel(screen);
                                                    renderTextPanel(screen);
                                                }
                                            } else if (b.equals(buttons[2])) {
                                                screen.onClose();
                                            }
                                        }));
            }

            if (!editColor && !randomPanel) {
                renderTextPanel(screen);
            } else if (editColor) {
                renderColorPanel(screen);
                buttons[1].setMessage(TEXT[10]);
            } else if (randomPanel) {
                removeMainButtons(screen);
                renderRandomPanel(screen);
            }
        }

        @Override
        public void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {

        }

        public Component predefineColorMapping(int x) {
            switch (x) {
                case 0 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.yellow");
                }
                case 1 -> {
                    return Component.translatable(
                            "container."
                                    + Kuayue.MODID
                                    + ".carriage_type_sign_edit_menu.color.yellow_2");
                }
                case 2 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.red");
                }
                case 3 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.blue");
                }
                case 4 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.blue_2");
                }
                case 5 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.blue_3");
                }
                case 6 -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.black");
                }
                default -> {
                    return Component.translatable(
                            "container." + Kuayue.MODID + ".carriage_type_sign_edit_menu.color.yellow");
                }
            }
        }

        public int predefineColorMapping2(int x) {
            switch (x) {
                case 0 -> {
                    return YELLOW;
                }
                case 1 -> {
                    return YELLOW2;
                }
                case 2 -> {
                    return RED;
                }
                case 3 -> {
                    return BLUE;
                }
                case 4 -> {
                    return BLUE2;
                }
                case 5 -> {
                    return BLUE3;
                }
                case 6 -> {
                    return BLACK;
                }
                default -> {
                    return YELLOW;
                }
            }
        }

        public void renderMainButtons(EditablePanelEditScreen screen) {
            for (Button b : buttons) {
                screen.addRenderableWidget(b);
            }
        }

        public void removeMainButtons(EditablePanelEditScreen screen) {
            for (Button b : buttons) {
                screen.removeWidget(b);
            }
        }

        public void renderTextPanel(EditablePanelEditScreen screen) {
            screen.addRenderableWidget(TypeChs);
            screen.addRenderableWidget(TypePinyin);
            screen.addRenderableWidget(TypeAbbr);
            screen.addRenderableWidget(SubType);
            screen.addRenderableWidget(No);
            screen.addRenderableWidget(random);
        }

        public void removeTextPanel(EditablePanelEditScreen screen) {
            screen.removeWidget(TypeChs);
            screen.removeWidget(TypePinyin);
            screen.removeWidget(TypeAbbr);
            screen.removeWidget(SubType);
            screen.removeWidget(No);
            screen.removeWidget(random);
        }

        public void renderColorPanel(EditablePanelEditScreen screen) {
            screen.addRenderableWidget(Color);
            for (Button b : colorButtons) {
                screen.addRenderableWidget(b);
            }
        }

        public void removeColorPanel(EditablePanelEditScreen screen) {
            screen.removeWidget(Color);
            for (Button b : colorButtons) {
                screen.removeWidget(b);
            }
        }

        public void renderRandomPanel(EditablePanelEditScreen screen) {
            screen.addRenderableWidget(random_min);
            screen.addRenderableWidget(random_box);
            screen.addRenderableWidget(random_max);
            screen.addRenderableWidget(generate);
            screen.addRenderableWidget(random_buttons[0]);
            screen.addRenderableWidget(random_buttons[1]);
        }

        public void removeRandomPanel(EditablePanelEditScreen screen) {
            screen.removeWidget(random_min);
            screen.removeWidget(random_box);
            screen.removeWidget(random_max);
            screen.removeWidget(generate);
            screen.removeWidget(random_buttons[0]);
            screen.removeWidget(random_buttons[1]);
        }

        public int offset(int x) {
            return x + (Minecraft.getInstance().getWindow().getWidth() - 176) / 2 + offsetX;
        }

        private boolean isEmpty(EditBox... boxes) {
            for (EditBox c : boxes) {
                if (!c.getValue().equals("")) {
                    return false;
                }
            }
            return true;
        }
    };
}
