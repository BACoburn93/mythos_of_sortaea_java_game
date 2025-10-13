package loot;

public final class LootEntry {
    public final String itemKey;
    public final double weight;
    public final double chance;
    public final int minQty;
    public final int maxQty;
    public final int goldAmount;

    public LootEntry(String itemKey, double weight, double chance, int minQty, int maxQty, int goldAmount) {
        this.itemKey = itemKey;
        this.weight = weight;
        this.chance = chance;
        this.minQty = Math.max(1, minQty);
        this.maxQty = Math.max(this.minQty, maxQty);
        this.goldAmount = goldAmount;
    }

    public static LootEntry gold(int amount, double chance) {
        return new LootEntry(null, 1.0, chance, 1, 1, amount);
    }
}