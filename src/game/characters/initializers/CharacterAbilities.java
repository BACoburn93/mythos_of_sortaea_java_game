package characters.initializers;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.single_target.TargetingAbility;

import java.util.ArrayList;

public class CharacterAbilities {
    public static ArrayList<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new TargetingAbility("Punch",
                0, 1,
                new Damage[]{new PhysicalBludgeoningDamage(6, 12)},
                "A clenched fist, followed by a world of hurt."));
        abilities.add(new TargetingAbility("Kick",
                2, 1,
                new Damage[]{new PhysicalBludgeoningDamage(5, 18)},
                "Extend the leg to cause ample pain."));
        abilities.add(new TargetingAbility("Kill",
                0, 3,
                new Damage[]{new PhysicalBludgeoningDamage(9999, 99999)},
                "Instant death to all who fall victim."));
        return abilities;
    }
}
