package characters;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.damages.physical.PhysicalBludgeoningDamage;
import abilities.reactions.*;
import abilities.single_target.SingleTargetAbility;
import actors.Actor;
import actors.ActorTypes;
import actors.CombatActor;
import actors.attributes.AttributeTypes;
import actors.attributes.Attributes;
import actors.resources.ResourceTypes;
import actors.stances.Stances;
import characters.jobs.Job;
import items.consumables.Consumable;
import items.equipment.Equipment;
import items.equipment.EquipmentTypes;
import items.equipment.item_types.ArmorTypes;
import items.equipment.item_types.ItemType;
import items.equipment.item_types.ShieldTypes;
import items.equipment.item_types.WeaponTypes;
import items.equipment.mainhand.Mainhand;
import utils.InputHandler;
import utils.StringUtils;

import java.util.*;

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
    private Map<EquipmentTypes, Equipment> equipmentSlots;
    private Consumable[] items;
    private ArrayList<Ability> charAbilities = new ArrayList<>();

    {
        charAbilities.add(new SingleTargetAbility("Punch",
                0, 1,
                new Damage[]{new PhysicalBludgeoningDamage(6, 12)},
                "A clenched fist, followed by a world of hurt."));

        charAbilities.add(new SingleTargetAbility("Kick",
                2, 1,
                new Damage[]{new PhysicalBludgeoningDamage(5, 18)},
                "Extend the leg to cause ample pain."));

        charAbilities.add(new SingleTargetAbility("Kill",
                0, 3,
                new Damage[]{new PhysicalBludgeoningDamage(9999, 99999)},
                "Instant death to all who fall victim."));
    }

    Reaction[] charReactions = {
            new DefendReaction(),
            new ParryReaction(),
            new ItemReaction(),
            new ObserveReaction(),
            new PassReaction()
    };

    Consumable[] charItems = {
        new Consumable("Minor Health Potion", 10, 10, ResourceTypes.HEALTH, 20),
        new Consumable("Moderate Health Potion", 30, 10, ResourceTypes.HEALTH, 50),
        new Consumable("Supreme Health Potion", 100, 10, ResourceTypes.HEALTH, 100),
        new Consumable("Full Health Potion", 300, 10, ResourceTypes.HEALTH, 99999),
            new Consumable("Minor Mana Potion", 8, 10, ResourceTypes.MANA, 20),
            new Consumable("Moderate Mana Potion", 25, 10, ResourceTypes.MANA, 50),
            new Consumable("Supreme Mana Potion", 80, 10, ResourceTypes.MANA, 100),
            new Consumable("Full Mana Potion", 250, 10, ResourceTypes.MANA, 99999),
    };

    public Character(String name, Job job) {
        super(name, job.getHealthValues(), job.getManaValues(), job.getAttributes(), job.getResistances(), job.getStatusConditions());
        this.setActorType(ActorTypes.CHARACTER);
        this.job = job;
        this.level = 1;
        this.experience = 0;
        this.actionPoints = 3;
        this.attributePoints = 0;
        this.maxActionPoints = this.actionPoints;
        this.abilities = charAbilities;
        this.reactions = charReactions;
        this.equipmentSlots = new EnumMap<>(EquipmentTypes.class);
        for (EquipmentTypes type : EquipmentTypes.values()) {
            equipmentSlots.put(type, null);
        }
        this.items = charItems;
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

        try (Scanner attributesToAllocate = new Scanner(System.in)) {
            while(this.attributePoints > 0) {
                System.out.println("Which attribute would you like to allocate points to? You have " +
                        this.attributePoints + " attribute points remaining.");
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
        }

        StringUtils.stringDivider(String.valueOf(this.getAttributes()), "=", 50);
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

    public void equipItem(Equipment item) {
        EquipmentTypes type = EquipmentTypes.valueOf(item.getEquipmentType().toString());
        Equipment currentItem = equipmentSlots.get(type);

        if (item.getQuantity() > 0) {
            if (currentItem != null) {
                unequipItem(currentItem.getEquipmentType());
            }

            if (item instanceof Mainhand mainhandItem) {
                if (mainhandItem.isTwoHanded()) {
                    Equipment offhandItem = equipmentSlots.get(EquipmentTypes.OFFHAND);
                    if (offhandItem != null) {
                        unequipItem(EquipmentTypes.OFFHAND);
                    }
                }
            }

            item.decrementQuantity();
            equipmentSlots.put(type, item);
            addItemAttributesAndResistances(item);

            if (item.getQuantity() < 1) {
                equipment.remove(item);
            }
        }
    }

    public boolean equipItem(String itemName) {
        for (Equipment item : equipment) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                this.equipItem(item);
                return true;
            }
        }
        return false; // item not found
    }

    public void unequipItem(EquipmentTypes type) {
        Equipment item = equipmentSlots.get(type);
        System.out.println("Unequipping " + item.getName());

        if (item != null) {
            removeItemAttributesAndResistances(item);
            if(item.getQuantity() <= 0) {
                equipment.add(item);
                item.setQuantity(1);
            } else {
                item.incrementQuantity();
            }
            
            equipment.sort(Comparator.comparing(Equipment::getName, String.CASE_INSENSITIVE_ORDER));
            equipmentSlots.put(type, null);
        }
    }

    public boolean unequipItem(String itemName) {
        for (Map.Entry<EquipmentTypes, Equipment> entry : equipmentSlots.entrySet()) {
            Equipment item = entry.getValue();
            if (item != null && item.getName().equalsIgnoreCase(itemName)) {
                unequipItem(entry.getKey());
                return true;
            }
        }
        return false; // item not equipped
    }


    public List<String> getEquippedItems() {
        List<String> equipped = new ArrayList<>();
        for (Map.Entry<EquipmentTypes, Equipment> entry : equipmentSlots.entrySet()) {
            Equipment item = entry.getValue();
            if (item != null) {
                equipped.add(entry.getKey().name() + ": " + item.getName());
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
                actorNames, "-", 50);
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
            default:
                System.out.println("Unhandled reaction: " + reaction);
                break;
        }
    }

    public void handleReaction(String reaction) {
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
                System.out.println(this.getName() + " is observing the surrounding area.");
                break;
            default:
                System.out.println("Unhandled reaction: " + reaction);
                break;
        }
    }

    public boolean isValidAbility(String action) {
        List<Ability> abilities = this.getAbilities();

        Ability selectedAbility = InputHandler.getItemByInput(action, abilities, Ability::getName);
        if (selectedAbility == null) return false;

        boolean armorRequired = false;
        boolean shieldRequired = false;
        boolean weaponRequired = false;

        for (Equipment equipment : this.getEquipmentSlots().values()) {
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
            Equipment equipment = this.equipmentSlots.get(slot);
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

            System.out.println("=".repeat(50));
            System.out.println(this.getName() + " has leveled up!");
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

    public Map<EquipmentTypes, Equipment> getEquipmentSlots() {
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
        return charAbilities;
    }

    public void setCharAbilities(ArrayList<Ability> charAbilities) {
        this.charAbilities = charAbilities;
    }

    public Reaction[] getCharReactions() {
        return charReactions;
    }

    public void setCharReactions(Reaction[] charReactions) {
        this.charReactions = charReactions;
    }

    public Consumable[] getCharItems() {
        return charItems;
    }

    public void setCharItems(Consumable[] charItems) {
        this.charItems = charItems;
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
        for (Map.Entry<EquipmentTypes, Equipment> entry : this.getEquipmentSlots().entrySet()) {
            String eqType = entry.getKey().name();
            String eqName = (entry.getValue() != null) ? entry.getValue().getName() : "None";
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
