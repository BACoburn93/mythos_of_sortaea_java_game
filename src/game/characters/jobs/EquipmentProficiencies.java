package characters.jobs;

import java.util.Set;
import items.equipment.item_types.*;

public class EquipmentProficiencies {
    public static final Set<ItemType> mageAllowed = Set.of(
        WeaponTypes.DAGGER,
        WeaponTypes.SHORTSWORD,
        WeaponTypes.STAFF, 
        WeaponTypes.WAND, 
        WeaponTypes.TOME, 
        ArmorTypes.CLOTH,
        ShieldTypes.SMALL
    );

    public static final Set<ItemType> warriorAllowed = Set.of(
        WeaponTypes.BATTLEAXE,
        WeaponTypes.BROADSWORD,
        WeaponTypes.CLAYMORE,
        WeaponTypes.CROSSBOW,
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.FLAIL,
        WeaponTypes.HALBERD,
        WeaponTypes.JAVELIN,
        WeaponTypes.KATANA,
        WeaponTypes.BOW,
        WeaponTypes.SWORD,
        WeaponTypes.MACE,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SCYTHE,
        WeaponTypes.SHORTBOW,
        WeaponTypes.SHORTSWORD,
        WeaponTypes.SPEAR,
        WeaponTypes.TRIDENT,
        WeaponTypes.WARHAMMER,
        WeaponTypes.WHIP,
        ArmorTypes.HEAVY,
        ArmorTypes.MEDIUM,
        ArmorTypes.LIGHT,
        ArmorTypes.CLOTH,
        ShieldTypes.LARGE,
        ShieldTypes.MEDIUM,
        ShieldTypes.SMALL
    );

    public static final Set<ItemType> rogueAllowed = Set.of(
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.FLAIL,
        WeaponTypes.KATANA,
        WeaponTypes.BOW,
        WeaponTypes.SWORD,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SHORTBOW,
        WeaponTypes.SHORTSWORD,
        WeaponTypes.SPEAR,
        WeaponTypes.WHIP,
        ArmorTypes.MEDIUM,
        ArmorTypes.LIGHT,
        ArmorTypes.CLOTH,
        ShieldTypes.SMALL
    );

    public static final Set<ItemType> rangerAllowed = Set.of(
        WeaponTypes.BOW,
        WeaponTypes.SHORTBOW,
        WeaponTypes.SPEAR,
        WeaponTypes.JAVELIN,
        WeaponTypes.CROSSBOW,
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.KATANA,
        WeaponTypes.SWORD,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SHORTSWORD,
        WeaponTypes.WHIP,
        ArmorTypes.MEDIUM,
        ArmorTypes.LIGHT,
        ArmorTypes.CLOTH,
        ShieldTypes.MEDIUM,
        ShieldTypes.SMALL
    );
}
