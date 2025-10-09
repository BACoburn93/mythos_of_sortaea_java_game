package items.equipment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import items.equipment.item_types.mainhand.Staff;

// TODO - populate with all equipment types
// Make sure default values are viable
// Finish getting the Factory logic working

public class EquipmentRegistry {
    private static final Map<String, Function<String, Equipment>> equipmentConstructors = new HashMap<>();
    static {
        equipmentConstructors.put("staff", s -> new Staff());
    }

    public static Function<String, Equipment> getConstructor(String type) {
        return equipmentConstructors.get(type.toLowerCase());
    }
}
