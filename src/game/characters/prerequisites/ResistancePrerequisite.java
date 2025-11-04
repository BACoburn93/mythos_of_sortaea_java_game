package characters.prerequisites;

import characters.Character;

// Requires a resistance value (by name) >= minValue. Uses reflection to be generic. 
public class ResistancePrerequisite implements Prerequisite {
    private final String resistanceName;
    private final double minValue;

    public ResistancePrerequisite(String resistanceName, double minValue) {
        this.resistanceName = resistanceName == null ? "" : resistanceName.trim();
        this.minValue = minValue;
    }

    @Override
    public boolean isMetBy(Character character) {
        if (resistanceName.isEmpty()) return false;
        try {
            Object res = character.getClass().getMethod("getResistances").invoke(character);
            if (res == null) return false;
            String getter = "get" + java.lang.Character.toUpperCase(resistanceName.charAt(0)) + resistanceName.substring(1);
            var g = res.getClass().getMethod(getter);
            Object robj = g.invoke(res);
            if (robj == null) return false;
            var gv = robj.getClass().getMethod("getValue").invoke(robj);
            if (gv instanceof Number) return ((Number) gv).doubleValue() >= minValue;
        } catch (Throwable ignored) {}
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires " + resistanceName + " resistance >= " + minValue;
    }
}