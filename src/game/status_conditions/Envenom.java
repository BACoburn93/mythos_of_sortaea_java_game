package status_conditions;

import actors.types.CombatActor;

public class Envenom extends DamageOverTime {
    // public Envenom(int value, int chanceToTrigger, int duration) {
    //     super(StatusTypes.ENVENOM, value, chanceToTrigger, duration, StatusAttributeTypes.CHANCE_TO_TRIGGER);
    // }

    // public Envenom(int value, int resistance) {
    //     super(StatusTypes.ENVENOM, value, resistance, 0, StatusAttributeTypes.RESISTANCE);
    // }

    public Envenom() {
        super(StatusTypes.ENVENOM); // Default constructor with default values
    }

    public Envenom(int value, int resistance) {
        super(StatusTypes.ENVENOM, value, resistance, 0, 0);
    }

    public Envenom(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.ENVENOM, value, duration, chanceToTrigger);
    }

    @Override
    public void applyDamage(CombatActor actor) {
        double totalValue = actor.getResistances().getVenom().getValue();

        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - totalValue);

        System.out.println(actor.getName() + " is taking " + totalValue + " venom damage due to the envenom condition.");
    }
}
