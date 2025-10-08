package items.equipment.item_types.offhand;

import java.util.List;
import java.util.function.BiFunction;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageTypeProvider;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;

public abstract class Offhand extends Equipment implements DamageTypeProvider {
    private List<Ability> abilities;
    private double damage;

    public Offhand(String name, int goldValue, ItemType itemType, Attributes attributes, Resistances resistances) {
        super(name, goldValue, EquipmentTypes.OFFHAND, itemType, attributes, resistances);
        this.abilities = null;
        this.damage = 0;
    }

    public Offhand(String name, int goldValue, ItemType itemType, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, goldValue, EquipmentTypes.OFFHAND, itemType, attributes, resistances);
        this.abilities = abilities;
        this.damage = 0;
    }

    public Offhand(String name, int goldValue, ItemType itemType, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, goldValue, EquipmentTypes.OFFHAND, itemType, attributes, resistances);
        this.abilities = abilities;
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    
    public AttributeTypes getWeaponDamageAttr() {
        return AttributeTypes.STRENGTH; 
    }

    @Override
    public abstract BiFunction<Integer, Integer, Damage> getBaseDamageType();
}