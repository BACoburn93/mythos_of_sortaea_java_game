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

    // generic factory methods
    public static PhysicalDamage of(DamageTypes type, int minDamage, int maxDamage) {
        return new PhysicalDamage(type, minDamage, maxDamage, StatusDamageData.getFor(type));
    }

    public static PhysicalDamage of(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        return new PhysicalDamage(type, minDamage, maxDamage, statusConditions);
    }

        // convenience static constructors
    public static PhysicalDamage bludgeoning(int min, int max) { return of(DamageTypes.BLUDGEONING, min, max); }
    public static PhysicalDamage slashing(int min, int max) { return of(DamageTypes.SLASHING, min, max); }
    public static PhysicalDamage piercing(int min, int max) { return of(DamageTypes.PIERCING, min, max); }
    public static PhysicalDamage earth(int min, int max) { return of(DamageTypes.EARTH, min, max); }
    public static PhysicalDamage fire(int min, int max) { return of(DamageTypes.FIRE, min, max); }
    public static PhysicalDamage ice(int min, int max) { return of(DamageTypes.ICE, min, max); }
    public static PhysicalDamage lightning(int min, int max) { return of(DamageTypes.LIGHTNING, min, max); }
    public static PhysicalDamage venom(int min, int max) { return of(DamageTypes.VENOM, min, max); }
    public static PhysicalDamage water(int min, int max) { return of(DamageTypes.WATER, min, max); }
    public static PhysicalDamage wind(int min, int max) { return of(DamageTypes.WIND, min, max); }
    public static PhysicalDamage light(int min, int max) { return of(DamageTypes.LIGHT, min, max); }
    public static PhysicalDamage darkness(int min, int max) { return of(DamageTypes.DARKNESS, min, max); }

    // overloads that accept explicit status conditions
    public static PhysicalDamage bludgeoning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.BLUDGEONING, min, max, statusConditions); }
    public static PhysicalDamage slashing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.SLASHING, min, max, statusConditions); }
    public static PhysicalDamage piercing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.PIERCING, min, max, statusConditions); }
    public static PhysicalDamage earth(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.EARTH, min, max, statusConditions); }
    public static PhysicalDamage fire(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.FIRE, min, max, statusConditions); }
    public static PhysicalDamage ice(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.ICE, min, max, statusConditions); }
    public static PhysicalDamage lightning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHTNING, min, max, statusConditions); }
    public static PhysicalDamage venom(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.VENOM, min, max, statusConditions); }
    public static PhysicalDamage water(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WATER, min, max, statusConditions); }
    public static PhysicalDamage wind(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WIND, min, max, statusConditions); }
    public static PhysicalDamage light(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHT, min, max, statusConditions); }
    public static PhysicalDamage darkness(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.DARKNESS, min, max, statusConditions); }

    // optional: convenience static constructors
    // public static PhysicalDamage bludgeoning(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.BLUDGEONING));
    // }

    // public static PhysicalDamage slashing(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.SLASHING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.SLASHING));
    // }

    // public static PhysicalDamage piercing(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.PIERCING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.PIERCING));
    // }

    // public static PhysicalDamage ice(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.ICE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.ICE));
    // }

    // public static PhysicalDamage earth(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.EARTH, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.EARTH));
    // }

    // public static PhysicalDamage wind(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.WIND, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WIND));
    // }

    // public static PhysicalDamage fire(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.FIRE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.FIRE));
    // }

    // public static PhysicalDamage water(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.WATER, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WATER));
    // }

    // public static PhysicalDamage lightning(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.LIGHTNING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHTNING));
    // }

    // public static PhysicalDamage venom(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.VENOM, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.VENOM));
    // }

    // public static PhysicalDamage light(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.LIGHT, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHT));
    // }

    // public static PhysicalDamage darkness(int minDamage, int maxDamage) {
    //     return new PhysicalDamage(DamageTypes.DARKNESS, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.DARKNESS));
    // }
}
