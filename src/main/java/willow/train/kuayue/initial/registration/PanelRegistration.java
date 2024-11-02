package willow.train.kuayue.initial.registration;

import kasuga.lib.registrations.BlockEntityRendererBuilder;
import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import willow.train.kuayue.block.panels.TrainPanelBlock;
import willow.train.kuayue.item.PanelBlockItem;

import java.util.function.Supplier;

public class PanelRegistration<T extends TrainPanelBlock> {

    public BlockReg<T> block;

    public ItemReg item;

    public PanelRegistration(String registrationKey) {
        block = new BlockReg<T>(registrationKey);
        item = new ItemReg<PanelBlockItem>(registrationKey);
        item.itemType(properties -> new PanelBlockItem(block.getBlock(), properties));
    }

    public PanelRegistration<T> materialAndColor(MapColor materialColor) {
        block.materialColor(materialColor);
        return this;
    }

    public PanelRegistration<T> noOcclusion() {
        block.addProperty(BlockBehaviour.Properties::noOcclusion);
        return this;
    }

    public PanelRegistration<T> block(BlockReg.BlockBuilder<T> builder) {
        this.block.blockType(builder);
        return this;
    }

    public PanelRegistration<T> blockEntity(BlockEntityType.BlockEntitySupplier<?> provider) {
        block.withBlockEntity(block.registrationKey, provider);
        return this;
    }

    public PanelRegistration<T> blockEntityRenderer(Supplier<BlockEntityRendererBuilder> renderer) {
        block.withBlockEntityRenderer(renderer);
        return this;
    }

    public PanelRegistration<T> tab(CreativeTabReg reg) {
        this.item.tab(reg);
        return this;
    }

    public PanelRegistration<T> stackSize(int size) {
        this.item.stackTo(size);
        return this;
    }

    public PanelRegistration<T> strength(float strength, float explosionResistance) {
        this.block.addProperty(properties -> properties.strength(strength, explosionResistance));
        return this;
    }

    public PanelRegistration<T> requireCorrectTools() {
        this.block.addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops);
        return this;
    }

    public PanelRegistration<T> strengthAndTool(float strength, float explosionResistance) {
        this.block.addProperty(properties -> properties.strength(strength, explosionResistance));
        this.block.addProperty(BlockBehaviour.Properties::requiresCorrectToolForDrops);
        return this;
    }


    public PanelRegistration<T> submit(SimpleRegistry registry) {
        this.block.submit(registry);
        this.item.submit(registry);
        return this;
    }
}
