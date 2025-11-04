package characters.prerequisites;

import characters.Character;

//  Requires a named attribute (e.g. "strength","luck") to be >= value.
//  Uses Character.getAttributes().getXxx().getValue() via reflection so it is generic.
public class AttributePrerequisite implements Prerequisite {
    private final String attributeName;
    private final double minValue;

    public AttributePrerequisite(String attributeName, double minValue) {
        this.attributeName = attributeName == null ? "" : attributeName.trim();
        this.minValue = minValue;
    }

    @Override
    public boolean isMetBy(Character character) {
        if (attributeName.isEmpty()) return false;
        try {
            Object attrs = character.getClass().getMethod("getAttributes").invoke(character);
            if (attrs == null) return false;
            String getter = "get" + java.lang.Character.toUpperCase(attributeName.charAt(0)) + attributeName.substring(1);
            var g = attrs.getClass().getMethod(getter);
            Object attrObj = g.invoke(attrs);
            if (attrObj == null) return false;
            var gv = attrObj.getClass().getMethod("getValue").invoke(attrObj);
            if (gv instanceof Number) return ((Number) gv).doubleValue() >= minValue;
        } catch (Throwable ignored) {}
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires " + attributeName + " >= " + minValue;
    }
}