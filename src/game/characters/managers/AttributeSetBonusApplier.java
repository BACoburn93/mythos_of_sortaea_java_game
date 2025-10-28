package characters.managers;

import characters.Character;
import items.equipment.sets.SetBonus;
import actors.attributes.Attributes;
import actors.Stat;

import java.util.HashMap;
import java.util.Map;


public class AttributeSetBonusApplier implements SetManager.SetBonusApplier {
    private final Map<Character, Map<String, Attributes>> applied = new HashMap<>();

    @Override
    public synchronized void apply(Character c, String sourceKey, SetBonus bonus) {
        if (c == null || sourceKey == null || bonus == null) return;
        // compute delta (same logic as before)...
        Attributes cur = c.getAttributes();
        Attributes delta = new Attributes(0,0,0,0,0,0,0);
        // flats
        for (var e : bonus.getFlat().entrySet()) {
            Stat stat = e.getKey(); double v = e.getValue();
            System.out.println(e.getKey());
            switch (stat) {
                case STRENGTH -> delta.increaseStrength(v);
                case AGILITY -> delta.increaseAgility(v);
                case KNOWLEDGE -> delta.increaseKnowledge(v);
                case DEFENSE -> delta.increaseDefense(v);
                case RESILIENCE -> delta.increaseResilience(v);
                case SPIRIT -> delta.increaseSpirit(v);
                case LUCK -> delta.increaseLuck(v);
                default -> {}
            }
        }
        // percents
        for (var e : bonus.getPercent().entrySet()) {
            Stat stat = e.getKey(); double pct = e.getValue();
            switch (stat) {
                case STRENGTH -> delta.increaseStrength(cur.getStrength().getValue() * pct);
                case AGILITY -> delta.increaseAgility(cur.getAgility().getValue() * pct);
                case KNOWLEDGE -> delta.increaseKnowledge(cur.getKnowledge().getValue() * pct);
                case DEFENSE -> delta.increaseDefense(cur.getDefense().getValue() * pct);
                case RESILIENCE -> delta.increaseResilience(cur.getResilience().getValue() * pct);
                case SPIRIT -> delta.increaseSpirit(cur.getSpirit().getValue() * pct);
                case LUCK -> delta.increaseLuck(cur.getLuck().getValue() * pct);
                default -> {}
            }
        }

        // apply and record
        c.getAttributes().add(delta);
        applied.computeIfAbsent(c, k -> new HashMap<>()).put(sourceKey, delta);
        System.out.println("[SetBonus APPLIED] " + sourceKey + " -> " + delta);
    }

    @Override
    public synchronized void remove(Character c, String sourceKey) {
        if (c == null || sourceKey == null) return;
        Map<String, Attributes> m = applied.get(c);
        if (m == null) return;
        Attributes delta = m.remove(sourceKey);
        if (delta != null) {
            c.getAttributes().subtract(delta);
            System.out.println("[SetBonus REMOVED] " + sourceKey + " -> " + delta);
        }
        if (m != null && m.isEmpty()) applied.remove(c);
    }
}