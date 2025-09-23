package actors.resources;

public abstract class ResourceValues {
    private double maxValue;
    private double value;
    private double regenValue;

    public ResourceValues(double maxValue, double value, double regenValue) {
        this.maxValue = maxValue;
        this.value = value;
        this.regenValue = regenValue;
    }

    public double getValue() {
        return value;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getRegenValue() {
        return regenValue;
    }

    public void setRegenValue(int regenValue) {
        this.regenValue = regenValue;
    }

    public void multiplyMaxValue(double multiplier) {
        this.maxValue *= multiplier;
    }

    public void divideMaxValue(double divisor) {
        if (divisor != 0) {
            this.maxValue /= divisor;
        }
    }

    public ResourceValues() {
        this.maxValue = 0;
        this.value = 0;
        this.regenValue = 0;
    }

    @Override
    public String toString() {
        return "Remaining: " +
                value + "/" + maxValue +
                " Regen: " + regenValue;
    }
}


