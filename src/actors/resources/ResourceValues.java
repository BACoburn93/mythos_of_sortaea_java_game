package actors.resources;

public abstract class ResourceValues {
    private int maxValue;
    private int value;
    private int regenValue;

    public ResourceValues(int maxValue, int value, int regenValue) {
        this.maxValue = maxValue;
        this.value = value;
        this.regenValue = regenValue;
    }

    public int getValue() {
        return value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRegenValue() {
        return regenValue;
    }

    public void setRegenValue(int regenValue) {
        this.regenValue = regenValue;
    }

    public ResourceValues() {
        this.maxValue = 0;
        this.value = 0;
        this.regenValue = 0;
    }

    @Override
    public String toString() {
        return "ResourceValues{" +
                value + "/" + maxValue +
                " regenValue=" + regenValue +
                '}';
    }
}


