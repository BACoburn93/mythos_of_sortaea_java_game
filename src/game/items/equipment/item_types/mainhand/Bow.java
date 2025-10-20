package items.equipment.item_types.mainhand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalPiercingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.WeaponTypes;

public class Bow extends Mainhand {
    private static final double DEFAULT_DAMAGE = 10.0;

    // central constructor 
    public Bow(
        String name,
        int tier,
        double value,
        // boolean isTwoHanded,
        Attributes attributes,
        Resistances resistances,
        List<Ability> abilities,
        double damage) {
        super(
            name,
            tier,
            value,
            WeaponTypes.BOW,
            true,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.SHOOT, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    // public Bow(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
    //     this(name, tier, value, true, attributes, resistances, abilities, damage);
    // }

    // public Bow(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
    //     this(name, tier, value, attributes, resistances, abilities, DEFAULT_DAMAGE);
    // }

    // public Bow(String name, int tier, double value, Attributes attributes, Resistances resistances) {
    //     this(name, tier, value, attributes, resistances, List.of(AbilityDatabase.SHOOT), DEFAULT_DAMAGE);
    // }

    // public Bow(String name, int tier, double value) {
    //     this(name, tier, value, null, null, List.of(AbilityDatabase.SHOOT), DEFAULT_DAMAGE);
    // }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalPiercingDamage(min, max);
    }

    public static class Builder extends Mainhand.MainhandBuilder<Builder> {

        public Builder() {
            this.value = 20.0;
            this.damage = DEFAULT_DAMAGE;
            this.itemType = WeaponTypes.BOW;
            this.equipmentType = EquipmentTypes.MAINHAND;
        }
        
        @Override protected Builder self() { return this; }

        @Override
        public Bow build() {
            Attributes attrs = (attributes != null) ? new Attributes() : null;
            Resistances resists = (this.resistances != null) ? new Resistances() : null;
            List<Ability> abils = (abilities == null) ?
            new ArrayList<Ability>(List.of(AbilityDatabase.SHOOT)) :
            (abilities instanceof ArrayList ? 
            abilities : new ArrayList<>(abilities));

            return new Bow( name, tier, value, attrs, resists, abils, damage );
        }
    }
}
