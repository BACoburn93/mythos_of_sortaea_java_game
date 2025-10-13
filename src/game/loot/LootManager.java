package loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enemies.Enemy;
import utils.FactoryRegistry;
import items.equipment.Equipment;

public final class LootManager {
    private static final java.util.Map<String, DropTable> TABLES = new java.util.HashMap<>();
    private static final Random rng = new Random();

    private LootManager() {}

    public static void registerDropTable(String enemyKey, DropTable table) {
        if (enemyKey == null || table == null) return;
        TABLES.put(enemyKey.trim().toLowerCase(), table);
    }

    public static DropTable getTable(String enemyKey) {
        return TABLES.get(enemyKey == null ? null : enemyKey.trim().toLowerCase());
    }

    public static List<Object> generateDrops(Enemy enemy) {
        List<Object> loot = new ArrayList<>(); 
        if (enemy == null) return loot;

        String key = enemy.getTypeKey();
        if (key == null) key = enemy.getClass().getSimpleName().toLowerCase();
        DropTable table = getTable(key);
        if (table == null || table.isEmpty()) return loot;

        List<LootEntry> rolled = table.roll(rng);
        for (LootEntry e : rolled) {
            if (e.itemKey == null) {
                // gold drop
                loot.add(e.goldAmount);
                continue;
            }
            int qty = e.minQty == e.maxQty ? e.minQty : (rng.nextInt(e.maxQty - e.minQty + 1) + e.minQty);
            for (int i = 0; i < qty; i++) {
                Equipment it = FactoryRegistry.getEquipmentFactory().createRandomByKey(e.itemKey, null, null);
                loot.add(it);
            }
        }
        return loot;
    }

    public static void setSeed(long seed) { rng.setSeed(seed); }
}