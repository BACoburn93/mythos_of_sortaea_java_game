package characters;

import items.equipment.Equipment;
import items.equipment.EquipmentFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Party {
    public ArrayList<Character> characters;
    public int partySize;
    private List<Equipment> sharedEquipment;
    private final Random rng = new Random();

    public Party(ArrayList<Character> characters) {
        this.characters = characters;
        this.partySize = characters.size();
        // this.sharedEquipment = new ArrayList<>(items.equipment.EquipmentDatabase.getEquipmentList());

        this.sharedEquipment = new ArrayList<>();
        populateSharedEquipmentForTesting(10);
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
        this.partySize = characters.size();
    }

    public void removeCharacter(Character character) {
        this.characters.remove(character);
        this.partySize = characters.size();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Party validTargetsInParty() {
        ArrayList<Character> validCharacters = new ArrayList<>();

        for (Character character : this.characters) {
            if (character.getHealth() > 0) {
                validCharacters.add(character);
            } else {
                System.out.println(character.getName() + " is unconscious and cannot be targeted.");
            }
        }

        return new Party(validCharacters);
    }

    private void populateSharedEquipmentForTesting(int count) {
        String[] keys = {"Staff", "Dagger", "LargeShield", "Longsword", "Longbow", "HeavyTorso", "LightTorso", "Ring", "Neck"};

        for (int i = 0; i < count; i++) {
            String key = keys[rng.nextInt(keys.length)];
            Equipment item = EquipmentFactory.INSTANCE.createRandomByKey(key, null, null);
            sharedEquipment.add(item);
        }
    }

    public List<Equipment> getSharedEquipment() {
        return sharedEquipment;
    }

    public String printPartySummary() {
        StringBuilder summary = new StringBuilder("=== Your Party ===\n");
        for (int i = 0; i < characters.size(); i++) {
            Character c = characters.get(i);
            summary.append(String.format("%d. %s - %s\n", i + 1, c.getName(), c.getJob()));
        }
        return summary.toString();
    }

    public String toString() {
        return "Party{" +
                "characters=" + characters +
                ", partySize=" + partySize +
                ", sharedEquipment=" + sharedEquipment +
                '}';
    }
}
