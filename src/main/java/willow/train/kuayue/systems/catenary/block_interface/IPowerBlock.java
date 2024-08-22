package willow.train.kuayue.systems.catenary.block_interface;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public interface IPowerBlock {
    ResourceLocation getPowerNodeType(Integer index);
    Vec3 getNode(Integer index);
}
