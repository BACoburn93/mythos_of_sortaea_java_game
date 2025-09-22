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
        return String.format("| %-15s | %-5s |  %-5s | %-20s | %-20s | %-20s | %-20s |",
                name,
                manaCost + " MP",
                actionCost + " AP",
                weaponRequirement != null ? Arrays.toString(weaponRequirement) : "No Weapon Requirement",
                armorRequirement != null ? Arrays.toString(armorRequirement) : "No Armor Requirement",
                shieldRequirement != null ? Arrays.toString(shieldRequirement) : "No Shield Requirement",
                Arrays.toString(damages));
    }
}

