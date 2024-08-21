package willow.train.kuayue.block.panels.block_entity;

import kasuga.lib.core.util.data_type.Pair;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

public class TestGameElement {

    // 核心数据结构MAP
    public static HashMap<ResourceLocation, Element> MAP = new HashMap<>();

    // 注册前的队列
    public static Queue<Pair<ResourceLocation, Supplier<Element>>> registrationQueue = new LinkedList<>();

    // 客户程序将元素Supplier和唯一提示符提交给注册机
    public static void register(ResourceLocation location, Supplier<Element> elementSupplier) {
        registrationQueue.add(Pair.of(location, elementSupplier));
    }

    // 在程序的某个阶段进行注册提交
    protected static void registerAll() {
        while (!registrationQueue.isEmpty()) {
            Pair<ResourceLocation, Supplier<Element>> pair = registrationQueue.poll();
            MAP.put(pair.getFirst(), pair.getSecond().get());
        }
    }

    // 获取已注册的Element的方法
    public static Element get(ResourceLocation location) {
        return MAP.getOrDefault(location, null);
    }

    // 别的辅助方法
    public static boolean contains(ResourceLocation location) {
        return MAP.containsKey(location);
    }

    // 待注册的元素Element
    public static class Element {

    }
}
