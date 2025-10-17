package items.equipment;

import items.equipment.item_types.enums.WeaponTypes;

public class AbilityPrerequisites {
    
    public static final WeaponTypes[] SPELL_CASTING_WEAPONS = {
        WeaponTypes.STAFF,
        WeaponTypes.ORB,
        WeaponTypes.TOME,
        WeaponTypes.WAND
    };

    public static final WeaponTypes[] SLASHING_WEAPONS = {
        WeaponTypes.SWORD,
        WeaponTypes.CLAYMORE,
        WeaponTypes.KATANA,
        WeaponTypes.SCIMITAR,
        WeaponTypes.BATTLEAXE,
        WeaponTypes.SCYTHE,
        WeaponTypes.TRIDENT,
        WeaponTypes.HALBERD,
        WeaponTypes.WHIP,
    };

    public static final WeaponTypes[] PIERCING_WEAPONS = {
        WeaponTypes.DAGGER,
        WeaponTypes.RAPIER,
        WeaponTypes.SPEAR,
        WeaponTypes.JAVELIN,
        WeaponTypes.CROSSBOW,
        WeaponTypes.BOW,
    };

    public static final WeaponTypes[] BLUDGEONING_WEAPONS = {
        WeaponTypes.FISTICUFFS,
        WeaponTypes.MACE,
        WeaponTypes.FLAIL,
        WeaponTypes.WARHAMMER,
        WeaponTypes.ROD,
    };

    public static final WeaponTypes[] RANGED_WEAPONS = {
        WeaponTypes.CROSSBOW,
        WeaponTypes.BOW,
        WeaponTypes.JAVELIN,
    };
}