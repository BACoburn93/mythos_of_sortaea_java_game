package status_conditions;

import actors.Actor;

public abstract class DamageOverTime extends StatusCondition {
    public DamageOverTime(StatusTypes name, int value, int duration, int chanceToTrigger) {
        super(value, duration, chanceToTrigger);
        setName(name);
    }

    public DamageOverTime(StatusTypes name, int value, int attribute, int duration, int chanceToTrigger) {
        // StatusTypes name, int value, int attribute, int duration, StatusAttributeTypes type
        super(value, duration, chanceToTrigger);
        setName(name);
    }


    public abstract void applyDamage(Actor actor);
    // {
        // int damageToMitigate = 0;

        // switch(getName()) {
        //     case BURN -> damageToMitigate = actor.getResistances().getFire().getValue();
        //     case ENVENOM -> damageToMitigate = actor.getResistances().getVenom().getValue();
        //     case POISON -> damageToMitigate = actor.getResistances().getEarth().getValue();
        //     case ROT -> damageToMitigate = actor.getResistances().getDarkness().getValue();
        //     default -> throw new IllegalArgumentException(damageToMitigate + " is not a damage over time status condition.");

        // }

        // int baseDamage = getValue();
        // int totalDamage = Math.max(baseDamage - damageToMitigate, 0);

        // switch(getName()) {
        //     case BLEED -> System.out.println(actor.getName() + " is bleeding for " + totalDamage + " unmitigated damage due to the bleed condition.");
        //     case BURN -> System.out.println(actor.getName() + " is burning for " + totalDamage + " fire damage due to the burn condition.");
        //     case ENVENOM -> System.out.println(actor.getName() + " is agonizing " + totalDamage + " venom damage due to the envenom condition.");
        //     case POISON -> System.out.println(actor.getName() + " is suffering " + totalDamage + " poison damage due to the poison condition.");
        //     case ROT -> System.out.println(actor.getName() + " is deteriorating for " + totalDamage + " darkness damage due to the rot condition.");
        //     default -> throw new IllegalArgumentException("Unexpected value: " + getName());
        // }

    // }
}
