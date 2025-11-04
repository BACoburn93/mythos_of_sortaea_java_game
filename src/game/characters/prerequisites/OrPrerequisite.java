package characters.prerequisites;

import characters.Character;
import java.util.Arrays;
import java.util.List;

// Composite that requires at least one child to be met.
public class OrPrerequisite implements Prerequisite {
    private final List<Prerequisite> children;

    public OrPrerequisite(Prerequisite... children) {
        this.children = Arrays.asList(children);
    }

    @Override
    public boolean isMetBy(Character character) {
        for (Prerequisite p : children) if (p.isMetBy(character)) return true;
        return false;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder("One of: ");
        for (int i = 0; i < children.size(); i++) {
            if (i > 0) sb.append("; ");
            sb.append(children.get(i).getDescription());
        }
        return sb.toString();
    }
}