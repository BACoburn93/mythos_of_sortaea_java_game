package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Envenom;
import status_conditions.StatusCondition;

public class PhysicalVenomDamage extends Damage {
    public PhysicalVenomDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.VENOM,
                new StatusCondition[] {
                        new Envenom(10, 1, 2)
                }
        );
    }

    public PhysicalVenomDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.VENOM,
                statusConditions
        );
    }
}
