package actors.skills;

public class Nature extends Skill {
    public Nature() {
        super(SkillTypes.NATURE);
    }

    public Nature(int level) {
        super(SkillTypes.NATURE);
        this.setLevel(Math.min(level, this.maxLevel));
    }
}
