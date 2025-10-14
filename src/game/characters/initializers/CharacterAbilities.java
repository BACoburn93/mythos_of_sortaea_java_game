package characters.initializers;

import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.damages.spiritual.SpiritualLightDamage;

import java.util.ArrayList;

public class CharacterAbilities {
    public static ArrayList<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new TargetingAbility("Kill",
                0, 3,
                new Damage[]{new PhysicalBludgeoningDamage(9999, 99999)},
                "Instant death to all who fall victim."));
        abilities.add(new TargetingAbility("Pinnacle of Existence",
                0, 1,
                new Damage[]{new SpiritualLightDamage(9999, 99999)},
                99,
                99,
                "The ultimate expression of power."));
        return abilities;
    }
}
