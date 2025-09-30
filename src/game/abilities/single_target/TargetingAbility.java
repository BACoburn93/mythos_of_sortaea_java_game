package abilities.single_target;

import abilities.Ability;
import abilities.damages.Damage;
import items.equipment.item_types.ArmorTypes;
import items.equipment.item_types.ShieldTypes;
import items.equipment.item_types.WeaponTypes;

public class TargetingAbility extends Ability {
    private int leftRange;
    private int rightRange;

    // Basic constructor with default ranges (single target)
    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, String description) {
        this(name, manaCost, actionCost, damages, 0, 0, description);
    }

    // Constructor for ArmorTypes
    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, ArmorTypes[] armorTypes,
                           String description) {
        this(name, manaCost, actionCost, damages, armorTypes, 0, 0, description);
    }

    // Constructor for ShieldTypes
    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, ShieldTypes[] shieldTypes,
                           String description) {
        this(name, manaCost, actionCost, damages, shieldTypes, 0, 0, description);
    }

    // Constructor for WeaponTypes
    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes,
                           String description) {
        this(name, manaCost, actionCost, damages, weaponTypes, 0, 0, description);
    }

    // New constructors with leftRange and rightRange
    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages,
                           int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, description);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, ArmorTypes[] armorTypes,
                           int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, armorTypes, description);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, ShieldTypes[] shieldTypes,
                           int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, shieldTypes, description);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    public TargetingAbility(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes,
                           int leftRange, int rightRange, String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, description);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    // Getters and setters
    public int getLeftRange() {
        return leftRange;
    }

    public void setLeftRange(int leftRange) {
        this.leftRange = leftRange;
    }

    public int getRightRange() {
        return rightRange;
    }

    public void setRightRange(int rightRange) {
        this.rightRange = rightRange;
    }
}
