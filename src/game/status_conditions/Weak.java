package status_conditions;

public class Weak extends StatusCondition {
    public Weak() {
        super(); 
        setName(status_conditions.StatusTypes.WEAK);
    }
    
    public Weak(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.WEAK, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Weak(int value, int resistance) {
        super(StatusTypes.WEAK, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
