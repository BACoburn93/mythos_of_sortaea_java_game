package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.Fear;
import abilities.status_conditions.Manipulate;
import abilities.status_conditions.Rot;
import abilities.status_conditions.StatusCondition;

public class PhysicalDarknessDamage extends Damage {
    public PhysicalDarknessDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.DARKNESS,
                new StatusCondition[] {
                        new Fear(10, 100, 1),
                        new Manipulate(10, 100, 1),
                        new Rot(10, 25, 2),
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
