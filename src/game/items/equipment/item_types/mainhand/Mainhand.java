package items.equipment.item_types.mainhand;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;
import abilities.Ability; // Import your Ability class
import java.util.List;

public class Mainhand extends Equipment {
    private boolean twoHanded;
    private List<Ability> abilities;

    public Mainhand(String name, int goldValue, ItemType itemType, boolean twoHanded, Attributes attributes, Resistances resistances, List<Ability> abilities) {
        super(name, goldValue, EquipmentTypes.MAINHAND, itemType, attributes, resistances);
        this.twoHanded = twoHanded;
        this.abilities = abilities;
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
}
