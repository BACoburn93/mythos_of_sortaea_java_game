package enemies;

import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;
import enemies.types.*;
import items.equipment.EquipmentKey;
import loot.DropTable;
import loot.LootEntry;
import loot.LootManager;
import modifiers.EnemyModifiers.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


public final class EnemyDatabase {
    public static final class Weighted<T> {
        public final T value;
        public final double weight;
        public Weighted(T value, double weight) { this.value = value; this.weight = weight; }
    }

    private static final Map<String, List<Weighted<EnemyPrefix>>> prefixPools = new HashMap<>();
    private static final Map<String, List<Weighted<EnemySuffix>>> suffixPools = new HashMap<>();
    private static final Map<String, Double> prefixChance = new HashMap<>();
    private static final Map<String, Double> suffixChance = new HashMap<>();
    private static final Map<String, Integer> spawnWeights = new HashMap<>();

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        initialized = true;

        // Example registration
        EnemyRegistry.register(EnemyKey.GOBLIN.key(), name -> new Goblin(name));
        EnemyRegistry.register(EnemyKey.MARLBORO.key(), name -> new Marlboro(name));
        EnemyRegistry.register(EnemyKey.DRAGON.key(), name -> new Dragon(name));
        EnemyRegistry.register(EnemyKey.ORC.key(), name -> new Orc(name));

        // Register Prefix/Suffix pools and spawn weights
        registerPrototype(
            EnemyKey.GOBLIN.key(),
                1, 
                GoblinPools.PREFIX_POOL, 0.5,
                GoblinPools.SUFFIX_POOL, 0.95
        );

        registerPrototype(
            EnemyKey.ORC.key(),
                1, 
                OrcPools.PREFIX_POOL, 0.5,
                OrcPools.SUFFIX_POOL, 0.5
        );

        registerPrototype(
            EnemyKey.DRAGON.key(),
                1, 
                DragonPools.PREFIX_POOL, 1,
                null, 0.0 
        );

        // Loot tables
        LootManager.registerDropTable(EnemyKey.GOBLIN.key(),
            new DropTable()
                .add(new LootEntry(EquipmentKey.DAGGER.key(), 1.0, 0.25, 1, 1, 0))
                .add(new LootEntry(EquipmentKey.LESSER_STAFF.key(), 1.0, 0.95, 1, 1, 0))
                .add(LootEntry.gold(5, 0.9))
        );

        LootManager.registerDropTable(EnemyKey.ORC.key(),
            new DropTable()
                .add(new LootEntry(EquipmentKey.SWORD.key(), 1.0, 0.15, 1, 1, 0))
                .add(new LootEntry(EquipmentKey.TOWER_SHIELD.key(), 1.0, 0.05, 1, 1, 0))
                .add(LootEntry.gold(25, 0.9))
        );

        LootManager.registerDropTable(EnemyKey.MARLBORO.key(),
            new DropTable()
                .add(new LootEntry(EquipmentKey.STAFF.key(), 1.0, 0.35, 1, 1, 0))
                .add(new LootEntry(EquipmentKey.AMULET.key(), 1.0, 0.15, 1, 1, 0))
                .add(LootEntry.gold(125, 0.9))
        );

        LootManager.registerDropTable(EnemyKey.DRAGON.key(),
            new DropTable()
                .add(new LootEntry(EquipmentKey.GREAT_STAFF.key(), 1.0, 0.5, 1, 1, 0))
                .add(new LootEntry(EquipmentKey.PLATE_ARMOR.key(), 1.0, 0.35, 1, 1, 0))
                .add(new LootEntry(EquipmentKey.RING.key(), 1.0, 0.25, 1, 1, 0))
                .add(LootEntry.gold(250, 0.9))
        );
    }

    public static void registerPrototype(String key, // Goblin
                                         int spawnWeight, // 1
                                         List<Weighted<EnemyPrefix>> prefixes, double pChance, 
                                         List<Weighted<EnemySuffix>> suffixes, double sChance) {
        if (key == null) return;
        String k = key.trim().toLowerCase();
        if (prefixes != null) prefixPools.put(k, prefixes);
        if (suffixes != null) suffixPools.put(k, suffixes);
        prefixChance.put(k, pChance);
        suffixChance.put(k, sChance);
        spawnWeights.put(k, spawnWeight);
    }

    public static List<Weighted<EnemyPrefix>> getPrefixPool(String key) { return prefixPools.get(normalize(key)); }
    public static List<Weighted<EnemySuffix>> getSuffixPool(String key) { return suffixPools.get(normalize(key)); }
    public static double getPrefixChance(String key) { return prefixChance.getOrDefault(normalize(key), 0.0); }
    public static double getSuffixChance(String key) { return suffixChance.getOrDefault(normalize(key), 0.0); }
    public static int getSpawnWeight(String key) { return spawnWeights.getOrDefault(normalize(key), 1); }

    private static String normalize(String k) { return k == null ? null : k.trim().toLowerCase(); }
}
