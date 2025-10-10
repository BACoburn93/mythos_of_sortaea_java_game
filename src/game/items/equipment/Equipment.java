package items.equipment;

import items.equipment.item_types.ItemType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import abilities.Ability;
import actors.attributes.Attributes;
import actors.resistances.Resistances;

public abstract class Equipment implements Comparator<Equipment> {
    private String name;
    private double value;
    private EquipmentTypes equipmentType;
    private ItemType itemType;
    private Attributes attributes;
    private Resistances resistances;

        // Helper factories that produce zero/default objects
    private static Attributes defaultAttributes() {
        return new Attributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    private static Resistances defaultResistances() {
        return new Resistances(0,0,0,0,0,0,0,0,0,0,0,0);
    }

    protected Equipment(String name,
                        double value,
                        EquipmentTypes equipmentType,
                        ItemType itemType,
                        Attributes attributes,
                        Resistances resistances,
                        List<Ability> abilities) {
        this.name = name;
        this.value = value;
        this.equipmentType = equipmentType;
        this.itemType = itemType;
        this.attributes = (attributes == null) ? defaultAttributes() : attributes;
        this.resistances = (resistances == null) ? defaultResistances() : resistances;
    }

    protected Equipment(String name, double value, EquipmentTypes equipmentType, Attributes attributes, Resistances resistances) {
        this(name, value, equipmentType, null, attributes, resistances, null);
    }

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

    protected Equipment(String name, double value, EquipmentTypes equipmentType) {
        this(name, value, equipmentType, null, null, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGoldValue() {
        return value;
    }

    public void setGoldValue(double value) {
        this.value = value;
    }

    public EquipmentTypes getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentTypes equipmentType) {
        this.equipmentType = equipmentType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Resistances getResistances() {
        return resistances;
    }

    public void setResistances(Resistances resistances) {
        this.resistances = resistances;
    }

    protected static List<Ability> combineWithAbilities(Ability baseAbility, List<Ability> abilities) {
        List<Ability> result = new ArrayList<>();
        if (baseAbility != null) result.add(baseAbility);
        if (abilities != null) result.addAll(abilities);
        return result;
    }

    @Override
    public int compare(Equipment o1, Equipment o2) {
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
