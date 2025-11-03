package actors.skills;

public class Alchemy extends Skill {
    public Alchemy() {
        super(SkillTypes.ALCHEMY);
    }

    public Alchemy(int level) {
        super(SkillTypes.ALCHEMY);
        this.setLevel(Math.min(level, this.maxLevel));
    }
    
}
