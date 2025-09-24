package ui;

import java.util.ArrayList;
import java.util.List;

import abilities.Ability;
import abilities.damages.Damage;
import actors.types.CombatActor;
import characters.Character;
import characters.jobs.Job;
import interfaces.Nameable;
import items.equipment.Equipment;
import status_conditions.StatusCondition;
import status_conditions.StatusConditions;
import utils.StringUtils;

public class CombatUIStrings {

    public static String formatEquipItemList(List<Equipment> list) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-3s | %-20s | %-5s | %-3s | %-10s | %-9s%n",
                "#", "Name", "Value", "Qty", "Type", "ItemType"));
        sb.append("-".repeat(80)).append("\n");

        for (int i = 0; i < list.size(); i++) {
            Equipment item = list.get(i);

            sb.append(String.format("%-3d | %-20s | %-5s | %-3d | %-10s | %-9s%n",
                    i + 1,
                    item.getName(),
                    StringUtils.formatInt(item.getGoldValue()),
                    item.getQuantity(),
                    item.getEquipmentType(),
                    item.getItemType()
            ));

            // Attributes line
            String attrLine = StringUtils.formatAttributes(item.getAttributes());
            if (!attrLine.isEmpty()) {
                sb.append("    | Attributes:   ").append(attrLine.replaceAll("\\.00", "")).append("\n");
            }

            String resLine = StringUtils.formatResistances(item.getResistances());
            if (!resLine.isEmpty()) {
                sb.append("    | Resistances:  ").append(resLine.replaceAll("\\.00", "")).append("\n");
            }

            // Spacer between items
            sb.append("-".repeat(80)).append("\n");
        }

        return sb.toString();
    }

    public static String formatTargetEnemyList(List<? extends Nameable> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            var item = list.get(i);
            sb.append(String.format("%d. %-20s%n", i + 1, item.getName()));
        }
        return sb.toString();
    }

    
    public static String formatJobList(List<Job> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            var job = list.get(i);
            sb.append(String.format("%d. %-20s%n", i + 1, job.getName()));
        }
        return sb.toString();
    }

    // Formats a command and its description into a consistent layout
    public static String formatKeyValue(String command, String description) {
        return String.format("%-20s %s%n", command, description);
    }

    public static void formatPartyStat(ArrayList<Character> partyCharacters) {
        System.out.println(" ");
        // Print Name row
        for (Character c : partyCharacters) {
            System.out.printf("%-15s", c.getName());
        }
        System.out.println();

        // Print HP row
        for (Character c : partyCharacters) {
            System.out.printf("HP: %-11s", StringUtils.formatInt(c.getHealth()) + "/" + StringUtils.formatInt(c.getHealthValues().getMaxValue()));
        }
        System.out.println();

        // Print MP row
        for (Character c : partyCharacters) {
            System.out.printf("MP: %-11s", StringUtils.formatInt(c.getManaValues().getValue()) + "/" + StringUtils.formatInt(c.getManaValues().getMaxValue()));
        }
        System.out.println();

        // Print AP row
        for (Character c : partyCharacters) {
            System.out.printf("AP: %-11s", c.getActionPoints() + "/" + c.getMaxActionPoints());
        }
        System.out.println();
    }

    // Appends a detailed damage message to the provided StringBuilder
    public static void appendDamageMessage(StringBuilder damageMessage, CombatActor attacker, CombatActor defender, Ability ability, Damage damage, int totalDamage, boolean isFirstDamage) {
        String article = StringUtils.startsWithVowel(ability.getName()) ? "an " : "a ";

        if (isFirstDamage) {
            damageMessage.append(defender.getName())
                    .append(" was hit by ")
                    .append(article)
                    .append(ability.getName())
                    .append(" from ")
                    .append(attacker.getName())
                    .append(" and received: | ")
                    .append(totalDamage)
                    .append(" ")
                    .append(damage.getDamageClassification())
                    .append(" ")
                    .append(damage.getDamageType())
                    .append(" damage | ");
        } else {
            damageMessage.append(totalDamage)
                    .append(" ")
                    .append(damage.getDamageClassification())
                    .append(" ")
                    .append(damage.getDamageType())
                    .append(" damage | ");
        }
    }

    public static void printAbilitiesWithDivider(List<Ability> abilities) {
        int idx = 1;
        for (Ability ability : abilities) {
            String header = idx + ". " + String.valueOf(ability); 
            StringUtils.stringDividerTop(header, "-", 80);
            idx++;
        }
    }

    public static void printAbilityPointUsage(Character character, Ability chosenAbility) {
        if(chosenAbility != null) {
            if (character.getActionPoints() <= 0) {
                System.out.println("No Ability Points remaining, ending turn.");
            } else if (!character.canUseAbility(chosenAbility)) {
                System.out.println("Insufficient Mana, please use another ability.");
            } else {
                System.out.println("Insufficient Ability Points, please use another ability.");
            }
        } else {
            if (character.getActionPoints() <= 0) {
                System.out.println("No Ability Points remaining, ending turn.");
            }
        }
    }

    public static void printHitPointsRemaining(CombatActor actor) {
        System.out.println(actor.getName() + " has " + StringUtils.formatInt(actor.getHealth()) + " hit points remaining.");
    }

    public static void printMissedAttack(CombatActor attacker, CombatActor defender, Ability ability) {
        // String article = StringUtils.startsWithVowel(ability.getName()) ? "an " : "a ";
        System.out.println(attacker.getName() + "'s " + ability.getName() + " missed " + defender.getName() + "!");
    }

    public static void printCombatActorStats(CombatActor combatActor) {
        StringUtils.stringDivider(combatActor.getName() + "'s Stats", "", 50);

        // Health and Mana
        System.out.println("Health:       " + StringUtils.formatInt(combatActor.getHealthValues().getValue()) + " / " + StringUtils.formatInt(combatActor.getHealthValues().getMaxValue()));
        System.out.println("Mana:         " + StringUtils.formatInt(combatActor.getManaValues().getValue()) + " / " + StringUtils.formatInt(combatActor.getManaValues().getMaxValue()));

        // Attributes and Resistances
        System.out.println("Attributes:   " + StringUtils.formatAttributes(combatActor.getAttributes()).replaceAll("\\.00", ""));
        System.out.println("Resistances:  " + StringUtils.formatResistances(combatActor.getResistances()).replaceAll("\\.00", ""));

        // Status Conditions
        StatusConditions status = combatActor.getStatusConditions();
        if (status != null) {
            System.out.println("\nStatus Effects:");
            printStatusCondition("Bleed", status.getBleed());
            printStatusCondition("Blind", status.getBlind());
            printStatusCondition("Burn", status.getBurn());
            printStatusCondition("Confused", status.getConfused());
            printStatusCondition("Dry", status.getDry());
            printStatusCondition("Envenom", status.getEnvenom());
            printStatusCondition("Fear", status.getFear());
            printStatusCondition("Freeze", status.getFreeze());
            printStatusCondition("Manipulate", status.getManipulate());
            printStatusCondition("Paralyze", status.getParalyze());
            printStatusCondition("Poison", status.getPoison());
            printStatusCondition("Rot", status.getRot());
            printStatusCondition("Sick", status.getSick());
            printStatusCondition("Slow", status.getSlow());
            printStatusCondition("Stun", status.getStun());
            printStatusCondition("Weak", status.getWeak());
            printStatusCondition("Wet", status.getWet());
        }
    }

    private static void printStatusCondition(String name, StatusCondition condition) {
        if (condition != null) {
            System.out.printf("  - %-12s | Value: %-3s | Resistance: %-5s | Duration: %d turns%n",
                    name,
                    StringUtils.formatInt(condition.getValue()),
                    StringUtils.formatInt(condition.getResistance()),
                    condition.getDuration());
        }
    }

    public static <T> void displayPaginatedList(List<T> items, int pageSize, java.util.Scanner scanner, java.util.function.Function<T, String> stringifier) {
        int page = 0;
        int totalPages = (int) Math.ceil(items.size() / (double) pageSize);

        while (true) {
            int start = page * pageSize;
            int end = Math.min(start + pageSize, items.size());
            System.out.println("");
            System.out.println("Page " + (page + 1) + " of " + totalPages);

            for (int i = start; i < end; i++) {
                System.out.println((i + 1) + ". " + stringifier.apply(items.get(i)));
            }

            if (totalPages <= 1) break;

            System.out.println("[N]ext page | [P]revious page | [I]ndex (jump to page) | Any other key to proceed");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("n") && page < totalPages - 1) {
                page++;
            } else if (input.equals("p") && page > 0) {
                page--;
            } else if (input.equals("i")) {
                System.out.print("Enter page number (1-" + totalPages + "): ");
                String pageInput = scanner.nextLine().trim();
                try {
                    int pageNum = Integer.parseInt(pageInput);
                    if (pageNum >= 1 && pageNum <= totalPages) {
                        page = pageNum - 1;
                    } else {
                        System.out.println("Invalid page number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else {
                break;
            }
        }
    }

}