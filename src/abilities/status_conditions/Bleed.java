package abilities.status_conditions;

public class Bleed extends StatusCondition {
    public Bleed(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.BLEED, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    }

    public Bleed(int value, int resistance) {
        super(StatusTypes.BLEED, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    }
}
