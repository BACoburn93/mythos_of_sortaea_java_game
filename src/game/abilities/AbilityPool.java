package abilities;

import java.util.List;

public class AbilityPool {
    private final List<Ability> abilities;

    public AbilityPool(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}
