package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Bleed;
import abilities.status_conditions.Dry;
import abilities.status_conditions.StatusCondition;

public class SpiritualWindDamage extends Damage {
    public SpiritualWindDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.WIND,
                new StatusCondition[] {
                        new Bleed(10, 1, 1),
                        new Dry(10, 1, 1),
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
