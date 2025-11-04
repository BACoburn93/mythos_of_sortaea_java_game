package characters.prerequisites;

import characters.Character;

// A prerequisite that checks if a character meets a minimum level requirement.
public class LevelPrerequisite implements Prerequisite {
    private final int minLevel;

    public LevelPrerequisite(int minLevel) {
        this.minLevel = Math.max(1, minLevel);
    }

    @Override
    public boolean isMetBy(Character character) {
        try {
            // try direct getter first
            return character.getLevel() >= minLevel;
        } catch (Throwable t) {
            // reflection fallback for different APIs
            try {
                var m = character.getClass().getMethod("getLevel");
                Object res = m.invoke(character);
                if (res instanceof Number) return ((Number) res).intValue() >= minLevel;
            } catch (Throwable ignored) { }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires character level >= " + minLevel;
    }
}