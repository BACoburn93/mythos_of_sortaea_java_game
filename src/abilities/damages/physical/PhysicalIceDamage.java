package abilities.damages.physical;

import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import abilities.status_conditions.*;

public class PhysicalIceDamage extends Damage {
    public PhysicalIceDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.ICE,
                new StatusCondition[] {
                        new Freeze(10, 100, 1),
                        new Sick(10, 100, 1),
                        new Slow(10, 100, 1),
                        new Weak(10, 100, 1),
                }
        );
    }

    public PhysicalIceDamage(int minDamage, int maxDamage, StatusCondition[] statusConditions) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.ICE,
                statusConditions
        );
    }
}
