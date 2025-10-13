package loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropTable {
    private final List<LootEntry> entries = new ArrayList<>();

    public DropTable() {}

    public DropTable add(LootEntry e) { entries.add(e); return this; }

    public List<LootEntry> roll(Random rng) {
        List<LootEntry> results = new ArrayList<>();
        if (entries.isEmpty()) return results;

        for (LootEntry e : entries) {
            if (rng.nextDouble() <= e.chance) {
                results.add(e);
            }
        }
        return results;
    }

    public boolean isEmpty() { return entries.isEmpty(); }
}