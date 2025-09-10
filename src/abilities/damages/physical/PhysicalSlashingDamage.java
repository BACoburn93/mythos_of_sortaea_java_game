package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Bleed;
import abilities.status_conditions.Slow;
import abilities.status_conditions.StatusCondition;
import abilities.status_conditions.Weak;

public class PhysicalSlashingDamage extends Damage {
    public PhysicalSlashingDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.SLASHING,
                new StatusCondition[] {
                        new Bleed(10, 100, 1),
                        new Slow(10, 100, 1),
                        new Weak(10, 100, 1),
                }
        );
    }

    public PhysicalSlashingDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.SLASHING,
                statusConditions
        );
    }
}
