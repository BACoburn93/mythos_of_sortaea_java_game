package actors.skills;

public abstract class Skill {
    private final SkillTypes type;
    private int level;
    protected final int minLevel = 0;
    protected final int maxLevel;

    public Skill(SkillTypes type) {
        this.type = type;
        this.level = minLevel;
        this.maxLevel = 5;
    }

    public SkillTypes getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < minLevel) {
            level = minLevel;
            return;
        }

        if(level > maxLevel) {
            level = maxLevel;
            return;
        }

        this.level = level;
    }
}
