package characters;

import abilities.Ability;
import abilities.ability_types.TargetingAbility;
import abilities.ability_types.WeaponAbility;
import abilities.damages.Damage;
import abilities.reactions.*;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.stances.Stances;
import actors.types.CombatActor;
import actors.attributes.AttributeTypes;
import characters.jobs.Job;
import characters.managers.AbilityManager;
import characters.managers.EquipmentManager;
import characters.managers.InventoryManager;
import characters.managers.LevelManager;
import items.consumables.Consumable;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.enums.AccessoryTypes;
import items.equipment.item_types.enums.ArmorTypes;
import items.equipment.item_types.enums.ShieldTypes;
import items.equipment.item_types.enums.WeaponTypes;
import items.equipment.item_types.mainhand.Mainhand;
import items.equipment.equipment_slots.*;
import utils.GameScanner;
import utils.InputHandler;
import utils.StringUtils;

import java.util.*;
import java.util.function.Supplier;

import characters.initializers.*;

public class Character extends CombatActor {
    private final Job job;
    private final EquipmentManager equipmentManager;
    private int level;
    private int experience;
    private int experienceToLevel = 100;
    private int actionPoints;
    private int maxActionPoints;
    private int attributePoints;
    public int hits;

    private final Reaction[] reactions;
    private final ArrayList<Ability> abilities;
    private final Map<String, EquipmentSlot> equipmentSlots;
    private final Consumable[] items;
    private List<Ability> itemAbilities = new ArrayList<>();
    private final GameScanner gameScanner;


    public Character(GameScanner gameScanner, String name, Job job) {
        super(name, job.getHealthValues(), job.getManaValues(), job.getAttributes(), job.getResistances());
        this.setActorType(ActorTypes.CHARACTER);
        
        this.job = job;
        this.level = 1;
        this.experience = 0;
        this.maxActionPoints = 3;
        this.attributePoints = 0;
        this.hits = job.getHits();
        this.actionPoints = this.maxActionPoints;

        this.gameScanner = gameScanner;

        this.abilities = new ArrayList<>(CharacterAbilities.getAbilities());
        this.reactions = CharacterReactions.getReactions();
        this.equipmentSlots = CharacterItems.getEquipmentSlots();
        this.items = CharacterItems.getItems();
        this.abilities.addAll(this.job.getJobAbilities());
        
        this.equipmentManager = new EquipmentManager(this, this.equipmentSlots);
    }

    public void addExperience(int expToAdd) {

        LevelManager.getInstance().addExperience(this, expToAdd);
    }

    // Learn new ability
    public void learnNewAbility() {
        var choicesPool = AbilityManager.getInstance().getLearnableAbilities(this);

        if (choicesPool.isEmpty()) {
            System.out.println("No new abilities available to learn.");
            return;
        }

        List<Ability> choices = AbilityManager.getInstance().randomChoices(choicesPool, 3);

        StringUtils.stringDivider("Choose a new ability to learn:", " ", 10);
        StringUtils.printOptionsGrid(choices, Ability::getName, 3, choices.size());

        String abilityChoice = gameScanner.nextLine();
        Ability selectedAbility = InputHandler.getItemByInput(abilityChoice, choices, Ability::getName);

        if (selectedAbility != null) {
            if (AbilityManager.getInstance().learnAbility(this, selectedAbility)) {
                System.out.println("You have learned a new ability: " + selectedAbility.getName());
            }
        } else {
            System.out.println("Invalid selection. No ability learned.");
            if (choices.size() == 1) {
                if (AbilityManager.getInstance().learnAbility(this, choices.get(0))) {
                    System.out.println("Automatically learned the only available ability: " + choices.get(0).getName());
                }
            }
        }
    }
    
    public void addAbility(Ability ability) {
        if (ability == null) return;
        this.abilities.add(ability);
    }

