package status_conditions;

public class Manipulate extends StatusCondition {
    public Manipulate() {
        super(); 
        setName(status_conditions.StatusTypes.MANIPULATE);
    }

    public Manipulate(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.MANIPULATE, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Manipulate(int value, int resistance) {
        super(StatusTypes.MANIPULATE, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
