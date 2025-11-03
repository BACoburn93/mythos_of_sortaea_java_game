package actors.skills;

public abstract class Skill {
    private final SkillTypes type;
    private int level;
    protected final int maxLevel;

    public Skill(SkillTypes type) {
        this.type = type;
        this.level = 1;
        this.maxLevel = 5;
    }

    public SkillTypes getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            level = 1;
            return;
        }

        if(level > maxLevel) {
            level = maxLevel;
            return;
        }

        this.level = level;
    }
}
