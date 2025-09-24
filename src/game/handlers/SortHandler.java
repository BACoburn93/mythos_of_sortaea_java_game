package handlers;

import abilities.Ability;
import characters.Character;
import characters.Party;
import items.consumables.Consumable;
import items.equipment.Equipment;
import ui.CombatUIStrings;
import utils.GameScanner;
import utils.InputHandler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortHandler {
    private final GameScanner scanner;
    private final InputHandler inputHandler;

    public SortHandler(GameScanner scanner, InputHandler inputHandler) {
        this.scanner = scanner;
        this.inputHandler = new InputHandler();
    }

    public enum SortTarget {
        ABILITIES,
        EQUIPMENT,
        CONSUMABLES
    }

    // Enums for sort keys
    public enum AbilitySortKey {
        NAME,
        MANA_COST,
        ACTION_COST,
        DAMAGES
    }

    public enum EquipmentSortKey {
        NAME,
        TYPE,
        VALUE
    }

    public enum ConsumableSortKey {
        NAME,
        TYPE,
        VALUE,
        QUANTITY
    }

    public enum YesOrNo {
        YES,
        NO
    }


    public void handleSortAction(Party party, Character character) {
        System.out.println("Available sort targets:");
        SortTarget[] targets = SortTarget.values();

        for (int i = 0; i < targets.length; i++) {
            System.out.printf("%d. %s%n", i + 1, targets[i].name().toUpperCase());
        }

        System.out.println("What would you like to sort?");
        String input = scanner.nextLine().trim().toLowerCase();

        SortTarget selectedTarget = null;

        try {
            int index = Integer.parseInt(input);
            if (index >= 1 && index <= targets.length) {
                selectedTarget = targets[index - 1];
            }
        } catch (NumberFormatException e) {
            for (SortTarget target : targets) {
                if (target.name().toLowerCase().startsWith(input)) { 
                    selectedTarget = target;
                    break;
                }
            }
        }

        if (selectedTarget == null) {
            System.out.println("Unknown sort target: " + input);
            return;
        }

        switch (selectedTarget) {
            case ABILITIES -> handleSortAbilities(character);
            case EQUIPMENT -> handleSortEquipment(party);
            case CONSUMABLES -> handleSortConsumables(character);
        }
    }


    public void handleSortAbilities(Character character) {
        AbilitySortKey sortKey = promptAbilitySortKey();
        boolean ascending = promptAscending();

        sortAnything(character.getAbilities(), sortKey, ascending);

        CombatUIStrings.printAbilitiesWithDivider(character.getAbilities());
    }

    private AbilitySortKey promptAbilitySortKey() {
        return inputHandler.promptEnumSelection(AbilitySortKey.class, "Sort Abilities by:");
    }

    private EquipmentSortKey promptEquipmentSortKey() {
        return inputHandler.promptEnumSelection(EquipmentSortKey.class, "Sort Equipment by:");
    }

    private ConsumableSortKey promptConsumableSortKey() {
        return inputHandler.promptEnumSelection(ConsumableSortKey.class, "Sort Consumables by:");
    }


    // TODO -- Allow selecting sort to allow for indexed input

    public void handleSortEquipment(Party party) {
        EquipmentSortKey sortKey = promptEquipmentSortKey();
        boolean ascending = promptAscending();

        sortAnything(party.getSharedEquipment(), sortKey, ascending);
    }


    public void handleSortConsumables(Character character) {
        ConsumableSortKey sortKey = promptConsumableSortKey();
        boolean ascending = promptAscending();

        List<Consumable> consumables = Arrays.asList(character.getItems());

        sortAnything(consumables, sortKey, ascending);
    }

    // Utility method to prompt for ascending/descending

    private boolean promptAscending() {
        YesOrNo result = inputHandler.promptEnumSelection(YesOrNo.class, "Ascending?");
        return result == YesOrNo.YES;
    }


    @SuppressWarnings("unchecked")
    public <T> void sortAnything(List<T> items, Object sortKey, boolean ascending) {
        if (items.isEmpty()) return;

        T firstItem = items.get(0);
        Comparator<T> comparator;

        if (firstItem instanceof Ability && sortKey instanceof AbilitySortKey) {
            comparator = (Comparator<T>) getAbilityComparator((AbilitySortKey) sortKey);
        } else if (firstItem instanceof Equipment && sortKey instanceof EquipmentSortKey) {
            comparator = (Comparator<T>) getEquipmentComparator((EquipmentSortKey) sortKey);
        } else if (firstItem instanceof Consumable && sortKey instanceof ConsumableSortKey) {
            comparator = (Comparator<T>) getConsumableComparator((ConsumableSortKey) sortKey);
        } else {
            throw new IllegalArgumentException("Unsupported list type or sort key.");
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        items.sort(comparator);
    }

    private Comparator<Ability> getAbilityComparator(AbilitySortKey sortKey) {
        return switch (sortKey) {
            case NAME -> Comparator.comparing(Ability::getName);
            case MANA_COST -> Comparator.comparingInt(Ability::getManaCost);
            case ACTION_COST -> Comparator.comparingInt(Ability::getActionCost);
            case DAMAGES -> Comparator.comparing(a -> a.getDamages().toString());
        };
    }

    private Comparator<Equipment> getEquipmentComparator(EquipmentSortKey sortKey) {
        return switch (sortKey) {
            case NAME -> Comparator.comparing(Equipment::getName);
            case VALUE -> Comparator.comparingInt(Equipment::getGoldValue); 
            case TYPE -> Comparator.comparing(e -> e.getEquipmentType().toString());
        };
    }

    private Comparator<Consumable> getConsumableComparator(ConsumableSortKey sortKey) {
        return switch (sortKey) {
            case NAME -> Comparator.comparing(Consumable::getName);
            case VALUE -> Comparator.comparingInt(Consumable::getGoldValue);
            case QUANTITY -> Comparator.comparingInt(Consumable::getQuantity);
            case TYPE -> Comparator.comparing(e -> e.getEffectType().toString());
        };
    }
}
