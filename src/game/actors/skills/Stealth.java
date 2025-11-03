package actors.skills;

public class Stealth extends Skill {
    public Stealth() {
        super(SkillTypes.STEALTH);
    }
    
    public Stealth(int level) {
        super(SkillTypes.STEALTH);
        this.setLevel(Math.min(level, this.maxLevel));
    }
}
