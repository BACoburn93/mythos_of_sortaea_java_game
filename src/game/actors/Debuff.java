package actors;

public class Debuff {
    private String name;
    private int value;
    private int originalValue;
    private int duration;

    public Debuff(String name, int value, int duration) {
        this.name = name;
        this.value = value;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public int getOriginalValue() {
        return originalValue;
    }

    public int getDuration() {
        return duration;
    }

    public void reduceDuration() {
        this.duration--;
    }

    public boolean isExpired() {
        return this.duration <= 0;
    }
}

