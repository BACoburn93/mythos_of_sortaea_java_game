package abilities;

import abilities.damages.Damage;
import abilities.damages.DamageTypes;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.interfaces.WeaponDamageProvider;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.IntUnaryOperator;

public abstract class Ability {
    private static IntUnaryOperator defaultLevelFunc = tier -> Math.max(tier * 2 - 1, 1);
    
    private String name;
    private int manaCost;
    private int actionCost;
    private int levelRequirement;
    private int tier;
    private ArmorTypes[] armorRequirement;
    private ShieldTypes[] shieldRequirement;
    private WeaponTypes[] weaponRequirement;
    private Damage[] damages;
    private String description;

    protected Set<ItemType> allowedEquipmentTypes;
    protected Set<DamageTypes> allowedDamageTypes;

    // Primary constructor
    public Ability(
        String name,
        int levelRequirement,
        int manaCost,
        int actionCost,
        Damage[] damages,
        ArmorTypes[] armorRequirement,
        ShieldTypes[] shieldRequirement,
        WeaponTypes[] weaponRequirement,
        int tier,
        String description,
        BuilderBase<?> b
    ) {
        this.name = name;
        this.levelRequirement = levelRequirement;
        this.manaCost = manaCost;
        this.actionCost = actionCost;
        this.damages = damages;
        this.armorRequirement = armorRequirement;
        this.shieldRequirement = shieldRequirement;
        this.weaponRequirement = weaponRequirement;
        this.description = description;
        this.tier = tier;

        this.allowedEquipmentTypes = (b == null || b.allowedEquipmentTypes == null)
            ? new HashSet<ItemType>()
            : new HashSet<ItemType>(b.allowedEquipmentTypes);
        
        this.allowedDamageTypes = (b == null || b.allowedDamageTypes == null)
            ? EnumSet.noneOf(DamageTypes.class)
            : EnumSet.copyOf(b.allowedDamageTypes);
    }

    public boolean isApplicableTo(Equipment e) {
        if (e == null) return false;

        // requires explicitly allowed types
        if (allowedEquipmentTypes.isEmpty() && allowedDamageTypes.isEmpty()) {
            return false;
        }

        if (!allowedEquipmentTypes.isEmpty()) {
            ItemType itemType = e.getItemType();
            boolean eqOk = (itemType != null && allowedEquipmentTypes.contains(itemType));
            if (!eqOk) return false;
        }

        if (!allowedDamageTypes.isEmpty() && (e instanceof items.equipment.interfaces.WeaponDamageProvider wdp)) {
            java.util.function.BiFunction<Integer,Integer,abilities.damages.Damage> baseFn = wdp.getBaseDamageType();
            abilities.damages.Damage sample = null;
            if (baseFn != null) {
                int low = 1, high = Math.max(1, (int)Math.ceil(wdp.getDamage()));
                try { sample = baseFn.apply(low, high); } catch (Throwable t){}
            }
            abilities.damages.DamageTypes dt = (sample == null) ? null : sample.getDamageType();
            if (dt == null || !allowedDamageTypes.contains(dt)) return false;
        }

        return true;
    }

    // Convenience constructors delegate to primary constructor
    // Reaction is currently using this one (might change Reaction later to use full constructor)
    public Ability(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, String description) {
        this(name, levelRequirement, manaCost, actionCost, damages, null, null, null, 0, description, null);
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int defaultLevelForTier(int tier) {
        return defaultLevelFunc.applyAsInt(tier);
    }

    public static void setDefaultLevelFunction(IntUnaryOperator fn) {
        if (fn != null) defaultLevelFunc = fn;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {
        this.levelRequirement = levelRequirement;
    }

    public int getManaCost() {
        return manaCost;
    }


    public int getActionCost() {
        return actionCost;
    }


    public Damage[] getDamages() {
        return damages;
    }

    public void setDamages(Damage[] damages) {
        this.damages = damages;
    }

    public String getDescription() {
        return description;
    }

    public ArmorTypes[] getArmorRequirement() {
        return armorRequirement;
    }

    public ShieldTypes[] getShieldRequirement() {
        return shieldRequirement;
    }

    public WeaponTypes[] getWeaponRequirement() {
        return weaponRequirement;
    }

    @Override
    public String toString() {
        int totalWidth = 90;
        String divider = "+" + "-".repeat(totalWidth - 2) + "+\n";
        StringBuilder sb = new StringBuilder();
        sb.append(divider);

        // Wrap description to fit within 35 characters
        String desc = description != null ? description : "";
        java.util.List<String> descLines = utils.StringUtils.wrapText(desc, 35);

        sb.append(String.format(
            "| %-25s | %-8s | %-8s | %-35s |\n",
            name,
            manaCost + " MP",
            actionCost + " AP",
            descLines.size() > 0 ? descLines.get(0) : ""
        ));

        for (int i = 1; i < descLines.size(); i++) {
            sb.append(String.format(
                "| %-25s | %-8s | %-8s | %-35s |\n",
                "", "", "", descLines.get(i)
            ));
        }

        sb.append(divider);
        sb.append(String.format(
            "| %-86s |\n",
            "Damage: " + (damages != null ? Arrays.toString(damages) : "None")
        ));
        sb.append(divider);
        sb.append(String.format(
            "| %-86s |\n",
            "Weapon Requirement: " + (weaponRequirement != null ? String.join(", ", Arrays.stream(weaponRequirement).map(Enum::name).toArray(String[]::new)) : "No Weapon")
        ));
        sb.append(divider);
        sb.append(String.format(
            "| %-43s | %-42s |\n",
            "Armor Requirement: " + (armorRequirement != null ? String.join(", ", Arrays.stream(armorRequirement).map(Enum::name).toArray(String[]::new)) : "No Armor"),
            "Shield Requirement: " + (shieldRequirement != null ? String.join(", ", Arrays.stream(shieldRequirement).map(Enum::name).toArray(String[]::new)) : "No Shield")
        ));
        sb.append(divider);
        return sb.toString();
    }

    protected static abstract class BuilderBase<T extends BuilderBase<T>> {
        protected int minTier = 0;
        protected HashSet<ItemType> allowedEquipmentTypes;
        protected EnumSet<DamageTypes> allowedDamageTypes;

        public T minTier(int v) { this.minTier = v; return self(); }
        public T allowedEquipmentTypes(HashSet<ItemType> s) { this.allowedEquipmentTypes = new HashSet<ItemType>(s); return self(); }
        public T allowedDamageTypes(Set<DamageTypes> s) { this.allowedDamageTypes = EnumSet.copyOf(s); return self(); }

        protected abstract T self();
    }
}

