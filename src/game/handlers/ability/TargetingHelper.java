package handlers.ability;

import java.util.ArrayList;
import java.util.List;
import actors.types.CombatActor;
import characters.Character;
import characters.Party;
import enemies.Enemy;

public final class TargetingHelper {
    private TargetingHelper() {}

    public static List<CombatActor> expandEnemyTargets(List<Enemy> enemies, Enemy center, int leftRange, int rightRange) {
        List<CombatActor> targets = new ArrayList<>();
        int idx = enemies.indexOf(center);
        if (idx == -1) return targets;
        targets.add(center);

        // expand left and right using a shared helper
        addDirectionalEnemies(enemies, idx, -1, leftRange, targets);
        addDirectionalEnemies(enemies, idx, +1, rightRange, targets);
        return targets;
    }
 
    public static List<Character> expandPartyTargets(Party party, Character center, int leftRange, int rightRange) {
        List<Character> chars = party.getCharacters();
        int centerIdx = chars.indexOf(center);

        if (centerIdx == -1) return List.of();

        int from = Math.max(0, centerIdx - leftRange);
        int to = Math.min(chars.size()-1, centerIdx + rightRange);

        return new ArrayList<>(chars.subList(from, to+1));
    }
 
    private static void addDirectionalEnemies(List<Enemy> enemies, int startIndex, int direction, int rangeLimit, List<CombatActor> out) {
        if (rangeLimit <= 0) return;

        Enemy center = enemies.get(startIndex);
        int remaining = center.getSpawnWeight() - 1;
        int i = startIndex + direction;

        while (i >= 0 && i < enemies.size() && remaining < rangeLimit) {
            Enemy e = enemies.get(i);
            int w = e.getSpawnWeight();

            if (remaining + (w / 2) <= rangeLimit) {
                out.add(e);
                remaining += w;
                i += direction;
            } else {
                break;
            }
        }
    }
}