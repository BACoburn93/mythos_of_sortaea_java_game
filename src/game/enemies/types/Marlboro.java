package enemies.types;

import abilities.ability_types.TargetingAbility;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import enemies.Enemy;
import enemies.EnemyKey;

public class Marlboro extends Enemy {
    public Marlboro(String name) {
        super(
            name,
            new HealthValues(200, 20),
            new ManaValues(100, 30),
            new Attributes(10, 12, 8, 10, 8, 8, 8),
            new Resistances(),
            new TargetingAbility[]{
                AbilityDatabase.ROTTING_TENTACLE,
                AbilityDatabase.VENOM_MAW,
                AbilityDatabase.POISON_MIST,
                AbilityDatabase.IMPALING_ICE
            },
            20,
            2
        );
        setActionsPerTurn(2);
        setTypeKey(EnemyKey.MARLBORO.key());
    }
}

