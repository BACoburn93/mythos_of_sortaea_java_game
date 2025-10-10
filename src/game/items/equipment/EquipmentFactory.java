package items.equipment;

import utils.Factory;
import utils.FlavorUtils;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;

import java.util.List;
import java.util.function.Supplier;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;


public final class EquipmentFactory implements Factory<Equipment, Prefix, Suffix> {
    // public static final EquipmentFactory INSTANCE = new EquipmentFactory();
    private Random rng = new Random();
    private final Map<String, Supplier<Equipment>> prototypes = new HashMap<>();
    private final Map<String, List<Weighted<Prefix>>> prefixPools = new HashMap<>();
    private final Map<String, List<Weighted<Suffix>>> suffixPools = new HashMap<>();
    private final Map<String, Double> prefixApplyChance = new HashMap<>(); 
    private final Map<String, Double> suffixApplyChance = new HashMap<>();

    public EquipmentFactory() {  };

    // normalize key for consistent lookup
    private static String normalizeKey(String key) {
        return key == null ? null : key.trim().toLowerCase();
    }

    // small weighted holder
    public static final class Weighted<T> {
        final T value;
        final double weight;
        Weighted(T value, double weight) { this.value = value; this.weight = weight; }
    }

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

    public void setSeed(long seed) {
        this.rng = new Random(seed);
    }

    public void setRandom(Random random) {
        this.rng = (random == null) ? new Random() : random;
    }

    @Override
    public Equipment create(Supplier<Equipment> ctor, Prefix prefix, Suffix suffix) {
        Equipment item = ctor.get();
        return FlavorUtils.applyFlavor(item, prefix, suffix);
    }

    // create with random prefix/suffix chosen from lists
    public Equipment createRandomByKey(String key, List<Prefix> prefixes, List<Suffix> suffixes) {
        Prefix p = (prefixes == null || prefixes.isEmpty()) ? null : prefixes.get(rng.nextInt(prefixes.size()));
        Suffix s = (suffixes == null || suffixes.isEmpty()) ? null : suffixes.get(rng.nextInt(suffixes.size()));
        return createByKey(key, p, s);
    }

    // allow registering additional prototypes (e.g. from EquipmentDatabase)
    public void registerPrototype(String key, Supplier<Equipment> ctor) {
        prototypes.put(normalizeKey(key), ctor);
    }

    // convenience that uses the Factory default random selection behavior
    public Equipment createRandom(Supplier<Equipment> ctor, List<Prefix> prefixes, List<Suffix> suffixes) {
        return Factory.super.createRandom(ctor, prefixes, suffixes, rng);
    }

    // register prototype with optional prefix/suffix pools and overall chances
    public void registerPrototype(String key, Supplier<Equipment> ctor,
                                  List<Weighted<Prefix>> prefixes, double prefixChance,
                                  List<Weighted<Suffix>> suffixes, double suffixChance) {
        String k = normalizeKey(key);
        prototypes.put(k, ctor);
        if (prefixes != null) prefixPools.put(k, prefixes);
        if (suffixes != null) suffixPools.put(k, suffixes);
        prefixApplyChance.put(k, prefixChance);
        suffixApplyChance.put(k, suffixChance);
    }

    // create by key uses prototype pools and chances
    public Equipment createByKey(String key, Prefix overridePrefix, Suffix overrideSuffix) {
        String k = normalizeKey(key);
        Supplier<Equipment> ctor = prototypes.get(k);
        if (ctor == null) throw new IllegalArgumentException("No prototype registered for key: " + key);

        Prefix chosenP = overridePrefix;
        Suffix chosenS = overrideSuffix;

        // if no override, roll using pools / chances registered for this key
        if (chosenP == null) {
            Double chance = prefixApplyChance.getOrDefault(key, 0.0);
            if (rng.nextDouble() < chance) {
                chosenP = pickWeighted(rng, prefixPools.get(key));
            }
        }
        if (chosenS == null) {
            Double chance = suffixApplyChance.getOrDefault(key, 0.0);
            if (rng.nextDouble() < chance) {
                chosenS = pickWeighted(rng, suffixPools.get(key));
            }
        }

        return create(ctor, chosenP, chosenS);
    }
}