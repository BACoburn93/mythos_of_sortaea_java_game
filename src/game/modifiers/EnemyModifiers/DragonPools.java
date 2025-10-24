package modifiers.EnemyModifiers;

import java.util.Arrays;
import java.util.List;

import enemies.EnemyDatabase;
import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.prefixes.Young;
import enemies.modifiers.prefixes.post_position.Red;

public class DragonPools {
    public static final List<EnemyDatabase.Weighted<EnemyPrefix>> PREFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Red(), 0.3),
        new EnemyDatabase.Weighted<>(new Young(), 0.7)
    );

    // public static final List<EnemyDatabase.Weighted<EnemySuffix>> SUFFIX_POOL = Arrays.asList(

    // );
}
