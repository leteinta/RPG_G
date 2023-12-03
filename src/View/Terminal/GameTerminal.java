package src.View.Terminal;

import src.Model.AbstractMapItem;
import src.Model.Coordinate;
import src.Model.Equipment.AbstractEquipment;
import src.Model.Equipment.Hand;
import src.Model.Equipment.Spells.AbstractSpell;
import src.Model.Equipment.Weapons.AbstractMeleeWeapon;
import src.Model.Equipment.Weapons.AbstractProjectileWeapon;
import src.Model.Exit;
import src.Model.Pawn.AbstractPlayer;
import src.Model.Pawn.Monsters.AbstractMonster;
import src.Model.Pawn.Obstacles.AbstractObstacle;
import src.Model.Wall;
import src.Service.FactionFactory;
import src.Service.Translator;

import java.util.List;
import java.util.Scanner;

public class GameTerminal {
    private final Scanner scanner = new Scanner(System.in);
    private final Translator translator = Translator.getInstance();

    private void liner(){
        System.out.println("------------------------------------");
    }

    public String getNamePlayer() {
        System.out.print(translator.getLanguage().getText("choice-name"));
        return scanner.nextLine();
    }

    public String getFaction() {
        List<String> factions = FactionFactory.getFactions();
        this.liner();
        String choice;
        do{
            for(int i=0;i<factions.size();i++){
                System.out.println(i+". "+translator.getLanguage().getText("pawn-"+factions.get(i)));
            }
            System.out.print(translator.getLanguage().getText("terminal-choice-faction"));
            try{
                choice = factions.get(Integer.parseInt(scanner.nextLine()));
            }catch (Exception ignore){
                choice = null;
            }
        } while (choice == null);
        return choice;
    }

    public void displayMap(src.Model.Map map){
        this.liner();
        for (int i = 0; i < map.getGrid().length; i++) {
            for (int j = 0; j < map.getGrid()[i].length; j++) {
                AbstractMapItem item = map.getGrid()[i][j];
                if(item == null){
                    System.out.print("_");
                }else if (item instanceof Wall){
                    System.out.print("#");
                }else if (item instanceof AbstractMonster){
                    System.out.print("M");
                }else if (item instanceof AbstractObstacle){
                    System.out.print("O");
                }else if (item instanceof AbstractPlayer){
                    System.out.print("P");
                }else if (item instanceof Exit){
                    System.out.print("E");
                }
            }
            System.out.println();
        }
    }

    public String getAction(List<String> actions){
        this.liner();
        String choice;
        do{
            for(int i=0;i<actions.size();i++){
                System.out.println(i+". "+translator.getLanguage().getText("action-"+actions.get(i)));
            }
            System.out.print(translator.getLanguage().getText("terminal-choice-action"));
            try{
                choice = actions.get(Integer.parseInt(scanner.nextLine()));
            }catch (Exception ignore){
                choice = null;
            }
        } while (choice == null);
        return choice;
    }

    public Coordinate getCoordinate(List<Coordinate> coordinates){
        this.liner();
        Coordinate choice;
        do{
            for(int i=0;i<coordinates.size();i++){
                System.out.println(i+". "+coordinates.get(i));
            }
            System.out.print(translator.getLanguage().getText("terminal-choice-coordinate"));
            try{
                choice = coordinates.get(Integer.parseInt(scanner.nextLine()));
            }catch (Exception ignore){
                choice = null;
            }
        } while (choice == null);
        return choice;
    }

