package willow.train.kuayue.initial.registration;

import kasuga.lib.core.base.item_helper.ExternalProperties;
import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.PlacementDrinkBlockItem;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.block.food.PlacementFoodBlockItem;
import willow.train.kuayue.block.food.instant_noodles.DriedInstantNoodlesBlockItem;

import java.util.function.Supplier;

public class PlacementFoodRegistration<T extends PlacementFoodBlock> {

    public BlockReg<T> block;

    public ItemReg item;

    public PlacementFoodRegistration(String registrationKey, PlacementFoodType placementFoodType) {
        this.block = new BlockReg<T>(registrationKey);
        itemRegistry(registrationKey, placementFoodType);
    }

    public PlacementFoodRegistration<T> block(BlockReg.BlockBuilder<T> builder) {
        this.block.blockType(builder);
        return this;
    }

    public PlacementFoodRegistration<T> noOcclusion() {
        block.addProperty(BlockBehaviour.Properties::noOcclusion);
        return this;
    }

    public PlacementFoodRegistration<T> material(Material material, MaterialColor color) {
        block.material(material);
        block.materialColor(color);
        return this;
    }

    public PlacementFoodRegistration<T> soundType(SoundType soundType) {
        this.block.addProperty(properties -> properties.sound(soundType));
        return this;
    }

    public PlacementFoodRegistration<T> strength(float strength) {
        block.addProperty(properties -> properties.strength(strength));
        return this;
    }

    public PlacementFoodRegistration<T> foodProperties(FoodProperties foodProperties) {
        this.item.withProperty(properties -> properties.food(foodProperties));
        return this;
    }

    public PlacementFoodRegistration<T> craftReminder(Supplier<Item> itemSupplier) {
        this.item.withProperty(properties -> ((ExternalProperties) properties).craftRemainder(itemSupplier));
        return this;
    }

    public PlacementFoodRegistration<T> tab(CreativeTabReg reg) {
        this.item.tab(reg);
        return this;
    }

    public PlacementFoodRegistration<T> stackSize(int size) {
        this.item.stackTo(size);
        return this;
    }

    public PlacementFoodRegistration<T> submit(SimpleRegistry registry) {
        this.block.submit(registry);
        this.item.submit(registry);
        return this;
    }

    public void itemRegistry(String registrationKey, PlacementFoodType placementFoodType) {
        switch (placementFoodType) {
            case EATING -> {
                this.item = new ItemReg<PlacementFoodBlockItem>(registrationKey);
                item.itemType(properties -> new PlacementFoodBlockItem(this.block.getBlock(), properties));}
            case DRINKING -> {
                this.item = new ItemReg<PlacementDrinkBlockItem>(registrationKey);
                item.itemType(properties -> new PlacementDrinkBlockItem(this.block.getBlock(), properties));
            }
            case DRIED_INSTANT_NOODLES -> {
                this.item = new ItemReg<DriedInstantNoodlesBlockItem>(registrationKey);
                item.itemType(properties -> new DriedInstantNoodlesBlockItem(this.block.getBlock(), properties));
            }
            default -> {
                this.item = new ItemReg<PlacementFoodBlockItem>(registrationKey);
                item.itemType(properties -> new PlacementFoodBlockItem(this.block.getBlock(), properties));}
        };
    }

    public enum PlacementFoodType {
        EATING, DRINKING, DRIED_INSTANT_NOODLES, SOAKED_INSTANT_NOODLES;
    }
}
