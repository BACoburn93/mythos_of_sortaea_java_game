package abilities.damages.magical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Envenom;
import status_conditions.StatusCondition;

public class MagicalVenomDamage extends Damage {
    public MagicalVenomDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.VENOM,
                new StatusCondition[] {
                        new Envenom(10, 1, 1)
                }
        );
    }

    public MagicalVenomDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.MAGICAL,
                DamageTypes.VENOM,
                statusConditions
        );
    }
}
