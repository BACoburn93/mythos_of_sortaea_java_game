package actors.resources;

public class StressValues extends ResourceValues {
    public StressValues(int maxValue, int value, int regenValue) {
        super(maxValue, value, regenValue);
    }

    public StressValues() {
        super(100, 100, 5);
    }
}
