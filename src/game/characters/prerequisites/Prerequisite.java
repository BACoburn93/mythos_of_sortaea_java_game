package characters.prerequisites;

import characters.Character;
import java.util.Optional;


public interface Prerequisite {
    // Returns true if the character meets the prerequisite.
    boolean isMetBy(Character character);

    // Returns a human-readable description of the prerequisite.
    String getDescription();

    // Returns an Optional containing the reason the prerequisite is not met, or empty if it is met.
    default Optional<String> unmetReason(Character character) {
        return isMetBy(character) ? Optional.empty() : Optional.of(getDescription());
    }
}