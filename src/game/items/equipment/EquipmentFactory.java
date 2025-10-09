package items.equipment;

import utils.Factory;
import utils.FlavorUtils;
import items.equipment.modifiers.Prefix;
import items.equipment.modifiers.Suffix;

import java.util.List;
import java.util.function.Supplier;
import java.util.Random;

public final class EquipmentFactory implements Factory<Equipment> {
    public static final EquipmentFactory INSTANCE = new EquipmentFactory();
    private final Random rng = new Random();

    private EquipmentFactory() {}

    @Override
    public Equipment create(Supplier<Equipment> ctor, Object prefix, Object suffix) {
        Equipment item = ctor.get();
        return FlavorUtils.applyFlavor(item, (Prefix) prefix, (Suffix) suffix);
    }

    public Equipment createRandom(Supplier<Equipment> ctor, List<Prefix> prefixes, List<Suffix> suffixes) {
        return Factory.super.createRandom(ctor, prefixes, suffixes, rng);
    }
}