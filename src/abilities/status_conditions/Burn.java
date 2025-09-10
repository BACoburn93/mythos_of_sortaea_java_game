package abilities.status_conditions;

public class Burn extends StatusCondition {
    public Burn(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.BURN, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Burn(int value, int resistance) {
        super(StatusTypes.BURN, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
