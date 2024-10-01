package willow.train.kuayue.initial.registration;

import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.food.PlacementFoodBlock;
import willow.train.kuayue.block.food.PlacementFoodBlockItem;

public class PlacementFoodRegistration<T extends PlacementFoodBlock> {

    public BlockReg<T> block;

    public ItemReg item;

    public PlacementFoodRegistration(String registrationKey) {
        this.block = new BlockReg<T>(registrationKey);
        this.item = new ItemReg<PlacementFoodBlockItem>(registrationKey);
        item.itemType(properties -> new PlacementFoodBlockItem(this.block.getBlock(), properties));
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
}
