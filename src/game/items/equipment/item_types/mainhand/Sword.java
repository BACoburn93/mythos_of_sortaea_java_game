package items.equipment.item_types.mainhand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalSlashingDamage;
import abilities.database.AbilityDatabase;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.enums.WeaponTypes;

public class Sword extends Mainhand {
    private static final double DEFAULT_DAMAGE = 8.0;

    // central constructor
    public Sword(String name, int tier, double value, boolean isTwoHanded,
                     Attributes attributes, Resistances resistances,
                     List<Ability> abilities, double damage) {
        super(
            name,
            tier,
            value,
            WeaponTypes.SWORD,
            isTwoHanded,
            attributes,
            resistances,
            Equipment.combineWithAbilities(AbilityDatabase.SLASH, abilities),
            damage
        );
    }

    // convenience overloads delegate to the central constructor
    // public Sword(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
    //     this(name, tier, value, false, attributes, resistances, abilities, damage);
    // }

    // public Sword(String name, int tier, double value, Attributes attributes, Resistances resistances, List<Ability> abilities) {
    //     this(name, tier, value, false, attributes, resistances, abilities, DEFAULT_DAMAGE);
    // }

    // public Sword(String name, int tier, double value, Attributes attributes, Resistances resistances) {
    //     this(name, tier, value, false, attributes, resistances, new ArrayList<>(List.of(AbilityDatabase.SLASH)), DEFAULT_DAMAGE);
    // }

    // public Sword(String name, int tier, double value) {
    //     this(name, tier, value, false, null, null, new ArrayList<>(List.of(AbilityDatabase.SLASH)), DEFAULT_DAMAGE);
    // }

    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.STRENGTH;
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        return (min, max) -> new PhysicalSlashingDamage(min, max);
    }

    public static class Builder extends Mainhand.MainhandBuilder<Builder> {

        public Builder() {
            this.value = 20.0;
            this.damage = DEFAULT_DAMAGE;
            this.twoHanded = false;
            this.itemType = WeaponTypes.SWORD;
            this.equipmentType = EquipmentTypes.MAINHAND;
        }
        
        @Override protected Builder self() { return this; }

        @Override
        public Sword build() {
            Attributes attrs = (attributes != null) ? new Attributes() : null;
            Resistances resists = (this.resistances != null) ? new Resistances() : null;
            List<Ability> abils = (abilities == null) ?
            new ArrayList<Ability>(List.of(AbilityDatabase.SLASH)) :
            (abilities instanceof ArrayList ? 
            abilities : new ArrayList<>(abilities));

            return new Sword( name, tier, value, twoHanded, attrs, resists, abils, damage );
        }
    }
}
