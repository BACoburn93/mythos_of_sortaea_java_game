package enemies.types;

import abilities.ability_types.TargetingAbility;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.species.*;
import enemies.Enemy;
import enemies.EnemyKey;

public class Dragon extends Enemy {
    public Dragon(String name) {
        super(
            name,
            new HealthValues(300, 20),
            new ManaValues(250, 20),
            new Attributes(24, 18, 22, 20, 22, 24, 18),
            new Resistances(10, 10, 10, 10, 10, 10,
                    10, 10, 10, 10, 10, 10),
            new TargetingAbility[]{ AbilityDatabase.CLAW, AbilityDatabase.TAIL, AbilityDatabase.BITE },
            25,
            3
        );

        this.addSpecies(new SpeciesType(SpeciesCategory.DRAGON, "Wyrm"));
        setActionsPerTurn(3);
        setTypeKey(EnemyKey.DRAGON.key());
    }
}
