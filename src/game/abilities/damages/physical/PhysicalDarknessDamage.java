package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.Fear;
import status_conditions.Manipulate;
import status_conditions.Rot;
import status_conditions.StatusCondition;

public class PhysicalDarknessDamage extends Damage {
    public PhysicalDarknessDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.DARKNESS,
                new StatusCondition[] {
                        new Fear(),
                        new Manipulate(),
                        new Rot(),
                }
        );
    }

    public PhysicalDarknessDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.DARKNESS,
                statusConditions
        );
    }
}
