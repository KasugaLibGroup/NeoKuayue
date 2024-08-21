package willow.train.kuayue.catenary.power_network;

public interface IPower {

    float getMaxVoltage();
    float getMaxCurrent();
    float getMaxPower();
    boolean isOverloaded(float voltage, float current);
}
