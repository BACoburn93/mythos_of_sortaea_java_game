package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Poison;
import status_conditions.StatusCondition;
import status_conditions.Weak;

public class SpiritualEarthDamage extends Damage {
    public SpiritualEarthDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.EARTH,
                new StatusCondition[] {
                        new Poison(),
                        new Weak(),
                }
        );
    }

    public SpiritualEarthDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.EARTH,
                statusConditions
        );
    }
}
