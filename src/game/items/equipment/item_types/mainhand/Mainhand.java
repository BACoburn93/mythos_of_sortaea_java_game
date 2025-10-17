package items.equipment.item_types.mainhand;

import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.interfaces.MutableWeaponDamage;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.item_types.ItemType;
import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageTypeProvider;

import java.util.List;
import java.util.function.BiFunction;

public abstract class Mainhand extends Equipment implements DamageTypeProvider, WeaponDamageProvider, MutableWeaponDamage {
    private boolean twoHanded;
    private double damage;
    private List<Ability> abilities;

    public Mainhand(String name, int tier, double value, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, tier, value, itemType, twoHanded, attributes, resistances, abilities, 0.0);
    }

    public Mainhand(String name, int tier, double value, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, tier, value, EquipmentTypes.MAINHAND, itemType, attributes, resistances, abilities);
        this.twoHanded = twoHanded;
        this.damage = damage;
        this.abilities = abilities;
    }

    public double getDamage() {
        return damage;
    }

    public boolean isTwoHanded() {
        return twoHanded;
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

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }
}
