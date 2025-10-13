package characters;

import items.equipment.Equipment;

import java.util.ArrayList;
import java.util.List;

public class Party {
    public ArrayList<Character> characters;
    public int partySize;
    public int gold = 0;
    private List<Equipment> sharedEquipment;

    public Party(ArrayList<Character> characters) {
        this.characters = characters;
        this.partySize = characters.size();

        this.sharedEquipment = new ArrayList<>();
    }

    public Party(ArrayList<Character> characters, List<Equipment> sharedEquipment) {
        this.characters = characters;
        this.partySize = characters.size();
        this.sharedEquipment = new ArrayList<>(sharedEquipment);
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

    public ArrayList<Character> validTargetsInParty() {
        ArrayList<Character> validCharacters = new ArrayList<>();

        for (Character character : this.characters) {
            if (character.getHealth() > 0) {
                validCharacters.add(character);
            } else {
                System.out.println(character.getName() + " is unconscious and cannot be targeted.");
            }
        }

        return validCharacters;
    }

    public void addGold(int amount) {
        if (amount > 0) {
            this.gold += amount;
        }
    }

    public void removeGold(int amount) {
        if (amount > 0 && this.gold >= amount) {
            this.gold -= amount;
        }
    }

    public int getGold() {
        return gold;
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
