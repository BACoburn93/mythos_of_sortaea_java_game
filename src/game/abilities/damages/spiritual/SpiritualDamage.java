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

    // optional: convenience static constructors
    public static SpiritualDamage bludgeoning(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.BLUDGEONING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.BLUDGEONING));
    }

    public static SpiritualDamage slashing(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.SLASHING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.SLASHING));
    }

    public static SpiritualDamage piercing(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.PIERCING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.PIERCING));
    }

    public static SpiritualDamage ice(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.ICE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.ICE));
    }

    public static SpiritualDamage earth(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.EARTH, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.EARTH));
    }

    public static SpiritualDamage wind(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.WIND, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WIND));
    }

    public static SpiritualDamage fire(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.FIRE, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.FIRE));
    }

    public static SpiritualDamage water(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.WATER, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.WATER));
    }

    public static SpiritualDamage lightning(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.LIGHTNING, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHTNING));
    }

    public static SpiritualDamage venom(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.VENOM, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.VENOM));
    }

    public static SpiritualDamage light(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.LIGHT, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.LIGHT));
    }

    public static SpiritualDamage darkness(int minDamage, int maxDamage) {
        return new SpiritualDamage(DamageTypes.DARKNESS, minDamage, maxDamage, StatusDamageData.getFor(DamageTypes.DARKNESS));
    }
}

