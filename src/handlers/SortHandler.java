package handlers;

import abilities.Ability;
import characters.Character;
import characters.Party;
import items.consumables.Consumable;
import items.equipment.Equipment;
import ui.CombatUIStrings;
import utils.GameScanner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortHandler {
    private final GameScanner scanner;

    public SortHandler(GameScanner scanner) {
        this.scanner = scanner;
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
        NAME
    }

    public enum ConsumableSortKey {
        NAME
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
        System.out.println("Sort Abilities by: Name, Mana Cost, Action Cost, Damages");
        String input = scanner.nextLine().trim().toLowerCase();

        return switch (input) {
            case "name" -> AbilitySortKey.NAME;
            case "mana cost" -> AbilitySortKey.MANA_COST;
            case "action cost" -> AbilitySortKey.ACTION_COST;
            case "damages" -> AbilitySortKey.DAMAGES;
            default -> {
                System.out.println("Invalid sort key. Defaulting to Name.");
                yield AbilitySortKey.NAME;
            }
        };
    }

    // TODO -- Allow selecting sort to allow for indexed input

    public void handleSortEquipment(Party party) {
        EquipmentSortKey sortKey = promptEquipmentSortKey();
        boolean ascending = promptAscending();

        sortAnything(party.getSharedEquipment(), sortKey, ascending);

        // CombatUIStrings.printEquipmentWithDivider(character.getEquipment());
    }

    private EquipmentSortKey promptEquipmentSortKey() {
        System.out.println("Sort Equipment by: Name");
        String input = scanner.nextLine().trim().toLowerCase();

        if ("name".equals(input)) {
            return EquipmentSortKey.NAME;
        } else {
            System.out.println("Invalid sort key. Defaulting to Name.");
            return EquipmentSortKey.NAME;
        }
    }

    public void handleSortConsumables(Character character) {
        ConsumableSortKey sortKey = promptConsumableSortKey();
        boolean ascending = promptAscending();

        List<Consumable> consumables = Arrays.asList(character.getItems());

        sortAnything(consumables, sortKey, ascending);

        // CombatUIStrings.printConsumablesWithDivider(consumables);
    }

    private ConsumableSortKey promptConsumableSortKey() {
        System.out.println("Sort Consumables by: Name");
        String input = scanner.nextLine().trim().toLowerCase();

        if ("name".equals(input)) {
            return ConsumableSortKey.NAME;
        } else {
            System.out.println("Invalid sort key. Defaulting to Name.");
            return ConsumableSortKey.NAME;
        }
    }

    // Utility method to prompt for ascending/descending

    private boolean promptAscending() {
        System.out.println("Ascending? (yes/no):");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
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
        };
    }

    private Comparator<Consumable> getConsumableComparator(ConsumableSortKey sortKey) {
        return switch (sortKey) {
            case NAME -> Comparator.comparing(Consumable::getName);
        };
    }
}
