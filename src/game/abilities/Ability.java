package abilities;

import abilities.damages.*;
import items.equipment.Equipment;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.*;

import java.util.*;
import java.util.function.*;

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
        String description
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

        Set<ItemType> computed = computeAllowedEquipmentTypes();
        this.allowedEquipmentTypes = (computed == null) ? new HashSet<>() : new HashSet<ItemType>(computed);
        this.allowedDamageTypes = EnumSet.noneOf(DamageTypes.class);

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

    // public read-only views
    public Set<items.equipment.item_types.ItemType> getAllowedEquipmentTypes() {
        return Collections.unmodifiableSet(allowedEquipmentTypes);
    }

    public Set<DamageTypes> getAllowedDamageTypes() {
        return Collections.unmodifiableSet(allowedDamageTypes);
    }

    // allow replacing the sets wholesale
    public void setAllowedEquipmentTypes(Set<items.equipment.item_types.ItemType> types) {
        this.allowedEquipmentTypes = (types == null) ? new HashSet<>() : new HashSet<>(types);
    }

    public void setAllowedDamageTypes(Set<DamageTypes> types) {
        this.allowedDamageTypes = (types == null) ? EnumSet.noneOf(DamageTypes.class) : EnumSet.copyOf(types);
    }

    // allow safe mutation via a Consumer (keeps control over null-handling)
    public void configureAllowedEquipmentTypes(Consumer<Set<items.equipment.item_types.ItemType>> mutator) {
        if (mutator == null) return;
        if (this.allowedEquipmentTypes == null) this.allowedEquipmentTypes = new HashSet<>();
        mutator.accept(this.allowedEquipmentTypes);
    }

    public void configureAllowedDamageTypes(Consumer<Set<DamageTypes>> mutator) {
        if (mutator == null) return;
        if (this.allowedDamageTypes == null) this.allowedDamageTypes = EnumSet.noneOf(DamageTypes.class);
        // wrap an adapter so callers see a Set<DamageTypes>
        Set<DamageTypes> proxy = new HashSet<>(this.allowedDamageTypes);
        mutator.accept(proxy);
        this.allowedDamageTypes = EnumSet.copyOf(proxy);
    }

    protected Set<ItemType> computeAllowedEquipmentTypes() {
        Set<ItemType> result = new HashSet<>();
        if (this.weaponRequirement != null) {
            for (WeaponTypes w : this.weaponRequirement) if (w instanceof ItemType) result.add((ItemType) w);
        }
        if (this.armorRequirement != null) {
            for (ArmorTypes a : this.armorRequirement) if (a instanceof ItemType) result.add((ItemType) a);
        }
        if (this.shieldRequirement != null) {
            for (ShieldTypes s : this.shieldRequirement) if (s instanceof ItemType) result.add((ItemType) s);
        }
        return result;
    }

    // Convenience constructors delegate to primary constructor
    // Reaction is currently using this one (might change Reaction later to use full constructor)
    public Ability(String name, int levelRequirement, int manaCost, int actionCost, Damage[] damages, String description) {
        this(name, levelRequirement, manaCost, actionCost, damages, null, null, null, 0, description);
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

    // protected static abstract class BuilderBase<T extends BuilderBase<T>> {
    //     protected int minTier = 0;
    //     protected HashSet<ItemType> allowedEquipmentTypes;
    //     protected ArmorTypes[] armorRequirement;
    //     protected ShieldTypes[] shieldRequirement;
    //     protected WeaponTypes[] weaponRequirement;
    //     protected EnumSet<DamageTypes> allowedDamageTypes;

    //     public T minTier(int v) { this.minTier = v; return self(); }
    //     public T allowedEquipmentTypes(Set<ItemType> s) { this.allowedEquipmentTypes = (s == null) ? null : new HashSet<ItemType>(s); return self(); }
    //     public T allowedDamageTypes(Set<DamageTypes> s) { this.allowedDamageTypes = (s == null) ? null : EnumSet.copyOf(s); return self(); }
    //     public T armorRequirement(ArmorTypes[] v) { this.armorRequirement = v; return self(); }
    //     public T shieldRequirement(ShieldTypes[] v) { this.shieldRequirement = v; return self(); }
    //     public T weaponRequirement(WeaponTypes[] v) { this.weaponRequirement = v; return self(); }

    //     // Computes allowed equipment types based on explicit set or inferred from requirements
    //     public Set<ItemType> computeAllowedEquipmentTypes() {
    //         if (this.allowedEquipmentTypes != null && !this.allowedEquipmentTypes.isEmpty()) {
    //             return new HashSet<ItemType>(this.allowedEquipmentTypes);
    //         }
    //         Set<ItemType> result = new HashSet<>();
    //         System.out.println("Computing allowed equipment types from requirements..." + 
    //             " armor=" + Arrays.toString(armorRequirement) +
    //             " shield=" + Arrays.toString(shieldRequirement) +
    //             " weapon=" + Arrays.toString(weaponRequirement)
    //         );
    //         if (weaponRequirement != null) for (WeaponTypes w : weaponRequirement) if (w instanceof ItemType) result.add((ItemType) w);
    //         if (armorRequirement  != null) for (ArmorTypes a : armorRequirement)   if (a instanceof ItemType) result.add((ItemType) a);
    //         if (shieldRequirement != null) for (ShieldTypes s : shieldRequirement) if (s instanceof ItemType) result.add((ItemType) s);
    //         return result;
    //     }

    //     protected abstract T self();
    // }
}

