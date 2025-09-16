package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Bleed;
import status_conditions.Dry;
import status_conditions.StatusCondition;

public class SpiritualWindDamage extends Damage {
    public SpiritualWindDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WIND,
                new StatusCondition[] {
                        new Bleed(),
                        new Dry(),
                }
        );
    }

    public SpiritualWindDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WIND,
                statusConditions
        );
    }
}
