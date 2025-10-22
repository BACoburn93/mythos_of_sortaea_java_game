package abilities.ability_types;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.DamageTypes;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.AccessoryTypes;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;

public class TargetingAbility extends Ability {
    private int leftRange;
    private int rightRange;
    private Set<ItemType> itemTypes = new HashSet<>();

    // Primary constructor
    public TargetingAbility(
        String name,
        int levelRequirement,
        int manaCost,
        int actionCost,
        Damage[] damages,
        ArmorTypes[] armorTypes,
        ShieldTypes[] shieldTypes,
        WeaponTypes[] weaponTypes,
        int leftRange,
        int rightRange,
        int tier,
        String description
    ) {
        super(name, levelRequirement, manaCost, actionCost, damages, armorTypes, shieldTypes, weaponTypes, tier, description, null);
        this.leftRange = leftRange;
        this.rightRange = rightRange;
    }

    // Builder
    public static class Builder {
        // Required
        private final String name;
        private Damage[] damages;

        // Optional/Defaults
        private int levelRequirement = -1;
        private int manaCost = 0;
        private int actionCost = 1;
        private ArmorTypes[] armorTypes = null;
        private ShieldTypes[] shieldTypes = null;
        private WeaponTypes[] weaponTypes = null;
        private int leftRange = 0;
        private int rightRange = 0;
        private int tier = 0;
        private String description = "";

        public EnumSet<DamageTypes> allowedDamageTypes = EnumSet.noneOf(DamageTypes.class);
        public Set<ItemType> allowedEquipmentTypes = new HashSet<>();

        public Builder(String name, Damage[] damages) {
            this.name = name;
            this.damages = damages;
        }

        public Builder allowedDamageTypes(DamageTypes... types) {
            if (types == null || types.length == 0) { this.allowedDamageTypes = null; }
            else {
                this.allowedDamageTypes = EnumSet.noneOf(DamageTypes.class);
                for (DamageTypes t : types) this.allowedDamageTypes.add(t);
            }
            return this;
        }

        public Builder allowedEquipmentTypes(ItemType... types) { // EquipmentType
            if (types == null || types.length == 0) { this.allowedEquipmentTypes = new HashSet<>(); return this; }
            for (ItemType t : types) this.allowedEquipmentTypes.add(t);
            return this;
        }

        public Builder allowedEquipmentTypes(WeaponTypes... types)   { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(ArmorTypes... types)    { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(ShieldTypes... types)   { return addEnumsToAllowed(types); }
        public Builder allowedEquipmentTypes(AccessoryTypes... types){ return addEnumsToAllowed(types); }

        private <E extends Enum<?>> Builder addEnumsToAllowed(E[] types) {
            if (types == null || types.length == 0) { this.allowedEquipmentTypes = null; return this; }
            if (this.allowedEquipmentTypes == null) this.allowedEquipmentTypes = new HashSet<>();
            for (E e : types) {
                if (e instanceof ItemType) {
                    this.allowedEquipmentTypes.add((ItemType) e);
                } else {
                    // enums that don't implement ItemType are ignored
                }
            }
            return this;
        }

        public Builder levelRequirement(int v) { this.levelRequirement = v; return this; }
        public Builder manaCost(int v) { this.manaCost = v; return this; }
        public Builder actionCost(int v) { this.actionCost = v; return this; }
        public Builder armorTypes(ArmorTypes[] v) { this.armorTypes = v; return this; }
        public Builder shieldTypes(ShieldTypes[] v) { this.shieldTypes = v; return this; }
        public Builder weaponTypes(WeaponTypes[] v) { this.weaponTypes = v; return this; }
        public Builder leftRange(int v) { this.leftRange = v; return this; }
        public Builder rightRange(int v) { this.rightRange = v; return this; }
        public Builder tier(int v) { this.tier = v; return this; }
        public Builder description(String v) { this.description = v; return this; }

        public TargetingAbility build() {
            int resolvedLevelReq = (levelRequirement >= 0) ? levelRequirement : abilities.Ability.defaultLevelForTier(tier);

            TargetingAbility ta = new TargetingAbility(
                name,
                resolvedLevelReq,
                manaCost,
                actionCost,
                damages,
                armorTypes,
                shieldTypes,
                weaponTypes,
                leftRange,
                rightRange,
                tier,
                description
            );

            ta.setAllowedDamageTypes(this.allowedDamageTypes);
            ta.setAllowedEquipmentTypes(this.allowedEquipmentTypes);

            return ta;
        }
    }

    // getters/setters
    public int getLeftRange() { return leftRange; }
    public void setLeftRange(int leftRange) { this.leftRange = leftRange; }
    public int getRightRange() { return rightRange; }
    public void setRightRange(int rightRange) { this.rightRange = rightRange; }

    public void setAllowedDamageTypes(EnumSet<DamageTypes> types) {
        this.allowedDamageTypes = (types == null) ? null : EnumSet.copyOf(types);
    }

    public void setAllowedEquipmentTypes(Set<ItemType> types) {
        this.allowedEquipmentTypes = (types == null) ? null : new HashSet<ItemType>(types);
    }
}
