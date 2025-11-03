package abilities.damages;

import status_conditions.StatusCondition;

public abstract class Damage {
    private int minDamage;
    private int maxDamage;
    private DamageClassificationTypes damageClassification;
    private DamageTypes damageType;
    private StatusCondition[] statusConditions;

    public Damage(int minDamage, int maxDamage, DamageClassificationTypes damageClassification, DamageTypes damageType) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.damageClassification = damageClassification;
        this.damageType = damageType;
    }

    public Damage(int minDamage, int maxDamage, DamageClassificationTypes damageClassification, DamageTypes damageType, StatusCondition[] statusConditions) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.damageClassification = damageClassification;
        this.damageType = damageType;
        this.statusConditions = statusConditions;
    }

    public boolean isCritable() {
        return true;
    }

    public double getCritMultiplier() {
        return 2.0;
    }

    public double getBaseCritChance() {
        return 0.0;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public void addBonus(double bonus) {
        this.minDamage += bonus;
        this.maxDamage += bonus;
    }

    public DamageTypes getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageTypes damageType) {
        this.damageType = damageType;
    }

    public DamageClassificationTypes getDamageClassification() {
        return damageClassification;
    }

    public void setDamageClassification(DamageClassificationTypes damageClassification) {
        this.damageClassification = damageClassification;
    }

    public StatusCondition[] getStatusConditions() {
        return statusConditions;
    }

    @Override
    public String toString() {
        return String.format("%d to %d %s (%s)", 
            minDamage, 
            maxDamage, 
            damageType, 
            damageClassification);
    }

}

