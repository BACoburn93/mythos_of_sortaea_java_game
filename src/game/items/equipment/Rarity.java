package items.equipment;

public enum Rarity {
    COMMON,
    UNCOMMON,
    RARE,
    VERY_RARE,
    EPIC,
    LEGENDARY,
    MYTHIC,
    COSMIC,
    DIVINE;

    public static Rarity fromTier(int tier) {
        switch (tier) {
            case 0:
                return COMMON;
            case 1:
                return UNCOMMON;
            case 2:
                return RARE;
            case 3:
                return VERY_RARE;
            case 4:
                return EPIC;
            case 5:
                return LEGENDARY;
            case 6:
                return MYTHIC;
            case 7:
                return COSMIC;
            case 8:
                return DIVINE;
            default:
                throw new IllegalArgumentException("Invalid tier: " + tier);
        }
    }
}
    