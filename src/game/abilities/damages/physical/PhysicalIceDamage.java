package abilities.damages.physical;

import abilities.AbilityCategory;
import abilities.damages.Damage;
import abilities.damages.DamageClassificationTypes;
import abilities.damages.DamageTypes;
import status_conditions.*;

public class PhysicalIceDamage extends Damage {
    public PhysicalIceDamage(int minDamage, int maxDamage) {
        super(
                minDamage,
                maxDamage,
                DamageClassificationTypes.PHYSICAL,
                DamageTypes.ICE,
                new StatusCondition[] {
                        new Freeze(),
                        new Sick(),
                        new Slow(),
                        new Weak(),
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
