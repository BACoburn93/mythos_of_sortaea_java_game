package actors.skills;

public class Arcana extends Skill {
    public Arcana() {
        super(SkillTypes.ARCANA);
    }

    public Arcana(int level) {
        super(SkillTypes.ARCANA);
        this.setLevel(Math.min(level, this.maxLevel));
    }
    
}
