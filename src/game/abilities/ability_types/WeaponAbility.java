package abilities.ability_types;

import abilities.damages.Damage;
import items.equipment.item_types.WeaponTypes;

public class WeaponAbility extends TargetingAbility {
    private double multiplier;

    // Basic Constructor with all default values except Damage
    public WeaponAbility(Damage[] damages) {
        super(damages);
        this.multiplier = 1.0;
    }

    // Basic constructor with default ranges (single target)
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, String description) {
        super(name, manaCost, actionCost, damages, description);
        this.multiplier = multiplier;
    }

    // Constructor for default weapons
    public WeaponAbility(String name, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, damages, weaponTypes, description);
        this.multiplier = multiplier;
    }

    // Constructor for WeaponTypes
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, description);
        this.multiplier = multiplier;
    }

    // Constructor with levelRequirement, default left/right range, and no equipment requirement
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, description);
        this.multiplier = multiplier;
    }

    // Constructor with levelRequirement, WeaponTypes, default left/right range
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, description);
        this.multiplier = multiplier;
    }

    // New constructors with leftRange and rightRange
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, leftRange, rightRange, description);
        this.multiplier = multiplier;
    }

    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
        this.multiplier = multiplier;
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, leftRange, rightRange, description);
        this.multiplier = multiplier;
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
