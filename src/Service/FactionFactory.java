package src.Service;

import src.Model.Pawn.AbstractPlayer;
import src.Model.Pawn.Factions.Elf;
import src.Model.Pawn.Factions.Warrior;
import src.Model.Pawn.Factions.Witch;

import java.util.ArrayList;
import java.util.List;

public class FactionFactory {

    public static List<String> getFactions() {
        List<String> factions = new ArrayList<>();
        factions.add("witch");
        factions.add("elf");
        factions.add("warrior");
        return factions;
    }

    public static AbstractPlayer createFaction(String faction , String namePlayer) throws Exception {
        switch (faction) {
            case "witch" -> {return new Witch(namePlayer);}
            case "elf" -> {return new Elf(namePlayer);}
            case "warrior" -> {return new Warrior(namePlayer);}
            default -> throw new Exception("Faction is unknown!");
        }
    }
}
