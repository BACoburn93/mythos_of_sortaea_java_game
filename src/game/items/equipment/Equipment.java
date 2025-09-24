package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.item_types.ItemType;

import java.util.Comparator;

public abstract class Equipment implements Comparator<Equipment> {
    private String name;
    private int value;
    private EquipmentTypes equipmentType;
    private ItemType itemType;
    private Attributes attributes;
    private Resistances resistances;

    public Equipment(String name, int value, EquipmentTypes equipmentType,
                     ItemType itemType, Attributes attributes, Resistances resistances) {
        this.name = name;
        this.value = value;
        this.equipmentType = equipmentType;
        this.itemType = itemType;
        this.attributes = attributes;
        this.resistances = resistances;
    }

    public Equipment(String name, int value, EquipmentTypes equipmentType,
                     Attributes attributes, Resistances resistances) {
        this(name, value, equipmentType, null, attributes, resistances);
    }

    public String getName() {
        return name;
    }

    public int getGoldValue() {
        return value;
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
        // You can implement a more meaningful comparison if needed
        return o1.getName().compareToIgnoreCase(o2.getName());
    }

    @Override
    public Comparator<Equipment> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public String toString() {
        int totalWidth = 90;
        int contentWidth = 86;
        String divider = "+" + "-".repeat(totalWidth - 2) + "+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(divider);

        // First row: name, type, item type, value
        String firstRow = String.format(
            "%s | %s | %s | %s",
            getName(),
            equipmentType != null ? equipmentType.toString() : "None",
            itemType != null ? itemType.toString() : "None",
            "Value: " + getGoldValue()
        );
        for (String line : utils.StringUtils.wrapText(firstRow, contentWidth)) {
            sb.append(String.format("| %-86s |\n", line));
        }
        sb.append(divider);

        // Second row: Attributes (as integers)
        String attrText = "Attributes: " + (attributes != null ? attributesToIntString(attributes) : "None");
        for (String line : utils.StringUtils.wrapText(attrText, contentWidth)) {
            sb.append(String.format("| %-86s |\n", line));
        }
        sb.append(divider);

        // Third row: Resistances (as integers)
        String resText = "Resistances: " + (resistances != null ? resistancesToIntString(resistances) : "None");
        for (String line : utils.StringUtils.wrapText(resText, contentWidth)) {
            sb.append(String.format("| %-86s |\n", line));
        }
        sb.append(divider);

        return sb.toString();
    }

    private String attributesToIntString(Attributes attrs) {
        return String.format(
            "STR: %d, AGI: %d, KNOW: %d, DEF: %d, RES: %d, SPIR: %d, LUCK: %d",
            (int) attrs.getStrength().getValue(),
            (int) attrs.getAgility().getValue(),
            (int) attrs.getKnowledge().getValue(),
            (int) attrs.getDefense().getValue(),
            (int) attrs.getResilience().getValue(),
            (int) attrs.getSpirit().getValue(),
            (int) attrs.getLuck().getValue()
        );
    }

    private String resistancesToIntString(Resistances res) {
        return String.format(
            "BLUDGE: %d, PIERC: %d, SLASH: %d, EARTH: %d, FIRE: %d, ICE: %d, LGTN: %d, VENOM: %d, WATER: %d, WIND: %d, DARK: %d, LIGHT: %d",
            (int) res.getBludgeoning().getValue(),
            (int) res.getPiercing().getValue(),
            (int) res.getSlashing().getValue(),
            (int) res.getEarth().getValue(),
            (int) res.getFire().getValue(),
            (int) res.getIce().getValue(),
            (int) res.getLightning().getValue(),
            (int) res.getVenom().getValue(),
            (int) res.getWater().getValue(),
            (int) res.getWind().getValue(),
            (int) res.getDarkness().getValue(),
            (int) res.getLight().getValue()
        );
    }
}
