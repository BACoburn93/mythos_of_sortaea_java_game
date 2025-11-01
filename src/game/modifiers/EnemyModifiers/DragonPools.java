package modifiers.EnemyModifiers;

import java.util.Arrays;
import java.util.List;

import enemies.EnemyDatabase;
import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;
import enemies.modifiers.prefixes.post_position.Red;
import enemies.modifiers.prefixes.post_position.White;
import enemies.modifiers.prefixes.pre_position.Young;
import enemies.modifiers.suffixes.*;

public class DragonPools {
    public static final List<EnemyDatabase.Weighted<EnemyPrefix>> PREFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new White(), 0.9),
        new EnemyDatabase.Weighted<>(new Red(), 0.1),
        new EnemyDatabase.Weighted<>(new Young(), 0.7)
    );

    public static final List<EnemyDatabase.Weighted<EnemySuffix>> SUFFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Cryomancer(), 0.5),
        new EnemyDatabase.Weighted<>(new Pyromancer(), 0.5)
    );
}
