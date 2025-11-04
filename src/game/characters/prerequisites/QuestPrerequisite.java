package characters.prerequisites;

import characters.Character;

// Requires the character to have completed a quest or flag. Uses a configurable method name. 
public class QuestPrerequisite implements Prerequisite {
    private final String questId;
    private final String methodName; // e.g. "hasCompletedQuest" or "hasFlag"

    public QuestPrerequisite(String questId) {
        this(questId, "hasCompletedQuest");
    }

    public QuestPrerequisite(String questId, String methodName) {
        this.questId = questId;
        this.methodName = methodName == null ? "hasCompletedQuest" : methodName;
    }

    @Override
    public boolean isMetBy(Character character) {
        if (questId == null || questId.isEmpty()) return false;
        try {
            var m = character.getClass().getMethod(methodName, String.class);
            Object res = m.invoke(character, questId);
            if (res instanceof Boolean) return (Boolean) res;
        } catch (Throwable ignored) {}
        return false;
    }

    @Override
    public String getDescription() {
        return "Requires quest/flag '" + questId + "'";
    }
}