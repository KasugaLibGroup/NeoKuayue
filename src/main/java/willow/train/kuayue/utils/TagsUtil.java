package willow.train.kuayue.utils;

import kasuga.lib.core.util.LazyRecomputable;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import willow.train.kuayue.initial.AllTags;

import java.util.HashSet;
import java.util.Set;

public class TagsUtil {

    /**
     * 为什么需要这么写？
     * <p>
     * 显然我们可以知道，这下面的方法遍历的都是整个 mc 进程里所有的方块/物品，游戏程序经不起我们动不动就遍历，所以需要引入 Lazy (惰性) 机制
     * Lazy 机制允许我们在一次计算之后就获得一个可以一直调用的 cache, 这样我们的调用就不需要来回遍历整个 mc 的所有方块了。
     * 我们只需要在这个集合真正需要更新的时候清空 cache(调用其clear()方法), 这样在下次get的时候它又会自动生成。
     * 因为如果在 mod 加的多的情况下，可能会出现有几万个方块/物品的情况，这种惰性机制就非常必要了。
     * <p>
     * 为什么要保留更新这个列表的能力？直接把整个 Set 写死 (即只在游戏开始时候才进行装载) 不好吗？
     * <p>
     * 因为玩家可能会往 mc 进程里加入扩展包，扩展包是一种 mojang 提供的类似 mod 的扩展机制，它会像资源包一样可以在游戏运行中进行装卸，因此
     * 我们也需要给这个列表以自动更新的能力，使它可以适应扩展包的装卸。
     */
    static LazyRecomputable<Set<Block>> bottomPanelBlockSet = LazyRecomputable.of(
            () -> getBlocksByTag(AllTags.BOTTOM_PANEL.tag())
    );

    /**
     * 从方块 Tag 获取方块
     * @param tag 需要匹配的 Tag
     * @return 方块集合
     */
    public static Set<Block> getBlocksByTag(TagKey<Block> tag) {
        Set<Block> result = new HashSet<>();
        ForgeRegistries.BLOCKS.getEntries().forEach(entry -> {
            if (entry.getValue().defaultBlockState().is(tag))
                result.add(entry.getValue());
        });
        return result;
    }

    /**
     * 从方块 Tag 获取方块对应的物品
     * @param tag 需要匹配的 tag
     * @return 物品集合
     */
    public static Set<Item> getBlockItemsByTag(TagKey<Block> tag) {
        Set<Item> result = new HashSet<>();
        ForgeRegistries.BLOCKS.getEntries().forEach(entry -> {
            if (entry.getValue().defaultBlockState().is(tag)) {
                result.add(entry.getValue().asItem());
            }
        });
        return result;
    }

    /**
     * 从物品 tag 获取物品
     * @param tag 需要匹配的 tag
     * @return 物品集合
     */
    public static Set<Item> getItemsByTag(TagKey<Item> tag) {
        Set<Item> result = new HashSet<>();
        ForgeRegistries.ITEMS.getEntries().forEach(entry -> {
            if (entry.getValue().getDefaultInstance().is(tag))
                result.add(entry.getValue());
        });
        return result;
    }

    /**
     * 从物品 tag 获取物品对应的方块
     * @param tag 需要匹配的 tag
     * @return 返回的方块集合
     */
    public static Set<Block> getItemBlocksByTag(TagKey<Item> tag) {
        Set<Block> result = new HashSet<>();
        ForgeRegistries.ITEMS.getEntries().forEach(entry -> {
            if (entry.getValue().getDefaultInstance().is(tag)) {
                Block b = null;
                for (Block block : Item.BY_BLOCK.keySet()) {
                    if (block.asItem().equals(entry.getValue())) {
                        b = block;
                        break;
                    }
                }
                if (b != null)
                    result.add(b);
            }
        });
        return result;
    }
}
