package characters.jobs;

import java.util.Set;
import items.equipment.item_types.*;

public class EquipmentProficiencies {
    public static final Set<ItemType> mageAllowed = Set.of(
        WeaponTypes.DAGGER,
        WeaponTypes.STAFF, 
        WeaponTypes.WAND, 
        WeaponTypes.TOME, 
        ArmorTypes.CLOTH,
        ShieldTypes.SMALL
    );

    public static final Set<ItemType> warriorAllowed = Set.of(
        WeaponTypes.BATTLEAXE,
        WeaponTypes.BOW,
        WeaponTypes.CLAYMORE,
        WeaponTypes.CROSSBOW,
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.FLAIL,
        WeaponTypes.HALBERD,
        WeaponTypes.JAVELIN,
        WeaponTypes.KATANA,
        WeaponTypes.MACE,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SCYTHE,
        WeaponTypes.SPEAR,
        WeaponTypes.SWORD,
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
        WeaponTypes.BOW,
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.FLAIL,
        WeaponTypes.KATANA,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SPEAR,
        WeaponTypes.SWORD,
        WeaponTypes.WHIP,
        ArmorTypes.MEDIUM,
        ArmorTypes.LIGHT,
        ArmorTypes.CLOTH,
        ShieldTypes.SMALL
    );

    public static final Set<ItemType> rangerAllowed = Set.of(
        WeaponTypes.BOW,
        WeaponTypes.SPEAR,
        WeaponTypes.JAVELIN,
        WeaponTypes.CROSSBOW,
        WeaponTypes.DAGGER,
        WeaponTypes.FISTICUFFS,
        WeaponTypes.KATANA,
        WeaponTypes.RAPIER,
        WeaponTypes.SCIMITAR,
        WeaponTypes.SWORD,
        WeaponTypes.WHIP,
        ArmorTypes.MEDIUM,
        ArmorTypes.LIGHT,
        ArmorTypes.CLOTH,
        ShieldTypes.MEDIUM,
        ShieldTypes.SMALL
    );
}
