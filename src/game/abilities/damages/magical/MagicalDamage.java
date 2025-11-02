package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;
import status_conditions.StatusDamageData;

public class MagicalDamage extends Damage {
    
    public MagicalDamage(DamageTypes type, int minDamage, int maxDamage) {
        this(type, minDamage, maxDamage, new StatusCondition[0]);
    }

    public MagicalDamage(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(minDamage, maxDamage, DamageClassificationTypes.MAGICAL, type, statusConditions);
    }

    // optional: convenience static constructors
    public static MagicalDamage bludgeoning(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.BLUDGEONING));
    }

    public static MagicalDamage slashing(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.SLASHING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.SLASHING));
    }

    public static MagicalDamage piercing(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.PIERCING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.PIERCING));
    }

    public static MagicalDamage ice(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.ICE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.ICE));
    }

    public static MagicalDamage earth(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.EARTH, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.EARTH));
    }

    public static MagicalDamage wind(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.WIND, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WIND));
    }

    public static MagicalDamage fire(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.FIRE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.FIRE));
    }

    public static MagicalDamage water(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.WATER, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WATER));
    }

    public static MagicalDamage lightning(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.LIGHTNING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHTNING));
    }

    public static MagicalDamage venom(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.VENOM, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.VENOM));
    }

    public static MagicalDamage light(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.LIGHT, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHT));
    }

    public static MagicalDamage darkness(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.DARKNESS, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.DARKNESS));
    }
}
