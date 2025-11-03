package actors.skills;

import java.lang.reflect.Method;

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

    public Skills(int alchemyLevel, int arcanaLevel, int historyLevel,
                  int natureLevel, int observationLevel, int religionLevel,
                  int stealthLevel) {
        this.alchemy = new Alchemy(alchemyLevel);
        this.arcana = new Arcana(arcanaLevel);
        this.history = new History(historyLevel);
        this.nature = new Nature(natureLevel);
        this.observation = new Observation(observationLevel);
        this.religion = new Religion(religionLevel);
        this.stealth = new Stealth(stealthLevel);
    }

    public Alchemy getAlchemy() { return alchemy; }
    public Arcana getArcana() { return arcana; }
    public History getHistory() { return history; }
    public Nature getNature() { return nature; }
    public Observation getObservation() { return observation; }
    public Stealth getStealth() { return stealth; }
    public Religion getReligion() { return religion; }

    public int getLevelForTag(String tag) {
        if (tag == null || tag.trim().isEmpty()) return 0;
        String name = tag.trim();
        String getter = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        try {
            Method m = this.getClass().getMethod(getter);
            Object skillObj = m.invoke(this);
            if (skillObj == null) return 0;
            Method gl = skillObj.getClass().getMethod("getLevel");
            Object lvl = gl.invoke(skillObj);
            if (lvl instanceof Number) return ((Number) lvl).intValue();
        } catch (Throwable ignored) {}
        return 0;
    }

    public double getCritChanceBonusForTags(Iterable<String> tags) {
        if (tags == null) return 0.0;
        final double PER_LEVEL = 0.04; // 4% per skill level
        int totalLevels = 0;
        for (String t : tags) {
            if (t == null) continue;
            totalLevels += getLevelForTag(t);
        }
        return totalLevels * PER_LEVEL;
    }
}