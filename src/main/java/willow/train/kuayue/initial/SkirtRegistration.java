package willow.train.kuayue.initial;

import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import kasuga.lib.registrations.common.CreativeTabReg;
import kasuga.lib.registrations.common.ItemReg;
import kasuga.lib.registrations.registry.SimpleRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import willow.train.kuayue.block.panels.SkirtBlock;
import willow.train.kuayue.item.SkirtBlockItem;

public class SkirtRegistration<T extends SkirtBlock> extends PanelRegistration<T> {

    public SkirtRegistration(String registrationKey) {
        super(registrationKey);
        item = new ItemReg<SkirtBlockItem>(registrationKey);
        item.itemType(p -> new SkirtBlockItem(block.getBlock(), p));
    }
    
    public SkirtRegistration<T> materialAndColor(Material material, MaterialColor materialColor) {
        block.material(material).materialColor(materialColor);
        return this;
    }

    public SkirtRegistration<T> noOcclusion() {
        block.addProperty(BlockBehaviour.Properties::noOcclusion);
        return this;
    }

    public SkirtRegistration<T> block(BlockReg.BlockBuilder<T> builder) {
        this.block.blockType(builder);
        return this;
    }

    public SkirtRegistration<T> blockEntity(BlockEntityType.BlockEntitySupplier<?> provider) {
        block.withBlockEntity(block.registrationKey, provider);
        return this;
    }

    public SkirtRegistration<T> blockEntityRenderer(BlockEntityReg.BlockEntityRendererBuilder<?> renderer) {
        block.withBlockEntityRenderer(renderer);
        return this;
    }

    public SkirtRegistration<T> tab(CreativeTabReg reg) {
        this.item.tab(reg);
        return this;
    }

    public SkirtRegistration<T> stackSize(int size) {
        this.item.stackTo(size);
        return this;
    }

    public SkirtRegistration<T> submit(SimpleRegistry registry) {
        this.block.submit(registry);
        this.item.submit(registry);
        return this;
    }
}
