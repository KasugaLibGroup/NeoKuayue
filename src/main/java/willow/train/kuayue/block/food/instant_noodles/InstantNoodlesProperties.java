package willow.train.kuayue.block.food.instant_noodles;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class InstantNoodlesProperties {

    public static final EnumProperty<NoodlesState> NOODLES_STATE =
            EnumProperty.create("noodles_state", NoodlesState.class);

    public enum NoodlesState implements StringRepresentable {
        WITH_FORK("with_fork"),
        OPENING("opening"),
        FINISH_EATING("finish_eating");

        private final String name;

        private NoodlesState(String name) {
            this.name = name;
        }

        public String toString() {
            return this.getSerializedName();
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
