package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Burn;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class SpiritualFireDamage extends Damage {
    public SpiritualFireDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.FIRE,
                new StatusCondition[] {
                        new Burn(),
                        new Dry(),
                }
        );
    }

    public SpiritualFireDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.FIRE,
                statusConditions
        );
    }
}
