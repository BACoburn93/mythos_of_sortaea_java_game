package ui;

import java.util.List;

import abilities.Ability;
import abilities.damages.Damage;
import abilities.interfaces.Nameable;
import abilities.interfaces.NameableWithQuantity;
import actors.Actor;
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

    public static void appendDamageMessage(StringBuilder damageMessage, Actor attacker, Actor defender, Ability ability, Damage damage, int totalDamage, boolean isFirstDamage) {
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
        for (Ability ability : abilities) {
            StringUtils.stringDividerTop(String.valueOf(ability), "-", 50);
        }
    }

    public static void printAbilitiesWithDescription(List<Ability> abilities) {
        for (Ability ability : abilities) {
            StringUtils.stringDividerTop(ability.getName() + ": " + ability.getDescription(), "-", 50);
        }
    }

}
