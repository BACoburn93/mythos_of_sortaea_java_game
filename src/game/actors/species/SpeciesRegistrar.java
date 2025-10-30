package actors.species;

public final class SpeciesRegistrar {
    private SpeciesRegistrar() {}

    public static void init() {
        var reg = SubspeciesRegistry.getInstance();

        // Aberration subspecies
        reg.register(SpeciesCategory.ABERRATION, "Hydra");
        reg.register(SpeciesCategory.ABERRATION, "Marlboro");
        reg.register(SpeciesCategory.ABERRATION, "Mind Flayer");

        // Beast subspecies
        reg.register(SpeciesCategory.BEAST, "Bear");
        reg.register(SpeciesCategory.BEAST, "Boar");
        reg.register(SpeciesCategory.BEAST, "Lion");
        reg.register(SpeciesCategory.BEAST, "Tiger");
        reg.register(SpeciesCategory.BEAST, "Wolf");

        // Celestial subspecies
        reg.register(SpeciesCategory.CELESTIAL, "Angel");
        reg.register(SpeciesCategory.CELESTIAL, "Archon");
        reg.register(SpeciesCategory.CELESTIAL, "Seraph");
        reg.register(SpeciesCategory.CELESTIAL, "Solar");

        // Construct subspecies
        reg.register(SpeciesCategory.CONSTRUCT, "Automaton");
        reg.register(SpeciesCategory.CONSTRUCT, "Golem");
        reg.register(SpeciesCategory.CONSTRUCT, "Warforged");

        // Demon subspecies
        reg.register(SpeciesCategory.DEMON, "Balor");
        reg.register(SpeciesCategory.DEMON, "Glabrezu");
        reg.register(SpeciesCategory.DEMON, "Hezrou");
        reg.register(SpeciesCategory.DEMON, "Marilith");
        reg.register(SpeciesCategory.DEMON, "Quasit");

        // Devils (lawful infernal hierarchy)
        reg.register(SpeciesCategory.DEVIL, "Barbed");
        reg.register(SpeciesCategory.DEVIL, "Erinyes");
        reg.register(SpeciesCategory.DEVIL, "Horned");
        reg.register(SpeciesCategory.DEVIL, "Imp");
        reg.register(SpeciesCategory.DEVIL, "PitFiend");

        // Dragon subspecies
        reg.register(SpeciesCategory.DRAGON, "Drake");
        reg.register(SpeciesCategory.DRAGON, "Dragonturtle");
        reg.register(SpeciesCategory.DRAGON, "Wyvern");
        reg.register(SpeciesCategory.DRAGON, "Wyrm");

        // Elemental subspecies
        reg.register(SpeciesCategory.ELEMENTAL, "Air Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Earth Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Fire Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Water Elemental");

        // Fey subspecies
        reg.register(SpeciesCategory.FEY, "Dryad");
        reg.register(SpeciesCategory.FEY, "Fairie");
        reg.register(SpeciesCategory.FEY, "Satyr");
        reg.register(SpeciesCategory.FEY, "Siren");
        reg.register(SpeciesCategory.FEY, "Witch");

        // Giant subspecies
        reg.register(SpeciesCategory.GIANT, "Bog Giant");
        reg.register(SpeciesCategory.GIANT, "Cyclops");
        reg.register(SpeciesCategory.GIANT, "Ettin");
        reg.register(SpeciesCategory.GIANT, "Jotuun");
        reg.register(SpeciesCategory.GIANT, "Ogre");
        reg.register(SpeciesCategory.GIANT, "Titan");
        reg.register(SpeciesCategory.GIANT, "Troll");

        // Humanoid subspecies
        reg.register(SpeciesCategory.HUMANOID, "Drow");
        reg.register(SpeciesCategory.HUMANOID, "Dwarf");
        reg.register(SpeciesCategory.HUMANOID, "Elf");
        reg.register(SpeciesCategory.HUMANOID, "Gnome");
        reg.register(SpeciesCategory.HUMANOID, "Goblin");
        reg.register(SpeciesCategory.HUMANOID, "Halfling");
        reg.register(SpeciesCategory.HUMANOID, "Human");
        reg.register(SpeciesCategory.HUMANOID, "Orc");

        // Insect subspecies
        reg.register(SpeciesCategory.INSECT, "Ant");
        reg.register(SpeciesCategory.INSECT, "Beetle");
        reg.register(SpeciesCategory.INSECT, "Butterfly");
        reg.register(SpeciesCategory.INSECT, "Fly");
        reg.register(SpeciesCategory.INSECT, "Spider");
        reg.register(SpeciesCategory.INSECT, "Wasp");

        // Lycanthrope subspecies
        reg.register(SpeciesCategory.LYCANTHROPE, "Werebear");
        reg.register(SpeciesCategory.LYCANTHROPE, "Wereboar");
        reg.register(SpeciesCategory.LYCANTHROPE, "Werefox");
        reg.register(SpeciesCategory.LYCANTHROPE, "Wererabbit");
        reg.register(SpeciesCategory.LYCANTHROPE, "Wererat");
        reg.register(SpeciesCategory.LYCANTHROPE, "Weretiger");
        reg.register(SpeciesCategory.LYCANTHROPE, "Werewolf");

        // Ooze subspecies
        reg.register(SpeciesCategory.OOZE, "Black Pudding");
        reg.register(SpeciesCategory.OOZE, "Gelatinous Cube");
        reg.register(SpeciesCategory.OOZE, "Gray Ooze");
        reg.register(SpeciesCategory.OOZE, "Slime");

        // Plant subspecies
        reg.register(SpeciesCategory.PLANT, "Fungi");
        reg.register(SpeciesCategory.PLANT, "Treant");
        reg.register(SpeciesCategory.PLANT, "Vine");

        // Undead subspecies
        reg.register(SpeciesCategory.UNDEAD, "Ghost");
        reg.register(SpeciesCategory.UNDEAD, "Lich");
        reg.register(SpeciesCategory.UNDEAD, "Mummy");
        reg.register(SpeciesCategory.UNDEAD, "Revenant");
        reg.register(SpeciesCategory.UNDEAD, "Skeleton");
        reg.register(SpeciesCategory.UNDEAD, "Vampire");
        reg.register(SpeciesCategory.UNDEAD, "Zombie");
    }
}