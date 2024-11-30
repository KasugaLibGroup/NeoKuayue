package willow.train.kuayue.systems.device.driver.combustion;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import kasuga.lib.core.menu.locator.BlockMenuLocator;
import kasuga.lib.core.menu.locator.GuiMenuHolder;
import kasuga.lib.example_env.AllExampleElements;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import willow.train.kuayue.systems.device.AllDeviceBlockEntities;
import willow.train.kuayue.systems.device.AllDevicesMenus;
import willow.train.kuayue.systems.device.driver.devices.CIRMenu;
import willow.train.kuayue.systems.device.driver.devices.LKJ2000Menu;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class InternalCombustionDriveControllerBlockEntity extends SmartBlockEntity {

    private GuiMenuHolder holder;

    public InternalCombustionDriveControllerBlockEntity(BlockPos blockPos, BlockState state) {
        super(AllDeviceBlockEntities.INTERNAL_COMBUSTION_DRIVE_CONTROLLER_BLOCK_ENTITY.getType(), blockPos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> list) {}

    @Override
    public void setLevel(Level pLevel) {
        super.setLevel(pLevel);
        if(holder != null) {
            holder.disable();
        }
        holder = new GuiMenuHolder.Builder()
                .with(AllDevicesMenus.LKJ2000)
                .with(AllDevicesMenus.CIR)
                .locatedAt(BlockMenuLocator.of(level, getBlockPos()))
                .build();
        holder.enable(pLevel);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        holder.disable();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
        holder.enable(level);
    }

    public Optional<LKJ2000Menu> getLKJ2000Menu() {
        return holder.getMenu(0).map((t)->(t instanceof LKJ2000Menu menu) ? menu : null);
    }

    public Optional<CIRMenu> getCIRMenu() {
        return holder.getMenu(1).map((t)->(t instanceof CIRMenu menu) ? menu : null);
    }
}
