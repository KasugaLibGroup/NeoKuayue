package willow.train.kuayue.systems.tech_tree.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import willow.train.kuayue.Kuayue;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ItemContext {

    public final ResourceLocation location;
    private final int size;
    private final ResourceLocation nbtLocation;
    private final CompoundTag nbt;

    public ItemContext(ResourceLocation location, JsonElement element) {
        this.location = location;
        if (element.isJsonPrimitive()) {
            this.size = element.getAsInt();
            nbtLocation = null;
        } else if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            this.size = obj.get("size").getAsInt();
            if (obj.has("nbt")) {
                nbtLocation = getNbtLocation(new ResourceLocation(obj.get("nbt").getAsString()));
            } else
                nbtLocation = null;
        } else {
            this.size = 1;
            this.nbtLocation = null;
        }
        nbt = new CompoundTag();
    }

    public ItemContext(ResourceLocation location) {
        this.location = location;
        this.size = 1;
        this.nbtLocation = null;
        this.nbt = new CompoundTag();
    }

    public ItemContext(Map.Entry<String, JsonElement> entry) {
        this(new ResourceLocation(entry.getKey()), entry.getValue());
    }

    public boolean hasNbt() {
        return nbtLocation != null;
    }

    public ItemStack getAsLogo() {
        Item item = ForgeRegistries.ITEMS.getValue(location);
        if (item == null) return ItemStack.EMPTY;
        return item.getDefaultInstance();
    }

    public int getSize() {
        return size;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public void updateNbt(ResourceManager manager) {
        if (!hasNbt()) return;
        Optional<Resource> resource = manager.getResource(nbtLocation);
        if (resource.isEmpty()) return;
        try {
            InputStream stream = resource.get().open();
            DataInputStream dis = new DataInputStream(stream);
            this.nbt.merge(NbtIo.read(dis));
        } catch (IOException e) {
            Kuayue.LOGGER.error("Failed to read nbt file: ", e);
            return;
        }
    }

    public Set<ItemStack> getItem() {
        Item item = ForgeRegistries.ITEMS.getValue(this.location);
        if (item == null) return Set.of(ItemStack.EMPTY);
        Set<ItemStack> result = new HashSet<>();
        int pile = this.size / (hasNbt() ? 1 : item.getMaxStackSize());
        if (pile > 0) {
            for (int i = 0; i < pile; i++) {
                ItemStack stack = item.getDefaultInstance();
                if (hasNbt()) {
                    stack.getOrCreateTag().merge(nbt);
                } else {
                    stack.setCount(item.getMaxStackSize());
                }
                result.add(stack);
            }
        }
        if (hasNbt()) return result;
        int lastCount = this.size % item.getMaxStackSize();
        ItemStack lastStack = item.getDefaultInstance();
        lastStack.setCount(lastCount);
        result.add(lastStack);
        return result;
    }

    public static ResourceLocation getNbtLocation(ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), "tech_tree/nbt/" + location.getPath() + ".nbt");
    }
}
