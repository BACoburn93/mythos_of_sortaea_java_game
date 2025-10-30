package actors.species;

import java.util.*;


public final class SubspeciesRegistry {
    private static final SubspeciesRegistry I = new SubspeciesRegistry();
    public static SubspeciesRegistry getInstance() { return I; }

    private final Map<SpeciesCategory, Set<String>> map = new EnumMap<>(SpeciesCategory.class);

    private SubspeciesRegistry() {
        // keep empty — populate on startup via registrar or JSON loader
    }

    public void register(SpeciesCategory category, String subspecies) {
        if (category == null || subspecies == null || subspecies.isBlank()) return;
        map.computeIfAbsent(category, k -> new LinkedHashSet<>()).add(subspecies.trim());
    }

    public Set<String> getForCategory(SpeciesCategory category) {
        return Collections.unmodifiableSet(map.getOrDefault(category, Collections.emptySet()));
    }

    public boolean isValid(SpeciesCategory category, String subspecies) {
        if (category == null || subspecies == null) return false;
        return map.getOrDefault(category, Collections.emptySet()).stream()
                  .anyMatch(s -> s.equalsIgnoreCase(subspecies.trim()));
    }

    public Optional<String> defaultFor(SpeciesCategory category) {
        var set = map.get(category);
        if (set == null || set.isEmpty()) return Optional.empty();
        return Optional.of(set.iterator().next()); // deterministic first-registered default
    }

    // Optional: load subspecies from a properties/JSON resource for easier editing
    public void loadFromMap(Map<SpeciesCategory, Collection<String>> data) {
        for (var e : data.entrySet()) {
            for (String sub : e.getValue()) register(e.getKey(), sub);
        }
    }
}