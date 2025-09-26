package characters;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.reactions.*;
import abilities.single_target.SingleTargetAbility;
import actors.Actor;
import actors.ActorTypes;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resources.ResourceTypes;
import actors.stances.Stances;
import actors.types.CombatActor;
import characters.jobs.Job;
import items.consumables.Consumable;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.ShieldTypes;
import items.equipment.item_types.WeaponTypes;
import items.equipment.equipment_slots.*;

import utils.InputHandler;
import utils.StringUtils;

import java.util.*;
import characters.initializers.*;

public class Character extends CombatActor {
    private Job job;
    private int level;
    private int experience;
    private int experienceToLevel = 100;
    private int actionPoints;
    private int maxActionPoints;
    private int attributePoints;
    private Reaction[] reactions;
    private ArrayList<Ability> abilities;
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private Map<String, EquipmentSlot> equipmentSlots; 
    private Consumable[] items;
    private ArrayList<Ability> charAbilities = new ArrayList<>();

    public Character(String name, Job job) {
        super(name, job.getHealthValues(), job.getManaValues(), job.getAttributes(), job.getResistances());
        this.setActorType(ActorTypes.CHARACTER);
        this.job = job;
        this.level = 1;
        this.experience = 0;
        this.actionPoints = 3;
        this.attributePoints = 0;
        this.maxActionPoints = this.actionPoints;
        this.charAbilities = CharacterAbilities.getAbilities();
        this.abilities = new ArrayList<>(this.charAbilities);
        this.reactions = CharacterReactions.getReactions();
        this.equipmentSlots = new LinkedHashMap<>();

        // Display names as keys, but keep a mapping from EquipmentTypes to display names for lookup
        equipmentSlots.put("Head", new HeadSlot());
        equipmentSlots.put("Mainhand", new MainHandSlot());
        equipmentSlots.put("Offhand", new OffHandSlot());
        equipmentSlots.put("Legs", new LegsSlot());
        equipmentSlots.put("Torso", new TorsoSlot());
        equipmentSlots.put("Feet", new FeetSlot());
        equipmentSlots.put("Neck", new NeckSlot());
        equipmentSlots.put("Left Ring", new LeftRingSlot());
        equipmentSlots.put("Right Ring", new RightRingSlot());

        this.items = CharacterItems.getItems();
        this.abilities.addAll(this.job.getJobAbilities());
    }


    public void addExperience(int expToAdd) {
        this.experience += expToAdd;
        // levelUp();
    }

