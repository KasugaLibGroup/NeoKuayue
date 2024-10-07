package willow.train.kuayue.initial.registration;

import kasuga.lib.core.base.item_helper.ExternalProperties;
import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.block.food.PlacementFoodBlockItem;

import java.util.function.Supplier;

public class PlacementFoodRegistration<T extends PlacementFoodBlock, U extends PlacementFoodBlockItem> {

    public BlockReg<T> block;

    public ItemReg<U> item;

    public interface BlockItemSup<T extends PlacementFoodBlock, U extends PlacementFoodBlockItem> {
        public U apply(BlockReg<T> reg, Item.Properties properties);
    }

    public PlacementFoodRegistration(String registrationKey) {
        this.block = new BlockReg<T>(registrationKey);
        this.item = new ItemReg<U>(registrationKey);
    }

    public PlacementFoodRegistration<T, U> block(BlockReg.BlockBuilder<T> builder) {
        this.block.blockType(builder);
        return this;
    }

    public PlacementFoodRegistration<T, U> item(BlockItemSup<T, U> blockItemSup) {
        this.item.itemType(properties -> blockItemSup.apply(this.block, properties));
        return this;
    }

    public PlacementFoodRegistration<T, U> noOcclusion() {
        block.addProperty(BlockBehaviour.Properties::noOcclusion);
        return this;
    }

    public PlacementFoodRegistration<T, U> material(Material material, MaterialColor color) {
        block.material(material);
        block.materialColor(color);
        return this;
    }

    public PlacementFoodRegistration<T, U> soundType(SoundType soundType) {
        this.block.addProperty(properties -> properties.sound(soundType));
        return this;
    }

    public PlacementFoodRegistration<T, U> strength(float strength) {
        block.addProperty(properties -> properties.strength(strength));
        return this;
    }

    public PlacementFoodRegistration<T, U> foodProperties(FoodProperties foodProperties) {
        this.item.withProperty(properties -> properties.food(foodProperties));
        return this;
    }

    public PlacementFoodRegistration<T, U> craftReminder(Supplier<Item> itemSupplier) {
        this.item.withProperty(properties -> ((ExternalProperties) properties).craftRemainder(itemSupplier));
        return this;
    }

    public PlacementFoodRegistration<T, U> tab(CreativeTabReg reg) {
        this.item.tab(reg);
        return this;
    }

    public PlacementFoodRegistration<T, U> stackSize(int size) {
        this.item.stackTo(size);
        return this;
    }

    public PlacementFoodRegistration<T, U> submit(SimpleRegistry registry) {
        this.block.submit(registry);
        this.item.submit(registry);
        return this;
    }
}
