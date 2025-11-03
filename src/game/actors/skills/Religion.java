package actors.skills;

public class Religion extends Skill {
    public Religion() {
        super(SkillTypes.RELIGION);
    }

    public Religion(int level) {
        super(SkillTypes.RELIGION);
        this.setLevel(Math.min(level, this.maxLevel));
    }

    public double getCritChanceBonus() {
        return getLevel() * 0.04; // 4% crit chance bonus per level
    }
    
}
