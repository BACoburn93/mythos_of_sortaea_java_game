package characters;

import items.equipment.Equipment;
import java.util.ArrayList;
import java.util.List;

public class Party {
    public ArrayList<Character> characters;
    public int partySize;
    private List<Equipment> sharedEquipment;

    public Party(ArrayList<Character> characters) {
        this.characters = characters;
        this.partySize = characters.size();
        this.sharedEquipment = new ArrayList<>(items.equipment.EquipmentDatabase.getEquipmentList());
    }

    public void addCharacter(Character character) {
        this.characters.add(character);
        this.partySize = characters.size();
    }

    public void removeCharacter(Character character) {
        this.characters.remove(character);
        this.partySize = characters.size();
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

    public List<Equipment> getSharedEquipment() {
        return sharedEquipment;
    }
}
