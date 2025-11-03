package actors.skills;

public class Skills {
    private Alchemy alchemy;
    private Arcana arcana;
    private History history;
    private Nature nature;
    private Observation observation;
    private Stealth stealth;
    private Religion religion;

    public Skills() {
        this.alchemy = new Alchemy();
        this.arcana = new Arcana();
        this.history = new History();
        this.nature = new Nature();
        this.observation = new Observation();
        this.stealth = new Stealth();
        this.religion = new Religion();
    }

    public Alchemy getAlchemy() { return alchemy; }
    public Arcana getArcana() { return arcana; }
    public History getHistory() { return history; }
    public Nature getNature() { return nature; }
    public Observation getObservation() { return observation; }
    public Stealth getStealth() { return stealth; }
    public Religion getReligion() { return religion; }
}