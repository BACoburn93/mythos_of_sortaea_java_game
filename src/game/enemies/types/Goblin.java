package enemies.types;

import abilities.database.AbilityDatabase;
import abilities.single_target.SingleTargetAbility;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import enemies.Enemy;

public class Goblin extends Enemy {
    public Goblin(String name) {
        super(
            name,
            new HealthValues(30, 3),
            new ManaValues(10, 3),
            new Attributes(10, 12, 8, 10, 8, 8, 8),
            new Resistances(),
            new SingleTargetAbility[]{ AbilityDatabase.FLASH_BANG },
            20
        );
    }
}
