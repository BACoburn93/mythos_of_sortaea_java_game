package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Confused;
import abilities.status_conditions.Paralyze;
import abilities.status_conditions.StatusCondition;

public class PhysicalLightningDamage extends Damage {
    public PhysicalLightningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHTNING,
                new StatusCondition[] {
                        new Confused(10, 100, 1),
                        new Paralyze(10, 100, 1),
                }
        );
    }

    public PhysicalLightningDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHTNING,
                statusConditions
        );
    }
}
