package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;

public class MagicalDamage extends Damage {
    
    public MagicalDamage(DamageTypes type, int minDamage, int maxDamage) {
        this(type, minDamage, maxDamage, new StatusCondition[0]);
    }

    public MagicalDamage(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(minDamage, maxDamage, DamageClassificationTypes.MAGICAL, type, statusConditions);
    }

    // optional: convenience static constructors
    public static MagicalDamage bludgeoning(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage);
    }

    public static MagicalDamage slashing(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.SLASHING, minDamage, maxDamage);
    }

    public static MagicalDamage piercing(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.PIERCING, minDamage, maxDamage);
    }

    public static MagicalDamage ice(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.ICE, minDamage, maxDamage);
    }

    public static MagicalDamage earth(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.EARTH, minDamage, maxDamage);
    }

    public static MagicalDamage wind(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.WIND, minDamage, maxDamage);
    }

    public static MagicalDamage fire(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.FIRE, minDamage, maxDamage);
    }

    public static MagicalDamage water(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.WATER, minDamage, maxDamage);
    }

    public static MagicalDamage lightning(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.LIGHTNING, minDamage, maxDamage);
    }

    public static MagicalDamage venom(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.VENOM, minDamage, maxDamage);
    }

    public static MagicalDamage light(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.LIGHT, minDamage, maxDamage);
    }

    public static MagicalDamage darkness(int minDamage, int maxDamage) {
        return new MagicalDamage(DamageTypes.DARKNESS, minDamage, maxDamage);
    }
}
