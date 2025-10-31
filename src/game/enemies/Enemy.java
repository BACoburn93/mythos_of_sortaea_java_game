package enemies;

import abilities.Ability;
import actors.ActorTypes;
import actors.attributes.Attributes;
import actors.resistances.Resistances;
import actors.resources.HealthValues;
import actors.resources.ManaValues;
import actors.types.CombatActor;
import characters.Character;
import enemies.modifiers.EnemyPrefix;
import enemies.modifiers.EnemySuffix;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends CombatActor {
    public List<Ability> abilities;
    private int experience;

    private String typeKey;
    private EnemyPrefix prefix;
    private EnemySuffix suffix;
    
    private int spawnWeight = 1;
    public int actionsPerTurn = 1;

    public Enemy(String name, HealthValues healthValues, ManaValues manaValues,
                 Attributes attributes, Resistances resistances, Ability[] baseAbilities, int level) {
        super(name, healthValues, manaValues, attributes, resistances, level);
        this.setActorType(ActorTypes.ENEMY);
        this.abilities = new ArrayList<>(List.of(baseAbilities));
        this.experience = getLevel() * 5;
    }

    public Enemy(String name, HealthValues healthValues, ManaValues manaValues,
                 Attributes attributes, Resistances resistances, Ability[] baseAbilities, int level, int spawnWeight) {
        super(name, healthValues, manaValues, attributes, resistances, level);
        this.setActorType(ActorTypes.ENEMY);
        this.abilities = new ArrayList<>(List.of(baseAbilities));
        this.spawnWeight = spawnWeight;
        this.experience = this.getSpawnWeight() > 0 ? getLevel() * (5 * (this.spawnWeight * this.spawnWeight)) : getLevel() * 5;
    }

    // Getters and Setters
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public List<Ability> getAbilities() { return abilities; }
    public void setAbilities(List<Ability> abilities) { this.abilities = abilities; }

    public String getTypeKey() {
        if (typeKey != null && !typeKey.isEmpty()) return typeKey;
        return this.getClass().getSimpleName().toLowerCase();
    }

    protected void setTypeKey(String key) {
        this.typeKey = (key == null) ? null : key.trim().toLowerCase();
    }

    public EnemyPrefix getPrefix() { return prefix; }
    public void setPrefix(EnemyPrefix prefix) { this.prefix = prefix; }

    public EnemySuffix getSuffix() { return suffix; }
    public void setSuffix(EnemySuffix suffix) { this.suffix = suffix; }

    public int getSpawnWeight() { return spawnWeight; }
    public void setSpawnWeight(int weight) { this.spawnWeight = Math.max(1, weight); }

    public int getActionsPerTurn() { return actionsPerTurn; }
    public void setActionsPerTurn(int actions) { this.actionsPerTurn = Math.max(1, actions); }

    public void addAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            abilities.add(ability);
        }
    }

    public void performAbilityNoConsume(Ability ability, Character target) {
        if (ability == null || target == null) return;

        boolean missedTarget = new java.util.Random().nextInt(100) < this.getStatusConditions().getBlind().getValue();

        if (!missedTarget) {
            this.attack(target, ability);
        } else {
            ui.CombatUIStrings.printMissedAttack(this, target, ability);
        }

        ui.CombatUIStrings.printHitPointsRemaining(target);
    }   

    public void updateLevelAndExperience(double level) {
        // Convert double to int to allow for multiplicative level setting without changing level to be a double
        int levelInt = (int) Math.floor(level);
        this.setLevel(this.level + levelInt);

        if (spawnWeight > 0) {
            this.experience = getLevel() * (5 * (this.spawnWeight * this.spawnWeight));
        } 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int leftWidth = 24, rightWidth = 40;
        String divider = "+--------------------------+------------------------------------------+\n";
        sb.append(divider);
        sb.append(String.format("| %-24s | %-40s |\n", "Name", this.getName()));
        sb.append(String.format("| %-24s | %-40s |\n", "Type", this.getTypeKey()));
        sb.append(String.format("| %-24s | %-40s |\n", "Species", this.getSpeciesTypes()));
        sb.append(String.format("| %-24s | %-40s |\n", "Experience", this.experience));
        sb.append(divider);
    
        // Attributes
        sb.append(String.format("| %-24s | %-40s |\n", "Attributes", ""));
        Attributes attrs = this.getAttributes();
        sb.append(String.format("|   %-22s | %-40s |\n", "Strength", (int) attrs.getStrength().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Agility", (int) attrs.getAgility().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Knowledge", (int) attrs.getKnowledge().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Defense", (int) attrs.getDefense().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Resilience", (int) attrs.getResilience().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Spirit", (int) attrs.getSpirit().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Luck", (int) attrs.getLuck().getValue()));
        sb.append(divider);

        // Resistances
        sb.append(String.format("| %-24s | %-40s |\n", "Resistances", ""));
        Resistances res = this.getResistances();
        sb.append(String.format("|   %-22s | %-40s |\n", "Bludgeoning", (int) res.getBludgeoning().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Slashing", (int) res.getSlashing().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Piercing", (int) res.getPiercing().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Fire", (int) res.getFire().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Ice", (int) res.getIce().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Lightning", (int) res.getLightning().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Water", (int) res.getWater().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Venom", (int) res.getVenom().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Earth", (int) res.getEarth().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Air", (int) res.getWind().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Light", (int) res.getLight().getValue()));
        sb.append(String.format("|   %-22s | %-40s |\n", "Darkness", (int) res.getDarkness().getValue()));


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
