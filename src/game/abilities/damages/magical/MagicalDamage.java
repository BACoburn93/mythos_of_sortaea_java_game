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

    // generic factory methods
    public static MagicalDamage of(DamageTypes type, int minDamage, int maxDamage) {
        return new MagicalDamage(type, minDamage, maxDamage, StatusDamageData.getFor(type));
    }

    public static MagicalDamage of(DamageTypes type, int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        return new MagicalDamage(type, minDamage, maxDamage, statusConditions);
    }

    // convenience static constructors
    public static MagicalDamage bludgeoning(int min, int max) { return of(DamageTypes.BLUDGEONING, min, max); }
    public static MagicalDamage slashing(int min, int max) { return of(DamageTypes.SLASHING, min, max); }
    public static MagicalDamage piercing(int min, int max) { return of(DamageTypes.PIERCING, min, max); }
    public static MagicalDamage earth(int min, int max) { return of(DamageTypes.EARTH, min, max); }
    public static MagicalDamage fire(int min, int max) { return of(DamageTypes.FIRE, min, max); }
    public static MagicalDamage ice(int min, int max) { return of(DamageTypes.ICE, min, max); }
    public static MagicalDamage lightning(int min, int max) { return of(DamageTypes.LIGHTNING, min, max); }
    public static MagicalDamage venom(int min, int max) { return of(DamageTypes.VENOM, min, max); }
    public static MagicalDamage water(int min, int max) { return of(DamageTypes.WATER, min, max); }
    public static MagicalDamage wind(int min, int max) { return of(DamageTypes.WIND, min, max); }
    public static MagicalDamage light(int min, int max) { return of(DamageTypes.LIGHT, min, max); }
    public static MagicalDamage darkness(int min, int max) { return of(DamageTypes.DARKNESS, min, max); }

    // overloads that accept explicit status conditions
    public static MagicalDamage bludgeoning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.BLUDGEONING, min, max, statusConditions); }
    public static MagicalDamage slashing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.SLASHING, min, max, statusConditions); }
    public static MagicalDamage piercing(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.PIERCING, min, max, statusConditions); }
    public static MagicalDamage earth(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.EARTH, min, max, statusConditions); }
    public static MagicalDamage fire(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.FIRE, min, max, statusConditions); }
    public static MagicalDamage ice(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.ICE, min, max, statusConditions); }
    public static MagicalDamage lightning(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHTNING, min, max, statusConditions); }
    public static MagicalDamage venom(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.VENOM, min, max, statusConditions); }
    public static MagicalDamage water(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WATER, min, max, statusConditions); }
    public static MagicalDamage wind(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.WIND, min, max, statusConditions); }
    public static MagicalDamage light(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.LIGHT, min, max, statusConditions); }
    public static MagicalDamage darkness(int min, int max, StatusCondition[] statusConditions) { return of(DamageTypes.DARKNESS, min, max, statusConditions); }
}
