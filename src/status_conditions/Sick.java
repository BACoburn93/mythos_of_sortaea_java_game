package status_conditions;

public class Sick extends StatusCondition {
    public Sick(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.SICK, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Sick(int value, int resistance) {
        super(StatusTypes.SICK, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
