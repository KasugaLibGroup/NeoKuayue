package willow.train.kuayue.event.client;

import kasuga.lib.core.client.render.texture.ImageMask;
import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.*;
import willow.train.kuayue.initial.panel.*;
import willow.train.kuayue.mixins.mixin.client.AccessorCreativeTabScreen;
import willow.train.kuayue.systems.editable_panel.widget.ImageButton;
import willow.train.kuayue.systems.editable_panel.widget.ItemIconButton;
import willow.train.kuayue.utils.TagsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class CarriageInventoryEvents {

    private static final int CARRIAGE_TYPE_COUNTS = 10;
    private static int carriageType = 0;
    ItemIconButton[] imgBtn = new ItemIconButton[CARRIAGE_TYPE_COUNTS];
    ImageButton[] upAndDownBtn = new ImageButton[2];
    ArrayList<List<ItemStack>> itemList = new ArrayList<>();
    List<ItemStack> schematicItemList = new ArrayList<>();
    int btn_location = 0;
    int showBtnNumber = 5;
    int guiLeft = 0, guiTop = 0;
    boolean onChanged = true;
    int bx = 0;

    // String playerName = "";

    // 懒加载向上箭头动态图标
    LazyRecomputable<ImageMask> upRegex = LazyRecomputable.of(() -> {
        try {
            return ClientInit.carriageEventRegexUp.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    // 懒加载向下箭头动态图标
    LazyRecomputable<ImageMask> downRegex = LazyRecomputable.of(() -> {
        try {
            return ClientInit.carriageEventRegexDown.getImage().get().getMask();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    @SubscribeEvent
    public void onPlayerLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        // 玩家登出时将列车类型置为0
        carriageType = 0;
        this.bx = 0;
    }

    @SubscribeEvent
    public void onPlayerLogin(ClientPlayerNetworkEvent.LoggingIn event) {
        // playerName = event.getPlayer().getDisplayName().getString();
        // generateSchematicTab();
    }

    @SubscribeEvent
    @SuppressWarnings({"resource"})
    public void onScreenInit(ScreenEvent.Init.Post event) {
        // 若当前screen不是创造模式物品栏，则直接返回。
        if (!(event.getScreen() instanceof CreativeModeInventoryScreen)) return;
        // 获取当前gui的左侧起始位置坐标
        this.guiLeft = ((CreativeModeInventoryScreen) event.getScreen()).getGuiLeft();
        // 获取当前gui的顶部起始位置坐标
        this.guiTop = ((CreativeModeInventoryScreen) event.getScreen()).getGuiTop();
        // 获取当前物品栏菜单对象
        CreativeModeInventoryScreen.ItemPickerMenu menu =
                ((CreativeModeInventoryScreen) event.getScreen()).getMenu();
        // 获取当前物品栏标签对象
        CreativeModeTab tab = ((AccessorCreativeTabScreen) event.getScreen()).getSelectedTab();


        if (tab.equals(AllElements.neoKuayueMainTab.getTab())) {
            addSchematicToTab(menu);
        }

        // 定义列车车厢板按钮图标的数据结构
        ItemStack[] icons = new ItemStack[CARRIAGE_TYPE_COUNTS];
        icons[0] = new ItemStack(AllBlocks.CR_LOGO.getBlock());
        icons[1] = new ItemStack(C25BPanel.PANEL_BOTTOM_25B.block.getBlock());
        icons[2] = new ItemStack(C25GPanel.PANEL_BOTTOM_25G.block.getBlock());
        icons[3] = new ItemStack(C25KPanel.PANEL_BOTTOM_25K.block.getBlock());
        icons[4] = new ItemStack(C25ZPanel.PANEL_BOTTOM_LINE_25Z.block.getBlock());
        icons[5] = new ItemStack(AllItems.LOGO_A25T.getItem());
        icons[6] = new ItemStack(C25BPanel.PANEL_SYMBOL_MARSHALLED_25B.block.getBlock());
        icons[7] = new ItemStack(CR200JPanel.PANEL_BOTTOM_MARSHALLED_CR200J.block.getBlock());
        icons[8] = new ItemStack(CM1Panel.BOTTOM_SLAB_M1.block.getBlock());
        icons[9] = new ItemStack(CFreightPanel.FREIGHT_C70_END_FACE.block.getBlock());

        // 定义左侧向上箭头按钮
        upAndDownBtn[0] = new ImageButton(upRegex, this.guiLeft - 22, this.guiTop - 8, 20, 20, Component.empty(),
                b -> {
                    // 按下按钮后触发事件
                    if(btn_location > 0){
                        btn_location --;
                        btn_location = Math.max(btn_location, 0);
                        refreshBtn(tab);
                        onUp();
                    }
                });
        // 定义左侧向下箭头按钮
        upAndDownBtn[1] = new ImageButton(downRegex, this.guiLeft - 22, this.guiTop - 8 + (showBtnNumber + 1) * 22, 20, 20, Component.empty(),
                b -> {
                    if(btn_location < imgBtn.length - showBtnNumber){
                        btn_location++;
                        btn_location = Math.min(btn_location, imgBtn.length - showBtnNumber);
                        refreshBtn(tab);
                        onDown();
                    }
                });

        // 初始化各车厢板类型的映射
        initMapping();

        // 定义列车车厢板类型信息的数据结构
        Component[] components = new Component[CARRIAGE_TYPE_COUNTS];
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
        components[8] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.m1");
        components[9] =
                Component.translatable("container." + Kuayue.MODID + ".inventory.button.freight");

        // 定义所有列车车厢板类型按钮
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
                                        this.bx = bx;
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
            // 给列车车厢板类型按钮添加监听器
            event.addListener(b);
        }
        // 刷新列车车厢板类型按钮显示状态
        refreshBtn(tab);
        for(ImageButton b : upAndDownBtn) {
            // 给两个箭头按钮添加监听器
            event.addListener(b);
            // 当前标签为跨越列车物品栏时显示上下箭头按钮，反之隐藏。
            b.visible = tab.equals(AllElements.neoKuayueCarriageTab.getTab());
        }

        // 默认显示所有的车厢板类型
        imgBtn[0].toggle();
        // imgBtn[bx].toggle();

        // 更新列车车厢板类型按钮状态与物品栏中物品
        updateButtons();
        // 菜单滚动到顶部
        menu.scrollTo(0);
    }

    // 刷新列车车厢板类型按钮的显示状态
    private void refreshBtn(CreativeModeTab tab) {
        int right_edge = btn_location + showBtnNumber - 1;
        for(int i = 0; i < imgBtn.length; i++) {
            if(i < btn_location || i > right_edge) {
                imgBtn[i].visible = false;
            } else {
                imgBtn[i].visible = tab.equals(AllElements.neoKuayueCarriageTab.getTab());
            }
        }
    }

    // 点击向上箭头按钮触发
    private void onUp() {
        // 列车车厢板类型按钮整体向上滚动
        for(ItemIconButton b : imgBtn) {
            b.setY(b.getY() + 22);
        }
    }

    // 点击向下箭头按钮触发
    private void onDown() {
        // 列车车厢板类型按钮整体向下滚动
        for(ItemIconButton b : imgBtn) {
            b.setY(b.getY() - 22);
        }
    }

    @SubscribeEvent
    public void onRender(ScreenEvent.Render.Pre event) {
        // 捕获screen渲染事件

        // 获取当前事件的screen
        Screen screen = event.getScreen();
        // 若当前screen为创造模式物品栏
        if (screen instanceof CreativeModeInventoryScreen) {
            // 更新列车车厢板类型按钮状态与物品栏中物品
            updateButtons();
            // 获取当前选择的物品栏标签
            CreativeModeTab tab = ((AccessorCreativeTabScreen) event.getScreen()).getSelectedTab();
            // 当选择跨越列车物品栏时显示左侧上下箭头按钮
            for(ImageButton b : upAndDownBtn) {
                b.visible = tab.equals(AllElements.neoKuayueCarriageTab.getTab());
            }
            // 刷新列车车厢板类型按钮显示状态
            refreshBtn(tab);
            // 若当前物品栏标签为跨越主标签
            if (tab.equals(AllElements.neoKuayueMainTab.getTab())) {
                // 向当前标签的菜单中添加物品列表schematicItemList
                addSchematicToTab(((CreativeModeInventoryScreen) screen).getMenu());
            }
        }
    }

    // 更新列车车厢板类型按钮状态与物品栏中物品
    public void updateButtons() {
        // 当前screen
        Screen screen = Minecraft.getInstance().screen;
        // 当前物品栏标签
        CreativeModeTab tab = null;
        if (screen == null) return;
        tab = ((AccessorCreativeTabScreen) screen).getSelectedTab();
        ;
        // 物品选择菜单
        CreativeModeInventoryScreen.ItemPickerMenu menu =
                ((CreativeModeInventoryScreen) screen).getMenu();
        // 是否为创造模式物品栏的screen
        if (screen instanceof CreativeModeInventoryScreen) {
            // 是否为跨越列车物品栏
            boolean vis = tab.equals(AllElements.neoKuayueCarriageTab.getTab());
            // 根据vis的值显示或隐藏列车车厢板类型按钮
            for (ItemIconButton b : imgBtn) {
                b.visible = vis;
            }
            // 如果vis为True，更新物品栏中物品，并重置滚动条。
            if (vis) {
                if (onChanged) {
                    menu.scrollTo(0.0f);
                    // 更新物品列表
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
            case 8: // M1
                menu.items.clear();
                menu.items.addAll(itemList.get(9));
                break;
            case 9: // Freight
                menu.items.clear();
                menu.items.addAll(itemList.get(10));
                break;
            default: // carriageType为0时添加所有类型
                menu.items.clear();
                menu.items.addAll(itemList.get(0)); // B
                menu.items.addAll(itemList.get(1)); // G
                menu.items.addAll(itemList.get(2)); // K
                menu.items.addAll(itemList.get(3)); // Z
                menu.items.addAll(itemList.get(4)); // T
                menu.items.addAll(itemList.get(5)); // M
                menu.items.addAll(itemList.get(6)); // cr200j
                menu.items.addAll(itemList.get(7)); // general
                menu.items.addAll(itemList.get(8)); // bgkzt
                menu.items.addAll(itemList.get(9)); // M1
                menu.items.addAll(itemList.get(10)); // Freight
        }
    }

    public void initMapping() {
        itemList =
                new ArrayList<>() {
                    {
                        add(getListByTag(AllTags.C25B.tag()));  // 25B 0
                        add(getListByTag(AllTags.C25G.tag()));  // 25G 1
                        add(getListByTag(AllTags.C25K.tag()));  // 25K 2
                        add(getListByTag(AllTags.C25Z.tag()));  // 25Z 3
                        add(getListByTag(AllTags.C25T.tag()));  // 25T 4
                        add(getListByTag(AllTags.MARSHALLED.tag()));  // 25 Marshalled Series 5
                        add(getListByTag(AllTags.C200J.tag())); // cr200j 6
                        add(List.of()); // 通用 7
                        add(getListByTag(AllTags.C25BGKZT.tag())); // BGKZT 8
                        add(getListByTag(AllTags.CM1.tag()));   // M1 9
                        add(getListByTag(AllTags.C_FREIGHT.tag())); // Freight 10
                    }
                };
    }

    // 根据给定的tag获取带有该tag的方块的物品栈列表
    public List<ItemStack> getListByTag(TagKey<Block> tag) {
        Set<Block> blockSetByTag = TagsUtil.getBlocksByTag(tag);
        List<ItemStack> itemStackList = new ArrayList<>();
        for (Block block : blockSetByTag) {
            itemStackList.add(block.asItem().getDefaultInstance());
        }
        return itemStackList;
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
