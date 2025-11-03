package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.StatusCondition;
import status_conditions.StatusDamageData;

public class SpiritualDamage extends Damage {
    
    public SpiritualDamage(DamageTypes type, int minDamage, int maxDamage) {
        this(type, minDamage, maxDamage, new StatusCondition[0]);
    }

    public SpiritualDamage(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(minDamage, maxDamage, DamageClassificationTypes.SPIRITUAL, type, statusConditions);
    }

    // generic factory methods
    public static SpiritualDamage of(DamageTypes type, int minDamage, int maxDamage) {
        return new SpiritualDamage(type, minDamage, maxDamage, StatusDamageData.getFor(type));
    }

    public static SpiritualDamage of(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        return new SpiritualDamage(type, minDamage, maxDamage, statusConditions);
    }

    // convenience static constructors
    public static SpiritualDamage bludgeoning(int min, int max) { return of(DamageTypes.BLUDGEONING, min, max); }
    public static SpiritualDamage slashing(int min, int max) { return of(DamageTypes.SLASHING, min, max); }
    public static SpiritualDamage piercing(int min, int max) { return of(DamageTypes.PIERCING, min, max); }
    public static SpiritualDamage earth(int min, int max) { return of(DamageTypes.EARTH, min, max); }
    public static SpiritualDamage fire(int min, int max) { return of(DamageTypes.FIRE, min, max); }
    public static SpiritualDamage ice(int min, int max) { return of(DamageTypes.ICE, min, max); }
    public static SpiritualDamage lightning(int min, int max) { return of(DamageTypes.LIGHTNING, min, max); }
    public static SpiritualDamage venom(int min, int max) { return of(DamageTypes.VENOM, min, max); }
    public static SpiritualDamage water(int min, int max) { return of(DamageTypes.WATER, min, max); }
    public static SpiritualDamage wind(int min, int max) { return of(DamageTypes.WIND, min, max); }
    public static SpiritualDamage light(int min, int max) { return of(DamageTypes.LIGHT, min, max); }
    public static SpiritualDamage darkness(int min, int max) { return of(DamageTypes.DARKNESS, min, max); }

    // overloads that accept explicit status conditions
    public static SpiritualDamage bludgeoning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.BLUDGEONING, min, max, statusConditions); }
    public static SpiritualDamage slashing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.SLASHING, min, max, statusConditions); }
    public static SpiritualDamage piercing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.PIERCING, min, max, statusConditions); }
    public static SpiritualDamage earth(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.EARTH, min, max, statusConditions); }
    public static SpiritualDamage fire(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.FIRE, min, max, statusConditions); }
    public static SpiritualDamage ice(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.ICE, min, max, statusConditions); }
    public static SpiritualDamage lightning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHTNING, min, max, statusConditions); }
    public static SpiritualDamage venom(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.VENOM, min, max, statusConditions); }
    public static SpiritualDamage water(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WATER, min, max, statusConditions); }
    public static SpiritualDamage wind(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WIND, min, max, statusConditions); }
    public static SpiritualDamage light(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHT, min, max, statusConditions); }
    public static SpiritualDamage darkness(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.DARKNESS, min, max, statusConditions); }

    // optional: convenience static constructors
    // public static SpiritualDamage bludgeoning(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.BLUDGEONING));
    // }

    // public static SpiritualDamage slashing(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.SLASHING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.SLASHING));
    // }

    // public static SpiritualDamage piercing(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.PIERCING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.PIERCING));
    // }

    // public static SpiritualDamage ice(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.ICE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.ICE));
    // }

    // public static SpiritualDamage earth(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.EARTH, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.EARTH));
    // }

    // public static SpiritualDamage wind(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.WIND, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WIND));
    // }

    // public static SpiritualDamage fire(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.FIRE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.FIRE));
    // }

    // public static SpiritualDamage water(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.WATER, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WATER));
    // }

    // public static SpiritualDamage lightning(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.LIGHTNING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHTNING));
    // }

    // public static SpiritualDamage venom(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.VENOM, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.VENOM));
    // }

    // public static SpiritualDamage light(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.LIGHT, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHT));
    // }

    // public static SpiritualDamage darkness(int minDamage, int maxDamage) {
    //     return new SpiritualDamage(DamageTypes.DARKNESS, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.DARKNESS));
    // }
}

