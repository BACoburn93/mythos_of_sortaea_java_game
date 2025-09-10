package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Bleed;
import abilities.status_conditions.Slow;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Weak;

public class SpiritualSlashingDamage extends Damage {
    public SpiritualSlashingDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.SLASHING,
                new StatusCondition[] {
                        new Bleed(10, 1, 1),
                        new Slow(10, 1, 1),
                        new Weak(10, 1, 1),
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
