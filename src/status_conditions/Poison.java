package status_conditions;

import actors.Actor;

public class Poison extends DamageOverTime {
    // public Poison(int value, int chanceToTrigger, int duration) {
    //     super(StatusTypes.POISON, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    // }

    // public Poison(int value, int resistance) {
    //     super(StatusTypes.POISON, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    // }

    public Poison() {
        super(StatusTypes.POISON); // Default constructor with default values
    }

    public Poison(int value, int resistance) {
        super(StatusTypes.POISON, value, resistance, 0, 0);
    }

    public Poison(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.POISON, value, duration, chanceToTrigger);
    }

    @Override
    public void applyDamage(Actor actor) {
        int resistance = actor.getResistances().getEarth().getValue();

        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - (getValue() - resistance));

        System.out.println(actor.getName() + " is poisoned for " + (getValue() - resistance) + " earth damage due to the poison condition.");
    }
}
