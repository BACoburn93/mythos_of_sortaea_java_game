package status_conditions;

import actors.Actor;
import actors.CombatActor;

public class Rot extends DamageOverTime {
    // public Rot(int value, int chanceToTrigger, int duration) {
    //     super(StatusTypes.ROT, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    // }

    // public Rot(int value, int resistance) {
    //     super(StatusTypes.ROT, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    // }

    public Rot() {
        super(StatusTypes.ROT); // Default constructor with default values
    }

    public Rot(int value, int resistance) {
        super(StatusTypes.ROT, value, resistance, 0, 0);
    }

    public Rot(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.ROT, value, duration, chanceToTrigger);
    }

    @Override
    public void applyDamage(CombatActor actor) {
        int totalValue = actor.getResistances().getDarkness().getValue();

        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - totalValue);

        System.out.println(actor.getName() + " is rotting for " + totalValue + " dark damage due to the rot condition.");
    }
}
