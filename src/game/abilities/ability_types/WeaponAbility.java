package abilities.ability_types;

import abilities.damages.Damage;
import characters.Character;
import items.equipment.item_types.WeaponTypes;
import items.equipment.item_types.mainhand.Mainhand;

public class WeaponAbility extends TargetingAbility {

    // Basic Constructor with all default values except Damage
    public WeaponAbility(Damage[] damages) {
        super(damages);
    }

    // Basic constructor with default ranges (single target)
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, String description) {
        super(name, manaCost, actionCost, damages, description);
    }

    // Constructor for default weapons
    public WeaponAbility(String name, Damage[] damages, WeaponTypes[] weaponTypes, String description) {
        super(name, damages, weaponTypes, description);
    }

    // Constructor for WeaponTypes
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, description);
    }

    // Constructor with levelRequirement, default left/right range, and no equipment requirement
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, description);
    }

    // Constructor with levelRequirement, WeaponTypes, default left/right range
    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, description);
    }

    // New constructors with leftRange and rightRange
    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, leftRange, rightRange, description);
    }

    public WeaponAbility(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, leftRange, rightRange, description);
    }

    public WeaponAbility(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes, int leftRange, int rightRange, String description) {
        super(name, levelRequirement, manaCost, actionCost, damages, weaponTypes, leftRange, rightRange, description);
    }

    public int calculateUnarmedAbilityDamage(Character user, int numHits) {
        if (user.hasWeaponEquipped()) {
            throw new IllegalStateException("Cannot use unarmed ability with weapon equipped.");
        }
        return (int) (user.getJobObj().getUnarmedDamage() * numHits);
    }

    public int calculateWeaponAbilityDamage(Character user, double multiplier) {
        Mainhand weapon = user.getEquippedMainHand();
        if (weapon == null) {
            throw new IllegalStateException("Weapon required for this ability.");
        }
        return (int) Math.round(weapon.getDamage() * multiplier);
    }
}
