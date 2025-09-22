package status_conditions;

public class Confused extends StatusCondition {
    public Confused() {
        super(); 
        setName(status_conditions.StatusTypes.CONFUSED);
    }

    public Confused(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.CONFUSED, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Confused(int value, int resistance) {
        super(StatusTypes.CONFUSED, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
