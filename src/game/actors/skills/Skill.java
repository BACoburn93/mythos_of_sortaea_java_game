package actors.skills;

public abstract class Skill {
    private final SkillTypes type;
    private int level;

    public Skill(SkillTypes type) {
        this.type = type;
        this.level = 1; 
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

        if(level > 5) {
            level = 5;
            return;
        }

        this.level = level;
    }
}
