package abilities.ability_types;

import abilities.Ability;
import abilities.damages.Damage;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;

public class TargetingAbility extends Ability {
    private int leftRange;
    private int rightRange;

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
        super(name, levelRequirement, manaCost, actionCost, damages, armorTypes, shieldTypes, weaponTypes, tier, description);
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

        public Builder(String name, Damage[] damages) {
            this.name = name;
            this.damages = damages;
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

            return new TargetingAbility(
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
        }
    }

    // getters/setters
    public int getLeftRange() { return leftRange; }
    public void setLeftRange(int leftRange) { this.leftRange = leftRange; }
    public int getRightRange() { return rightRange; }
    public void setRightRange(int rightRange) { this.rightRange = rightRange; }
}
