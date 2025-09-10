package abilities.status_conditions;

public class Envenom extends StatusCondition {
    public Envenom(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.ENVENOM, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Envenom(int value, int resistance) {
        super(StatusTypes.ENVENOM, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
