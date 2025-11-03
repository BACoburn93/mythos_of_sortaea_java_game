package actors.species;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum SpeciesCategory {
    ABERRATION(), 
    BEAST(), 
    CELESTIAL("religion"), 
    CONSTRUCT(), 
    DEMON("religion"), 
    DEVIL("religion"), 
    DRAGON(), 
    ELEMENTAL(), 
    FEY(), 
    GIANT(), 
    HUMANOID("history"),
    INSECT(), 
    LYCANTHROPE(), 
    OOZE(), 
    PLANT(), 
    UNDEAD("religion");

    private final Set<String> skillTags;

    SpeciesCategory(String... tags) {
        if (tags == null || tags.length == 0) {
            this.skillTags = Collections.emptySet();
        } else {
            var s = new HashSet<String>(tags.length);
            for (String t : tags) if (t != null && !t.trim().isEmpty()) s.add(t.trim().toLowerCase());
            this.skillTags = Collections.unmodifiableSet(s);
        }
    }

    public Set<String> getSkillTags() {
        return this.skillTags;
    }
}
