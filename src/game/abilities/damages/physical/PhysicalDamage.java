package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;

public class PhysicalDamage extends Damage {
    
    public PhysicalDamage(DamageTypes type, int minDamage, int maxDamage) {
        this(type, minDamage, maxDamage, new StatusCondition[0]);
    }

    public PhysicalDamage(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(minDamage, maxDamage, DamageClassificationTypes.PHYSICAL, type, statusConditions);
    }

    // optional: convenience static constructors
    public static PhysicalDamage bludgeoning(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage);
    }

    public static PhysicalDamage slashing(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.SLASHING, minDamage, maxDamage);
    }

    public static PhysicalDamage piercing(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.PIERCING, minDamage, maxDamage);
    }

    public static PhysicalDamage ice(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.ICE, minDamage, maxDamage);
    }

    public static PhysicalDamage earth(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.EARTH, minDamage, maxDamage);
    }

    public static PhysicalDamage wind(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.WIND, minDamage, maxDamage);
    }

    public static PhysicalDamage fire(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.FIRE, minDamage, maxDamage);
    }

    public static PhysicalDamage water(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.WATER, minDamage, maxDamage);
    }

    public static PhysicalDamage lightning(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.LIGHTNING, minDamage, maxDamage);
    }

    public static PhysicalDamage venom(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.VENOM, minDamage, maxDamage);
    }

    public static PhysicalDamage light(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.LIGHT, minDamage, maxDamage);
    }

    public static PhysicalDamage darkness(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.DARKNESS, minDamage, maxDamage);
    }
}
