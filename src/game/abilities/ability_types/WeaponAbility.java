package abilities.ability_types;

import abilities.damages.Damage;
import items.equipment.item_types.WeaponTypes;

public class WeaponAbility extends TargetingAbility {
    private double multiplier;
    private boolean offhand;

    // Basic Constructor with all default values except Damage
    public WeaponAbility(Damage[] damages) {
        super(damages);
        this.multiplier = 1.0;
    }

    // Basic constructor with default ranges (single target)
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, String description) {
        super(name, manaCost, actionCost, damages, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    // Constructor for default weapons
    public WeaponAbility(String name, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, damages, weaponTypes, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    // Constructor for WeaponTypes
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    // Constructor with levelRequirement, default left/right range, and no equipment requirement
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    // Constructor with levelRequirement, WeaponTypes, default left/right range
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    // New constructors with leftRange and rightRange
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, leftRange, rightRange, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, leftRange, rightRange, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, double multiplier, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
        this.multiplier = multiplier;
        this.offhand = false;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public WeaponAbility withOffhand() {
        this.offhand = true;
        return this;
    }

    public boolean isOffhand() {
        return offhand;
    }
}
