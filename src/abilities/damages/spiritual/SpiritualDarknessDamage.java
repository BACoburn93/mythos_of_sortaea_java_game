package abilities.damages.spiritual;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Fear;
import status_conditions.Manipulate;
import status_conditions.Rot;
import status_conditions.StatusCondition;

public class SpiritualDarknessDamage extends Damage {
    public SpiritualDarknessDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.DARKNESS,
                new StatusCondition[] {
                        new Fear(),
                        new Manipulate(),
                        new Rot(),
                }
        );
    }

    public SpiritualDarknessDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.SPIRITUAL,
                DamageTypes.DARKNESS,
                statusConditions
        );
    }
}
