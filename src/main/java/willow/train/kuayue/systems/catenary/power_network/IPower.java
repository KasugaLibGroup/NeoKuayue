package willow.train.kuayue.systems.catenary.power_network;

public interface IPower {

    float getMaxVoltage();
    float getMaxCurrent();
    float getMaxPower();
    boolean isOverloaded(float voltage, float current);
}
