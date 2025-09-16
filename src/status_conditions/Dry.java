package status_conditions;

public class Dry extends StatusCondition {
    public Dry(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.DRY, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Dry(int value, int resistance) {
        super(StatusTypes.DRY, value, resistance, true, StatusAttributeTypes.RESISTANCE);
    }
}
