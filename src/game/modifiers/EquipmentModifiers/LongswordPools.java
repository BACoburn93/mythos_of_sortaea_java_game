package modifiers.EquipmentModifiers;

import java.util.Arrays;
import java.util.List;

import items.equipment.EquipmentFactory;
import items.equipment.modifiers.EquipmentPrefix;
import items.equipment.modifiers.EquipmentSuffix;
import items.equipment.modifiers.prefixes.Ancient;
import items.equipment.modifiers.prefixes.Enflamed;
import items.equipment.modifiers.suffixes.OfFortitude;
import items.equipment.modifiers.suffixes.OfTheNorthWind;

public class LongswordPools {
    public static final List<EquipmentFactory.Weighted<EquipmentPrefix>> PREFIX_POOL = Arrays.asList(
        new EquipmentFactory.Weighted<>(new Ancient(), 0.25),
        new EquipmentFactory.Weighted<>(new Enflamed(), 0.75)
    );

    public static final List<EquipmentFactory.Weighted<EquipmentSuffix>> SUFFIX_POOL = Arrays.asList(
        new EquipmentFactory.Weighted<>(new OfTheNorthWind(), 0.5),
        new EquipmentFactory.Weighted<>(new OfFortitude(), 0.5)
    );
}
