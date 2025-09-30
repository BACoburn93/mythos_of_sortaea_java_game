package enemies.types;

import abilities.database.AbilityDatabase;
import abilities.single_target.SingleTargetAbility;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import enemies.Enemy;

public class Dragon extends Enemy {
    public Dragon(String name) {
        super(
            name,
            new HealthValues(300, 20),
            new ManaValues(250, 20),
            new Attributes(24, 18, 22, 20, 22, 24, 18),
            new Resistances(10, 10, 10, 10, 10, 10,
                    10, 10, 10, 10, 10, 10),
            new SingleTargetAbility[]{ AbilityDatabase.CLAW, AbilityDatabase.TAIL, AbilityDatabase.BITE },
            25,
            3
        );
    }
}
