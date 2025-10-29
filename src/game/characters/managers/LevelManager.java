package characters.managers;

import characters.Character;

public final class LevelManager {
    private static final LevelManager INSTANCE = new LevelManager();
    private LevelManager() {}
    public static LevelManager getInstance() { return INSTANCE; }

    public void levelUp(Character c) {
        if(c == null) return;

        c.incrementLevel();

        if(c.getLevel() % 4 == 0) {
            c.setMaxActionPoints(c.getMaxActionPoints() + 1);
        }

        c.notifyLevelUp();

        c.allocateAttributePoints();
        c.learnNewAbility();
    }
}
