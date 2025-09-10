package abilities.status_conditions;

public class Poison extends StatusCondition {
    public Poison(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.POISON, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Poison(int value, int resistance) {
        super(StatusTypes.POISON, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
