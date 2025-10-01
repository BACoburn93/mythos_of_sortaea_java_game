package enemies;

import abilities.ability_types.TargetingAbility;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;

import java.util.ArrayList;

public class EnemyDatabase {
    private static Enemy goblin = new Enemy(
                       "Goblin",
                       new HealthValues(30, 3),
                       new ManaValues(10, 3),
                       new Attributes(10, 12, 8, 10, 8, 8, 8),
                       new Resistances(),
                       new TargetingAbility[]{ AbilityDatabase.FLASH_BANG },
                       20);

    public static ArrayList<Enemy> getDefaultEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(
            new Enemy(
                "Marlboro",
                new HealthValues(300, 30),
                new ManaValues(100, 30),
                new Attributes(10, 12, 8, 10, 8, 8, 8),
                new Resistances(),
                new TargetingAbility[]{
                    AbilityDatabase.ROTTING_TENTACLE,
                    AbilityDatabase.VENOM_MAW,
                    AbilityDatabase.POISON_MIST,
                    AbilityDatabase.IMPALING_ICE
                },
                20
            )
        );
       enemies.add(
               new Enemy(
                       "Goblin",
                       new HealthValues(30, 3),
                       new ManaValues(10, 3),
                       new Attributes(10, 12, 8, 10, 8, 8, 8),
                       new Resistances(),
                       new TargetingAbility[]{ AbilityDatabase.FLASH_BANG },
                       20));
       enemies.add(
               new Enemy("Orc",
                       new HealthValues(45, 5),
                       new ManaValues(10, 5),
                       new Attributes(15, 9,15, 13, 14, 10, 8),
                       new Resistances(),
                       new TargetingAbility[]{ AbilityDatabase.FLASH_BANG, AbilityDatabase.PUNCH, AbilityDatabase.KICK },
                       35));
       enemies.add(
               new Enemy("Red Dragon",
                       new HealthValues(300, 20),
                       new ManaValues(250, 20),
                       new Attributes(24, 18, 22, 20, 22, 24, 18),
                       new Resistances(10, 10, 10, 5, 50, 0,
                               5, 5, 2, 5, 3, 3),
                       new TargetingAbility[]{ AbilityDatabase.CLAW, AbilityDatabase.TAIL, AbilityDatabase.BITE, AbilityDatabase.FIRE_BREATH },
                       200));

        return enemies;
    }

    public static ArrayList<Enemy> goblinScenario() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(goblin);
        enemies.add(goblin);
        enemies.add(goblin);

        return enemies;
    }
}
