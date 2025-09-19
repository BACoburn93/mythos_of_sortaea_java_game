package ui;

import java.util.List;

import abilities.Ability;
import abilities.damages.Damage;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.types.CombatActor;
import characters.Character;
import interfaces.Nameable;
import interfaces.NameableWithQuantity;
import items.equipment.Equipment;
import utils.StringUtils;

public class CombatUIStrings {

    // public static String formatEquipItemList(List<? extends NameableWithQuantity> list) {
    //     StringBuilder sb = new StringBuilder();
    //     for (int i = 0; i < list.size(); i++) {
    //         var item = list.get(i);
    //         sb.append(String.format("%d. %-20s (x%d)%n", i + 1, item.getName(), item.getQuantity()));
    //     }
    //     return sb.toString();
    // }

    public static String formatEquipItemListDetailed(List<Equipment> list) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%-3s | %-20s | %-5s | %-3s | %-10s | %-9s%n",
                "#", "Name", "Value", "Qty", "Type", "ItemType"));
        sb.append("-".repeat(80)).append("\n");

        for (int i = 0; i < list.size(); i++) {
            Equipment item = list.get(i);

            sb.append(String.format("%-3d | %-20s | %-5d | %-3d | %-10s | %-9s%n",
                    i + 1,
                    item.getName(),
                    item.getGoldValue(),
                    item.getQuantity(),
                    item.getEquipmentType(),
                    item.getItemType()
            ));

            // Attributes line
            String attrLine = formatAttributes(item.getAttributes());
            if (!attrLine.isEmpty()) {
                sb.append("    | Attributes:   ").append(attrLine).append("\n");
            }

            // Resistances line
            String resLine = formatResistances(item.getResistances());
            if (!resLine.isEmpty()) {
                sb.append("    | Resistances:  ").append(resLine).append("\n");
            }

            // Spacer between items
            sb.append("-".repeat(80)).append("\n");
        }

        return sb.toString();
    }

    private static String formatAttributes(Attributes attrs) {
        StringBuilder sb = new StringBuilder();

        if (attrs.getStrength().getValue() != 0) sb.append(formatAttr("STR", attrs.getStrength().getValue()));
        if (attrs.getAgility().getValue() != 0) sb.append(formatAttr("AGI", attrs.getAgility().getValue()));
        if (attrs.getKnowledge().getValue() != 0) sb.append(formatAttr("KNOW", attrs.getKnowledge().getValue()));
        if (attrs.getDefense().getValue() != 0) sb.append(formatAttr("DEF", attrs.getDefense().getValue()));
        if (attrs.getResilience().getValue() != 0) sb.append(formatAttr("RES", attrs.getResilience().getValue()));
        if (attrs.getSpirit().getValue() != 0) sb.append(formatAttr("SPIR", attrs.getSpirit().getValue()));
        if (attrs.getLuck().getValue() != 0) sb.append(formatAttr("LUCK", attrs.getLuck().getValue()));

        return sb.toString().replaceAll(", $", ""); // Trim trailing comma
    }


    private static String formatResistances(Resistances res) {
        StringBuilder sb = new StringBuilder();

        if (res.getBludgeoning().getValue() != 0) sb.append(formatRes("BLUDGE", res.getBludgeoning().getValue()));
        if (res.getPiercing().getValue() != 0) sb.append(formatRes("PIERC", res.getPiercing().getValue()));
        if (res.getSlashing().getValue() != 0) sb.append(formatRes("SLASH", res.getSlashing().getValue()));
        if (res.getEarth().getValue() != 0) sb.append(formatRes("EARTH", res.getEarth().getValue()));
        if (res.getFire().getValue() != 0) sb.append(formatRes("FIRE", res.getFire().getValue()));
        if (res.getIce().getValue() != 0) sb.append(formatRes("ICE", res.getIce().getValue()));
        if (res.getLightning().getValue() != 0) sb.append(formatRes("LGTN", res.getLightning().getValue()));
        if (res.getVenom().getValue() != 0) sb.append(formatRes("VENOM", res.getVenom().getValue()));
        if (res.getWater().getValue() != 0) sb.append(formatRes("WATER", res.getWater().getValue()));
        if (res.getWind().getValue() != 0) sb.append(formatRes("WIND", res.getWind().getValue()));
        if (res.getDarkness().getValue() != 0) sb.append(formatRes("DARK", res.getDarkness().getValue()));
        if (res.getLight().getValue() != 0) sb.append(formatRes("LIGHT", res.getLight().getValue()));

        return sb.toString().replaceAll(", $", ""); // Trim trailing comma
    }


    private static String formatAttr(String name, int value) {
        return String.format("%+d %s, ", value, name);
    }

    private static String formatRes(String name, int value) {
        return String.format("%+d %s, ", value, name);
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
