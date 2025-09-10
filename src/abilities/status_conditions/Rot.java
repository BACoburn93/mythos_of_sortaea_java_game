package abilities.status_conditions;

public class Rot extends StatusCondition {
    public Rot(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.ROT, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Rot(int value, int resistance) {
        super(StatusTypes.ROT, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