    public void allocateAttributePoints() {
        Random randomRoll = new Random();

        this.attributePoints = randomRoll.nextInt(3,
                (int) Math.ceil((double) this.getAttributes().getLuck().getValue() / 4)
        );

        Scanner attributesToAllocate = new Scanner(System.in);
        while(this.attributePoints > 0) {
            System.out.println("Which attribute would you like to allocate points to? You have " +
                    this.attributePoints + " attribute points remaining.");
            if (!attributesToAllocate.hasNextLine()) {
                System.out.println("No input available. Skipping attribute allocation.");
                break;
            }
            String attributeToLevel = attributesToAllocate.nextLine().toUpperCase();
            if(
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.STRENGTH.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.AGILITY.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.KNOWLEDGE.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.DEFENSE.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.RESILIENCE.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.SPIRIT.toString()) ||
                    attributeToLevel.equalsIgnoreCase(AttributeTypes.LUCK.toString())
            ) {
                System.out.println("How many points to allocate? You have " + this.attributePoints + " remaining.");
                if (!attributesToAllocate.hasNextLine()) {
                    System.out.println("No input available. Skipping attribute allocation.");
                    break;
                }
                try {
                    int numAttributePointToAllocate = Integer.parseInt(attributesToAllocate.nextLine());
                    if(numAttributePointToAllocate <= this.attributePoints) {
                        this.getAttributes().incrementAttribute(attributeToLevel, numAttributePointToAllocate);
                        this.attributePoints -= numAttributePointToAllocate;
                    } else {
                        System.out.println("Insufficient Attribute Points.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
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

        return new SingleTargetAbility("pass", 0, 0, null, "Conserve or recuperate.");
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

    // Helper to map EquipmentTypes to display names
    private static final Map<EquipmentTypes, String> equipmentTypeToDisplayName = Map.of(
        EquipmentTypes.HEAD, "Head",
        EquipmentTypes.MAINHAND, "Mainhand",
        EquipmentTypes.OFFHAND, "Offhand",
        EquipmentTypes.LEGS, "Legs",
        EquipmentTypes.TORSO, "Torso",
        EquipmentTypes.FEET, "Feet",
        EquipmentTypes.NECK, "Neck",
        EquipmentTypes.RING, "Ring"
    );

    public boolean equipItem(Equipment item) {
        String slotKey = equipmentTypeToDisplayName.get(item.getEquipmentType());

        if ("Ring".equals(slotKey)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Equip ring to [L]eft or [R]ight ring?");

            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("L") || choice.equals("LEFT")) {
                slotKey = "Left Ring";
            } else if (choice.equals("R") || choice.equals("RIGHT")) {
                slotKey = "Right Ring";
            } else {
                System.out.println("Invalid choice. Cancelling equip.");
                return false;
            }
        }

        EquipmentSlot slot = equipmentSlots.get(slotKey);

        if (slot != null && slot.canEquip(item)) {
            Equipment currentlyEquipped = slot.getEquippedItem();
            if (currentlyEquipped != null) {
                unequipItem(slotKey);
            }
            slot.setEquippedItem(item);
            addItemAttributesAndResistances(item);
            equipment.remove(item);
            return true;
        } else {
            System.out.println("Cannot equip " + item.getName() + " in slot " + slotKey);
        }
        return false;
    }

    public boolean equipItem(String itemName) {
        for (Equipment item : equipment) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                this.equipItem(item);
                return true;
            }
        }
        return false;
    }

    public void unequipItem(String slotKey) {
        EquipmentSlot slot = equipmentSlots.get(slotKey);
        if (slot != null && slot.getEquippedItem() != null) {
            Equipment item = slot.getEquippedItem();
            System.out.println("Unequipping " + item.getName());
            removeItemAttributesAndResistances(item);
            equipment.add(item);
            equipment.sort(Comparator.comparing(Equipment::getName, String.CASE_INSENSITIVE_ORDER));
            slot.unequip();
        }
    }

    public boolean unequipItemByName(String itemName) {
        for (EquipmentSlot slot : equipmentSlots.values()) {
            Equipment item = slot.getEquippedItem();
            if (item != null && item.getName().equalsIgnoreCase(itemName)) {
                unequipItem(slot.getName().toUpperCase());
                return true;
            }
        }
        return false;
    }

    public List<String> getEquippedItems() {
        List<String> equipped = new ArrayList<>();
        for (Map.Entry<String, EquipmentSlot> entry : equipmentSlots.entrySet()) {
            Equipment item = entry.getValue().getEquippedItem();
            if (item != null) {
                equipped.add(entry.getKey() + ": " + item.getName());
            }
        }
        return equipped;
    }

    private void addItemAttributesAndResistances(Equipment item) {
        this.getAttributes().add(item.getAttributes());
        this.getResistances().add(item.getResistances());
    }

    private void removeItemAttributesAndResistances(Equipment item) {
        this.getAttributes().subtract(item.getAttributes());
        this.getResistances().subtract(item.getResistances());
    }

    private void handleDefend() {
        this.changeStance(Stances.DEFENDING);
    }

    public void handleItem(String action) {
        // List Items
        if(Objects.equals(action.toLowerCase(), "item")) {
            for (int i = 0; i < this.getItems().length; i++) {
                StringUtils.stringDivider(String.valueOf(this.getItems()[i]), "-", 50);
            }
        }
        // Use getItems().Consumable
        for(int i = 0; i < this.getItems().length; i++) {
            if(Objects.equals(action.toLowerCase(), this.getItems()[i].getName().toLowerCase()) &&
                    this.getItems()[i].getQuantity() > 0 && this.getActionPoints() >= 1) {
                this.actionPoints -= 1;
                this.getItems()[i].useConsumable(this, this.getItems()[i]);
                System.out.println(this.getItems()[i]);
            }
        }
    }

    public void handleObserve(ArrayList<Character> actors) {
        String actorNames = actors.stream()
                .map(Actor::getName)
                .collect(java.util.stream.Collectors.joining(", "));

        StringUtils.twoStringDivider(this.getName() + " is observing the surrounding area.",
                actorNames, "", 50);
    }

    public void handleReaction(ArrayList<Character> actors, String reaction) {
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
                System.out.println("Invalid reaction, please try again. If you need help, type HELP.");
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
            EquipmentSlot equipmentSlot = this.equipmentSlots.get(slot.name());
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

    public void levelUp() {
        while(this.experience >= this.experienceToLevel) {
            this.level++;
            this.experience -= this.experienceToLevel;
            this.experienceToLevel = (int) (this.experienceToLevel * 1.25);
            if(this.level % 4 == 0) this.maxActionPoints++;

            StringUtils.stringDivider(this.getName() + " has leveled up!", "* ", 10);
            allocateAttributePoints();
        }
    }

    // Getters and Setters

    @Override
    public String getName() {
        return super.getName();
    }

    public String getJob() {
        return job.getName();
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public Map<String, EquipmentSlot> getEquipmentSlots() {
        return equipmentSlots;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public int getMaxActionPoints() {
        return maxActionPoints;
    }


    public Reaction[] getReactions() {
        return reactions;
    }

    public void setReactions(Reaction[] reactions) {
        this.reactions = reactions;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public Consumable[] getItems() {
        return items;
    }

    public void setItems(Consumable[] items) {
        this.items = items;
    }

    public ArrayList<Ability> getCharAbilities() {
        return CharacterAbilities.getAbilities();
    }

    public void setCharAbilities(ArrayList<Ability> charAbilities) {
        this.charAbilities = charAbilities;
    }

    public Reaction[] getCharReactions() {
        return CharacterReactions.getReactions();
    }

    public void setCharReactions(Reaction[] charReactions) {
        this.reactions = charReactions;
    }

    public Consumable[] getCharItems() {
        return CharacterItems.getItems();
    }

    public void setCharItems(Consumable[] charItems) {
        this.items = charItems;
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
