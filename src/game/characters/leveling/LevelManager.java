package characters.leveling;

import characters.Character;

// to do -- Perk Tree, Subclass, Paragon Path, Epic Destiny 
public final class LevelManager {
    private static final LevelManager INSTANCE = new LevelManager();
    private LevelManager() {}
    public static LevelManager getInstance() { return INSTANCE; }

    public void addExperience(Character c, int xp) {
        if(c == null || xp <= 0) return;

        int updatedXp = c.getExperience() + xp;
        c.setExperience(updatedXp);

        while(c.getExperience() >= c.getExperienceToLevel()) {
            c.setExperience(c.getExperience() - c.getExperienceToLevel());

            c.setExperienceToLevel((int) (c.getExperienceToLevel() * 1.25));

            levelUp(c);
        }
    }

    public void levelUp(Character c) {
        if(c == null) return;

        LevelUpService.getInstance().handleLevelUp(c);
    }
}
