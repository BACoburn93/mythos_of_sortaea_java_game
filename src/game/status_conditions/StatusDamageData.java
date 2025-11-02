package status_conditions;

import abilities.damages.DamageTypes;


public final class StatusDamageData {
    private StatusDamageData() {}

    public static StatusCondition[] getBludgeoning() {
        return new StatusCondition[] { new Confused(), new Stun() };
    }

    public static StatusCondition[] getSlashing() {
        return new StatusCondition[] { new Bleed(), new Slow(), new Weak() };
    }

    public static StatusCondition[] getPiercing() {
        return new StatusCondition[] { new Bleed(), new Slow(), new Weak() };
    }

    public static StatusCondition[] getEarth() {
        return new StatusCondition[] { new Poison(), new Weak() };
    }

    public static StatusCondition[] getFire() {
        return new StatusCondition[] { new Burn(), new Dry() };
    }

    public static StatusCondition[] getIce() {
        return new StatusCondition[] { new Freeze(), new Sick(), new Slow(), new Weak() };
    }

    public static StatusCondition[] getLightning() {
        return new StatusCondition[] { new Confused(), new Paralyze() };
    }

    public static StatusCondition[] getVenom() {
        return new StatusCondition[] { new Envenom() };
    }

    public static StatusCondition[] getWater() {
        return new StatusCondition[] { new Wet() };
    }

    public static StatusCondition[] getWind() {
        return new StatusCondition[] { new Bleed(), new Dry() };
    }

    public static StatusCondition[] getLight() {
        return new StatusCondition[] { new Blind(), new Confused() };
    }

    public static StatusCondition[] getDarkness() {
        return new StatusCondition[] { new Fear(), new Manipulate(), new Rot() };
    }


    // Generic accessor by DamageTypes
    public static StatusCondition[] getFor(DamageTypes dmgType) {
        if (dmgType == null) return new StatusCondition[0];
        switch (dmgType) {
            case BLUDGEONING: return getBludgeoning();
            case PIERCING:    return getPiercing();
            case SLASHING:    return getSlashing();
            case EARTH:       return getEarth();
            case FIRE:        return getFire();
            case ICE:         return getIce();
            case LIGHTNING:   return getLightning();
            case VENOM:       return getVenom();
            case WATER:       return getWater();
            case WIND:        return getWind();
            case LIGHT:       return getLight();
            case DARKNESS:    return getDarkness();
            default:          return new StatusCondition[0];
        }
    }
}