package abilities;

import abilities.damages.Damage;
import items.equipment.item_types.ArmorTypes;
import items.equipment.item_types.ShieldTypes;
import items.equipment.item_types.WeaponTypes;

import java.util.Arrays;

public abstract class Ability {
    private String name;
    private int manaCost;
    private int actionCost;
    private ArmorTypes[] armorRequirement;
    private ShieldTypes[] shieldRequirement;
    private WeaponTypes[] weaponRequirement;
    private Damage[] damages;
    private String description;

    public Ability(String name, int manaCost, int actionCost, Damage[] damages, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.actionCost = actionCost;
        this.damages = damages;
        this.description = description;
    }

    public Ability(String name, int manaCost, int actionCost, Damage[] damages, ArmorTypes[] armorRequirement, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.actionCost = actionCost;
        this.damages = damages;
        this.armorRequirement = armorRequirement;
        this.description = description;
    }

    public Ability(String name, int manaCost, int actionCost, Damage[] damages, ShieldTypes[] shieldRequirement, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.actionCost = actionCost;
        this.damages = damages;
        this.shieldRequirement = shieldRequirement;
        this.description = description;
    }

    public Ability(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponRequirement, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.actionCost = actionCost;
        this.damages = damages;
        this.weaponRequirement = weaponRequirement;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManaCost() {
        return manaCost;
    }


    public int getActionCost() {
        return actionCost;
    }


    public Damage[] getDamages() {
        return damages;
    }

    public void setDamages(Damage[] damages) {
        this.damages = damages;
    }

    public String getDescription() {
        return description;
    }

    public ArmorTypes[] getArmorRequirement() {
        return armorRequirement;
    }

    public ShieldTypes[] getShieldRequirement() {
        return shieldRequirement;
    }

    public WeaponTypes[] getWeaponRequirement() {
        return weaponRequirement;
    }

    @Override
    public String toString() {
        int totalWidth = 90;
        String divider = "+" + "-".repeat(totalWidth - 2) + "+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(divider);

        // Wrap description to fit within 35 characters
        String desc = description != null ? description : "";
        java.util.List<String> descLines = utils.StringUtils.wrapText(desc, 35);

        sb.append(String.format(
            "| %-25s | %-8s | %-8s | %-35s |\n",
            name,
            manaCost + " MP",
            actionCost + " AP",
            descLines.size() > 0 ? descLines.get(0) : ""
        ));

        for (int i = 1; i < descLines.size(); i++) {
            sb.append(String.format(
                "| %-25s | %-8s | %-8s | %-35s |\n",
                "", "", "", descLines.get(i)
            ));
        }

        sb.append(divider);
        sb.append(String.format(
            "| %-86s |\n",
            "Damage: " + (damages != null ? Arrays.toString(damages) : "None")
        ));
        sb.append(divider);
        sb.append(String.format(
            "| %-86s |\n",
            "Weapon Requirement: " + (weaponRequirement != null ? String.join(", ", Arrays.stream(weaponRequirement).map(Enum::name).toArray(String[]::new)) : "No Weapon")
        ));
        sb.append(divider);
        sb.append(String.format(
            "| %-43s | %-42s |\n",
            "Armor Requirement: " + (armorRequirement != null ? String.join(", ", Arrays.stream(armorRequirement).map(Enum::name).toArray(String[]::new)) : "No Armor"),
            "Shield Requirement: " + (shieldRequirement != null ? String.join(", ", Arrays.stream(shieldRequirement).map(Enum::name).toArray(String[]::new)) : "No Shield")
        ));
        sb.append(divider);
        return sb.toString();
    }
}

