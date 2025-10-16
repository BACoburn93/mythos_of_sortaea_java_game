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

        abilities.add(new TargetingAbility.Builder(
                "Kill",
                new Damage[]{ new PhysicalBludgeoningDamage(9999, 99999) }
            )
            .levelRequirement(0)
            .manaCost(3)
            .description("Instant death to all who fall victim.")
            .build()
        );

        abilities.add(new TargetingAbility.Builder(
                "Pinnacle of Existence",
                new Damage[]{ new SpiritualLightDamage(9999, 99999) }
            )
            .levelRequirement(0)
            .manaCost(1)
            .leftRange(99)
            .rightRange(99)
            .description("The ultimate expression of power.")
            .build()
        );

        return abilities;
    }
}
