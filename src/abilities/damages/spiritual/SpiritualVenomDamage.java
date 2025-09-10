package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Envenom;
import abilities.status_conditions.StatusCondition;

public class SpiritualVenomDamage extends Damage {
    public SpiritualVenomDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.VENOM,
                new StatusCondition[] {
                        new Envenom(10, 1, 1)
                }
        );
    }

    public SpiritualVenomDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.VENOM,
                statusConditions
        );
    }
}
