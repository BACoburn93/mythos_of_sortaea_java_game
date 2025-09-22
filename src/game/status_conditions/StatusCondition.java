package status_conditions;

import actors.Actor;

public abstract class StatusCondition {
    private StatusTypes name;
    private boolean isActive;
    private int value;
    private int chanceToTrigger;
    private int resistance;
    private int duration;
    private int totalAdjustment;

    public StatusCondition() {
        this(10, 1, 1);
    }

    public StatusCondition(int value, int duration, int chanceToTrigger) {
        this.value = value;
        this.duration = duration;
        this.chanceToTrigger = chanceToTrigger;
    }

    public StatusCondition(StatusTypes name, int value, int attribute, int duration, StatusAttributeTypes type) {
        this.name = name;
        this.value = value;
        this.duration = duration;

        if (type == StatusAttributeTypes.CHANCE_TO_TRIGGER) {
            this.chanceToTrigger = attribute;
            this.resistance = 0; // Default or invalid value
        } else if (type == StatusAttributeTypes.RESISTANCE) {
            this.resistance = attribute;
            this.chanceToTrigger = 0; // Default or invalid value
        } else {
            throw new IllegalArgumentException("Invalid StatusAttributeType");
        }
    }

    public StatusCondition(StatusTypes name, int value, int attribute, boolean isActive, StatusAttributeTypes type) {
        this.name = name;
        this.value = value;
        this.isActive = isActive;

        if (type == StatusAttributeTypes.CHANCE_TO_TRIGGER) {
            this.chanceToTrigger = attribute;
            this.resistance = 0; // Default or invalid value
        } else if (type == StatusAttributeTypes.RESISTANCE) {
            this.resistance = attribute;
            this.chanceToTrigger = 0; // Default or invalid value
        } else {
            throw new IllegalArgumentException("Invalid StatusAttributeType");
        }
    }

    public StatusTypes getName() {
        return name;
    }

    public void setName(StatusTypes name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getChanceToTrigger() {
        return chanceToTrigger;
    }

    public void setChanceToTrigger(int chanceToTrigger) {
        this.chanceToTrigger = chanceToTrigger;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTotalAdjustment() {
        return totalAdjustment;
    }

    public void setTotalAdjustment(int totalAdjustment) {
        this.totalAdjustment = totalAdjustment;
    }

    public void applyEffect(Actor actor) { }

    public void endEffect(Actor actor) { }

    @Override
    public String toString() {
        return "(" + name + " Val: " + value + " Dur: " + duration + " Res: " + resistance + ")";
    }
}
