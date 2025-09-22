package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Slow;
import status_conditions.StatusCondition;
import status_conditions.Weak;

public class SpiritualSlashingDamage extends Damage {
    public SpiritualSlashingDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.SLASHING,
                new StatusCondition[] {
                        new Bleed(),
                        new Slow(),
                        new Weak(),
                }
        );
    }
    public SpiritualSlashingDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.SLASHING,
                statusConditions
        );
    }

}
