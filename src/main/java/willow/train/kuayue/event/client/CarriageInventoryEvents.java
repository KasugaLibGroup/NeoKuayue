package willow.train.kuayue.event.client;

import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.initial.ClientInit;
import willow.train.kuayue.initial.ImageInit;
import willow.train.kuayue.initial.create.AllTracks;
import willow.train.kuayue.initial.panel.*;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.ItemIconButton;
import willow.train.kuayue.systems.train_carriage_package.*;
import willow.train.kuayue.utils.client.RenderablePicture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CarriageInventoryEvents {
    public static final ImageInit.ImageRegex UP_REGEX = ImageInit.register("textures/gui/up_button.png", 0);
    public static final ImageInit.ImageRegex DOWN_REGEX = ImageInit.register("textures/gui/down_button.png", 0);
    private static int carriageType = 0;
    ItemIconButton[] imgBtn = new ItemIconButton[8];
    ImageButton[] upAndDownBtn = new ImageButton[2];
    ArrayList<List<ItemStack>> itemList = new ArrayList<>();
    List<ItemStack> schematicItemList = new ArrayList<>();
    int btn_location = 0;
    int showBtnNumber = 5;
    int guiLeft = 0, guiTop = 0;
    boolean onChanged = true;
    String playerName = "";

    LazyRecomputable<ImageMask> upRegex = LazyRecomputable.of(() -> {
        try {
            return ClientInit.carriageEventRegexUp.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    LazyRecomputable<ImageMask> downRegex = LazyRecomputable.of(() -> {
        try {
            return ClientInit.carriageEventRegexDown.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    @SubscribeEvent
    public void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        carriageType = 0;
    }

    @SubscribeEvent
    public void onPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event) {
        playerName = event.getPlayer().getDisplayName().getString();
        generateSchematicTab();
    }

    @SubscribeEvent
    @SuppressWarnings({"resource"})
    public void onScreenInit(ScreenEvent.Init.Post event) {
        if (!(event.getScreen() instanceof CreativeModeInventoryScreen)) return;
        this.guiLeft = ((CreativeModeInventoryScreen) event.getScreen()).getGuiLeft();
        this.guiTop = ((CreativeModeInventoryScreen) event.getScreen()).getGuiTop();
        CreativeModeInventoryScreen.ItemPickerMenu menu =
                ((CreativeModeInventoryScreen) event.getScreen()).getMenu();
        int tab = ((CreativeModeInventoryScreen) event.getScreen()).getSelectedTab();

        if (tab == AllElements.neoKuayueMainTab.getTab().getId()) {
            addSchematicToTab(menu);
        }

        ItemStack[] icons = new ItemStack[8];
        icons[0] = new ItemStack(AllTracks.standardTrack.getBlock());
        icons[1] = new ItemStack(C25BPanel.PANEL_BOTTOM_25B.block.getBlock());
        icons[2] = new ItemStack(C25GPanel.PANEL_BOTTOM_25G.block.getBlock());
        icons[3] = new ItemStack(C25KPanel.PANEL_BOTTOM_25K.block.getBlock());
        icons[4] = new ItemStack(C25ZPanel.PANEL_BOTTOM_LINE_25Z.block.getBlock());
        icons[5] = new ItemStack(C25TPanel.PANEL_BOTTOM_25T.block.getBlock());
        icons[6] = new ItemStack(C25BPanel.PANEL_SYMBOL_MARSHALLED_25B.block.getBlock());
        icons[7] = new ItemStack(CR200JPanel.PANEL_BOTTOM_MARSHALLED_CR200J.block.getBlock());

        upAndDownBtn[0] = new ImageButton(upRegex, this.guiLeft - 22, this.guiTop - 8, 20, 20, Component.empty(),
                b -> {
                    if(btn_location > 0){
                        btn_location --;
                        btn_location = Math.max(btn_location, 0);
                        refreshBtn(tab);
                        onUp();
                    }
                });
        upAndDownBtn[1] = new ImageButton(downRegex, this.guiLeft - 22, this.guiTop - 8 + (showBtnNumber + 1) * 22, 20, 20, Component.empty(),
                b -> {
                    if(btn_location < imgBtn.length - showBtnNumber){
                        btn_location++;
                        btn_location = Math.min(btn_location, imgBtn.length - showBtnNumber);
                        refreshBtn(tab);
                        onDown();
                    }
                });

        initMapping();

        Component[] components = new Component[8];
        components[0] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.all");
        components[1] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.25b");
        components[2] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.25g");
        components[3] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.25k");
        components[4] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.25z");
        components[5] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.25t");
        components[6] =
                Component.translatable(
                        "container." + Kuayue.MODID + ".inventory.button.marshalled_25_series");
        components[7] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.cr200j");

        for (int i = 0; i < imgBtn.length; i++) {
            imgBtn[i] =
                    new ItemIconButton(
                            this.guiLeft - 22,
                            this.guiTop  + 14 + (i - btn_location) * 22,
                            components[i],
                            icons[i],
                            b -> {
                                for (int bx = 0; bx < imgBtn.length; bx++) {
                                    if (b.equals(imgBtn[bx])) {
                                        carriageType = bx;
                                        ((ItemIconButton) b).toggle();
                                        onChanged = true;
                                        menu.scrollTo(0.0f);
                                    } else {
                                        (imgBtn[bx]).reset();
                                    }
                                }
                            },
                            i == 0 ? -2 : 1,
                            i == 0 ? 0 : 2);
        }
        for (ItemIconButton b : imgBtn) {
            event.addListener(b);
        }
        refreshBtn(tab);
        for(ImageButton b : upAndDownBtn) {
            event.addListener(b);
            b.visible = tab == AllElements.neoKuayueCarriageTab.getTab().getId();
        }

        imgBtn[0].toggle();
        updateButtons();
        menu.scrollTo(0);
    }

    private void refreshBtn(int tab) {
        int right_edge = btn_location + showBtnNumber - 1;
        for(int i = 0; i < imgBtn.length; i++) {
            if(i < btn_location || i > right_edge) {
                imgBtn[i].visible = false;
            } else {
                imgBtn[i].visible = tab == AllElements.neoKuayueCarriageTab.getTab().getId();
            }
        }
    }

    private void onUp() {
        for(ItemIconButton b : imgBtn) {
            b.y += 22;
        }
    }

    private void onDown() {
        for(ItemIconButton b : imgBtn) {
            b.y -= 22;
        }
    }

    @SubscribeEvent
    public void onRender(ScreenEvent.Render.Pre event) {
        Screen screen = event.getScreen();
        if (screen instanceof CreativeModeInventoryScreen) {
            updateButtons();
            int tab = ((CreativeModeInventoryScreen) screen).getSelectedTab();
            for(ImageButton b : upAndDownBtn) {
                b.visible = tab == AllElements.neoKuayueCarriageTab.getTab().getId();
            }
            refreshBtn(tab);
            if (tab == AllElements.neoKuayueMainTab.getTab().getId()) {
                addSchematicToTab(((CreativeModeInventoryScreen) screen).getMenu());
            }
        }
    }

    public void updateButtons() {
        Screen screen = Minecraft.getInstance().screen;
        int tab = ((CreativeModeInventoryScreen) screen).getSelectedTab();
        CreativeModeInventoryScreen.ItemPickerMenu menu =
                ((CreativeModeInventoryScreen) screen).getMenu();
        if (screen instanceof CreativeModeInventoryScreen) {
            boolean vis = tab == AllElements.neoKuayueCarriageTab.getTab().getId();
            for (ItemIconButton b : imgBtn) {
                b.visible = vis;
            }
            if (vis) {
                if (onChanged) {
                    menu.scrollTo(0.0f);
                    updateMenuItem(menu);
                    menu.scrollTo(0.0f);
                    onChanged = false;
                }
            } else {
                onChanged = true;
            }
        }
    }

    public void updateMenuItem(CreativeModeInventoryScreen.ItemPickerMenu menu) {
        switch (carriageType) {
            case 1: // B
                menu.items.clear();
                menu.items.addAll(itemList.get(0));
                menu.items.addAll(itemList.get(7));
                menu.items.addAll(itemList.get(8));
                break;
            case 2: // G
                menu.items.clear();
                menu.items.addAll(itemList.get(1));
                menu.items.addAll(itemList.get(7));
                menu.items.addAll(itemList.get(8));
                break;
            case 3: // K
                menu.items.clear();
                menu.items.addAll(itemList.get(2));
                menu.items.addAll(itemList.get(7));
                menu.items.addAll(itemList.get(8));
                break;
            case 4: // Z
                menu.items.clear();
                menu.items.addAll(itemList.get(3));
                menu.items.addAll(itemList.get(7));
                menu.items.addAll(itemList.get(8));
                break;
            case 5: // T
                menu.items.clear();
                menu.items.addAll(itemList.get(4));
                menu.items.addAll(itemList.get(7));
                break;
            case 6: // M
                menu.items.clear();
                menu.items.addAll(itemList.get(5));
                menu.items.addAll(itemList.get(7));
                break;
            case 7: // M
                menu.items.clear();
                menu.items.addAll(itemList.get(6));
                menu.items.addAll(itemList.get(7));
                break;
            default:
                menu.items.clear();
                menu.items.addAll(itemList.get(0)); // B
                menu.items.addAll(itemList.get(1)); // G
                menu.items.addAll(itemList.get(2)); // K
                menu.items.addAll(itemList.get(3)); // Z
                menu.items.addAll(itemList.get(4)); // T
                menu.items.addAll(itemList.get(5)); // M
                menu.items.addAll(itemList.get(6));  // cr200j
                menu.items.addAll(itemList.get(7)); // general
                menu.items.addAll(itemList.get(8)); // bgzk
        }
    }

    public void initMapping() {
        itemList =
                new ArrayList<>() {
                    {
                        add(Carriage25B.getList());  // 25B 0
                        add(Carriage25G.getList());  // 25G 1
                        add(Carriage25K.getList());  // 25K 2
                        add(Carriage25Z.getList());  // 25Z 3
                        add(Carriage25T.getList());  // 25T 4
                        add(Marshalled25Series.getList());  // 25 Marshalled Series 5
                        add(CarriageCR200j.getList()); // cr200j 6
                        add(
                                List.of(
                                        // BlockInit.ORIGINAL_COLOR_WINDOW_25.get().asItem().getDefaultInstance(),
                                        // BlockInit.ORIGINAL_COLOR_WINDOW_25_SEALED.get().asItem().getDefaultInstance()
                                        )); // 通用 7

                        add(Carriage25BGZK.getList()); // BGZK 8
                    }
                };
    }

    public void addSchematicToTab(CreativeModeInventoryScreen.ItemPickerMenu menu) {
        if (!schematicItemList.isEmpty()) {
            for (ItemStack stack : schematicItemList) {
                if (!menu.items.contains(stack)) {
                    menu.items.add(stack);
                }
            }
            //menu.scrollTo(1);
        }
    }

    public void generateSchematicTab() {
        try {
            // TODO: for next version (0.4)
            // MegumiKasuga 24/02/16
            /*
            Map<String, byte[]> streamMap = SchematicInjectionInit.schematicMap;

            for (String name : streamMap.keySet()) {
                if(name.contains("25k")) {
                    schematicItemList.add(
                            SchematicItemUtil.create(SchematicItemInit.SCHEMATIC_25K.asStack(), name, ""));
                    continue;
                }
                if(name.contains("25g")) {
                    schematicItemList.add(
                            SchematicItemUtil.create(SchematicItemInit.SCHEMATIC_25G.asStack(), name, ""));
                    continue;
                }
                if(name.contains("25b")) {
                    schematicItemList.add(
                            SchematicItemUtil.create(SchematicItemInit.SCHEMATIC_25B.asStack(), name, ""));
                    continue;
                }
                if(name.contains("25z")) {
                    schematicItemList.add(
                            SchematicItemUtil.create(SchematicItemInit.SCHEMATIC_25Z.asStack(), name, ""));
                    continue;
                }
                if(name.contains("25t")) {
                    schematicItemList.add(
                            SchematicItemUtil.create(SchematicItemInit.SCHEMATIC_25T.asStack(), name, ""));
                    continue;
                }
                schematicItemList.add(SchematicItem.create(name, ""));
            }

             */
        } catch (Exception e) {
            // Kuayue.LOGGER.error("FATAL ERROR IN LOADING KUAYUE SCHEMATICS ", e);
        }
    }
}
