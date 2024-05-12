package willow.train.kuayue.block.seat;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.initial.AllBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class SeatBlockEntity extends SmartBlockEntity {
    private final UUID[] seatMap;
    public SeatBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        Block block = state.getBlock();
        if (block instanceof YZSeatBlock yzSeatBlock) {
            seatMap = new UUID[yzSeatBlock.getSeatSize()];
            for (int i = 0; i < seatMap.length; i++)
                seatMap[i] = null;
        }
        else
            seatMap = new UUID[0];
    }

    public SeatBlockEntity(BlockPos pos, BlockState state) {
        this(AllBlocks.SEAT_BLOCK_ENTITY.getType(), pos, state);
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        writeSeatData(tag);
    }

    public void writeSeatData(CompoundTag tag) {
        int counter = 0;
        for (UUID b : seatMap) {
            tag.putBoolean("hasSeat" + counter, b != null);
            if (b != null)
                tag.putUUID("seat" + counter, b);
            counter ++;
        }
    }

    public CompoundTag writeSeatData() {
        CompoundTag tag = new CompoundTag();
        writeSeatData(tag);
        return tag;
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        readSeatData(tag);
    }

    public void readSeatData(CompoundTag tag) {
        for (int i = 0; i < seatMap.length; i++) {
            boolean hasSeat = tag.getBoolean("hasSeat" + i);
            seatMap[i] = hasSeat ? tag.getUUID("seat" + i) : null;
        }
    }


    public boolean isValidSeat() {
        return seatMap.length > 0;
    }

    public boolean isEmpty() {
        for (UUID b : seatMap) {
            if (b != null) return false;
        }
        return true;
    }

    public boolean isFull() {
        for (UUID b : seatMap) {
            if (b == null) return false;
        }
        return true;
    }

    public void clearAllSeat() {
        Arrays.fill(seatMap, null);
    }

    public int getAnEmptySeat() {
        for(int i = 0; i < seatMap.length; i++) {
            if (seatMap[i] == null) return i;
        }
        return -1;
    }

    public int getNextEmptySeat(int fromIndex) {
        int index;
        for (int i = 1; i < seatMap.length; i++) {
            index = (fromIndex + i) % seatMap.length;
            if (seatMap[index] == null) return index;
        }
        return -1;
    }

    public void fillPassenger(int index, UUID id) {
        if (index < 0 || index >= seatMap.length || seatMap[index] != null) return;
        seatMap[index] = id;
        notifyUpdate();
    }

    public void removePassenger(int index) {
        if (index < 0 || index >= seatMap.length || seatMap[index] == null) return;
        seatMap[index] = null;
        notifyUpdate();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {

    }
}
