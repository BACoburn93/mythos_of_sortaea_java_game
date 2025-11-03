package actors.skills;

public class History extends Skill {
    public History() {
        super(SkillTypes.HISTORY);
    }

    public History(int level) {
        super(SkillTypes.HISTORY);
        this.setLevel(Math.min(level, this.maxLevel));
    }
}
