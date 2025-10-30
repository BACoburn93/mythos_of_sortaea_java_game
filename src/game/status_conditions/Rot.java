package status_conditions;

import actors.types.CombatActor;

public class Rot extends DamageOverTime {
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
        double totalValue = actor.getResistances().getDarkness().getValue();

        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - totalValue);

        System.out.println(actor.getName() + " is rotting for " + totalValue + " dark damage due to the rot condition.");
    }
}
