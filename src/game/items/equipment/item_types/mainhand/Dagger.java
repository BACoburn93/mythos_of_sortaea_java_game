package items.equipment.item_types.mainhand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.item_types.enums.WeaponTypes;

public class Dagger extends Mainhand {
    private static final double DEFAULT_DAMAGE = 6.0;

    // central constructor
    public Dagger(
            String name,
            int tier,   
            double value,
            boolean isTwoHanded,
            Attributes attributes,
            Resistances resistances,
            List<Ability> abilities,
            double damage
        ) {
        super(
            name,
            tier,
            value,
            WeaponTypes.DAGGER,
            isTwoHanded,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.STAB, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    // public Dagger(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
    //     this(name, tier, value, false, attributes, resistances, abilities, damage);
    // }

    // public Dagger(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
    //     this(name, tier, value, false, attributes, resistances, abilities, DEFAULT_DAMAGE);
    // }

    // public Dagger(String name, int tier, double value, Attributes attributes, Resistances resistances) {
    //     this(name, tier, value, false, attributes, resistances, new ArrayList<>(List.of(AbilityDatabase.STAB)), DEFAULT_DAMAGE);
    // }

    // public Dagger(String name, int tier, double value) {
    //     this(name, tier, value, false, null, null, new ArrayList<>(List.of(AbilityDatabase.STAB)), DEFAULT_DAMAGE);
    // }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.AGILITY;
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalPiercingDamage(min, max);
    }

    public static class Builder extends Mainhand.MainhandBuilder<Builder> {
        public Builder() {
            this.name = "Dagger";
            this.tier = 0;
            this.value = 5.0;
            this.damage = DEFAULT_DAMAGE;
            this.itemType = WeaponTypes.DAGGER;
            this.twoHanded = false;
            this.abilities = new ArrayList<>();
        }

        @Override
        protected Builder self() { return this; }

        public Dagger build() {
            // Defensive copy of all parameters to pass to Dagger constructor
            Attributes attrs = (attributes != null) ? new Attributes() : null;
            Resistances resists = (this.resistances != null) ? new Resistances() : null;
            List<Ability> abils = (abilities == null) ?
            new ArrayList<Ability>(List.of(AbilityDatabase.STAB)) :
            (abilities instanceof ArrayList ? 
            abilities : new ArrayList<>(abilities));

            // Defensive copy of all parameters to pass to Dagger constructor
            return new Dagger(name, tier, value, twoHanded, attrs, resists, abils, damage);
        }
    }
}
