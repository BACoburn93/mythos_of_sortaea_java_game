package actors.species;

public final class SpeciesRegistrar {
    private SpeciesRegistrar() {}

    public static void init() {
        var reg = SubspeciesRegistry.getInstance();

        // Aberration subspecies
        reg.register(SpeciesCategory.ABERRATION, "Coganos");
        reg.register(SpeciesCategory.ABERRATION, "Deep Maw");
        reg.register(SpeciesCategory.ABERRATION, "Hydra");
        reg.register(SpeciesCategory.ABERRATION, "Void Horror");

        // Animal subspecies
        reg.register(SpeciesCategory.ANIMAL, "Bear");
        reg.register(SpeciesCategory.ANIMAL, "Boar");
        reg.register(SpeciesCategory.ANIMAL, "Lion");
        reg.register(SpeciesCategory.ANIMAL, "Tiger");
        reg.register(SpeciesCategory.ANIMAL, "Wolf");
        
        // Celestial subspecies
        reg.register(SpeciesCategory.CELESTIAL, "Angel");
        reg.register(SpeciesCategory.CELESTIAL, "Archon");
        reg.register(SpeciesCategory.CELESTIAL, "Cherub");
        reg.register(SpeciesCategory.CELESTIAL, "Nephilim");
        reg.register(SpeciesCategory.CELESTIAL, "Seraph");

        // Construct subspecies
        reg.register(SpeciesCategory.CONSTRUCT, "Clockwork");
        reg.register(SpeciesCategory.CONSTRUCT, "Golem");
        reg.register(SpeciesCategory.CONSTRUCT, "Mechanoid");

        // Deity subspecies
        reg.register(SpeciesCategory.DEITY, "Demigod");
        reg.register(SpeciesCategory.DEITY, "God");

        // Demon subspecies
        reg.register(SpeciesCategory.DEMON, "Carrionite");
        reg.register(SpeciesCategory.DEMON, "Demon Lord");
        reg.register(SpeciesCategory.DEMON, "Fiend");
        reg.register(SpeciesCategory.DEMON, "Harrowkin");
        reg.register(SpeciesCategory.DEMON, "Oni");
        reg.register(SpeciesCategory.DEMON, "Rakshasa");
        reg.register(SpeciesCategory.DEMON, "Succubus");
        reg.register(SpeciesCategory.DEMON, "Umbrae");

        // Devils
        reg.register(SpeciesCategory.DEVIL, "Aetherial");
        reg.register(SpeciesCategory.DEVIL, "Archdevil");
        reg.register(SpeciesCategory.DEVIL, "Djinn");
        reg.register(SpeciesCategory.DEVIL, "Imp");
        reg.register(SpeciesCategory.DEVIL, "Infernu");
        reg.register(SpeciesCategory.DEVIL, "Malachite");
        reg.register(SpeciesCategory.DEVIL, "Sanguinari");
        reg.register(SpeciesCategory.DEVIL, "Vexari");

        // Dragon subspecies
        reg.register(SpeciesCategory.DRAGON, "Drake");
        reg.register(SpeciesCategory.DRAGON, "Wyvern");
        reg.register(SpeciesCategory.DRAGON, "Wyrm");

        // Elemental subspecies
        reg.register(SpeciesCategory.ELEMENTAL, "Air Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Earth Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Fire Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Lightning Elemental");
        reg.register(SpeciesCategory.ELEMENTAL, "Water Elemental");

        // Faerie subspecies
        reg.register(SpeciesCategory.FAERIE, "Dryad");
        reg.register(SpeciesCategory.FAERIE, "Pixie");
        reg.register(SpeciesCategory.FAERIE, "Satyr");
        reg.register(SpeciesCategory.FAERIE, "Siren");
        reg.register(SpeciesCategory.FAERIE, "Witch");

        // Giant subspecies
        reg.register(SpeciesCategory.GIANT, "Bicranial");
        reg.register(SpeciesCategory.GIANT, "Cyclops");
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
        reg.register(SpeciesCategory.OOZE, "Amorphous");
        reg.register(SpeciesCategory.OOZE, "Acidic");
        reg.register(SpeciesCategory.OOZE, "Plasmoid");
        reg.register(SpeciesCategory.OOZE, "Slime");

        // Plant subspecies
        reg.register(SpeciesCategory.PLANT, "Fungi");
        reg.register(SpeciesCategory.PLANT, "Treefolk");
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