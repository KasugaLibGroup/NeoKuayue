package willow.train.kuayue.initial;

import com.mojang.blaze3d.vertex.PoseStack;
import kasuga.lib.registrations.Reg;
import kasuga.lib.registrations.common.BlockTagReg;
import kasuga.lib.registrations.common.FluidTagReg;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AllTags {

    public static final BlockTagReg SIDE_PLACEMENT = new BlockTagReg("side_placement", "side_placement")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg PANEL = new BlockTagReg("carriage_panel", "carriage_panel")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg UPPER_PANEL = new BlockTagReg("upper_panel", "panel/upper_panel")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg MIDDLE_PANEL = new BlockTagReg("middle_panel", "panel/middle_panel")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg BOTTOM_PANEL = new BlockTagReg("bottom_panel", "panel/bottom_panel")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg WINDOW = new BlockTagReg("window", "panel/window")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg LADDER = new BlockTagReg("ladder", "panel/ladder")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg CARPORT = new BlockTagReg("carport", "panel/carport")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg FLOOR = new BlockTagReg("floor", "panel/floor")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg C25T = new BlockTagReg("c25t", "panel/c25t")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C25K = new BlockTagReg("c25k", "panel/c25k")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C25G = new BlockTagReg("c25g", "panel/c25g")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C25B = new BlockTagReg("c25b", "panel/c25b")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C25Z = new BlockTagReg("c25z", "panel/c25z")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C200J = new BlockTagReg("c200j", "panel/c200j")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg C25BGKZT = new BlockTagReg("c25bgkzt", "panel/c25bgkzt")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg MARSHALLED = new BlockTagReg("marshalled", "panel/marshalled")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg CM1 = new BlockTagReg("cm1", "panel/cm1")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg C_FREIGHT = new BlockTagReg("c_freight", "panel/c_freight")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg I3D = new BlockTagReg("i3d", "panel/i3d")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg I11G = new BlockTagReg("i11g", "panel/i11g")
            .submit(AllElements.testRegistry);
    public static final BlockTagReg ISS3 = new BlockTagReg("iss3", "panel/iss3")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg I21 = new BlockTagReg("i21", "panel/i21")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg MULTI_SEAT_BLOCK = new BlockTagReg("multi_seat_block", "multi_seat_block")
            .submit(AllElements.testRegistry);

    public static final BlockTagReg END_FACES = new BlockTagReg("end_face", "panel/end_face")
            .submit(AllElements.testRegistry);

    public static final Reg COLA_FLUID =
            new FluidTagReg("forge", "cola_fluid", "fluids/cola_fluid")
                    .submit(AllElements.testRegistry);

    public static void invoke() {}

    @FunctionalInterface
    public interface RenderLambda<T extends BlockEntity> {
        public void render(T be, BlockState state, CompoundTag nbt, PoseStack stack,
                           MultiBufferSource buffer, int light, int overlay);
    }
}
