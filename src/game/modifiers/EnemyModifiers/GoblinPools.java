package modifiers.EnemyModifiers;

import java.util.Arrays;
import java.util.List;

import enemies.EnemyDatabase;
import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;
import enemies.modifiers.prefixes.*;
import enemies.modifiers.suffixes.*;

public class GoblinPools {
    public static final List<EnemyDatabase.Weighted<EnemyPrefix>> PREFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Arch(), 0.1),
        new EnemyDatabase.Weighted<>(new Wrathful(), 0.9)
    );

    public static final List<EnemyDatabase.Weighted<EnemySuffix>> SUFFIX_POOL = Arrays.asList(
        new EnemyDatabase.Weighted<>(new Cryomancer(), 0.5),
        new EnemyDatabase.Weighted<>(new Pyromancer(), 0.5)
    );
}
