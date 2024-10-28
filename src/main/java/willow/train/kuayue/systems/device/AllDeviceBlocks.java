package willow.train.kuayue.systems.device;

import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.track.TrackTargetingBlockItem;
import com.tterrag.registrate.util.nullness.NonNullBiFunction;
import kasuga.lib.registrations.common.BlockEntityReg;
import kasuga.lib.registrations.common.BlockReg;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.util.Lazy;
import willow.train.kuayue.initial.AllEdgePoints;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.systems.catenary.constants.AllCatenaryLineTypes;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignalBlock;
import willow.train.kuayue.systems.device.graph.signals.entry.EntrySignalBlockEntity;
import willow.train.kuayue.systems.device.station.train_station.StationBlock;
import willow.train.kuayue.systems.device.station.train_station.StationBlockEntity;
import willow.train.kuayue.systems.device.station.train_station.client.StationBlockEntityRenderer;

import java.util.function.Function;

public class AllDeviceBlocks {


    public static Function<EdgePointType<?>,NonNullBiFunction<? super Block, Item.Properties, TrackTargetingBlockItem>>
            registerTrackTargetingBlockItem =
            Util.memoize(TrackTargetingBlockItem::ofType);

    public static BlockReg<StationBlock> STATION_BLOCK;

    public static BlockReg<EntrySignalBlock> ENTRY_SIGNAL;

    static{
        STATION_BLOCK = new BlockReg<StationBlock>("station_block")
                .blockType(StationBlock::new)
                .withItem( p->
                        registerTrackTargetingBlockItem.apply(AllEdgePoints.TRAIN_STATION)
                                .apply(STATION_BLOCK.getBlock(), p),
                        null)
                .tabTo(AllElements.neoKuayueDeviceTab)
                .submit(AllElements.testRegistry);

        ENTRY_SIGNAL =
                new BlockReg<EntrySignalBlock>("entry_signal_block")
                        .blockType(EntrySignalBlock::new)
                        .withItem( p->
                                        registerTrackTargetingBlockItem.apply(AllEdgePoints.ENTRY_SIGNAL)
                                                .apply(ENTRY_SIGNAL.getBlock(), p),
                                null)
                        .tabTo(AllElements.neoKuayueDeviceTab)
                        .withBlockEntity("entry_signal", EntrySignalBlockEntity::new)
                        .submit(AllElements.testRegistry);
    }

    public static BlockEntityReg<StationBlockEntity> STATION_BLOCK_ENTITY =
            new BlockEntityReg<StationBlockEntity>("station_block_entity")
                    .blockEntityType(StationBlockEntity::new)
                    .addBlock(STATION_BLOCK)
                    .withRenderer(()-> StationBlockEntityRenderer::new)
                    .submit(AllElements.testRegistry);
    public static final BlockEntityReg<EntrySignalBlockEntity> ENTRY_SIGNAL_BLOCK_ENTITY =
            new BlockEntityReg<EntrySignalBlockEntity>("entry_signal_block_entity")
                    .blockEntityType(EntrySignalBlockEntity::new)
                    .addBlock(ENTRY_SIGNAL)
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
