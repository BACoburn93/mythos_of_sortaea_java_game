package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Confused;
import status_conditions.Paralyze;
import status_conditions.StatusCondition;

public class PhysicalLightningDamage extends Damage {
    public PhysicalLightningDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.LIGHTNING,
                new StatusCondition[] {
                        new Confused(10, 1, 1),
                        new Paralyze(10, 1, 1),
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
