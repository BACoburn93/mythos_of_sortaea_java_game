package modifiers.EnemyModifiers;

import java.util.Arrays;
import java.util.List;

import enemies.EnemyDatabase;
import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;
import enemies.modifiers.prefixes.pre_position.Wrathful;
import enemies.modifiers.suffixes.Cryomancer;
import enemies.modifiers.suffixes.Pyromancer;

public class OrcPools {
    public static final List<EnemyDatabase.Weighted<EnemyPrefix>> PREFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Wrathful(), 1)
    );

    public static final List<EnemyDatabase.Weighted<EnemySuffix>> SUFFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Cryomancer(), 0.6),
        new EnemyDatabase.Weighted<>(new Pyromancer(), 0.4)
    );
}
