package characters.leveling;

import characters.Character;
import characters.jobs.Job;

public final class LevelUpService {
    private static final LevelUpService INSTANCE = new LevelUpService();

    private LevelUpService() {}

    public static LevelUpService getInstance() { return INSTANCE; }

    public void handleLevelUp(Character c) {
        if (c == null) return;

        int oldLevel = c.getLevel();
        c.incrementLevel();
        int newLevel = c.getLevel();

        try {
            Job job = c.getJobObj();
            if (job != null) {
                job.applyLevelUpToCharacter(c, oldLevel, newLevel);
            }
        } catch (Throwable ignored) {}
        
        if (newLevel % 4 == 0) {
            c.changeMaxActionPointsBy(1);
        }


        // Notify / allocate / learn (keep Character methods responsible for specifics)
        try { c.notifyLevelUp(); } catch (Throwable ignored) {}
        try { c.allocateAttributePoints(); } catch (Throwable ignored) {}
        try { c.learnNewAbility(); } catch (Throwable ignored) {}
    }
}
