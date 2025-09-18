package items.equipment;

import actors.attributes.Attributes;
import actors.resistances.Resistances;
import items.equipment.mainhand.Dagger;
import items.equipment.mainhand.Longbow;
import items.equipment.mainhand.Staff;
import items.equipment.offhand.LargeShield;
import items.equipment.torso.HeavyTorso;
import items.equipment.torso.LightTorso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquipmentDatabase {
    private static final List<Equipment> EQUIPMENT_LIST = new ArrayList<>();

    static {
        EQUIPMENT_LIST.add(new HeavyTorso(
                "Armor of the Damned",
                10000,
                new Attributes(20, 20, 20, 20, 20, 20, 20),
                new Resistances(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        ));
        EQUIPMENT_LIST.add(new LightTorso(
                "Leather Armor",
                10,
                3,
                new Attributes(2, 2, 2, 2, 2, 2, 2),
                new Resistances(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        ));
        EQUIPMENT_LIST.add(new Staff(
                "Gods Finger",
                1000,
                new Attributes(0, 0, 100, 20, 20, 20, 20),
                new Resistances(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)
        ));
        EQUIPMENT_LIST.add(new Dagger(
                "Riteknife",
                100,
                new Attributes(0, 35, 0, 0, 0, 0, 10),
                new Resistances(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        ));
        EQUIPMENT_LIST.add(new Longbow(
                "Dragon Longbow",
                800,
                new Attributes(0, 60, 0, 0, 0, 0, 5),
                new Resistances(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        ));
        EQUIPMENT_LIST.add(new LargeShield(
                "Tower Shield",
                200,
                new Attributes(0, 0, 0, 30, 20, 0, 0),
                new Resistances(10, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        ));

        // EQUIPMENT_LIST.sort(Comparator.comparing(Equipment::getName));
    }

    public static List<Equipment> getEquipmentList() {
        return EQUIPMENT_LIST;
    }
}
