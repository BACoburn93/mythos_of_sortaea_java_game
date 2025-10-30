package actors.species;

import java.util.Objects;

public final class SpeciesType {
    private final SpeciesCategory species;
    private final String subSpecies; // empty string if none

    public SpeciesType(SpeciesCategory species, String subSpecies) {
        this.species = Objects.requireNonNull(species);
        this.subSpecies = (subSpecies == null) ? "" : subSpecies.trim();
    }

    public SpeciesType(SpeciesCategory species) {
        this(species, "");
    }

    public SpeciesCategory getSpecies() { return species; }
    public String getSubSpecies() { return subSpecies; }

    public String key() {
        return subSpecies.isEmpty() ? species.name() : (species.name() + ":" + subSpecies.toUpperCase());
    }

    @Override
    public String toString() {
        return subSpecies.isEmpty() ? 
        species.name().toString().substring(0, 1).toUpperCase() + species.name().toString().substring(1).toLowerCase() 
            : 
        species.name().toString().substring(0, 1).toUpperCase() + species.name().toString().substring(1).toLowerCase() + " (" + subSpecies + ")";
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