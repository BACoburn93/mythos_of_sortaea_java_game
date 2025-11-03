package actors.species;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class SpeciesType {
    private final SpeciesCategory species;
    private final String subSpecies; // empty string if none
    private final Set<String> skillTags = new HashSet<>();

    public SpeciesType(SpeciesCategory species, String subSpecies, Set<String> skillTags) {
        this.species = Objects.requireNonNull(species);
        this.subSpecies = (subSpecies == null) ? "" : subSpecies.trim();
        if (skillTags != null) {
            for (String tag : skillTags) {
                addSkillTag(tag);
            }
        }
    }

    public SpeciesType(SpeciesCategory species, String subSpecies) {
        this.species = Objects.requireNonNull(species);
        this.subSpecies = (subSpecies == null) ? "" : subSpecies.trim();
    }

    public SpeciesType(SpeciesCategory species) {
        this(species, "");
    }

    /** Add a single tag. */
    public SpeciesType addSkillTag(String tag) {
        if (tag == null) return this;
        String t = tag.trim().toLowerCase();
        if (!t.isEmpty()) skillTags.add(t);
        return this;
    }

    /** Add multiple tags. */
    public SpeciesType addSkillTags(String... tags) {
        if (tags == null) return this;
        for (String t : tags) addSkillTag(t);
        return this;
    }

    public boolean hasSkillTag(String tag) {
        if (tag == null) return false;
        String t = tag.trim().toLowerCase();
        if (t.isEmpty()) return false;
        if (skillTags.contains(t)) return true;
        return this.species.getSkillTags().contains(t);
    }

    public Set<String> getSkillTags() {
        Set<String> union = new HashSet<>(species.getSkillTags());
        union.addAll(skillTags);
        return Collections.unmodifiableSet(union);
    }

    public SpeciesCategory getSpecies() { return species; }
    public String getSubSpecies() { return subSpecies; }

    public String key() {
        return subSpecies.isEmpty() ? species.name() : (species.name() + ":" + subSpecies.toUpperCase());
    }

    public List<String> lookupKeys() {
        var keys = new ArrayList<String>();
        if (!subSpecies.isEmpty()) {
            keys.add(species.name() + ":" + subSpecies.toUpperCase());
        }
        keys.add(species.name());
        return keys;
    }

    @Override
    public String toString() {
        String base = species.name().substring(0, 1).toUpperCase() + species.name().substring(1).toLowerCase();
        StringBuilder out = new StringBuilder(base);
        if (!subSpecies.isEmpty()) {
            out.append(" (").append(subSpecies).append(")");
        }
        Set<String> tags = getSkillTags();
        if (tags != null && !tags.isEmpty()) {
            out.append(": {").append(String.join(", ", tags)).append("} ");
        }
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesType)) return false;
        SpeciesType that = (SpeciesType) o;
        return species == that.species && subSpecies.equalsIgnoreCase(that.subSpecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, subSpecies.toUpperCase());
    }
}