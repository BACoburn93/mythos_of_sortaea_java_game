package characters.prerequisites;

import characters.Character;
import java.util.Arrays;
import java.util.List;

// Composite that requires all child prerequisites to be met. 
public class AndPrerequisite implements Prerequisite {
    private final List<Prerequisite> children;

    public AndPrerequisite(Prerequisite... children) {
        this.children = Arrays.asList(children);
    }

    @Override
    public boolean isMetBy(Character character) {
        for (Prerequisite p : children) if (!p.isMetBy(character)) return false;
        return true;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("All of: ");
        for (int i = 0; i < children.size(); i++) {
            if (i > 0) sb.append("; ");
            sb.append(children.get(i).getDescription());
        }
        return sb.toString();
    }
}