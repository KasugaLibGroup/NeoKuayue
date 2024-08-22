package willow.train.kuayue.systems.catenary.types;

import net.minecraft.resources.ResourceLocation;

public class CatenaryLineType {
    private final ResourceLocation name;
    private final float maxVoltage, maxCurrent, maxLength;

    public CatenaryLineType(ResourceLocation name, float maxVoltage, float maxCurrent, float maxLength) {
        this.name = name;
        this.maxVoltage = maxVoltage;
        this.maxCurrent = maxCurrent;
        this.maxLength = maxLength;
    }

    public ResourceLocation getName() {
        return name;
    }

    public float getMaxCurrent() {
        return maxCurrent;
    }

    public float getMaxVoltage() {
        return maxVoltage;
    }

    public float getMaxLength() {return maxLength;}

    public float getMaxPower() {
        return maxCurrent * maxVoltage;
    }


}
