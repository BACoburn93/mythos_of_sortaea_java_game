package items.equipment;

import utils.Factory;
import utils.FlavorUtils;
import items.equipment.modifiers.EquipmentPrefix;
import items.equipment.modifiers.EquipmentSuffix;

import java.util.List;
import java.util.function.Supplier;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;


// Responsible only for creation / RNG / applying flavors.
// Prototypes and pools are registered by EquipmentDatabase (using EquipmentRegistry suppliers).

public final class EquipmentFactory implements Factory<Equipment, EquipmentPrefix, EquipmentSuffix> {
    private Random rng = new Random();
    private final Map<String, Supplier<Equipment>> prototypes = new HashMap<>();
    private final Map<String, List<Weighted<EquipmentPrefix>>> prefixPools = new HashMap<>();
    private final Map<String, List<Weighted<EquipmentSuffix>>> suffixPools = new HashMap<>();
    private final Map<String, Double> prefixApplyChance = new HashMap<>();
    private final Map<String, Double> suffixApplyChance = new HashMap<>();

    public EquipmentFactory() { }

    // public static Weighted so callers (EquipmentDatabase) can build pools easily
    public static final class Weighted<T> {
        public final T value;
        public final double weight;
        public Weighted(T value, double weight) { this.value = value; this.weight = weight; }
    }

    // normalize keys used for register/lookup
    private static String normalizeKey(String key) {
        return key == null ? null : key.trim().toLowerCase();
    }

    // helpers for tests / debugging
    public void setSeed(long seed) { this.rng = new Random(seed); }
    public void setRandom(Random random) { this.rng = (random == null) ? new Random() : random; }
    public Set<String> getRegisteredKeys() { return Collections.unmodifiableSet(prototypes.keySet()); }
    public void clearPrototypes() {
        prototypes.clear();
        prefixPools.clear();
        suffixPools.clear();
        prefixApplyChance.clear();
        suffixApplyChance.clear();
    }

    @Override
    public Equipment create(Supplier<Equipment> ctor, EquipmentPrefix prefix, EquipmentSuffix suffix) {
        Equipment item = ctor.get();
        return FlavorUtils.applyFlavor(item, prefix, suffix);
    }

    // pick random entry from a weighted pool
    private static <T> T pickWeighted(Random rng, List<Weighted<T>> pool) {
        if (pool == null || pool.isEmpty()) return null;
        double total = 0.0;
        for (Weighted<T> w : pool) total += Math.max(0.0, w.weight);
        if (total <= 0.0) return null;
        double r = rng.nextDouble() * total;
        double accum = 0.0;
        for (Weighted<T> w : pool) {
            accum += Math.max(0.0, w.weight);
            if (r < accum) return w.value;
        }
        return pool.get(pool.size() - 1).value;
    }

    // create using explicit random lists (keeps previous convenience)
    public Equipment createRandomByKey(String key, List<EquipmentPrefix> prefixes, List<EquipmentSuffix> suffixes) {
        EquipmentPrefix p = (prefixes == null || prefixes.isEmpty()) ? null : prefixes.get(rng.nextInt(prefixes.size()));
        EquipmentSuffix s = (suffixes == null || suffixes.isEmpty()) ? null : suffixes.get(rng.nextInt(suffixes.size()));
        return createByKey(key, p, s);
    }

    // register a simple prototype (no pools)
    public void registerPrototype(String key, Supplier<Equipment> ctor) {
        prototypes.put(normalizeKey(key), ctor);
    }

    // register prototype together with pools and per-prototype apply chances
    public void registerPrototype(String key, Supplier<Equipment> ctor,
                                  List<Weighted<EquipmentPrefix>> prefixes, double prefixChance,
                                  List<Weighted<EquipmentSuffix>> suffixes, double suffixChance) {
        String k = normalizeKey(key);
        prototypes.put(k, ctor);
        if (prefixes != null) prefixPools.put(k, prefixes);
        if (suffixes != null) suffixPools.put(k, suffixes);
        prefixApplyChance.put(k, prefixChance);
        suffixApplyChance.put(k, suffixChance);
    }

    // create by key uses registered supplier and rolls pools/chances
    public Equipment createByKey(String key, EquipmentPrefix overridePrefix, EquipmentSuffix overrideSuffix) {
        String k = normalizeKey(key);
        Supplier<Equipment> ctor = prototypes.get(k);
        if (ctor == null) throw new IllegalArgumentException("No prototype registered for key: " + key);

        EquipmentPrefix chosenP = overridePrefix;
        EquipmentSuffix chosenS = overrideSuffix;

        if (chosenP == null) {
            Double chance = prefixApplyChance.getOrDefault(k, 0.0);
            if (rng.nextDouble() < chance) {
                chosenP = pickWeighted(rng, prefixPools.get(k));
            }
        }
        if (chosenS == null) {
            Double chance = suffixApplyChance.getOrDefault(k, 0.0);
            if (rng.nextDouble() < chance) {
                chosenS = pickWeighted(rng, suffixPools.get(k));
            }
        }

        return create(ctor, chosenP, chosenS);
    }
}