    public AbstractEquipment buyEquipment(List<AbstractEquipment> equipments, int money){
        this.liner();
        System.out.println(translator.getLanguage().getText("terminal-money", money));
        AbstractEquipment choice;
        do{
            for(int i=0;i<equipments.size();i++){
                AbstractEquipment equipment = equipments.get(i);
                if(equipment instanceof AbstractSpell spell){
                    System.out.println(i+". "+translator.getLanguage().getText("store-item-spell", spell.getName(), spell.getDamage(), spell.getRange(), spell.getMagicPointConsumption(), spell.getPrice()));
                }else if(equipment instanceof AbstractProjectileWeapon projectileWeapon){
                    System.out.println(i+". "+translator.getLanguage().getText("store-item-projectile-weapon", projectileWeapon.getName(), projectileWeapon.getDamage(), projectileWeapon.getRange(), projectileWeapon.getAmmo(), projectileWeapon.getAmmo(), projectileWeapon.getPrice()));
                }else if(equipment instanceof AbstractMeleeWeapon meleeWeapon){
                    System.out.println(i+". "+translator.getLanguage().getText("store-item-melee-weapon", meleeWeapon.getName(), meleeWeapon.getDamage(), meleeWeapon.getRange(), meleeWeapon.getResistance(), meleeWeapon.getResistanceMax(), meleeWeapon.getPrice()));
                }else{
                    System.out.println(i+". "+translator.getLanguage().getText("store-item", equipment.getName(), equipment.getDamage(), equipment.getRange(), equipment.getPrice()));
                }
            }
            System.out.print(translator.getLanguage().getText("terminal-choice-store-equipment"));
            try{
                choice = equipments.get(Integer.parseInt(scanner.nextLine()));
            }catch (Exception ignore){
                choice = null;
            }
        } while (choice == null);
        return choice;
    }

    public AbstractEquipment selectEquipment(AbstractPlayer player){
        this.liner();
        System.out.println(
            translator.getLanguage().getText("terminal-inventory-info", player.getName(), translator.getLanguage().getText("pawn-"+player.getClassName())) + " | " +
            translator.getLanguage().getText("player-health-point", player.getHealthPoint(), player.getHealthPointMax()) + " | " +
            translator.getLanguage().getText("player-magic-point", player.getMagicPoint(), player.getMagicPointMax()) + " | " +
            translator.getLanguage().getText("player-money", player.getMoney())
        );
        System.out.println(
            translator.getLanguage().getText("player-level", player.getLevel()) + " | " +
            translator.getLanguage().getText("player-experience", player.getExperiencePoint(), player.getExperiencePointMax()) + " | " +
            translator.getLanguage().getText("player-strength", player.getStrength()) + " | " +
            translator.getLanguage().getText("player-move-speed", player.getMoveSpeed())
        );
        AbstractEquipment choice = new Hand();
        if(player.getEquipments().size() > 0){
            System.out.println(translator.getLanguage().getText("terminal-inventory-equipments"));
            do{
                for(int i=0;i<player.getEquipments().size();i++){
                    AbstractEquipment equipment = player.getEquipments().get(i);
                    if(equipment instanceof AbstractSpell spell){
                        System.out.println(i+". "+translator.getLanguage().getText("inventory-item-spell", spell.getName(), spell.getDamage(), spell.getRange(), spell.getMagicPointConsumption()));
                    }else if(equipment instanceof AbstractProjectileWeapon projectileWeapon){
                        System.out.println(i+". "+translator.getLanguage().getText("inventory-item-projectile-weapon", projectileWeapon.getName(), projectileWeapon.getDamage(), projectileWeapon.getRange(), projectileWeapon.getAmmo(), projectileWeapon.getAmmoMax()));
                    }else if(equipment instanceof AbstractMeleeWeapon meleeWeapon){
                        System.out.println(i+". "+translator.getLanguage().getText("inventory-item-melee-weapon", meleeWeapon.getName(), meleeWeapon.getDamage(), meleeWeapon.getRange(), meleeWeapon.getResistance(), meleeWeapon.getResistanceMax()));
                    }else{
                        System.out.println(i+". "+translator.getLanguage().getText("inventory-item", equipment.getName(), equipment.getDamage(), equipment.getRange()));
                    }
                }
                System.out.print(translator.getLanguage().getText("terminal-choice-inventory-equipment"));
                try{
                    choice = player.getEquipments().get(Integer.parseInt(scanner.nextLine()));
                }catch (Exception ignore){
                    choice = null;
                }
            } while (choice == null);
        }
        return choice;
    }

    public void win() {
        this.liner();
        System.out.print(translator.getLanguage().getText("terminal-game-win"));
    }

    public void lost() {
        this.liner();
        System.out.print(translator.getLanguage().getText("terminal-game-lost"));
    }
}
