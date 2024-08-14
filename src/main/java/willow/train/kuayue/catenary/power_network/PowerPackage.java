package willow.train.kuayue.catenary.power_network;

public record PowerPackage(float voltage, float current) {
    public static final PowerPackage EMPTY = new PowerPackage(0, 0);

    public float getPower() {
        return current * voltage;
    }
}
