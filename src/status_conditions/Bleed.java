package status_conditions;

import actors.CombatActor;

public class Bleed extends DamageOverTime {
    public Bleed() {
        super(StatusTypes.BLEED); // Default constructor with default values
    }
    
    public Bleed(int value, int resistance) {
        super(StatusTypes.BLEED, value, resistance, 0, 0);
    }

    public Bleed(int value, int chanceToTrigger, int duration) {
        super(StatusTypes.BLEED, value, duration, chanceToTrigger);
    }

    @Override
    public void applyDamage(CombatActor actor) {
        actor.getHealthValues().setValue(actor.getHealthValues().getValue() - getValue());

        System.out.println(actor.getName() + " is bleeding for " + getValue() + " unmitigated damage due to the bleed condition.");
    }
}
