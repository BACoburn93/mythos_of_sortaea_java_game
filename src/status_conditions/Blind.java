package status_conditions;

public class Blind extends StatusCondition {
    public Blind(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.BLIND, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }
    public Blind(int value, int resistance) {
        super(StatusTypes.BLIND, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }

}
