package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.Item;
import items.equipment.item_types.ItemType;

import java.util.Comparator;
import abilities.interfaces.NameableWithQuantity;

public abstract class Equipment extends Item implements Comparator<Equipment>, NameableWithQuantity {
    private EquipmentTypes equipmentType;
    private ItemType itemType;
    private Attributes attributes;
    private Resistances resistances;

    public Equipment(String name, int value, int quantity, EquipmentTypes equipmentType,
                     ItemType itemType, Attributes attributes, Resistances resistances) {
        super(name, value, quantity);
        this.equipmentType = equipmentType;
        this.itemType = itemType;
        this.attributes = attributes;
        this.resistances = resistances;
    }

    public Equipment(String name, int value, int quantity, EquipmentTypes equipmentType,
                     Attributes attributes, Resistances resistances) {
        super(name, value, quantity);
        this.equipmentType = equipmentType;
        this.attributes = attributes;
        this.resistances = resistances;
    }

    public EquipmentTypes getEquipmentType() {
        return equipmentType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public Resistances getResistances() {
        return resistances;
    }

    @Override
    public int compare(Equipment o1, Equipment o2) {
        return 0;
    }

    @Override
    public Comparator<Equipment> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }
}
