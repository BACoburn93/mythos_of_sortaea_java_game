package characters.prerequisites;

import characters.Character;

// Requires the character to match a specific recruitable id or name. 
public class CharacterExclusivePrerequisite implements Prerequisite {
    private final String requiredCharacterIdOrName;

    public CharacterExclusivePrerequisite(String idOrName) {
        this.requiredCharacterIdOrName = idOrName == null ? "" : idOrName.trim();
    }

    @Override
    public boolean isMetBy(Character character) {
        if (requiredCharacterIdOrName.isEmpty()) return false;
        try {
            // try getId then getName
            try {
                var m = character.getClass().getMethod("getId");
                Object r = m.invoke(character);
                if (r != null && requiredCharacterIdOrName.equalsIgnoreCase(r.toString())) return true;
            } catch (Throwable ignored) {}
            var m2 = character.getClass().getMethod("getName");
            Object r2 = m2.invoke(character);
            if (r2 != null && requiredCharacterIdOrName.equalsIgnoreCase(r2.toString())) return true;
        } catch (Throwable ignored) {}
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires character '" + requiredCharacterIdOrName + "'";
    }
}