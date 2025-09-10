package abilities.status_conditions;

public class Slow extends StatusCondition {
    public Slow(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.SLOW, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Slow(int value, int resistance) {
        super(StatusTypes.SLOW, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
