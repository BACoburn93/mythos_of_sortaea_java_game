package abilities.status_conditions;

public class Fear extends StatusCondition {
    public Fear(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.FEAR, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Fear(int value, int resistance) {
        super(StatusTypes.FEAR, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
