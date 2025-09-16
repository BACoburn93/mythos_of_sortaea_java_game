package status_conditions;

public class Paralyze extends StatusCondition{
    public Paralyze(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.PARALYZE, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Paralyze(int value, int resistance) {
        super(StatusTypes.PARALYZE, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
