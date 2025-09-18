package ui;

import java.util.List;

import abilities.Ability;
import abilities.damages.Damage;
import actors.CombatActor;
import characters.Character;
import interfaces.Nameable;
import interfaces.NameableWithQuantity;
import utils.StringUtils;

public class CombatUIStrings {

    public static String formatEquipItemList(List<? extends NameableWithQuantity> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            var item = list.get(i);
            sb.append(String.format("%d. %-20s (x%d)%n", i + 1, item.getName(), item.getQuantity()));
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

    // Formats a command and its description into a consistent layout
    public static String formatKeyValue(String command, String description) {
        return String.format("%-20s %s%n", command, description);
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
            StringUtils.stringDividerTop(header, "-", 50);
            idx++;
        }
    }

    public static void printAbilitiesWithDescription(List<Ability> abilities) {
        int idx = 1;
        for (Ability ability : abilities) {
            String header = idx + ". " + ability.getName() + ": " + ability.getDescription();
            StringUtils.stringDividerTop(header, "-", 50);
            idx++;
        }
    }

    public static void printActionPoints(Character character) {
        StringUtils.stringDivider(character.getActionPoints() + "/" +
        character.getMaxActionPoints() + " Action Points.",
        "-", 50);
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
        System.out.println(actor.getName() + " has " + actor.getHealth() + " hit points remaining.");
    }

    public static void printMissedAttack(CombatActor attacker, CombatActor defender, Ability ability) {
        // String article = StringUtils.startsWithVowel(ability.getName()) ? "an " : "a ";
        System.out.println(attacker.getName() + "'s " + ability.getName() + " missed " + defender.getName() + "!");
    }

}
