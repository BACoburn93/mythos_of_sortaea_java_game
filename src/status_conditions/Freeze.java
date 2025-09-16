package status_conditions;

public class Freeze extends StatusCondition {
    public Freeze(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.FREEZE, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Freeze(int value, int resistance) {
        super(StatusTypes.FREEZE, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