    public void allocateAttributePoints() {
        List<String> attributeOptions = Arrays.asList(
            "Strength", "Agility", "Knowledge", "Defense", "Resilience", "Spirit", "Luck"
        );

        Random randomRoll = new Random();
        int minValue = this.level / 4 + 2;
        int luckBonus = (int) Math.ceil((double) this.getAttributes().getLuck().getValue() / 4);
        int maxValue = minValue + luckBonus + 1;

        if (maxValue > minValue) {
            this.attributePoints = randomRoll.nextInt(minValue, maxValue); 
        } else {
            this.attributePoints = minValue;
        }

        while(this.attributePoints > 0) {
            StringUtils.stringDivider("Which attribute would you like to allocate points to? You have " +
                    this.attributePoints + " attribute points remaining.", " ", 10);

            StringUtils.printOptionsGrid(
                attributeOptions,
                attr -> attr, 
                3,
                attributeOptions.size()
            );

            String attrToLevel = gameScanner.nextLine();

            String selected = InputHandler.getItemByInput(attrToLevel, attributeOptions, java.util.function.Function.identity());

            if (selected == null) {
                System.out.println("Invalid attribute selection. Please try again.");
                continue;
            }

            System.out.println("How many points to allocate? You have " + this.attributePoints + " remaining.");

            try {
                int numAttributePointToAllocate = Integer.parseInt(gameScanner.nextLine());
                if(numAttributePointToAllocate <= this.attributePoints) {
                    this.getAttributes().incrementAttribute(selected, numAttributePointToAllocate);
                    this.attributePoints -= numAttributePointToAllocate;
                } else {
                    System.out.println("Insufficient Attribute Points.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
            // }
        }

        StringUtils.stringDivider(String.valueOf(this.getAttributes()), "", 50);
    }

    public void changeStance(Stances stance) {
        if(stance.equals(Stances.DEFENDING) || stance.equals(Stances.PARRYING)) {
            this.setStance(stance);
        }
    }

    public Ability chooseAbility(String action) {
        Ability selected = InputHandler.getItemByInput(action, this.getAbilities(), Ability::getName);

        if (selected != null) {
            return selected;
        }

        return new TargetingAbility.Builder(
            "pass", 
            null)
            .description("Conserve or recuperate.").build();
    }


    public Reaction chooseReaction(String action) {
        Reaction pass = new Reaction("Pass", 0, "Conserve or recuperate.");
        for(int i = 0; i < this.getReactions().length; i++) {
            if(Objects.equals(action.toLowerCase(), this.getReactions()[i].getName().toLowerCase())) {
                if(this.getActionPoints() >= this.getReactions()[i].getActionCost()) {
                    return this.getReactions()[i];
                } else {
                    return pass;
                }
            }
        }
        return pass;
    }

    // Helper to map Equipment to display names
    private static final Map<Enum<?>, String> equipmentTypeToDisplayName = Map.of(
        EquipmentTypes.HEAD, "Head",
        EquipmentTypes.MAINHAND, "Mainhand",
        EquipmentTypes.OFFHAND, "Offhand",
        EquipmentTypes.TORSO, "Torso",
        EquipmentTypes.BACK, "Back",
        EquipmentTypes.LEGS, "Legs",
        EquipmentTypes.WAIST, "Waist",
        EquipmentTypes.FEET, "Feet",
        AccessoryTypes.NECK, "Neck",
        AccessoryTypes.RING, "Ring"
    );

    public boolean equipItem(Equipment item) {
        if (item == null) return false;

        // preserve interactive choice for rings but delegate actual equip logic to EquipmentManager
        String slotKey = (item.getEquipmentType() == EquipmentTypes.ACCESSORY)
                ? equipmentTypeToDisplayName.get(item.getItemType())
                : equipmentTypeToDisplayName.get(item.getEquipmentType());

        if ("Ring".equals(slotKey)) {
            System.out.println("Equip ring to [L]eft or [R]ight ring?");
            String choice = gameScanner.nextLine().trim().toUpperCase();
            if (choice.equals("L") || choice.equals("LEFT")) {
                return equipmentManager.equipToSlot(item, "Left Ring");
            } else if (choice.equals("R") || choice.equals("RIGHT")) {
                return equipmentManager.equipToSlot(item, "Right Ring");
            } else {
                System.out.println("Invalid choice. Cancelling equip.");
                return false;
            }
        }

        // non-ring equip: let manager choose slot / replace existing if needed
        return equipmentManager.equip(item);
    }

    public void unequipItem(String slotKey) {
        equipmentManager.unequip(slotKey);
    }

    public boolean unequipItemByName(String itemName) {
        return equipmentManager.unequipByName(itemName);
    }

    public List<String> getEquippedItems() {
        List<String> equipped = new ArrayList<>();
        for (Map.Entry<String, EquipmentSlot> entry : equipmentManager.getSlots().entrySet()) {
            Equipment item = entry.getValue().getEquippedItem();
            if (item != null) {
                equipped.add(entry.getKey() + ": " + item.getName());
            }
        }
        return equipped;
    }

    public Mainhand getEquippedMainHand() {
        EquipmentSlot mainhandSlot = equipmentManager.getSlots().get("Mainhand");
        if (mainhandSlot != null && mainhandSlot.getEquippedItem() instanceof Mainhand mainHand) {
            return mainHand;
        }
        return null;
    }

    public boolean hasWeaponEquipped() {
        return equipmentManager.hasWeaponEquipped();
    }

    public List<Ability> getAbilities() {
        List<Ability> all = new ArrayList<>(this.abilities);
        all.addAll(itemAbilities);
        return all;
    }

    public void addItemAbilities(List<Ability> abilities) {
        if (abilities != null) {
            Set<String> knownNames = new HashSet<>();

            for (Ability a : this.abilities) {
                knownNames.add(a.getName());
            }

            for (Ability a : itemAbilities) {
                knownNames.add(a.getName());
            }

            for (Ability a : abilities) {
                if (!knownNames.contains(a.getName())) {
                    itemAbilities.add(a);
                    knownNames.add(a.getName());
                }
            }
        }
    }

    public void removeItemAbilities(List<Ability> abilities) {
        if (abilities != null) {
            List<String> namesToRemove = abilities.stream().map(Ability::getName).toList();
            itemAbilities.removeIf(a -> namesToRemove.contains(a.getName()));
        }
    }

    public void handlePostCombat() {
        this.actionPoints = this.maxActionPoints;
    }

    public void attack(CombatActor target, Supplier<Damage> damageSupplier, AttributeTypes attrDamageBonus) {
        
        double calculatedValue = switch (attrDamageBonus) {
            case STRENGTH -> this.getAttributes().getStrength().getValue() / 6.0;
            case AGILITY -> this.getAttributes().getAgility().getValue() / 8.0;
            case KNOWLEDGE -> this.getAttributes().getKnowledge().getValue() / 10.0;
            default -> 0.0;
        };

        double attrToDamageBonus = Math.max(0, calculatedValue);

        Damage[] damages = new Damage[hits];
        for (int i = 0; i < hits; i++) {
            Damage baseDamage = damageSupplier.get();
            baseDamage.addBonus(attrToDamageBonus); 
            damages[i] = baseDamage;
        }
        WeaponAbility ability = new WeaponAbility.Builder("Attack", damages).build();

        target.takeDamage(this, ability);
    }

    private void handleDefend() {
        this.changeStance(Stances.DEFENDING);
    }

    // Use an item from inventory
    public void handleItem(String action) {
        if (action == null) return;
        if ("item".equalsIgnoreCase(action)) {
            for (Consumable c : InventoryManager.getInstance().listConsumables(this)) {
                StringUtils.stringDivider(String.valueOf(c), "-", 50);
            }
            return;
        }
        // Attempt to use named consumable via InventoryManager
        boolean used = InventoryManager.getInstance().useConsumable(this, action);
        if (!used) {
            System.out.println("No such usable item or insufficient action points.");
        }
    }

    public void handleObserve(ArrayList<CombatActor> combatActors) {

        if (this.getStatusConditions().getBlind().getDuration() <= 0) {
            System.out.println("Choose a target to observe (by name or number):");
            
            StringUtils.printOptionsGrid(
                combatActors,
                CombatActor::getName,
                3,
                4
            );

            String action = gameScanner.nextLine();

            CombatActor selected = InputHandler.getItemByInput(action, combatActors, CombatActor::getName);

            if (selected != null) {
                ui.CombatUIStrings.printCombatActorStats(selected);
            } else {
                System.out.println("No such target found.");
            }

        } else {
            System.out.println("You're blinded and can't observe.");
        }

    }

    public void handleReaction(ArrayList<CombatActor> actors, ArrayList<Character> partyCharacters, String reaction) {
        ReactionTypes reactionType;
        try {
            reactionType = ReactionTypes.valueOf(reaction.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid reaction: " + reaction);
            return;
        }

        switch (reactionType) {
            case DEFEND:
                System.out.println(this.getName() + " is now in a defending stance.");
                this.handleDefend();
                break;
            case PARRY:
                System.out.println(this.getName() + " is now in a parrying stance.");
                this.setStance(Stances.PARRYING);
                break;
            case ITEM:
                this.handleItem(reaction);
                break;
            case OBSERVE:
                handleObserve(actors);
                break;
            case PASS:
                System.out.println(this.getName() + " is passing their turn.");
                break;
            default:
                System.out.println("Invalid reaction, please try again.");
        }
    }

    public boolean isValidAbility(String action) {
        List<Ability> abilities = this.getAbilities();

        Ability selectedAbility = InputHandler.getItemByInput(action, abilities, Ability::getName);
        if (selectedAbility == null) return false;

        boolean armorRequired = false;
        boolean shieldRequired = false;
        boolean weaponRequired = false;

        for (EquipmentSlot slot : this.getEquipmentSlots().values()) {
            Equipment equipment = slot.getEquippedItem();
            if (selectedAbility.getArmorRequirement() != null) {
                for (ArmorTypes armorType : selectedAbility.getArmorRequirement()) {
                    if (equipment != null && armorType.equals(equipment.getItemType())) {
                        return true;
                    }
                }
                armorRequired = true;
            }
            if (selectedAbility.getShieldRequirement() != null) {
                for (ShieldTypes shieldType : selectedAbility.getShieldRequirement()) {
                    if (equipment != null && shieldType.equals(equipment.getItemType())) {
                        return true;
                    }
                }
                shieldRequired = true;
            }
            if (selectedAbility.getWeaponRequirement() != null) {
                for (WeaponTypes weaponType : selectedAbility.getWeaponRequirement()) {
                    if (equipment != null && weaponType.equals(equipment.getItemType())) {
                        return true;
                    }
                }
                weaponRequired = true;
            }
        }

        if (armorRequired) {
            displayEquipmentRequirements("armor", selectedAbility.getArmorRequirement(), EquipmentTypes.TORSO);
        }
        if (shieldRequired) {
            displayEquipmentRequirements("shield", selectedAbility.getShieldRequirement(), EquipmentTypes.MAINHAND, EquipmentTypes.OFFHAND);
        }
        if (weaponRequired) {
            displayEquipmentRequirements("weapon", selectedAbility.getWeaponRequirement(), EquipmentTypes.MAINHAND, EquipmentTypes.OFFHAND);
        }

        return !armorRequired && !shieldRequired && !weaponRequired;
    }


    private void displayEquipmentRequirements(String type, ItemType[] requirements, EquipmentTypes... slots) {
        System.out.println("You do not have the required " + type + " to use this ability.");
        System.out.print("The requirements are: ");
        for (ItemType requirement : requirements) {
            System.out.print(requirement + " ");
        }
        System.out.println();

        for (EquipmentTypes slot : slots) {
            EquipmentSlot equipmentSlot = this.equipmentManager.getSlots().get(slot.name());
            Equipment equipment = equipmentSlot != null ? equipmentSlot.getEquippedItem() : null;
            if (equipment != null) {
                System.out.println("You currently have a " + equipment.getItemType() + " equipped in your " + slot.name().toLowerCase() + ".");
            } else {
                System.out.println("You are not currently using anything in your " + slot.name().toLowerCase() + ".");
            }
        }
    }

    public boolean isValidAction(String action) {
        boolean isValidItem = false;

        for(int i = 0; i < this.getItems().length; i++) {
            if (Objects.equals(action.toLowerCase(), this.getItems()[i].getName().toLowerCase()) &&
                    this.getItems()[i].getQuantity() > 0) {
                isValidItem = true;
                break;
            }
        }

        java.util.List<String> validActions = java.util.Arrays.asList(
                "item", "ability", "abildesc", "ability description", "observe", "equip", "unequip",
                "guide", "combat guide", "sort"
        );

        return validActions.contains(action.toLowerCase()) || isValidItem;
    }

    public boolean isValidReaction(String reaction) {
        boolean isValidItem = false;

        for(int i = 0; i < this.getItems().length; i++) {
            if (Objects.equals(reaction.toLowerCase(), this.getItems()[i].getName().toLowerCase()) &&
                    this.getItems()[i].getQuantity() > 0) {
                isValidItem = true;
                break;
            }
        }

        java.util.List<String> validReactions = java.util.Arrays.asList(
                "help", "defend", "parry", "item", "observe", "pass", "quit", ""
        );

        return validReactions.contains(reaction.toLowerCase()) || isValidItem;
    }

    // Level up methods
    public void levelUp() {
        LevelManager.getInstance().levelUp(this);
    }

    public int getLevel() { return this.level; }
    public void incrementLevel() { this.level++; }
    public void notifyLevelUp() {
        StringUtils.stringDivider(this.getName() + " is now level " + this.level, "* ", 10);
    }

    // Getters

    @Override
    public String getName() {
        return super.getName();
    }

    public String getJob() {
        return job.getName();
    }

    public Job getJobObj() {
        return job;
    }

    public Map<String, EquipmentSlot> getEquipmentSlots() {
        return equipmentManager.getSlots();
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceToLevel() {
        return experienceToLevel;
    }

    public void setExperienceToLevel(int experienceToLevel) {
        this.experienceToLevel = experienceToLevel;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getMaxActionPoints() {
        return maxActionPoints;
    }

    public Reaction[] getReactions() {
        return reactions;
    }

    public Consumable[] getItems() {
        return items;
    }

    public Reaction[] getCharReactions() {
        return CharacterReactions.getReactions();
    }

    public Consumable[] getCharItems() {
        return CharacterItems.getItems();
    }

    // Setters

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public void setMaxActionPoints(int maxActionPoints) {
        this.maxActionPoints = maxActionPoints;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int leftWidth = 24, rightWidth = 40;
        String divider = "+--------------------------+------------------------------------------+\n";
        sb.append(divider);
        sb.append(String.format("| %-24s | %-40s |\n", "Name", this.getName()));
        sb.append(String.format("| %-24s | %-40s |\n", "Job", this.getJob()));
        sb.append(String.format("| %-24s | %-40s |\n", "Level", this.level));
        sb.append(String.format("| %-24s | %-40s |\n", "Experience", this.experience + " / " + this.experienceToLevel));
        sb.append(divider);
    
        // Attributes
        sb.append(String.format("| %-24s | %-40s |\n", "Attributes", ""));
        Attributes attrs = this.getAttributes();
        sb.append(String.format("|   %-22s | %-40s |\n", "Strength", attrs.getStrength().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Agility", attrs.getAgility().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Knowledge", attrs.getKnowledge().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Defense", attrs.getDefense().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Resilience", attrs.getResilience().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Spirit", attrs.getSpirit().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Luck", attrs.getLuck().getValue()));
        sb.append(divider);
    
        // Equipment
        sb.append(String.format("| %-24s | %-40s |\n", "Equipment", ""));
        for (Map.Entry<String, EquipmentSlot> entry : this.getEquipmentSlots().entrySet()) {
            String eqType = entry.getKey();
            String eqName = (entry.getValue().getEquippedItem() != null) ? entry.getValue().getEquippedItem().getName() : "None";
            for (int i = 0; i < StringUtils.wrapText(eqName, rightWidth).size(); i++) {
                sb.append(String.format("|   %-22s | %-40s |\n",
                    i == 0 ? eqType : "",
                    StringUtils.wrapText(eqName, rightWidth).get(i)));
            }
        }
        sb.append(divider);
    
        // Abilities
        sb.append(String.format("| %-24s | %-40s |\n", "Abilities", ""));
        for (Ability ability : this.getAbilities()) {
            List<String> nameLines = StringUtils.wrapText(ability.getName(), leftWidth - 3);
            List<String> descLines = StringUtils.wrapText(ability.getDescription(), rightWidth);
            int maxLines = Math.max(nameLines.size(), descLines.size());
            for (int i = 0; i < maxLines; i++) {
                sb.append(String.format("|   %-22s | %-40s |\n",
                    i < nameLines.size() ? nameLines.get(i) : "",
                    i < descLines.size() ? descLines.get(i) : ""));
            }
        }
        sb.append(divider);
    
        // Items
        sb.append(String.format("| %-24s | %-40s |\n", "Items", ""));
        for (Consumable item : this.getItems()) {
            List<String> nameLines = StringUtils.wrapText(item.getName(), leftWidth - 3);
            String qty = "x" + item.getQuantity();
            for (int i = 0; i < nameLines.size(); i++) {
                sb.append(String.format("|   %-22s | %-40s |\n",
                    nameLines.get(i),
                    i == 0 ? qty : ""));
            }
        }
        sb.append(divider);
    
        return sb.toString();
    }
}