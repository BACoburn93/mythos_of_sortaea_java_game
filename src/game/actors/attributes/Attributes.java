package actors.attributes;



public class Attributes {
    private Attribute strength;
    private Attribute agility;
    private Attribute knowledge;
    private Attribute defense;
    private Attribute resilience;
    private Attribute spirit;
    private Attribute luck;

    public Attributes(Attribute strength, Attribute agility, Attribute knowledge, Attribute defense,
                      Attribute resilience, Attribute spirit, Attribute luck) {
        this.strength = strength;
        this.agility = agility;
        this.knowledge = knowledge;
        this.defense = defense;
        this.resilience = resilience;
        this.spirit = spirit;
        this.luck = luck;
    }

    public Attributes(double strength, double agility, double knowledge, double defense, double resilience, double spirit, double luck) {
        this.strength = new StrengthAttribute(strength);
        this.agility = new AgilityAttribute(agility);
        this.knowledge = new KnowledgeAttribute(knowledge);
        this.defense = new DefenseAttribute(defense);
        this.resilience = new ResilienceAttribute(resilience);
        this.spirit = new SpiritAttribute(spirit);
        this.luck = new LuckAttribute(luck);
    }

    public Attributes() {
        this(10.0, 10.0, 10.0, 10.0, 10.0, 10.0, 10.0);
    }

    public Attribute getStrength() { return strength; }
    public void setStrength(Attribute strength) { this.strength = strength; }

    public Attribute getAgility() { return agility; }
    public void setAgility(Attribute agility) { this.agility = agility; }

    public Attribute getKnowledge() { return knowledge; }
    public void setKnowledge(Attribute knowledge) { this.knowledge = knowledge; }

    public Attribute getDefense() { return defense; }
    public void setDefense(Attribute defense) { this.defense = defense; }

    public Attribute getResilience() { return resilience; }
    public void setResilience(Attribute resilience) { this.resilience = resilience; }

    public Attribute getSpirit() { return spirit; }
    public void setSpirit(Attribute spirit) { this.spirit = spirit; }

    public Attribute getLuck() { return luck; }
    public void setLuck(Attribute luck) { this.luck = luck; }

    public void incrementAttribute(String attribute, double value) {
        switch (attribute.toUpperCase()) {
            case "STRENGTH" -> setStrength(new StrengthAttribute(getStrength().getValue() + value));
            case "AGILITY" -> setAgility(new AgilityAttribute(getAgility().getValue() + value));
            case "KNOWLEDGE" -> setKnowledge(new KnowledgeAttribute(getKnowledge().getValue() + value));
            case "DEFENSE" -> setDefense(new DefenseAttribute(getDefense().getValue() + value));
            case "RESILIENCE" -> setResilience(new ResilienceAttribute(getResilience().getValue() + value));
            case "SPIRIT" -> setSpirit(new SpiritAttribute(getSpirit().getValue() + value));
            case "LUCK" -> setLuck(new LuckAttribute(getLuck().getValue() + value));
            default -> { }
        }
        System.out.println(attribute + " " + value);
    }

    // Increase methods
    public void increaseStrength(double amount) { strength.setValue(strength.getValue() + amount); }
    public void increaseAgility(double amount) { agility.setValue(agility.getValue() + amount); }
    public void increaseKnowledge(double amount) { knowledge.setValue(knowledge.getValue() + amount); }
    public void increaseDefense(double amount) { defense.setValue(defense.getValue() + amount); }
    public void increaseResilience(double amount) { resilience.setValue(resilience.getValue() + amount); }
    public void increaseSpirit(double amount) { spirit.setValue(spirit.getValue() + amount); }
    public void increaseLuck(double amount) { luck.setValue(luck.getValue() + amount); }

    // Decrease methods
    public void decreaseStrength(double amount) { strength.setValue(strength.getValue() - amount); }
    public void decreaseAgility(double amount) { agility.setValue(agility.getValue() - amount); }
    public void decreaseKnowledge(double amount) { knowledge.setValue(knowledge.getValue() - amount); }
    public void decreaseDefense(double amount) { defense.setValue(defense.getValue() - amount); }
    public void decreaseResilience(double amount) { resilience.setValue(resilience.getValue() - amount); }
    public void decreaseSpirit(double amount) { spirit.setValue(spirit.getValue() - amount); }
    public void decreaseLuck(double amount) { luck.setValue(luck.getValue() - amount); }

    // Duplicate add(Attributes other) method removed.

    // Removed duplicate subtract(Attributes other) method.

    // Remove duplicate toString() method here.

    public void increaseDefense(int amount) {
        defense.setValue(defense.getValue() + amount);
    }

    public void increaseResilience(int amount) {
        resilience.setValue(resilience.getValue() + amount);
    }

    public void increaseSpirit(int amount) {
        spirit.setValue(spirit.getValue() + amount);
    }

    public void increaseLuck(int amount) {
        luck.setValue(luck.getValue() + amount);
    }

    // Decrease methods
    public void decreaseStrength(int amount) {
        strength.setValue(strength.getValue() - amount);
    }

    public void decreaseAgility(int amount) {
        agility.setValue(agility.getValue() - amount);
    }

    public void decreaseKnowledge(int amount) {
        knowledge.setValue(knowledge.getValue() - amount);
    }

    public void decreaseDefense(int amount) {
        defense.setValue(defense.getValue() - amount);
    }

    public void decreaseResilience(int amount) {
        resilience.setValue(resilience.getValue() - amount);
    }

    public void decreaseSpirit(int amount) {
        spirit.setValue(spirit.getValue() - amount);
    }

    public void decreaseLuck(int amount) {
        luck.setValue(luck.getValue() - amount);
    }


    public void add(Attributes other) {
        this.strength.setValue(this.strength.getValue() + other.strength.getValue());
        this.agility.setValue(this.agility.getValue() + other.agility.getValue());
        this.knowledge.setValue(this.knowledge.getValue() + other.knowledge.getValue());
        this.defense.setValue(this.defense.getValue() + other.defense.getValue());
        this.resilience.setValue(this.resilience.getValue() + other.resilience.getValue());
        this.spirit.setValue(this.spirit.getValue() + other.spirit.getValue());
        this.luck.setValue(this.luck.getValue() + other.luck.getValue());
    }

    public void subtract(Attributes other) {
        this.strength.setValue(this.strength.getValue() - other.strength.getValue());
        this.agility.setValue(this.agility.getValue() - other.agility.getValue());
        this.knowledge.setValue(this.knowledge.getValue() - other.knowledge.getValue());
        this.defense.setValue(this.defense.getValue() - other.defense.getValue());
        this.resilience.setValue(this.resilience.getValue() - other.resilience.getValue());
        this.spirit.setValue(this.spirit.getValue() - other.spirit.getValue());
        this.luck.setValue(this.luck.getValue() - other.luck.getValue());
    }

    @Override
    public String toString() {
        return strength + ", " + agility + ", " + knowledge + ", " + defense
                + ", " + resilience + ", " + spirit + ", " + luck;
    }
}
