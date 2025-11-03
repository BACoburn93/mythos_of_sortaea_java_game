package actors.skills;

public class Observation extends Skill {
    public Observation() {
        super(SkillTypes.OBSERVATION);
    }
    
    public Observation(int level) {
        super(SkillTypes.OBSERVATION);
        this.setLevel(Math.min(level, this.maxLevel));
    }
}
