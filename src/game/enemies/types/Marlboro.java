package enemies.types;

import abilities.database.AbilityDatabase;
import abilities.single_target.SingleTargetAbility;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import enemies.Enemy;

public class Marlboro extends Enemy {
    public Marlboro(String name) {
        super(
            name,
            new HealthValues(300, 30),
            new ManaValues(100, 30),
            new Attributes(10, 12, 8, 10, 8, 8, 8),
            new Resistances(),
            new SingleTargetAbility[]{
                AbilityDatabase.ROTTING_TENTACLE,
                AbilityDatabase.VIPER_FANGS,
                AbilityDatabase.POISON_MIST,
                AbilityDatabase.IMPALING_ICE
            },
            20
        );
    }
}

