package items.equipment.sets;

import java.util.Set;

// Implement on Equipment classes that contribute to set bonuses.
// Return tags like "leather", "bow" etc.
public interface SetTagged {
    Set<String> getSetTags();
}