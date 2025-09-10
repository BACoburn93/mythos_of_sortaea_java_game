package abilities.status_conditions;

public class Stun extends StatusCondition {
    public Stun(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.STUN, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Stun(int value, int resistance) {
        super(StatusTypes.STUN, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
