package actors.species;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum SpeciesCategory {
    ABERRATION("arcana"), 
    ANIMAL("nature"), 
    CELESTIAL("religion"), 
    CONSTRUCT("arcana"),
    DEITY("religion"), 
    DEMON("religion"), 
    DEVIL("religion"), 
    DRAGON("history"), 
    ELEMENTAL("arcana"), 
    FAERIE("nature"), 
    GIANT("history"), 
    HUMANOID("history"),
    INSECT("nature"), 
    LYCANTHROPE("nature"), 
    OOZE("arcana"), 
    PLANT("nature"), 
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
