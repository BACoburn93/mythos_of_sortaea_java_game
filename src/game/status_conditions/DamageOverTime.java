package status_conditions;

import actors.types.CombatActor;

public abstract class DamageOverTime extends StatusCondition {
    public DamageOverTime(StatusTypes name) {
        super();
        setName(name);
    }

    public DamageOverTime(StatusTypes name, int value, int duration, int chanceToTrigger) {
        super(value, duration, chanceToTrigger);
        setName(name);
    }

    public DamageOverTime(StatusTypes name, int value, int attribute, int duration, int chanceToTrigger) {
        // StatusTypes name, int value, int attribute, int duration, StatusAttributeTypes type
        super(value, duration, chanceToTrigger);
        setName(name);
    }


    public abstract void applyDamage(CombatActor actor);
}
