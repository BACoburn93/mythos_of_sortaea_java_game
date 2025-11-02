package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;
import status_conditions.StatusDamageData;

public class PhysicalDamage extends Damage {
    
    public PhysicalDamage(DamageTypes type, int minDamage, int maxDamage) {
        this(type, minDamage, maxDamage, new StatusCondition[0]);
    }

    public PhysicalDamage(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(minDamage, maxDamage, DamageClassificationTypes.PHYSICAL, type, statusConditions);
    }

    // optional: convenience static constructors
    public static PhysicalDamage bludgeoning(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.BLUDGEONING));
    }

    public static PhysicalDamage slashing(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.SLASHING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.SLASHING));
    }

    public static PhysicalDamage piercing(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.PIERCING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.PIERCING));
    }

    public static PhysicalDamage ice(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.ICE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.ICE));
    }

    public static PhysicalDamage earth(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.EARTH, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.EARTH));
    }

    public static PhysicalDamage wind(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.WIND, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WIND));
    }

    public static PhysicalDamage fire(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.FIRE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.FIRE));
    }

    public static PhysicalDamage water(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.WATER, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WATER));
    }

    public static PhysicalDamage lightning(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.LIGHTNING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHTNING));
    }

    public static PhysicalDamage venom(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.VENOM, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.VENOM));
    }

    public static PhysicalDamage light(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.LIGHT, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHT));
    }

    public static PhysicalDamage darkness(int minDamage, int maxDamage) {
        return new PhysicalDamage(DamageTypes.DARKNESS, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.DARKNESS));
    }
}
