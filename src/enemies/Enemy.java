package enemies;

import abilities.Ability;
import abilities.single_target.SingleTargetAbility;
import actors.ActorTypes;
import actors.CombatActor;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import characters.Character;
import characters.Party;
import ui.CombatUIStrings;

import java.util.Random;

public class Enemy extends CombatActor {
    private Ability[] abilities;
    private int experience;

    public Enemy(String name, HealthValues healthValues, ManaValues manaValues,
                 Attributes attributes, Resistances resistances, SingleTargetAbility[] abilities, int experience) {
        super(name, healthValues, manaValues, attributes, resistances);
        this.abilities = abilities;
        this.experience = experience;
        this.setActorType(ActorTypes.ENEMY);
    }

    public void chooseEnemyAbility(Party targetsToChooseFrom) {
        Party validTargets = targetsToChooseFrom.validTargetsInParty();

        boolean abilityChosen = false;
        Random random = new Random();

        double abilityRoll = random.nextDouble(1, 100);
        int targetRoll = random.nextInt(0, validTargets.partySize);

        for (Ability ability : this.abilities) {
            if (abilityRoll < (double) 100 / this.abilities.length &&
                    this.getManaValues().getValue() >= ability.getManaCost()) {
                Character target = validTargets.characters.get(targetRoll);

                boolean missedTarget = random.nextInt(100) < this.getStatusConditions().getBlind().getValue();

                if(!missedTarget) {
                    this.attack(this, target, ability);
                } else {
                    CombatUIStrings.printMissedAttack(this, target, ability);
                }

//                this.attack(this, target, ability);
                this.spendMana(ability);
                abilityChosen = true;

                CombatUIStrings.printHitPointsRemaining(target);

                break;
            } else {
                abilityRoll -= (double) 100 / this.abilities.length;
            }
        }

        if(abilityRoll <= 0 && !abilityChosen) {
            System.out.println(this.getName() + " has insufficient mana.");
        }
    }
    

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int leftWidth = 24, rightWidth = 40;
        String divider = "+--------------------------+------------------------------------------+\n";
        sb.append(divider);
        sb.append(String.format("| %-24s | %-40s |\n", "Name", this.getName()));
        sb.append(String.format("| %-24s | %-40s |\n", "Type", "Enemy"));
        sb.append(String.format("| %-24s | %-40s |\n", "Experience", this.experience));
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
    
        // Abilities
        sb.append(String.format("| %-24s | %-40s |\n", "Abilities", ""));
        if (this.abilities != null) {
            for (Ability ability : this.abilities) {
                java.util.List<String> nameLines = utils.StringUtils.wrapText(ability.getName(), leftWidth - 3);
                java.util.List<String> descLines = utils.StringUtils.wrapText(ability.getDescription(), rightWidth);
                int maxLines = Math.max(nameLines.size(), descLines.size());
                for (int i = 0; i < maxLines; i++) {
                    sb.append(String.format("|   %-22s | %-40s |\n",
                        i < nameLines.size() ? nameLines.get(i) : "",
                        i < descLines.size() ? descLines.get(i) : ""));
                }
            }
        }
        sb.append(divider);

        return sb.toString();
    }

}
