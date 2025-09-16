package status_conditions;

public class Wet extends StatusCondition {
    public Wet() {
        super(); 
        setName(status_conditions.StatusTypes.WET);
    }
    
    public Wet(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.WET, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Wet(int value, int resistance) {
        super(StatusTypes.WET, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
