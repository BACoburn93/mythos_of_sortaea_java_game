package items.equipment.item_types.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;
import abilities.Ability; // Import your Ability class
import abilities.damages.Damage;
import abilities.damages.DamageTypeProvider;
import abilities.damages.physical.PhysicalBludgeoningDamage;

import java.util.List;
import java.util.function.BiFunction;

public class Mainhand extends Equipment implements DamageTypeProvider {
    private boolean twoHanded;
    private double damage;
    private List<Ability> abilities;

    public Mainhand(String name, int goldValue, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        this(name, goldValue, itemType, twoHanded, attributes, resistances, abilities, 0.0);
    }

    public Mainhand(String name, int goldValue, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities, double damage) {
        super(name, goldValue, EquipmentTypes.MAINHAND, itemType, attributes, resistances);
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

    public String getWeaponDamageAttr() {
        return "strength"; 
    }

    @Override
    public BiFunction<Integer, Integer, Damage> getBaseDamageType() {
        
        return (min, max) -> new PhysicalBludgeoningDamage(min, max);
    }
}
