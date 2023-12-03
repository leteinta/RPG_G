package src.i18n;

public class Fr extends AbstractLanguage {
    public Fr() {
        this.text.put("choice-name", "Votre nom : ");

        this.text.put("pawn-witch", "Sorcier");
        this.text.put("pawn-elf", "Elfe");
        this.text.put("pawn-warrior", "Guerrier");
        this.text.put("pawn-tarantula", "Tarantula");
        this.text.put("pawn-anaconda", "Anaconda");
        this.text.put("pawn-barrel", "Baril");

        this.text.put("equipment-hand", "Main nue");
        this.text.put("equipment-fire", "Feu");
        this.text.put("equipment-ice", "Ice");
        this.text.put("equipment-axe", "Hache");
        this.text.put("equipment-bow", "Arc");
        this.text.put("equipment-hammer", "Marteau");

        this.text.put("player-health-point", "PV : %d/%d");
        this.text.put("player-magic-point", "PM : %d/%d");
        this.text.put("player-money", "Pièces d'or : %d");
        this.text.put("player-level", "Niv. %d");
        this.text.put("player-experience", "Exp. : %d/%d");
        this.text.put("player-strength", "Force : %d");
        this.text.put("player-move-speed", "Vitesse : %d");

        this.text.put("attack-health-point", "%s inflige %d de dégats à %s !");
        this.text.put("attack-destroy", "%s a détruit %s !");
        this.text.put("attack-not-enough-magic-point", "%s n'a pas assez de point magique pour utiliser le sort !");
        this.text.put("attack-equipment-lost", "%s à perdu l'équipement '%s' !");
        this.text.put("attack-new-level", "%s est niveau %d !");

        this.text.put("target-health-point", "%s à récupéré %d point de vie !");
        this.text.put("target-magic-point", "%s à récupéré %d point de magie !");

        this.text.put("level-start", "Début du niveau %d !");
        this.text.put("level-win", "Niveau %d terminé !");

        this.text.put("store-success-bought", "Vous avez acheté '%s' (l'équipement a été équipé automatiquement).");

        this.text.put("nothing-coordinate", "Aucune coordonnée trouvé !");

        this.text.put("inventory-item", "%s (Dégats : %d | Portée : %d)");
        this.text.put("inventory-item-spell", "%s (Dégats : %d | Portée : %d | Consommation PM : %d )");
        this.text.put("inventory-item-melee-weapon", "%s (Dégats : %d | Portée : %d | Résistance : %d/%d)");
        this.text.put("inventory-item-projectile-weapon", "%s (Dégats : %d | Portée : %d | Munitions : %d/%d)");

        this.text.put("action-move", "Se déplacer");
        this.text.put("action-target", "Cibler / Attaquer");
        this.text.put("action-store", "Boutique");
        this.text.put("action-inventory", "Inventaire");

        this.text.put("store-item", "%s (Dégats : %d | Portée : %d | Prix : %d)");
        this.text.put("store-item-spell", "%s (Dégats : %d | Portée : %d | Consommation PM : %d | Prix : %d)");
        this.text.put("store-item-melee-weapon", "%s (Dégats : %d | Portée : %d | Résistance : %d/%d | Prix : %d)");
        this.text.put("store-item-projectile-weapon", "%s (Dégats : %d | Portée : %d | Munitions : %d/%d | Prix : %d)");


        this.text.put("terminal-choice-faction", "Choisissez une faction : ");
        this.text.put("terminal-choice-action", "Entrez le numéro de l'action à effectuer : ");
        this.text.put("terminal-choice-coordinate", "Entrez le numéro de la coordonnée : ");
        this.text.put("terminal-choice-store-equipment", "Entrez le numéro de l'equipement à acheter : ");
        this.text.put("terminal-choice-inventory-equipment", "Entrez le numéro de l'equipement à équiper : ");

        this.text.put("terminal-game-win", "Fin de la partie, félicitation vous avez sauvé la princesse Xila !");
        this.text.put("terminal-game-lost", "Fin de la partie, vous êtes mort !");

        this.text.put("terminal-store-not-enough-money", "Vous n'avez pas assez de pièce d'or !");
        this.text.put("terminal-money", "Vous possedez %d pièces d'or !");

        this.text.put("terminal-inventory-info", "Nom : %s | Faction : %s");
        this.text.put("terminal-inventory-equipments", "Voici vos equipements : ");

        this.text.put("frame-start-title", "Borcele | Bienvenue");
        this.text.put("frame-start-text", "Bienvenue dans le monde de Borcele !");
        this.text.put("frame-start-button", "Jouer");

        this.text.put("frame-main-title", "Borcele | Niveau %d");
        this.text.put("frame-main-music", "Musique");
        this.text.put("frame-main-enabled", "Activer");
        this.text.put("frame-main-disabled", "Désactiver");

        this.text.put("frame-player-equipment", "Equipement : %s");
        this.text.put("frame-dialog-button", "Ok");
        this.text.put("frame-dialog-end-title", "Jeu terminé");
        this.text.put("frame-dialog-end-lost", "Vous êtes mort !");
        this.text.put("frame-dialog-end-win", "Félicitation vous avez sauvé la princesse Xila !");

        this.text.put("frame-store-buy", "Acheter");
        this.text.put("frame-inventory-button", "Equiper");
        this.text.put("frame-inventory-selected", "%s à été équiper !");
    }
}
