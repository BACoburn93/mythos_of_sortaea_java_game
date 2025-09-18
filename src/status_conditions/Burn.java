package status_conditions;

import actors.Actor;
import actors.CombatActor;

public class Burn extends DamageOverTime {
    // public Burn(int value, int chanceToTrigger, int duration) {
    //     super(StatusTypes.BURN, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    // }

    // public Burn(int value, int resistance) {
    //     super(StatusTypes.BURN, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    // }

    public Burn() {
        super(StatusTypes.BURN); // Default constructor with default values
    }

    public Burn(int value, int resistance) {
        super(StatusTypes.BURN, value, resistance, 0, 0);
    }

    public Burn(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.BURN, value, duration, chanceToTrigger);
    }

    @Override
    public void applyDamage(CombatActor actor) {
        int totalValue = actor.getResistances().getFire().getValue();

        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - totalValue);

        System.out.println(actor.getName() + " is burning for " + totalValue + " fire damage due to the burn condition.");
    }
}
