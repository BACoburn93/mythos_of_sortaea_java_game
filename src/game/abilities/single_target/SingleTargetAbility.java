package abilities.single_target;

import abilities.Ability;
import abilities.damages.Damage;
import items.equipment.item_types.ArmorTypes;
import items.equipment.item_types.ShieldTypes;
import items.equipment.item_types.WeaponTypes;

public class SingleTargetAbility extends Ability {

    public SingleTargetAbility(String name, int manaCost, int actionCost, Damage[] damages, String description) {
        super(name, manaCost, actionCost, damages, description);
    }
    public SingleTargetAbility(String name, int manaCost, int actionCost, Damage[] damages, ArmorTypes[] armorTypes,
                               String description) {
        super(name, manaCost, actionCost, damages, armorTypes, description);
    }
    public SingleTargetAbility(String name, int manaCost, int actionCost, Damage[] damages, ShieldTypes[] shieldTypes,
                               String description) {
        super(name, manaCost, actionCost, damages, shieldTypes, description);
    }
    public SingleTargetAbility(String name, int manaCost, int actionCost, Damage[] damages, WeaponTypes[] weaponTypes,
                               String description) {
        super(name, manaCost, actionCost, damages, weaponTypes, description);
    }
}
