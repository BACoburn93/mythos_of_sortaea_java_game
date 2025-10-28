package items.equipment.sets;

import java.util.EnumMap;
import java.util.Map;
import actors.Stat;


// Passive stat modifiers: flat and percent parts.
// Percent expressed as 0.05 == +5%.

public class SetBonus {
    private final EnumMap<Stat, Double> flat = new EnumMap<>(Stat.class);
    private final EnumMap<Stat, Double> percent = new EnumMap<>(Stat.class);

    public void addFlat(Stat s, double value) { flat.merge(s, value, Double::sum); }
    public void addPercent(Stat s, double value) { percent.merge(s, value, Double::sum); }

    public Map<Stat, Double> getFlat() { return Map.copyOf(flat); }
    public Map<Stat, Double> getPercent() { return Map.copyOf(percent); }

    public boolean isEmpty() { return flat.isEmpty() && percent.isEmpty(); }

    public void combine(SetBonus other) {
        if (other == null) return;
        other.flat.forEach((k,v) -> flat.merge(k, v, Double::sum));
        other.percent.forEach((k,v) -> percent.merge(k, v, Double::sum));
    }

    public static SetBonus merged(SetBonus a, SetBonus b) {
        SetBonus out = new SetBonus();
        if (a != null) out.combine(a);
        if (b != null) out.combine(b);
        return out;
    }

    @Override
    public String toString() {
        return "SetBonus{flat=" + flat + ", percent=" + percent + "}";
    }
}