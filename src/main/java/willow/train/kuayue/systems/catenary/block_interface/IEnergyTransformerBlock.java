package willow.train.kuayue.systems.catenary.block_interface;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.block.IBE;

public abstract class IEnergyTransformerBlock<T extends KineticBlockEntity> extends KineticBlock implements IBE<T> {
    private final Class<T> beClass;
    public IEnergyTransformerBlock(Properties properties, Class<T> beClass) {
        super(properties);
        this.beClass = beClass;
    }

    @Override
    public Class<T> getBlockEntityClass() {
        return beClass;
    }
}
